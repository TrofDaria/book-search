package com.booksearch.data.network.response

import com.google.gson.annotations.SerializedName

class ImageLinksResponse(
    @SerializedName("smallThumbnail") val smallThumbnail : String?
)