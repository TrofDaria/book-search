package com.booksearch.booksearch.ui.fragment.book.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.booksearch.booksearch.R
import com.booksearch.booksearch.databinding.FragmentBookSearchBinding
import com.booksearch.booksearch.ui.base.BaseFragment
import com.booksearch.booksearch.ui.extension.goneUnless


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

        binding.recyclerViewBooks.apply {
            adapter = viewModel.booksAdapter
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            val dividerItemDecoration =
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            val dividerDrawable = ContextCompat.getDrawable(
                context,
                R.drawable.divider
            )!!
            dividerItemDecoration.setDrawable(dividerDrawable)
            addItemDecoration(dividerItemDecoration)
        }
    }

    override fun subscribeToViewModelObservables() {
        super.subscribeToViewModelObservables()
        viewModel.books.observe(viewLifecycleOwner, { books ->
            binding.frameLayoutNoBooks.goneUnless(books.isEmpty())
            viewModel.booksAdapter.submitList(books)
        })
    }

}