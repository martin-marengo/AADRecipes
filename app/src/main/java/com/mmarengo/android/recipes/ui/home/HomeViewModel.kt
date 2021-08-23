package com.mmarengo.android.recipes.ui.home

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mmarengo.android.recipes.data.RecipesRepository
import com.mmarengo.android.recipes.data.Response
import com.mmarengo.android.recipes.model.Recipe
import com.mmarengo.android.recipes.model.RecipeDetail
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(
    private val recipesRepository: RecipesRepository
) : ViewModel() {

    companion object {
        private const val TYPING_DELAY_MS: Long = 500L
        private val RANDOM_TIMER_PROGRESSION: IntProgression = 9 downTo 0
        private const val ONE_SECOND_MS: Long = 1_000L
    }

    private val _inProgress = MutableLiveData<Boolean>()
    val inProgress: LiveData<Boolean> get() = _inProgress

    private val _mealsResult = MutableLiveData<List<Recipe>>()
    val mealsResult: LiveData<List<Recipe>> get() = _mealsResult

    private val _currentQuery = MutableLiveData<String>()
    val currentQuery: LiveData<String> get() = _currentQuery

    private val _noRecipesViewsVisible = MutableLiveData<Boolean>()
    val noRecipesViewsVisible: LiveData<Boolean> get() = _noRecipesViewsVisible

    private val _randomRecipe = MutableLiveData<RecipeDetail>()
    val randomRecipe: LiveData<RecipeDetail> get() = _randomRecipe

    private var currentSearchJob: Job? = null
    private val timer = SearchTimer()
    private val emptyRecipesList: List<Recipe> by lazy { listOf() }

    init {
        _noRecipesViewsVisible.value = true
        searchRandomRecipe()
    }

    fun search(query: String) {
        if (query != _currentQuery.value) {
            _currentQuery.value = query
            cancelCurrentSearch()

            if (query.isBlank()) {
                _mealsResult.value = emptyRecipesList
                _inProgress.value = false
                _noRecipesViewsVisible.value = true
            } else {
                timer.start()
                _inProgress.value = true
                _noRecipesViewsVisible.value = false
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        cancelCurrentSearch()
    }

    private fun searchRandomRecipe() {
        viewModelScope.launch {
            recipesRepository.lookUpRandomRecipe().collect { response ->
                when (response) {
                    Response.InProgress -> { }
                    is Response.Success -> {
                        response.data.let { recipeDetail ->
                            _randomRecipe.value = recipeDetail
                        }
                        initRandomRecipeTimer()
                    }
                    is Response.Error -> {
                        initRandomRecipeTimer()
                    }
                }
            }
        }
    }

    // TODO: make reusable
    private fun initRandomRecipeTimer() {
        viewModelScope.launch {
            RANDOM_TIMER_PROGRESSION.asFlow()
                .onEach { delay(ONE_SECOND_MS) }
                .onCompletion { searchRandomRecipe() }
                .conflate()
                .collect { }
        }
    }

    private fun executeSearch(query: String) {
        currentSearchJob = viewModelScope.launch {
            recipesRepository.searchRecipes(query).collect { response ->
                when (response) {
                    Response.InProgress -> { }
                    is Response.Success -> {
                        _inProgress.value = false
                        response.data.let { recipeList ->
                            _noRecipesViewsVisible.value = recipeList.isEmpty()
                            _mealsResult.value = recipeList
                        }
                    }
                    is Response.Error -> {
                        _noRecipesViewsVisible.value = true
                        _inProgress.value = false
                        _mealsResult.value = listOf()
                    }
                }
            }
        }
    }

    private fun cancelCurrentSearch() {
        currentSearchJob?.cancel()
        timer.cancel()
    }

    // TODO: maybe migrate to Flow implementation.
    private inner class SearchTimer : CountDownTimer(TYPING_DELAY_MS, TYPING_DELAY_MS) {
        override fun onTick(millisUntilFinished: Long) {
            //no-op
        }
        override fun onFinish() {
            requireNotNull(_currentQuery.value) { "Running timer should always have a query." }
            _currentQuery.value?.let {
                executeSearch(it)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    class HomeViewModelFactory(
        private val recipesRepository: RecipesRepository
    ) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>) =
            (HomeViewModel(recipesRepository) as T)
    }
}
