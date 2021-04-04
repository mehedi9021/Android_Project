package com.example.localdatabase.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.localdatabase.MoviesAdapter;
import com.example.localdatabase.R;
import com.example.localdatabase.model.ModelImdb;
import com.example.localdatabase.repo.ImdbRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText name, rating, year;

    RecyclerView recyclerView;

    MoviesAdapter adapter;

    List<ModelImdb> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImdbRepository repository = new ImdbRepository(getApplicationContext());

        name = findViewById(R.id.et_name);
        rating = findViewById(R.id.et_rating);
        year = findViewById(R.id.et_year);

        movies = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

        adapter = new MoviesAdapter(getApplicationContext(), movies,repository);

        recyclerView.setAdapter(adapter);

        findViewById(R.id.bt_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String local_name = name.getText().toString();
                String local_rating = rating.getText().toString();
                String local_year = year.getText().toString();

                repository.insertSingleData(new ModelImdb(local_name, Float.parseFloat(local_rating), Integer.parseInt(local_year)));
            }
        });

        repository.getAllData().observe(this, new Observer<List<ModelImdb>>() {

            @Override
            public void onChanged(List<ModelImdb> modelImdbs) {
                movies.clear();
                movies.addAll(modelImdbs);
                adapter.notifyDataSetChanged();
            }
        });

        adapter.setOnButtonClick(new MoviesAdapter.OnButtonClickListener() {

            @Override
            public void onClick(int position) {
                Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
