package com.example.newparq;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddSlotActivity extends AppCompatActivity {

    DatabaseReference addslotref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://newparq-475c8-default-rtdb.firebaseio.com/");
    ProgressBar progressBar;


    EditText editTextAphone, editTextAcharges, editTextNslots, editTextLocation;
    Button buttonSubmit;
    private static final String TAG = "BookSlotActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_slot);


        editTextAphone = findViewById(R.id.phone);
        editTextAcharges = findViewById(R.id.charges);
        editTextNslots = findViewById(R.id.slots);
        editTextLocation = findViewById(R.id.location);

        buttonSubmit = findViewById(R.id.SubmitAdd);

        final ProgressBar progressBar = findViewById(R.id.progress);

//        addslotRef = FirebaseDatabase.getInstance().getReference().child("slots");


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String PhoneN = editTextAphone.getText().toString();
                final String Charges = editTextAcharges.toString();
                final String Nslots = editTextNslots.getText().toString();
                final String location = editTextLocation.getText().toString();


                String mobileRegex ="[0-7][0-9]{9}";
                Matcher mobileMatcher;
                Pattern mobilePattern = Pattern.compile(mobileRegex);
                mobileMatcher= mobilePattern.matcher(PhoneN);



                if (Charges.isEmpty() || Nslots.isEmpty() || PhoneN.isEmpty() || location.isEmpty()) {
                    Toast.makeText(AddSlotActivity.this, "All fields are required",
                            Toast.LENGTH_SHORT).show();




                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    addslotDetails(PhoneN, Charges, Nslots, location);


                }
            }
        });
    }


        private void addslotDetails (String PhoneN, String Charges,String Nslots, String location){


            AddDetails addDetails = new AddDetails(Charges, location, Nslots, PhoneN);

            FirebaseAuth auth = FirebaseAuth.getInstance();

            FirebaseUser firebaseUser = auth.getCurrentUser();
//
//
////        addslotRef.push().setValue(addDetails);
////        Toast.makeText(this, " successful", Toast.LENGTH_SHORT).show();
//
            DatabaseReference addslotRef = FirebaseDatabase.getInstance().getReference("slots");

            addslotRef.child(Objects.requireNonNull(firebaseUser).getUid()).setValue(addDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {
                        Toast.makeText(AddSlotActivity.this, "Data inserted succeful", Toast.LENGTH_SHORT).show();


                        Intent intent = new Intent(AddSlotActivity.this, AvailableSlotsActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(AddSlotActivity.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                    }


                }


            });
        }


    }






