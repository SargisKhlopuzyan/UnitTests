package com.sargis.khlopuzyan.data.remote.dto

import java.io.Serializable

data class HitsDto(
    val total: Int,
    val totalHits: Int,
    val hits: List<HitDto>,
) : Serializable