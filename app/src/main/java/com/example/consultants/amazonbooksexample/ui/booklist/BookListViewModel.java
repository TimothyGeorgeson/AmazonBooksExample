package com.example.consultants.amazonbooksexample.ui.booklist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.consultants.amazonbooksexample.model.Book;
import com.example.consultants.amazonbooksexample.model.data.BookRepository;
import com.example.consultants.amazonbooksexample.model.data.local.LocalDataSource;
import com.example.consultants.amazonbooksexample.model.data.remote.RemoteDataSource;

import java.util.List;

public class BookListViewModel extends AndroidViewModel {
    public static final String TAG = BookListViewModel.class.getSimpleName() + "_TAG";

    private BookRepository bookRepository;

    public BookListViewModel(@NonNull Application application) {
        super(application);
        bookRepository = new BookRepository(new RemoteDataSource(), new LocalDataSource(application.getApplicationContext()));
    }

    public LiveData<List<Book>> getBooks() {
        return bookRepository.getBooks();
    }

}
