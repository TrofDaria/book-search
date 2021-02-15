package com.booksearch.booksearch.di.component

import com.booksearch.booksearch.App
import com.booksearch.common.di.scope.AppScope
import com.booksearch.data.DataModule
import com.booksearch.domain.repository.BooksRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

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