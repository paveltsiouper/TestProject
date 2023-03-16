package com.example.walmart.presentation.countries

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.walmart.domain.di.ServiceProvider
import com.example.walmart.presentation.R
import com.example.walmart.presentation.databinding.CountriesFragmentBinding
import com.example.walmart.presentation.ext.repeatOnStart
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CountriesFragment : Fragment(R.layout.countries_fragment) {

    private val viewModel by viewModels<CountriesViewModel> { ServiceProvider.get() }
    private var viewBinding: CountriesFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = CountriesFragmentBinding.bind(view)
        repeatOnStart { viewModel.state.collectLatest(::renderState) }
    }

    override fun onDestroyView() {
        viewBinding = null
        super.onDestroyView()
    }

    private fun renderState(state: CountriesViewModel.State){

    }
}