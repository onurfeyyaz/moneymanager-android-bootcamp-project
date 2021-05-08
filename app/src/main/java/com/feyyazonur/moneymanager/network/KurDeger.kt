package com.feyyazonur.moneymanager.network

data class ApiResponse(val conversion_rates: ParaBirimleri)

data class ParaBirimleri(
    val USD: Float,
    val EUR: Float,
    val GBP: Float
)