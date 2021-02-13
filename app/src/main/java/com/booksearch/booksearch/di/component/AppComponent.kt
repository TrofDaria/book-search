package com.booksearch.booksearch.di.component

import com.booksearch.booksearch.App
import com.booksearch.booksearch.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindApplication(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

}