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
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val recipesRepository: RecipesRepository
) : ViewModel() {

    private val _inProgress = MutableLiveData<Boolean>()
    val inProgress: LiveData<Boolean> = _inProgress

    private val _mealsResult = MutableLiveData<List<Recipe>>()
    val mealsResult: LiveData<List<Recipe>> = _mealsResult

    private val _currentQuery = MutableLiveData<String>()
    val currentQuery: LiveData<String> = _currentQuery

    private var currentSearchJob: Job? = null
    private val timer = SearchTimer()

    fun search(query: String) {
        if (query.isNotBlank() && query != _currentQuery.value) {
            _currentQuery.value = query
            _inProgress.value = true
            cancelCurrentSearch()
            timer.start()
        }
    }

    private fun executeSearch(query: String) {
        currentSearchJob = viewModelScope.launch {
            recipesRepository.searchMeals(query).collect { response ->
                when (response) {
                    is Response.InProgress -> { }
                    is Response.Success -> {
                        _inProgress.value = false
                        response.data.let {
                            _mealsResult.value = it
                        }
                    }
                    is Response.Error -> {
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

    override fun onCleared() {
        super.onCleared()
        cancelCurrentSearch()
    }

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

    companion object {
        private const val TYPING_DELAY_MS = 500L
    }

    @Suppress("UNCHECKED_CAST")
    class HomeViewModelFactory(
        private val recipesRepository: RecipesRepository
    ) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>) =
            (HomeViewModel(recipesRepository) as T)
    }
}
