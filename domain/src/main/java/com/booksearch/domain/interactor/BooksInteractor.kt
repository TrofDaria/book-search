package com.booksearch.domain.interactor

import com.booksearch.domain.dto.GetBooksParameters
import com.booksearch.domain.model.Book
import io.reactivex.Single

interface BooksInteractor {

    fun getBooks(getBooksParameters: GetBooksParameters): Single<List<Book>>

}