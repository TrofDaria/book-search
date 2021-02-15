package com.booksearch.data

import com.booksearch.common.di.scope.AppScope
import com.booksearch.data.network.NetworkModule
import com.booksearch.data.network.service.BooksService
import com.booksearch.data.repository.BooksRepositoryImpl
import com.booksearch.domain.repository.BooksRepository
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