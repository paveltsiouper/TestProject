package com.example.walmart.presentation.di

import com.example.walmart.domain.di.add
import com.example.walmart.domain.di.get
import com.example.walmart.domain.di.module
import com.example.walmart.presentation.countries.CountriesViewModel
import com.example.walmart.presentation.countries.CountriesViewModelFactory

val presentationModule = module {
    add { CountriesViewModelFactory(repo = get(), dispatchers = get(), errorFormatter = get()) }
    add { CountriesViewModel(repo = get(), dispatchers = get(), errorFormatter = get()) }
}