package com.booksearch.booksearch.data.network.response

import com.google.gson.annotations.SerializedName

class VolumeInfoResponse(
    @SerializedName("title") val title: String,
    @SerializedName("authors") val authors: List<String>?,
    @SerializedName("imageLinks") val imageLinksResponse: ImageLinksResponse?
)