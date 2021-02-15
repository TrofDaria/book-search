package com.booksearch.booksearch.data.repository

import com.booksearch.booksearch.data.network.service.BooksService
import com.booksearch.booksearch.domain.dto.GetBooksParameters
import com.booksearch.booksearch.domain.model.Book
import com.booksearch.booksearch.domain.repository.BooksRepository
import io.reactivex.Single

class BooksRepositoryImpl(private val booksService: BooksService) : BooksRepository {

    override fun getBooks(getBooksParameters: GetBooksParameters): Single<List<Book>> {
        return booksService.getBooks(getBooksParameters)
            .map { booksResponse ->
                booksResponse.items.map { bookResponse ->
                    with(bookResponse) {
                        Book(
                            id,
                            volumeInfo.title,
                            volumeInfo.authors?.joinToString(", "),
                            volumeInfo.imageLinksResponse?.smallThumbnail
                        )
                    }
                }
            }
    }

}