package com.booksearch.booksearch.ui.fragment.book.search.parameters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.booksearch.booksearch.databinding.FragmentBookSearchParametersBinding
import com.booksearch.booksearch.ui.base.BaseFragment

class BookSearchParametersFragment : BaseFragment() {

    override val viewModel: BookSearchParametersViewModel by viewModels()

    private lateinit var binding: FragmentBookSearchParametersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            FragmentBookSearchParametersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageButtonBack.setOnClickListener {
            viewModel.onBackClicked()
        }
    }

}