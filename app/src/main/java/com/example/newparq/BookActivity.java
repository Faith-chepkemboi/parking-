package com.example.newparq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookActivity extends AppCompatActivity {


    DatabaseReference adlotRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://newparq-475c8-default-rtdb.firebaseio.com/");

    TextView textViewDuration, textViewCharges;
    TextView textViewmin1, textViewmin2, textViewmin3;
    TextView textViewmoney1, textViewmoney2, textViewmoney3;

    Button bSubmit;

    Button bSubmit1;
    Button bSubmit2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);


        textViewDuration = findViewById(R.id.rDuration);
        textViewCharges = findViewById(R.id.rCharges);
        textViewmin1 = findViewById(R.id.min1);
        textViewmin2 = findViewById(R.id.min2);
        textViewmin3 = findViewById(R.id.min3);
        textViewmoney1 = findViewById(R.id.money1);
        textViewmoney2 = findViewById(R.id.money2);
        textViewmoney3 = findViewById(R.id.money3);


        bSubmit = findViewById(R.id.Sbook);
        bSubmit1 = findViewById(R.id.Sbook1);
        bSubmit2 = findViewById(R.id.Sbook2);


        adlotRef = FirebaseDatabase.getInstance().getReference().child("book");

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookslotDetails();

            }
        });

        bSubmit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookActivity.this, MpesaActivity.class);
                startActivity(intent);
            }
        });

        bSubmit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookActivity.this, MpesaActivity.class);
                startActivity(intent);
            }
        });


    }

    private void bookslotDetails() {

        String minutes1 = textViewmin1.getText().toString();
        String minutes2 = textViewmin2.getText().toString();
        String minutes3 = textViewmin3.getText().toString();

        String sh1 = textViewmoney1.getText().toString();
        String sh2 = textViewmoney2.getText().toString();
        String sh3 = textViewmoney3.getText().toString();

        BookDetails bookDetails = new BookDetails(minutes1, minutes2, minutes3, sh1, sh2, sh3);

        adlotRef.push().setValue(bookDetails);

        Toast.makeText(this, "Go to payment", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(BookActivity.this, PaymentsActivity.class);
        startActivity(intent);


    }
}