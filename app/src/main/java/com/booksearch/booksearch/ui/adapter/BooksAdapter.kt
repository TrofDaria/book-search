package com.booksearch.booksearch.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.booksearch.booksearch.databinding.ItemBookBinding
import com.booksearch.booksearch.ui.model.Book
import com.booksearch.booksearch.ui.viewholder.BookViewHolder


class BooksAdapter : ListAdapter<Book, BookViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(bookViewHolder: BookViewHolder, position: Int) {
        getItem(position)?.let { book ->
            with(bookViewHolder.binding) {
                textViewName.text = book.name
                textViewAuthor.text = book.author
            }
        }
    }

}

class BookDiffCallback : DiffUtil.ItemCallback<Book>() {

    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.author == newItem.author &&
                oldItem.imageLink == newItem.imageLink
    }

}