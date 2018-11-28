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

import java.util.List;

public class BookListActivity extends AppCompatActivity {

    RecyclerView rvBookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklist);

        rvBookList = findViewById(R.id.rvBookList);

        BookListViewModel viewModel = ViewModelProviders.of(this).get(BookListViewModel.class);

        viewModel.getBooks().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(@Nullable List<Book> books) {

                BookListAdapter adapter = new BookListAdapter(books, getApplicationContext());
                rvBookList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rvBookList.setAdapter(adapter);

            }
        });
    }

}
