package com.example.consultants.amazonbooksexample.model.data.local;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.consultants.amazonbooksexample.model.Book;
import com.example.consultants.amazonbooksexample.model.data.BookRepository;
import com.example.consultants.amazonbooksexample.model.data.local.room.BookDao;
import com.example.consultants.amazonbooksexample.model.data.local.room.BookDatabase;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LocalDataSource {
    public static final String MY_SHARED_PREF = "mySharedPref";
    public static final String CACHE_TIME = "cacheTime";
    public static final long TIME_LIMIT = 20000;

    Context context;
    BookDao bookDao;
    BookDatabase bookDatabase;

    public LocalDataSource(Context context) {
        this.context = context;
        bookDatabase = Room.databaseBuilder(context, BookDatabase.class, "BookDatabase")
                .fallbackToDestructiveMigration().build();
        bookDao = bookDatabase.bookDao();
    }

    public void saveBooks(final List<Book> books) {

        Completable.fromAction(new Action() {
            @Override
            public void run() {
                bookDao.saveBooks(books);
                updateCacheTime(getCurrentTime());
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnError(error -> System.err.println("Error: " + error.getMessage()))
                .subscribe();
    }

    @SuppressLint("CheckResult")
    public void getBooks(final Callback callback) {
        bookDao.getBooks()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Book>>() {
                    @Override
                    public void accept(List<Book> bookList) throws Exception {
                        callback.onBookData(bookList);
                    }
                });
    }

    public void isCacheDirty(final BookRepository.CheckCacheCallback cacheCallback) {
        Long lastTime = context.getSharedPreferences(MY_SHARED_PREF, Context.MODE_PRIVATE)
                .getLong(CACHE_TIME, 0);

        long currentTime = System.currentTimeMillis();
        long timeDiff = currentTime - lastTime;

        if(timeDiff > TIME_LIMIT) cacheCallback.cacheDirtyResults(true);
        else cacheCallback.cacheDirtyResults(false);
    }

    public void updateCacheTime(Long time) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putLong(CACHE_TIME, time).apply();
    }

    private Long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public interface Callback {
        void onBookData(List<Book> books);
    }
}
