package com.sargis.khlopuzyan.data.remote.mapper

import com.sargis.khlopuzyan.data.remote.dto.HitsDto
import com.sargis.khlopuzyan.domain.entity.Hits

fun HitsDto.toHits() = Hits(
    total = this.total,
    totalHits = this.totalHits,
    hits = this.hits.map { it.toHit() },
)