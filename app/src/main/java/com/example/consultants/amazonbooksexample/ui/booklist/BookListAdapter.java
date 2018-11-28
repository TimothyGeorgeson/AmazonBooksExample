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

    public BookListAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    public void addAll(List<Book> books) {
        this.bookList.clear();
        this.bookList.addAll(books);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(bookList.get(position).getTitle().contains("Harry Potter")) return 0;
        else return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_book_list,
                viewGroup, false);
        if (viewType == 0) {
            return new BookViewHolder(view);
        } else {//viewType == 1
            return new BookImageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Book book = bookList.get(i);

        switch (viewHolder.getItemViewType()) {
            case 0:
                BookViewHolder bookViewHolder = (BookViewHolder) viewHolder;
                bookViewHolder.tvTitle.setText(book.getTitle());
                Glide.with(viewHolder.itemView.getContext())
                        .load(book.getImageURL())
                        .into(bookViewHolder.ivBook);

                break;

            case 1:
                BookImageViewHolder bookImageViewHolder = (BookImageViewHolder) viewHolder;
                Glide.with(viewHolder.itemView.getContext())
                        .load(book.getImageURL())
                        .into(bookImageViewHolder.ivBook);
                break;
        }
    }


    @Override
    public int getItemCount() {
        return bookList.size();
    }
}
