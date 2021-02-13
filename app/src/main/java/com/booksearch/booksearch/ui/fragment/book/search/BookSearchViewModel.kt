package com.booksearch.booksearch.ui.fragment.book.search

import androidx.lifecycle.MutableLiveData
import com.booksearch.booksearch.R
import com.booksearch.booksearch.ui.adapter.BooksAdapter
import com.booksearch.booksearch.ui.base.BaseViewModel
import com.booksearch.booksearch.ui.model.Book

class BookSearchViewModel : BaseViewModel() {

    val booksAdapter = BooksAdapter()

    val books = MutableLiveData<List<Book>>()

    fun onSearchParametersClicked() {
        navController?.navigate(R.id.book_search_parameters_fragment)
    }

}