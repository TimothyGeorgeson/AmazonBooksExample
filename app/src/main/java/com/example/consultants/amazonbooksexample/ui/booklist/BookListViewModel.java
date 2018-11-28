package com.example.consultants.amazonbooksexample.ui.booklist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.consultants.amazonbooksexample.model.Book;
import com.example.consultants.amazonbooksexample.model.data.BookRepository;
import com.example.consultants.amazonbooksexample.model.data.local.LocalDataSource;
import com.example.consultants.amazonbooksexample.model.data.remote.RemoteDataSource;

import java.util.List;

public class BookListViewModel extends ViewModel {
    public static final String TAG = BookListViewModel.class.getSimpleName() + "_TAG";

    private BookRepository bookRepository;

    public BookListViewModel() {
        bookRepository = new BookRepository(new RemoteDataSource(), new LocalDataSource());
    }

    public LiveData<List<Book>> getBooks() {
        return bookRepository.getBooks();
    }

}
