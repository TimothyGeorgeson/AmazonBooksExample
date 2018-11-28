package com.example.consultants.amazonbooksexample.model.data.local.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.consultants.amazonbooksexample.model.Book;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface BookDao {

    @Insert
    void saveBooks(List<Book> books);
//
    @Query("Select * from Book")
    Flowable<List<Book>> getBooks();

}
