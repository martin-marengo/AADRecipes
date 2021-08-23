package com.mmarengo.android.recipes.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mmarengo.android.recipes.databinding.ItemRecipeBinding
import com.mmarengo.android.recipes.model.Recipe
import com.mmarengo.android.recipes.ui.loadImageFromUrl

class RecipesAdapter : ListAdapter<Recipe, RecipesAdapter.RecipeViewHolder>(RecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder =
        RecipeViewHolder.from(parent)

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)
    }

    class RecipeViewHolder private constructor(
        private val binding: ItemRecipeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            with(binding) {
                imageviewRecipeThumbnail.loadImageFromUrl(recipe.thumbUrl.orEmpty())
                textviewRecipeName.text = recipe.name
                textviewRecipeCategory.text = recipe.category
            }
        }

        companion object {
            fun from(parent: ViewGroup): RecipeViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemRecipeBinding.inflate(inflater, parent, false)
                return RecipeViewHolder(binding)
            }
        }
    }
}

class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {

    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
        oldItem == newItem
}
