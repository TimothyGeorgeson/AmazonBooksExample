package com.example.consultants.amazonbooksexample.ui.booklist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.consultants.amazonbooksexample.R;
import com.example.consultants.amazonbooksexample.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookListActivity extends AppCompatActivity implements Observer<List<Book>> {

    RecyclerView rvBookList;
    private BookListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklist);

        rvBookList = findViewById(R.id.rvBookList);

        BookListViewModel viewModel = ViewModelProviders.of(this).get(BookListViewModel.class);

        viewModel.getBooks().observe(this, this);

        adapter = new BookListAdapter(new ArrayList<Book>());
        rvBookList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvBookList.setAdapter(adapter);
    }

    @Override
    public void onChanged(@Nullable List<Book> bookList) {
        adapter.addAll(bookList);
    }
}
