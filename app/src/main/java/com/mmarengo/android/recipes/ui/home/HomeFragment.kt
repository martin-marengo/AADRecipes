package com.mmarengo.android.recipes.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mmarengo.android.recipes.R
import com.mmarengo.android.recipes.databinding.FragmentHomeBinding
import com.mmarengo.android.recipes.di.ServiceLocator

class HomeFragment : Fragment(), SearchView.OnQueryTextListener {

    private var _binding: FragmentHomeBinding? = null
    // onCreateView - onDestroyView
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.HomeViewModelFactory(ServiceLocator.provideRecipesRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel.loading.observe(viewLifecycleOwner) {
            Log.d("HOME_LOADING", it.toString())
        }

        viewModel.mealsResult.observe(viewLifecycleOwner) {
            Log.d("HOME_MEALS", it.size.toString())
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_search_menu, menu)
        val searchItem = menu.findItem(R.id.meals_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(this)
        searchView.queryHint = getString(R.string.home_search_meals_title)

        //searchView.setQuery(viewModel.currentQuery.value)
        super.onCreateOptionsMenu(menu, inflater)
    }

    //region SearchView.OnQueryTextListener
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            viewModel.search(newText)
        }
        return true
    }
    //endregion
}
