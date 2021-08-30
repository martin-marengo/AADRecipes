package com.mmarengo.android.recipes.ui.home

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.transition.Explode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mmarengo.android.recipes.R
import com.mmarengo.android.recipes.databinding.FragmentHomeBinding
import com.mmarengo.android.recipes.di.ServiceLocator
import com.mmarengo.android.recipes.ui.TestActivity
import com.mmarengo.android.recipes.ui.loadImageFromUrl
import com.mmarengo.android.recipes.ui.mainAppBarConfiguration

class HomeFragment : Fragment(), SearchView.OnQueryTextListener {

    companion object {
        const val EMPTY_SEARCH = ""
    }

    private var _binding: FragmentHomeBinding? = null
    // onCreateView - onDestroyView
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by activityViewModels {
        HomeViewModel.Factory(ServiceLocator.provideRecipesRepository())
    }

    private var recipesAdater: RecipesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeAppBar.root.setupWithNavController(
            findNavController(), mainAppBarConfiguration
        )

        setUpSearchView()
        setUpRecipesList()
        setObservers()
    }

    private fun setUpSearchView() {
        binding.homeSearchviewRecipes.let {
            it.setIconifiedByDefault(false)
            it.setOnQueryTextListener(this)
            it.queryHint = getString(R.string.home_search_meals_title)
            it.imeOptions = EditorInfo.IME_ACTION_NONE

            if (!viewModel.currentQuery.value.isNullOrBlank()) {
                it.post {
                    it.setQuery(viewModel.currentQuery.value, true)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        recipesAdater = null
    }

    //region SearchView.OnQueryTextListener
    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        viewModel.search(newText ?: EMPTY_SEARCH)
        return true
    }
    //endregion

    private fun setUpRecipesList() {
        val layoutManager = LinearLayoutManager(requireActivity())
        recipesAdater = RecipesAdapter()

        with(binding.homeRecyclerviewRecipes) {
            this.layoutManager = layoutManager
            this.adapter = recipesAdater
        }
    }

    private fun setObservers() {
        viewModel.run {
            inProgress.observe(viewLifecycleOwner) { inProgress ->
                binding.homeProgressBar.isVisible = inProgress
            }

            mealsResult.observe(viewLifecycleOwner) { recipeList ->
                recipesAdater?.submitList(recipeList)
            }

            noRecipesViewsVisible.observe(viewLifecycleOwner) { visible ->
                binding.homeImageviewNoRecipes.isVisible = visible
                binding.homeTextviewNoRecipes.isVisible = visible
            }

            randomRecipe.observe(viewLifecycleOwner) { recipeDetail ->
                binding.run {
                    homeImageviewRecipeThumbnail.loadImageFromUrl(recipeDetail.thumbUrl.orEmpty())
                    homeTextviewRecipeName.text = recipeDetail.name
                    homeTextviewRecipeCategory.text = recipeDetail.category
                    homeCardRandomRecipe.isVisible = true

                    homeCardRandomRecipe.setOnClickListener {
                        /*val action = HomeFragmentDirections.actionHomeFragmentToRecipeDetailFragment(
                            recipeDetail.id, recipeDetail
                        )
                        findNavController().navigate(action)*/

                        val options = ActivityOptions.makeSceneTransitionAnimation(requireActivity())
                        val intent = Intent(requireActivity(), TestActivity::class.java)

                        requireActivity().window.exitTransition = Explode()
                        requireActivity().startActivity(intent, options.toBundle())
                    }
                }
            }
        }
    }
}
