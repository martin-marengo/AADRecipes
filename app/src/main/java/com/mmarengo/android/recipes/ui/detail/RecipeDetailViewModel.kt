package com.mmarengo.android.recipes.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mmarengo.android.recipes.data.RecipesRepository
import com.mmarengo.android.recipes.data.Response
import com.mmarengo.android.recipes.model.RecipeDetail
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RecipeDetailViewModel(
    private val recipesRepository: RecipesRepository
) : ViewModel() {

    private var recipeId: Long? = null

    private val _recipeDetail = MutableLiveData<RecipeDetail>()
    val recipeDetail: LiveData<RecipeDetail> get() = _recipeDetail

    private val _inProgress = MutableLiveData<Boolean>()
    val inProgress: LiveData<Boolean> get() = _inProgress

    fun setRecipeDetail(recipeDetail: RecipeDetail) {
        if (_recipeDetail.value == null) {
            recipeId = recipeDetail.id
            _recipeDetail.value = recipeDetail
        }
    }

    fun setRecipeId(id: Long) {
        if (recipeId == null && _recipeDetail.value == null) {
            recipeId = id
            fetchRecipeDetail()
        }
    }

    private fun fetchRecipeDetail() {
        recipeId?.let { id ->
            viewModelScope.launch {
                recipesRepository.lookUpRecipe(id).collect { response ->
                    when (response) {
                        Response.InProgress -> {
                            _inProgress.value = true
                        }
                        is Response.Success -> {
                            _inProgress.value = false
                            response.data.let { _recipeDetail.value = it }
                        }
                        is Response.Error -> {
                            _inProgress.value = false
                            // TODO: see what to do here.
                        }
                    }
                }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val recipesRepository: RecipesRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            (RecipeDetailViewModel(recipesRepository) as T)
    }
}
