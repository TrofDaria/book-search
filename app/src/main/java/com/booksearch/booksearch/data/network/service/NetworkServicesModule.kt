package com.booksearch.booksearch.data.network.service

import com.booksearch.booksearch.data.network.GoogleBooksApi
import dagger.Module
import dagger.Provides

@Module
class NetworkServicesModule {

    @Provides
    fun provideBooksService(googleBooksApi: GoogleBooksApi) = BooksService(googleBooksApi)

}