package com.agicquel.popcorntime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.agicquel.popcorntime.adapter.RecyclerAdapterCinema;
import com.agicquel.popcorntime.model.Cinema;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // DATA
    List<Cinema> mCinemas;
    // GUI
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCinemas = new ArrayList<>();
        mRecyclerView = findViewById(R.id.recyclerView);

        // fill with data
        for(int i = 0; i < 3; i++) {
            mCinemas.add(new Cinema(R.drawable.affiche_avatar, "Avatar", "James Cameron", 162));
            mCinemas.add(new Cinema(R.drawable.affiche_inception, "Inception", "Christopher Nolan", 165));
            mCinemas.add(new Cinema(R.drawable.affiche_leda3, "Le Seigneur des anneaux : Le Retour du roi", "Peter Jackson", 263));
            mCinemas.add(new Cinema(R.drawable.affiche_pulpfiction, "Pulp Fiction", "Quentin Tarantino", 150));
        }

        RecyclerAdapterCinema recyclerAdapterTask = new RecyclerAdapterCinema(mCinemas, R.layout.cinema_row_item, null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(recyclerAdapterTask);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setItemViewCacheSize(10);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setFocusable(false);

    }
}
