package com.mmarengo.android.recipes.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.mmarengo.android.recipes.databinding.FragmentRecipeDetailBinding
import com.mmarengo.android.recipes.di.ServiceLocator
import com.mmarengo.android.recipes.model.RecipeDetail
import com.mmarengo.android.recipes.ui.mainAppBarConfiguration

class RecipeDetailFragment : Fragment() {

    private var _binding: FragmentRecipeDetailBinding? = null
    // onCreateView - onDestroyView
    private val binding get() = _binding!!

    private val viewModel: RecipeDetailViewModel by viewModels {
        RecipeDetailViewModel.Factory(ServiceLocator.provideRecipesRepository())
    }

    private val args: RecipeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: Toolbar = binding.detailAppBar.root
        NavigationUI.setupWithNavController(
            toolbar,
            findNavController(),
            mainAppBarConfiguration
        )

        val recipeDetail: RecipeDetail? = args.recipeDetail
        if (recipeDetail != null) {
            viewModel.setRecipeDetail(recipeDetail)
        } else {
            viewModel.setRecipeId(args.recipeId)
        }

        addObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addObservers() {
        viewModel.run {
            inProgress.observe(viewLifecycleOwner) { inProgress ->
                binding.detailProgressBar.isVisible = inProgress
            }

            recipeDetail.observe(viewLifecycleOwner) { recipeDetail ->
                // TODO: set detail
            }
        }
    }
}
