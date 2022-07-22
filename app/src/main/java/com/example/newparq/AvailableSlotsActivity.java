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

//    TextView textViewWelcome, textViewPhoneNumber, textViewCharges, textViewNumberofslots, textViewLocation;
//    ProgressBar progressBar;
//    ImageView imageView;
//    String phoneTxt,chargeTxt,slotTxt,locationTxt;
//    FirebaseAuth AvailableProfile;
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

        addslotRef= FirebaseDatabase.getInstance().getReference("slots");

        addslotRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                addDetailsList.clear();

                for (DataSnapshot detailsDataSnapshot : snapshot.getChildren()){
                    AddDetails addDetails=detailsDataSnapshot.getValue(AddDetails.class);

                    addDetailsList.add(addDetails);

                }
                ListAvailableAdapter adapter =new ListAvailableAdapter(AvailableSlotsActivity.
                        this,addDetailsList);



                myListview.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }


//        setContentView(R.layout.activity_available_slots);
//        textViewWelcome = findViewById(R.id.show_welcome);
//        textViewPhoneNumber = findViewById(R.id.textView_show_phone);
//        textViewCharges = findViewById(R.id.textView_show_money);
//        textViewNumberofslots = findViewById(R.id.textView_show_Number);
//        textViewLocation = findViewById(R.id.textView_show_location);
//
//
//        imageView = findViewById(R.id.profile_available);
//
//        progressBar = findViewById(R.id.progressbar);
//
//
//        AvailableProfile = FirebaseAuth.getInstance();
//        FirebaseUser firebaseUser =AvailableProfile.getCurrentUser();
//
//
//
//
//
//
//        if (firebaseUser == null){
//            Toast.makeText(this, "Something went wrong.users data not available at the moment",
//                    Toast.LENGTH_SHORT).show();
//
//        }else {
//
//            progressBar.setVisibility(View.VISIBLE);
//            showAvailableProfile(firebaseUser);
//        }
//
//
//
//    }
//
//    private void showAvailableProfile(FirebaseUser firebaseUser) {
//        String userID = firebaseUser.getUid();
//
//        DatabaseReference   addslotRef = FirebaseDatabase.getInstance().getReference("slots");
//        addslotRef.keepSynced(true);
//        addslotRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                AddDetails addDetails = snapshot.getValue(AddDetails.class);
//
//
//
//                if (addDetails != null) {
//                    phoneTxt = addDetails.PhoneN;
//                    chargeTxt = addDetails.Charges;
//                    slotTxt = addDetails.Nslots;
//                    locationTxt = addDetails.location;
//
//
//                    textViewPhoneNumber.setText(phoneTxt);
//                    textViewCharges.setText(chargeTxt);
//                    textViewNumberofslots.setText(slotTxt);
//                    textViewLocation.setText(locationTxt);
//
//
//                }
//                 else { Toast.makeText(AvailableSlotsActivity.this, "not able to access data at momment:something went wrong",
//                        Toast.LENGTH_SHORT).show();
//                }
//
//                progressBar.setVisibility(View.GONE);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//                Toast.makeText(AvailableSlotsActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
//
//
//    }


}

