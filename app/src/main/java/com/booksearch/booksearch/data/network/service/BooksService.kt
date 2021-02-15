package com.booksearch.booksearch.data.network.service

import com.booksearch.booksearch.data.network.GoogleBooksApi
import com.booksearch.booksearch.data.network.response.BooksResponse
import com.booksearch.booksearch.domain.dto.GetBooksParameters
import com.booksearch.booksearch.domain.model.BookSearchParameter
import io.reactivex.Single
import java.net.HttpURLConnection

class BooksService(private val googleBooksApi: GoogleBooksApi) {

    fun getBooks(getBooksParameters: GetBooksParameters): Single<BooksResponse> {
        val q = getBooksParameters.phrase + when (getBooksParameters.bookSearchParameter) {
            BookSearchParameter.SEARCH_BY_ALL -> ""
            BookSearchParameter.SEARCH_BY_TITLE -> GoogleBooksApi.QUERY_KEYWORD_IN_TITLE
            BookSearchParameter.SEARCH_BY_AUTHOR -> GoogleBooksApi.QUERY_KEYWORD_IN_AUTHOR
            BookSearchParameter.SEARCH_BY_GENRE -> GoogleBooksApi.QUERY_KEYWORD_SUBJECT
            BookSearchParameter.SEARCH_BY_PUBLISHER -> GoogleBooksApi.QUERY_KEYWORD_IN_PUBLISHER
        }
        return googleBooksApi.getBooks(q, getBooksParameters.limit)
            .flatMap { response ->
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    Single.just(response.body())
                } else {
                    Single.error(Throwable("Network request failed"))
                }
            }
    }
}