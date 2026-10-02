package com.example.assignment_6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BooksAdapter extends ArrayAdapter<Book> {

    public BooksAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.book_list, parent, false);
        }

        Book book = getItem(position);

        TextView title = convertView.findViewById(R.id.textViewBookTitle);
        TextView author = convertView.findViewById(R.id.textViewBookAuthor);
        TextView genre = convertView.findViewById(R.id.textViewBookGenre);
        TextView year = convertView.findViewById(R.id.textViewBookYear);

        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        genre.setText(book.getGenre());
        year.setText(String.valueOf(book.getYear()));

        return convertView;
    }
}