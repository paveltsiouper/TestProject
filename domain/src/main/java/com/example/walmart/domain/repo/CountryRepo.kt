package com.example.walmart.domain.repo

import com.example.walmart.domain.model.Country

interface CountryRepo {
    suspend fun getCountries(): List<Country>
}