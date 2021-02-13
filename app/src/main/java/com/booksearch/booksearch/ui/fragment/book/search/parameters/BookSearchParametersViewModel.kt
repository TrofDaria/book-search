package com.booksearch.booksearch.ui.fragment.book.search.parameters

import com.booksearch.booksearch.ui.base.BaseViewModel

class BookSearchParametersViewModel : BaseViewModel() {

    fun onBackClicked() {
        navController?.navigateUp()
    }

}