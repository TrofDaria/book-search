package com.booksearch.data.network

import com.booksearch.data.network.response.BooksResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksApi {

    companion object {

        const val QUERY_KEYWORD_IN_TITLE = "intitle:"

        const val QUERY_KEYWORD_IN_AUTHOR = "inauthor:"

        const val QUERY_KEYWORD_IN_PUBLISHER = "inpublisher:"

        const val QUERY_KEYWORD_SUBJECT = "subject:"

    }

    @GET(GoogleApiRoute.BOOKS_V1_VOLUMES)
    fun getBooks(
        @Query("q") q: String,
        @Query("maxResults") maxResults: Int? = null
    ): Single<Response<BooksResponse>>

}