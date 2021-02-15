package com.booksearch.booksearch.data

import com.booksearch.booksearch.data.network.NetworkModule
import com.booksearch.booksearch.data.network.service.BooksService
import com.booksearch.booksearch.data.repository.BooksRepositoryImpl
import com.booksearch.booksearch.di.component.AppScope
import com.booksearch.booksearch.domain.repository.BooksRepository
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
class DataModule {

    @Provides
    @AppScope
    fun provideBooksRepository(booksService: BooksService): BooksRepository {
        return BooksRepositoryImpl(booksService)
    }

}