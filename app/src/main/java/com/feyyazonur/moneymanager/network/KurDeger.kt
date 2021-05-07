package com.feyyazonur.moneymanager.network

import com.squareup.moshi.Json

data class KurDeger(
    val id: String, @Json(name = "img_src") val imgSrcUrl: String
)