package com.example.consultants.amazonbooksexample.model.data.local.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.consultants.amazonbooksexample.model.Book;

@Database(entities = Book.class, version = 1)
public abstract class BookDatabase extends RoomDatabase {

    public abstract BookDao bookDao();
}
