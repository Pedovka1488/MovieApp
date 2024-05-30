package com.example.movie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    int i = 0;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        button = findViewById(R.id.button);
        TextView titleTextView = findViewById(R.id.textViewTitlte);
        ImageView imageView = findViewById(R.id.imageView);
        TextView descriptionTextView = findViewById(R.id.textViewDesc);
        TextView priceTextView = findViewById(R.id.price);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String image = intent.getStringExtra("image");
        String description = intent.getStringExtra("description");
        double price = intent.getDoubleExtra("price", 0);

        titleTextView.setText(title);
        descriptionTextView.setText(description);
        priceTextView.setText(String.format("$%.2f", price));
        Picasso.get().load(image).into(imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (i) {
                    case 0:
                        button.setText("you are recorded");
                        i++;
                        break;
                    case 1:
                        button.setText("Take a seat");
                        i--;
                        break;
                }
            }
        });




    }
}