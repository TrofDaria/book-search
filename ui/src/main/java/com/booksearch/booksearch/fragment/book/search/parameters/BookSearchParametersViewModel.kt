package com.booksearch.booksearch.fragment.book.search.parameters

import com.booksearch.booksearch.base.BaseViewModel

class BookSearchParametersViewModel : BaseViewModel() {

    fun onBackClicked() {
        navController?.navigateUp()
    }

    fun onSearchParameterChanged() {
        navController?.navigateUp()
    }

}