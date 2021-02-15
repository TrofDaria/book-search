package com.booksearch.booksearch.ui.base

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

abstract class BaseViewModel : ViewModel(){

    var navController: NavController? = null

}