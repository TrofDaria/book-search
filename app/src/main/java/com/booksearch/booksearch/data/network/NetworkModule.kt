package com.booksearch.booksearch.data.network

import com.booksearch.booksearch.BuildConfig
import com.booksearch.booksearch.data.network.service.NetworkServicesModule
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module(includes = [NetworkServicesModule::class])
class NetworkModule {

    @Provides
    fun provideGoogleBooksApi(retrofit: Retrofit): GoogleBooksApi {
        return retrofit.create(GoogleBooksApi::class.java)
    }

    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setPrettyPrinting()
            .enableComplexMapKeySerialization()
            .create()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        val connectionSpecs = listOf(ConnectionSpec.COMPATIBLE_TLS, ConnectionSpec.CLEARTEXT)

        if (BuildConfig.DEBUG) {
            addLoggingInterceptor(okHttpClientBuilder)
        }

        return okHttpClientBuilder
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectionSpecs(connectionSpecs)
            .build()
    }

    private fun addLoggingInterceptor(
        okHttpClientBuilder: OkHttpClient.Builder
    ): OkHttpClient.Builder {
        val bodyLogsInterceptor = HttpLoggingInterceptor()
        bodyLogsInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return okHttpClientBuilder.addInterceptor(bodyLogsInterceptor)
    }
}
