package com.example.newparq;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfileActivity extends AppCompatActivity {

    private TextView textViewWelcome,textViewFullName, textViewMobileNumber, textViewId, textViewEmail;
    private ProgressBar progressBar;
    private String fullName, mobileN, identityN, emaiL;
    private ImageView imageView;
    private FirebaseAuth authProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        textViewWelcome =findViewById(R.id.show_welcome);
        textViewFullName= findViewById(R.id.textView_show_fullname);
        textViewMobileNumber= findViewById(R.id.textView_show_mobile);
        textViewId=findViewById(R.id.textView_show_id);
        textViewEmail=findViewById(R.id.textView_show_email);

        imageView=findViewById(R.id.profile_dp);

        progressBar=findViewById(R.id.progressbar);

        authProfile =FirebaseAuth.getInstance();
        FirebaseUser firebaseUser =authProfile.getCurrentUser();

        if (firebaseUser == null){
            Toast.makeText(this, "Something went wrong.users data not available at the moment",
                    Toast.LENGTH_SHORT).show();

        }else {
            checkifEmailVerified(firebaseUser);
            progressBar.setVisibility(View.VISIBLE);
            showUserProfile(firebaseUser);
        }



    }
       //user can go to user activity after a success in registration
    private void checkifEmailVerified(FirebaseUser firebaseUser) {

        if (!firebaseUser.isEmailVerified()){

            showAlertDialog();

        }



    }

    private void showAlertDialog() {


        //set up alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(UserProfileActivity.this);
        builder.setTitle("Email not verified");
        builder.setMessage("Please verify your email now.you cannot login without email verification next time");

        //open email if user clickd continue button
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_EMAIL);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //opens mail app
                startActivity(intent);
//                finish();
            }
        });

        //create the alertdialog
        AlertDialog alertDialog =builder.create();

        //show the alertdialog
        alertDialog.show();



    }

    private void showUserProfile( FirebaseUser firebaseUser ) {
        String userID = firebaseUser.getUid();

        //extract user reference from database
        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered Users");
        referenceProfile.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ReadWriteUserDetails readUserDetails= snapshot.getValue(ReadWriteUserDetails.class);
                if (readUserDetails != null){
                    fullName =firebaseUser.getDisplayName();
                    mobileN =readUserDetails.phoneTxt;
                    identityN =readUserDetails.identyNumberTxt;
                    emaiL=firebaseUser.getEmail();

                    textViewFullName.setText("Welcome," +fullName);
                    textViewFullName.setText(fullName);
                    textViewMobileNumber.setText(mobileN);
                    textViewId.setText(identityN);
                    textViewEmail.setText(emaiL);
                }

                progressBar.setVisibility(View.GONE);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserProfileActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);

            }
        });




    }
}