package com.example.assignment_6;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BooksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BooksFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public interface BooksListener {
        void onBack();
        void onBookSelected(Book book);
    }

    BooksListener listener;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BooksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BooksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BooksFragment newInstance(String param1, String param2) {
        BooksFragment fragment = new BooksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof BooksListener) {
            listener = (BooksListener) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_books, container, false);
        Button buttonBack = view.findViewById(R.id.buttonBack);

        buttonBack.setOnClickListener(v -> {
            listener.onBack();
        });

        TextView textViewGenre = view.findViewById(R.id.textViewGenre);
        String genre = getArguments().getString("genre");
        textViewGenre.setText(genre);
        ArrayList<Book> books = Data.getBooksByGenre(genre);
        ListView listViewBooks = view.findViewById(R.id.listViewBooks);
        BooksAdapter adapter = new BooksAdapter(requireContext(), books);
        listViewBooks.setAdapter(adapter);

        listViewBooks.setOnItemClickListener((parent, view1, position, id) -> {
            Book selectedBook = books.get(position);
            listener.onBookSelected(selectedBook);
        });

        return view;
    }
}