package com.booksearch.data.network.response

import com.google.gson.annotations.SerializedName

class BookResponse(
    @SerializedName("id") val id: String,
    @SerializedName("volumeInfo") val volumeInfo: VolumeInfoResponse
)
