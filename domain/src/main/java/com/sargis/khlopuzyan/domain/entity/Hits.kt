package com.sargis.khlopuzyan.domain.entity

import java.io.Serializable

data class Hits(
    val total: Int,
    val totalHits: Int,
    val hits: List<Hit>,
) : Serializable