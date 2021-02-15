package com.booksearch.booksearch.domain.repository

import com.booksearch.booksearch.domain.dto.GetBooksParameters
import com.booksearch.booksearch.domain.model.Book
import io.reactivex.Single

interface BooksRepository {

    fun getBooks(getBooksParameters: GetBooksParameters): Single<List<Book>>

}