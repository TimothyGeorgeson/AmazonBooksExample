package com.example.consultants.amazonbooksexample.ui.booklist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.consultants.amazonbooksexample.R;
import com.example.consultants.amazonbooksexample.model.Book;
import com.example.consultants.amazonbooksexample.ui.viewholder.BookImageViewHolder;
import com.example.consultants.amazonbooksexample.ui.viewholder.BookViewHolder;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Book> bookList;
    Context context;

    public BookListAdapter(List<Book> bookList, Context context) {
        this.bookList = bookList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 * 2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_book_list,
                viewGroup, false);
        if (viewType == 0) {
            return new BookImageViewHolder(view);
        } else {//viewType == 2
            return new BookViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Book book = bookList.get(i);

        switch (viewHolder.getItemViewType()) {
            case 0:
                BookImageViewHolder bookImageViewHolder = (BookImageViewHolder) viewHolder;
                Glide.with(context)
                        .load(book.getImageURL())
                        .into(bookImageViewHolder.ivBook);

                break;

            case 2:
                BookViewHolder bookViewHolder = (BookViewHolder) viewHolder;
                bookViewHolder.tvTitle.setText(book.getTitle());
                Glide.with(context)
                        .load(book.getImageURL())
                        .into(bookViewHolder.ivBook);
                break;
        }
    }


    @Override
    public int getItemCount() {
        return bookList.size();
    }
}
