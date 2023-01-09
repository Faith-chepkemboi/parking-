package com.example.newparq;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AvailableSlotsActivity extends AppCompatActivity {


    ListView myListview;
    List<AddDetails> addDetailsList;
    DatabaseReference addslotRef;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_slots);

        myListview = findViewById(R.id.ListView);
        addDetailsList = new ArrayList<>();

        addslotRef = FirebaseDatabase.getInstance().getReference("slots");

        addslotRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                addDetailsList.clear();

                for (DataSnapshot detailsDataSnapshot : snapshot.getChildren()) {
                    AddDetails addDetails = detailsDataSnapshot.getValue(AddDetails.class);

                    addDetailsList.add(addDetails);

                }
                ListAvailableAdapter adapter = new ListAvailableAdapter(AvailableSlotsActivity.
                        this, addDetailsList);


                myListview.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



}

