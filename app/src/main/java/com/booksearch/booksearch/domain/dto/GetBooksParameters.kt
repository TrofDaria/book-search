package com.booksearch.booksearch.domain.dto

import com.booksearch.booksearch.domain.model.BookSearchParameter

class GetBooksParameters(
    val phrase : String,
    val bookSearchParameter: BookSearchParameter,
    val limit : Int? = null
)