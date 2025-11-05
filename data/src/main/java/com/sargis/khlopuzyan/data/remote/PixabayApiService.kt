package com.sargis.khlopuzyan.data.remote

import com.sargis.khlopuzyan.data.remote.dto.HitsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApiService {
    @GET("api/")
    suspend fun getImagesByQuery(
        @Query("q") query: String,
        @Query("key") accessKey: String,
        @Query("image_type") imageType: String = "photo",
        @Query("pretty") pretty: Boolean = true,
    ): HitsDto
}