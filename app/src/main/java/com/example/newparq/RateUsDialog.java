package com.example.newparq;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

public class RateUsDialog extends Dialog {
    private float UserRate =0;
    RatingBar ratingstars;
    int myRating=0;
    public RateUsDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);

        setContentView(R.layout.rate_us_dialog_layout);

        final AppCompatButton rateNowBtn = findViewById(R.id.rateNowBtn);
        final AppCompatButton laterBtn = findViewById(R.id.laterBtn);
        final RatingBar ratingBar = findViewById(R.id.ratingBar);
        final ImageView ratingImage = findViewById(R.id.ratingImage);

        rateNowBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String totalStars = "Total Stars:: " + ratingBar.getNumStars();
                String rating = "Rating :: " + ratingBar.getRating();
//                Toast.makeText(this, "You can Now Register", Toast.LENGTH_SHORT).show();





            }



        });


        laterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hide rating dialog
                dismiss();

            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                int rating=(int) v;
                if (v <= 1) {
                    ratingImage.setImageResource(R.drawable.one_star);

                } else if (v <= 2) {
                    ratingImage.setImageResource(R.drawable.two_star);

                } else if (v <= 3) {
                    ratingImage.setImageResource(R.drawable.three_star);

                } else if (v <= 4) {
                    ratingImage.setImageResource(R.drawable.four_star);

                } else if (v <= 5) {
                    ratingImage.setImageResource(R.drawable.star);
                }

                //animating emoji image
                animateImage(ratingImage);

                //selectes rating by user
                UserRate = v;


            }
        });


    }

    private void animateImage(ImageView ratingImage) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0,1f,0,1f, Animation.RELATIVE_TO_SELF,0.5f);
         scaleAnimation.setFillAfter(true);
         scaleAnimation.setDuration(200);
         ratingImage.startAnimation(scaleAnimation);


    }

}

