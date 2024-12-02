package com.example.appnews.data.remote.api.model

import com.google.gson.annotations.SerializedName

data class Image(
    val url: String?,
    val sizes: ImageSizes?

)

data class ImageSizeDetails(
    val url: String?
)

data class ImageSizes(
    @SerializedName("L")
    val sizeL: ImageSizeDetails?

)