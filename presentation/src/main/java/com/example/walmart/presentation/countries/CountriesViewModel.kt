package com.example.walmart.presentation.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmart.domain.error.ErrorFormatter
import com.example.walmart.domain.ext.onError
import com.example.walmart.domain.model.Country
import com.example.walmart.domain.provider.DispatcherProvider
import com.example.walmart.domain.repo.CountryRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CountriesViewModel(
    private val repo: CountryRepo,
    private val dispatchers: DispatcherProvider,
    private val errorFormatter: ErrorFormatter
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        loadItems()
    }

    fun consumerError() {
        // reset error message after it was showed
        _state.update { it.copy(errorMessage = null) }
    }

    fun retryLoading() {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch(onError(::handleError) + dispatchers.io()) {
            _state.update { it.copy(loading = true) }
            val items = repo.getCountries()
            _state.update { it.copy(loading = false, items = items) }
        }
    }

    private fun handleError(throwable: Throwable) {
        _state.update {
            it.copy(
                loading = false,
                errorMessage = errorFormatter.getDisplayErrorMessage(throwable)
            )
        }
    }

    data class State(
        val loading: Boolean = false,
        val errorMessage: String? = null,
        val items: List<Country> = emptyList()
    )
}