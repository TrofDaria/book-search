package com.booksearch.booksearch.ui.fragment.book.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.booksearch.booksearch.R
import com.booksearch.booksearch.databinding.FragmentBookSearchBinding
import com.booksearch.domain.model.BookSearchParameter
import com.booksearch.booksearch.ui.activity.shared.viewmodel.SharedBookSearchViewModel
import com.booksearch.booksearch.ui.base.BaseFragment
import com.booksearch.booksearch.ui.extension.goneUnless


class BookSearchFragment : BaseFragment(), SearchView.OnQueryTextListener {

    override val viewModel: BookSearchViewModel by viewModels()

    private lateinit var binding: FragmentBookSearchBinding

    private lateinit var sharedBookSearchViewModel: SharedBookSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedBookSearchViewModel =
            ViewModelProvider(requireActivity()).get(SharedBookSearchViewModel::class.java)
    }

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

        binding.searchView.setOnQueryTextListener(this)

        binding.recyclerViewBooks.apply {
            adapter = viewModel.booksAdapter
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            ContextCompat.getDrawable(
                context,
                R.drawable.divider
            )?.let { dividerDrawable ->
                val dividerItemDecoration =
                    DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
                dividerItemDecoration.setDrawable(dividerDrawable)
                addItemDecoration(dividerItemDecoration)
            }
        }
    }

    override fun subscribeToViewModelObservables() {
        super.subscribeToViewModelObservables()
        viewModel.books.observe(viewLifecycleOwner, { books ->
            viewModel.booksAdapter.submitList(books)
        })

        viewModel.searchPhrase.observe(viewLifecycleOwner, { searchPhase ->
            binding.frameLayoutNoBooks.goneUnless(searchPhase.isNullOrEmpty())
        })

        viewModel.isLoadingInProcess.observe(viewLifecycleOwner, { isLoadingInProcess ->
            binding.frameLayoutLoading.goneUnless(isLoadingInProcess)
        })

        sharedBookSearchViewModel.selectedBookSearchParameter.observe(
            viewLifecycleOwner,
            { bookSearchParameter ->
                if (bookSearchParameter != BookSearchParameter.SEARCH_BY_ALL) {
                    binding.imageButtonSearchParameters.setImageResource(R.drawable.ic_parameters_active)
                } else {
                    binding.imageButtonSearchParameters.setImageResource(R.drawable.ic_parameters)
                }
                viewModel.onSearchParameterReceived(bookSearchParameter)
            })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.onSearchPhraseReceived(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        viewModel.onSearchPhraseReceived(newText)
        return false
    }

}