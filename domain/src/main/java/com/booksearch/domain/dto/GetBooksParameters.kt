package com.booksearch.domain.dto

import com.booksearch.domain.model.BookSearchParameter

class GetBooksParameters(
    val phrase : String,
    val bookSearchParameter: BookSearchParameter,
    val limit : Int? = null
)