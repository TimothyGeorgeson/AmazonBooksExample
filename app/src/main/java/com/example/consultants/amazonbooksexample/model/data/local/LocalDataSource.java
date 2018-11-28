package com.example.consultants.amazonbooksexample.model.data.local;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.consultants.amazonbooksexample.model.Book;
import com.example.consultants.amazonbooksexample.model.data.local.room.BookDao;
import com.example.consultants.amazonbooksexample.model.data.local.room.BookDatabase;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class LocalDataSource {
    BookDao bookDao;
    BookDatabase bookDatabase;

    public LocalDataSource() {

        //bookDatabase = Room.databaseBuilder(context, BookDatabase.class, "BookDatabase").build();
        //bookDao = bookDatabase.bookDao();
    }

//    public void saveBook(final Book book) {
//
//        Completable.fromAction(new Action() {
//            @Override
//            public void run() throws Exception {
//                bookDao.saveBook(book);
//            }
//        })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe();
//    }

//    public void getBooks(final Callback callback) {
//        bookDao.getBooks()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Observer<List<Book>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(List<Book> books) {
//                        callback.onBookData(books);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                    }
//
//                    @Override
//                    public void onComplete() {
//                    }
//                });
//    }

    public interface Callback {
        void onBookData(List<Book> books);
    }
}
