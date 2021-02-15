package com.booksearch.booksearch.ui.fragment.book.search.parameters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.booksearch.booksearch.databinding.FragmentBookSearchParametersBinding
import com.booksearch.domain.model.BookSearchParameter
import com.booksearch.booksearch.ui.activity.shared.viewmodel.SharedBookSearchViewModel
import com.booksearch.booksearch.ui.base.BaseFragment

class BookSearchParametersFragment : BaseFragment() {

    override val viewModel: BookSearchParametersViewModel by viewModels()

    private lateinit var binding: FragmentBookSearchParametersBinding

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
        binding =
            FragmentBookSearchParametersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageButtonBack.setOnClickListener {
            viewModel.onBackClicked()
        }

        binding.radioGroupSearchParameters.setOnCheckedChangeListener { _, checkedRadioButtonId ->
            val selectedSearchParameter = when (checkedRadioButtonId) {
                binding.radioButtonSearchByAll.id -> BookSearchParameter.SEARCH_BY_ALL
                binding.radioButtonByAuthor.id -> BookSearchParameter.SEARCH_BY_AUTHOR
                binding.radioButtonByTitle.id -> BookSearchParameter.SEARCH_BY_TITLE
                binding.radioButtonByGenre.id -> BookSearchParameter.SEARCH_BY_GENRE
                binding.radioButtonByPublisher.id -> BookSearchParameter.SEARCH_BY_PUBLISHER
                else -> BookSearchParameter.SEARCH_BY_ALL
            }
            val previousSearchParameter =
                sharedBookSearchViewModel.selectedBookSearchParameter.value
            if (previousSearchParameter != selectedSearchParameter) {
                sharedBookSearchViewModel.selectedBookSearchParameter.postValue(
                    selectedSearchParameter
                )
                viewModel.onSearchParameterChanged()
            }
        }
    }

    override fun subscribeToViewModelObservables() {
        super.subscribeToViewModelObservables()
        sharedBookSearchViewModel.selectedBookSearchParameter.observe(
            viewLifecycleOwner,
            { bookSearchParameter ->

                when (bookSearchParameter) {
                    BookSearchParameter.SEARCH_BY_ALL -> {
                        binding.radioButtonSearchByAll.isChecked = true
                    }
                    BookSearchParameter.SEARCH_BY_AUTHOR -> {
                        binding.radioButtonByAuthor.isChecked = true
                    }
                    BookSearchParameter.SEARCH_BY_TITLE -> {
                        binding.radioButtonByTitle.isChecked = true
                    }
                    BookSearchParameter.SEARCH_BY_GENRE -> {
                        binding.radioButtonByGenre.isChecked = true
                    }
                    BookSearchParameter.SEARCH_BY_PUBLISHER -> {
                        binding.radioButtonByPublisher.isChecked = true
                    }
                    else -> {
                        // ignore
                    }
                }

            })
    }

}