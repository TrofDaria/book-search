package com.booksearch.booksearch.fragment.book.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.booksearch.booksearch.App
import com.booksearch.booksearch.R
import com.booksearch.booksearch.di.component.DaggerBookSearchComponent
import com.booksearch.booksearch.adapter.BooksAdapter
import com.booksearch.booksearch.base.BaseViewModel
import com.booksearch.common.safeLet
import com.booksearch.domain.dto.GetBooksParameters
import com.booksearch.domain.interactor.BooksInteractor
import com.booksearch.domain.model.Book
import com.booksearch.domain.model.BookSearchParameter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BookSearchViewModel : BaseViewModel() {

    companion object {

        private const val MAX_AMOUNT_OF_BOOKS = 10

    }

    @Inject
    lateinit var booksInteractor: BooksInteractor

    val booksAdapter = BooksAdapter()

    val books = MutableLiveData<List<Book>>()

    val searchPhrase = MutableLiveData<String>()

    private val searchParameter = MutableLiveData<BookSearchParameter>()

    val isLoadingInProcess = MutableLiveData(false)

    private var booksLoadingDisposable: Disposable? = null

    init {
        DaggerBookSearchComponent.builder()
            .appComponent(App.appComponent)
            .build()
            .inject(this)
    }

    override fun onCleared() {
        super.onCleared()
        booksLoadingDisposable?.dispose()
    }

    fun onSearchPhraseReceived(searchPhrase: String?) {
        if (this.searchPhrase.value != searchPhrase) {
            this.searchPhrase.postValue(searchPhrase)
            onSearchParametersChanged()
        }
    }

    fun onSearchParameterReceived(searchParameter: BookSearchParameter) {
        if (this.searchParameter.value != searchParameter) {
            this.searchParameter.postValue(searchParameter)
            onSearchParametersChanged()
        }
    }

    fun onSearchParametersClicked() {
        navController?.navigate(R.id.action_book_search_fragment_to_book_search_parameters_fragment)
    }

    private fun onSearchParametersChanged() {
        safeLet(searchPhrase.value, searchParameter.value) { searchPhrase, searchParameter ->
            if (searchPhrase.isNotBlank()) {
                loadBooks(searchPhrase, searchParameter)
            }
        }
    }

    private fun loadBooks(searchPhrase: String, bookSearchParameter: BookSearchParameter) {
        booksLoadingDisposable?.dispose()
        val getBooksRequest =
            GetBooksParameters(searchPhrase, bookSearchParameter, MAX_AMOUNT_OF_BOOKS)
        booksLoadingDisposable = booksInteractor.getBooks(getBooksRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoadingInProcess.postValue(true) }
            .doFinally { isLoadingInProcess.postValue(false) }
            .subscribe({ books ->
                this.books.postValue(books)
            }, { throwable ->
                Log.d(BookSearchViewModel::class.java.simpleName, throwable.message, throwable)
            })
    }

}