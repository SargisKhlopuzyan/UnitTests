package com.sargis.khlopuzyan.domain.entity

import com.squareup.moshi.Json

data class Hit(
    @Json(name = "collections")
    val collections: Int?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "imageHeight")
    val imageHeight: Int,
    @Json(name = "imageSize")
    val imageSize: Int,
    @Json(name = "imageWidth")
    val imageWidth: Int,
    @Json(name = "largeImageURL")
    val largeImageURL: String,
    @Json(name = "pageURL")
    val pageURL: String,
    @Json(name = "previewHeight")
    val previewHeight: Int,
    @Json(name = "previewURL")
    val previewURL: String,
    @Json(name = "previewWidth")
    val previewWidth: Int,
    @Json(name = "type")
    val type: String,
)