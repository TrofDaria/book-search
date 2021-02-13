package com.booksearch.booksearch.ui.fragment.book.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.booksearch.booksearch.databinding.FragmentBookSearchBinding
import com.booksearch.booksearch.ui.base.BaseFragment

class BookSearchFragment : BaseFragment() {

    override val viewModel: BookSearchViewModel by viewModels()

    private lateinit var binding: FragmentBookSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageButtonSearchParameters.setOnClickListener {
            viewModel.onSearchParametersClicked()
        }
    }

}