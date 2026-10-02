package com.example.assignment_6;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class BookDetailsFragment extends Fragment {

    private TextView tvTitle, tvAuthor, tvGenre, tvYear;
    private Button btnBack;

    private OnBackClickedListener listener;

    public interface OnBackClickedListener {
        void onBackClicked();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnBackClickedListener) {
            listener = (OnBackClickedListener) context;
        } else {
            throw new RuntimeException(
                    context.toString()
                            + " must implement OnBackClickedListener"
            );
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_book_details,
                container,
                false);

        // Connect views
        tvTitle = view.findViewById(R.id.tvTitle);
        tvAuthor = view.findViewById(R.id.tvAuthor);
        tvGenre = view.findViewById(R.id.tvGenre);
        tvYear = view.findViewById(R.id.tvYear);

        btnBack = view.findViewById(R.id.buttonBack2);

        // Get Book from Bundle
        Bundle bundle = getArguments();

        if (bundle != null) {

            Book book = (Book) bundle.getSerializable("BOOK");

            if (book != null) {
                tvTitle.setText(book.getTitle());
                tvAuthor.setText(book.getAuthor());
                tvGenre.setText(book.getGenre());
                tvYear.setText(String.valueOf(book.getYear()));
            }
        }

        // Back button
        btnBack.setOnClickListener(v -> {
            if (listener != null) {
                listener.onBackClicked();
            }
        });

        return view;
    }
}