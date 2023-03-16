package com.example.walmart.presentation.countries

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy
import com.example.walmart.domain.di.ServiceProvider.get
import com.example.walmart.presentation.R
import com.example.walmart.presentation.databinding.CountriesFragmentBinding
import com.example.walmart.presentation.ext.repeatOnStart
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest

class CountriesFragment : Fragment(R.layout.countries_fragment) {

    private val viewModel by viewModels<CountriesViewModel> { get<CountriesViewModelFactory>() }
    private var viewBinding: CountriesFragmentBinding? = null
    private lateinit var adapter: CountriesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = CountriesFragmentBinding.bind(view)
        initViews()
        repeatOnStart { viewModel.state.collectLatest(::renderState) }
    }

    private fun initViews() {
        adapter = CountriesAdapter()
        adapter.stateRestorationPolicy = StateRestorationPolicy.PREVENT_WHEN_EMPTY
        viewBinding?.apply {
            swipeRefreshLayout.setOnRefreshListener { viewModel.reloadList() }
            countryRecyclerView.adapter = adapter
            countryRecyclerView.addItemDecoration(
                CountriesItemDecoration(resources.getDimensionPixelSize(R.dimen.item_inner_space))
            )
        }
    }

    override fun onDestroyView() {
        viewBinding = null
        super.onDestroyView()
    }

    private fun renderState(state: CountriesViewModel.State) {
        viewBinding?.apply {
            swipeRefreshLayout.isRefreshing = state.loading
            adapter.submitList(state.items)
            state.errorMessage?.let { showError(it) }
        }
    }

    private fun showError(text: String) {
        Snackbar.make(requireView(), text, Snackbar.LENGTH_LONG)
            .setAction(R.string.retry) { viewModel.reloadList() }
            .show()
    }
}