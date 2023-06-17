package com.example.lesinterfacesdeprojetl3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class Help extends AppCompatActivity {

    private ImageSwitcher imageSwitcher1,imageSwitcher2,imageSwitcher3;

    private int[] images1 = {
            R.drawable.p1,
            R.drawable.p2,
            R.drawable.p3,
            R.drawable.p4,
            R.drawable.p5,
            R.drawable.p6,
            R.drawable.p7,
            R.drawable.p8,
    };

    private int[] images2 = {
            R.drawable.p9,
            R.drawable.p10,
            R.drawable.p11,
            R.drawable.p12,
    };

    private int[] images3 = {
            R.drawable.p15,
            R.drawable.p16,
            R.drawable.p17,
    };

    private int currentIndex = 0;

    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);



        imageSwitcher1 = findViewById(R.id.imageSwitcher1);
        imageSwitcher2 = findViewById(R.id.imageSwitcher2);
        imageSwitcher3 = findViewById(R.id.imageSwitcher3);


        imageSwitcher1.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ImageSwitcher.LayoutParams.MATCH_PARENT,
                        ImageSwitcher.LayoutParams.MATCH_PARENT
                ));
                return imageView;
            }
        });

        Animation slideInAnimation = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation slideOutAnimation = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        imageSwitcher1.setInAnimation(slideInAnimation);
        imageSwitcher1.setOutAnimation(slideOutAnimation);

        imageSwitcher1.setImageResource(images1[currentIndex]);

        imageSwitcher1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex++;
                if (currentIndex == images1.length) {
                    currentIndex = 0;
                }
                imageSwitcher1.setImageResource(images1[currentIndex]);
            }
        });


























        imageSwitcher2.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ImageSwitcher.LayoutParams.MATCH_PARENT,
                        ImageSwitcher.LayoutParams.MATCH_PARENT
                ));
                return imageView;
            }
        });

        Animation slideInAnimation2 = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation slideOutAnimation2 = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        imageSwitcher2.setInAnimation(slideInAnimation2);
        imageSwitcher2.setOutAnimation(slideOutAnimation2);

        imageSwitcher2.setImageResource(images2[currentIndex]);

        imageSwitcher2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex++;
                if (currentIndex == images2.length) {
                    currentIndex = 0;
                }
                imageSwitcher2.setImageResource(images2[currentIndex]);
            }
        });





        imageSwitcher3.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ImageSwitcher.LayoutParams.MATCH_PARENT,
                        ImageSwitcher.LayoutParams.MATCH_PARENT
                ));
                return imageView;
            }
        });

        Animation slideInAnimation3 = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation slideOutAnimation3 = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        imageSwitcher3.setInAnimation(slideInAnimation3);
        imageSwitcher3.setOutAnimation(slideOutAnimation3);

        imageSwitcher3.setImageResource(images3[currentIndex]);

        imageSwitcher3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex++;
                if (currentIndex == images3.length) {
                    currentIndex = 0;
                }
                imageSwitcher3.setImageResource(images3[currentIndex]);
            }
        });

    }
}