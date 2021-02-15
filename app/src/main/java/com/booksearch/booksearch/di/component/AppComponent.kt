package com.booksearch.booksearch.di.component

import com.booksearch.booksearch.App
import com.booksearch.booksearch.data.DataModule
import com.booksearch.booksearch.domain.repository.BooksRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@Scope
annotation class AppScope

@AppScope
@Component(modules = [DataModule::class])
interface AppComponent {

    val booksRepository: BooksRepository

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindApplication(app: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

}