package com.booksearch.booksearch.ui.activity.shared.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.booksearch.domain.model.BookSearchParameter

class SharedBookSearchViewModel : ViewModel() {

    val selectedBookSearchParameter = MutableLiveData(BookSearchParameter.SEARCH_BY_ALL)

}