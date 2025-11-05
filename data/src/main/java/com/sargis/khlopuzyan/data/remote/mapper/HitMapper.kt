package com.sargis.khlopuzyan.data.remote.mapper

import com.sargis.khlopuzyan.data.remote.dto.HitDto
import com.sargis.khlopuzyan.domain.entity.Hit

fun HitDto.toHit() = Hit(
    collections = this.collections,
    id = this.id,
    imageHeight = this.imageHeight,
    imageSize = this.imageSize,
    imageWidth = this.imageWidth,
    largeImageURL = this.largeImageURL,
    pageURL = this.pageURL,
    previewHeight = this.previewHeight,
    previewURL = this.previewURL,
    previewWidth = this.previewWidth,
    type = this.type
)