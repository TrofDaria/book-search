package com.booksearch.domain.repository

import com.booksearch.domain.dto.GetBooksParameters
import com.booksearch.domain.model.Book
import io.reactivex.Single

interface BooksRepository {

    fun getBooks(getBooksParameters: GetBooksParameters): Single<List<Book>>

}