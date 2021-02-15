package com.booksearch.booksearch.domain.interactor

import com.booksearch.booksearch.domain.dto.GetBooksParameters
import com.booksearch.booksearch.domain.model.Book
import com.booksearch.booksearch.domain.repository.BooksRepository
import io.reactivex.Single

class BooksInteractorImpl(private val booksRepository: BooksRepository) : BooksInteractor {

    override fun getBooks(getBooksParameters: GetBooksParameters): Single<List<Book>> {
        return booksRepository.getBooks(getBooksParameters)
    }

}