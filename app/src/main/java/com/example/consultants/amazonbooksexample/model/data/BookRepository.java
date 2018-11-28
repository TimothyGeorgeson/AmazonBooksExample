package com.example.consultants.amazonbooksexample.model.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.consultants.amazonbooksexample.model.Book;
import com.example.consultants.amazonbooksexample.model.data.local.LocalDataSource;
import com.example.consultants.amazonbooksexample.model.data.remote.RemoteDataSource;

import java.util.List;

public class BookRepository {
    public static final String TAG = BookRepository.class.getSimpleName() + "_TAG";

    private MutableLiveData<List<Book>> listLiveData;
    private RemoteDataSource remoteDataSource;
    private LocalDataSource localDataSource;

    public BookRepository(RemoteDataSource remoteDataSource, LocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;

        listLiveData = new MutableLiveData<>();
    }

    public LiveData<List<Book>> getBooks() {

        checkCache(new CheckCacheCallback() {
            @Override
            public void cacheDirtyResults(boolean isCacheDirty) {
                if (isCacheDirty) {
                    //load from remote source
                    remoteDataSource.getBooks(new DataCallback() {
                        @Override
                        public void onSuccess(List<Book> bookList) {
                            Log.d(TAG, "onSuccess: ");
                            listLiveData.setValue(bookList);
                            //save books here... commented out, I was getting the error
                            //UNIQUE constraint failed: Book.title
                            //There was only title and image in Book, so I don't know what to use as unique primary key

                            //localDataSource.saveBooks(bookList);
                        }

                        @Override
                        public void onFailure(String error) {

                        }
                    });

                } else {
                    //load from local source
                    localDataSource.getBooks(new LocalDataSource.Callback() {
                        @Override
                        public void onBookData(List<Book> books) {
                            listLiveData.setValue(books);
                        }
                    });
                }
            }
        });

        return listLiveData;
    }

    private void checkCache(@Nullable CheckCacheCallback cacheCallback) {
        localDataSource.isCacheDirty(cacheCallback);
    }

    public interface CheckCacheCallback {
        void cacheDirtyResults(boolean isCacheDirty);
    }


}
