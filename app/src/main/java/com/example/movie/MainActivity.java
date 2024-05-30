package com.example.movie;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie.models.Film;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageButton qr_code,btn_exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        checkAuth();

        qr_code = findViewById(R.id.qr_code);
        btn_exit = findViewById(R.id.btn_exit);


        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });

        qr_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,qrActivity.class));
            }
        });


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Films");

        List<Film> filmList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.list_film);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FilmAdapter adapter = new FilmAdapter(filmList);
        recyclerView.setAdapter(adapter);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                filmList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Film film = snapshot.getValue(Film.class);
                    filmList.add(film);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Error
            }
        });

    }

    void checkAuth(){
        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

}