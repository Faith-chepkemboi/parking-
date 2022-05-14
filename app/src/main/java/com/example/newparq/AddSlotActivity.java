package com.example.newparq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddSlotActivity extends AppCompatActivity {

    DatabaseReference bookslotRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://newparq-475c8-default-rtdb.firebaseio.com/");
    ProgressBar progressBar;

    EditText editTextVehiclePlate, editTextVehicleType, editTextDuration, editTextLocation;
    Button buttonSubmit;
    private static final String TAG = "BookSlotActivity";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_slot);


        editTextVehiclePlate= findViewById(R.id.plate);
        editTextVehicleType =findViewById(R.id.Vtype);
        editTextDuration = findViewById(R.id.duration);
        editTextLocation= findViewById(R.id.location);

        buttonSubmit=findViewById(R.id.SubmitBook);

        final ProgressBar progressBar = findViewById(R.id.progress);

        bookslotRef = FirebaseDatabase.getInstance().getReference().child("slots");



        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bookslotDetails();



            }
        });
    }

    private void bookslotDetails() {
        String vehicleplate = editTextVehiclePlate.getText().toString();
        String vehicletype = editTextVehicleType.toString();
        String Duration = editTextDuration.getText().toString();
        String Location = editTextLocation.getText().toString();

        AddDetails bookDetails = new AddDetails(vehicleplate,vehicletype,Duration,Location);

        bookslotRef.push().setValue(bookDetails);
        Toast.makeText(this, " successful", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(AddSlotActivity.this,GoogleMapsActivity.class);
        startActivity(intent);

    }
}
