package com.sargis.khlopuzyan.domain.repository

import com.sargis.khlopuzyan.domain.entity.Hits
import com.sargis.khlopuzyan.domain.util.Result

interface ImageSearchRepository {
    suspend fun searchImageByQuery(query: String): Result<Hits>
}