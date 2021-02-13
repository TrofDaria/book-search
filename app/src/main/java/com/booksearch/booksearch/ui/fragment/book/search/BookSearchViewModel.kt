package com.booksearch.booksearch.ui.fragment.book.search

import com.booksearch.booksearch.R
import com.booksearch.booksearch.ui.base.BaseViewModel

class BookSearchViewModel : BaseViewModel() {


    fun onSearchParametersClicked() {
        navController?.navigate(R.id.book_search_parameters_fragment)
    }

}