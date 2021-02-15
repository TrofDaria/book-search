package com.booksearch.data.network.service

import com.booksearch.data.network.GoogleBooksApi
import dagger.Module
import dagger.Provides

@Module
class NetworkServicesModule {

    @Provides
    fun provideBooksService(googleBooksApi: GoogleBooksApi) = BooksService(googleBooksApi)

}