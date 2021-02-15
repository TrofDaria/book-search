package com.booksearch.booksearch.data.network.response

import com.google.gson.annotations.SerializedName

class BooksResponse(@SerializedName("items") val items: List<BookResponse>)