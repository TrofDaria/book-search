package com.booksearch.booksearch.domain.interactor

import com.booksearch.booksearch.domain.dto.GetBooksParameters
import com.booksearch.booksearch.domain.model.Book
import io.reactivex.Single

interface BooksInteractor {

    fun getBooks(getBooksParameters: GetBooksParameters): Single<List<Book>>

}