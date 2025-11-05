package com.sargis.khlopuzyan.data.repository

import com.sargis.khlopuzyan.data.constants.PIXABAY_API_KEY
import com.sargis.khlopuzyan.data.remote.PixabayApiService
import com.sargis.khlopuzyan.data.remote.mapper.toHits
import com.sargis.khlopuzyan.domain.entity.Hits
import com.sargis.khlopuzyan.domain.repository.ImageSearchRepository
import com.sargis.khlopuzyan.domain.util.Result

class ImageSearchRepositoryImpl(
    private val pixabayApiService: PixabayApiService,
) : ImageSearchRepository {
    override suspend fun searchImageByQuery(query: String): Result<Hits> {
        return try {
            Result.Success(
                pixabayApiService.getImagesByQuery(
                    query,
                    PIXABAY_API_KEY
                ).toHits()
            )
        } catch (e: Exception) {
            Result.Error(e.message ?: "Unknown error")
        }
    }
}