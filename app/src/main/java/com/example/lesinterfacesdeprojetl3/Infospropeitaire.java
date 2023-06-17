package com.example.lesinterfacesdeprojetl3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;

public class Infospropeitaire extends AppCompatActivity {

    private ImageSwitcher imageSwitchero1,imageSwitchero2,imageSwitchero3,imageSwitchero4,imageSwitchero5,imageSwitchero6,imageSwitchero7,imageSwitchero8;

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
            R.drawable.p22,
            R.drawable.p23,
            R.drawable.p24,
            R.drawable.p25,
            R.drawable.p26,

    };

    private int[] images4 = {
            R.drawable.p18,
            R.drawable.p19,
            R.drawable.p20,
            R.drawable.p21
    };

    private int[] images5 = {
            R.drawable.p27,
            R.drawable.p28,
            R.drawable.p29,
    };

    private int[] images6 = {
            R.drawable.p30,
            R.drawable.p31,
            R.drawable.p32,
    };
    private int[] images7 = {
            R.drawable.p15,
            R.drawable.p33,
            R.drawable.p34,
    };
    private int[] images8 = {
            R.drawable.p15,
            R.drawable.p33,
            R.drawable.p34,
            R.drawable.p35,
            R.drawable.p36,

    };

    private int currentIndex1 = 0;
    private int currentIndex2 = 0;
    private int currentIndex3 = 0;
    private int currentIndex4 = 0;
    private int currentIndex5 = 0;
    private int currentIndex6 = 0;
    private int currentIndex7 = 0;
    private int currentIndex8 = 0;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infospropietaire);


        imageSwitchero1 = findViewById(R.id.imageSwitchero5);
        imageSwitchero2 = findViewById(R.id.imageSwitchero6);
        imageSwitchero3 = findViewById(R.id.imageSwitchero7);
        imageSwitchero4 = findViewById(R.id.imageSwitchero8);
        imageSwitchero5 = findViewById(R.id.imageSwitchero1);
        imageSwitchero6 = findViewById(R.id.imageSwitchero2);
        imageSwitchero7 = findViewById(R.id.imageSwitchero3);
        imageSwitchero8 = findViewById(R.id.imageSwitchero4);

//        initializeImageSwitcher(imageSwitchero1, images1);
//        initializeImageSwitcher(imageSwitchero2, images2);
//        initializeImageSwitcher(imageSwitchero3, images3);
//        initializeImageSwitcher(imageSwitchero4, images4);
//        initializeImageSwitcher(imageSwitchero5, images5);
//        initializeImageSwitcher(imageSwitchero6, images6);
//        initializeImageSwitcher(imageSwitchero7, images7);
//        initializeImageSwitcher(imageSwitchero8, images8);







        imageSwitchero5.setFactory(new ViewSwitcher.ViewFactory() {
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

        Animation slideInAnimation5 = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation slideOutAnimation5 = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        imageSwitchero5.setInAnimation(slideInAnimation5);
        imageSwitchero5.setOutAnimation(slideOutAnimation5);

        imageSwitchero5.setImageResource(images5[currentIndex5]);

        imageSwitchero5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex5++;
                if (currentIndex5 == images5.length) {
                    currentIndex5 = 0;
                }
                imageSwitchero5.setImageResource(images5[currentIndex5]);
            }
        });





        imageSwitchero6.setFactory(new ViewSwitcher.ViewFactory() {
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

        Animation slideInAnimation6 = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation slideOutAnimation6 = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        imageSwitchero6.setInAnimation(slideInAnimation6);
        imageSwitchero6.setOutAnimation(slideOutAnimation6);

        imageSwitchero6.setImageResource(images6[currentIndex6]);

        imageSwitchero6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex6++;
                if (currentIndex6 == images6.length) {
                    currentIndex6 = 0;
                }
                imageSwitchero6.setImageResource(images6[currentIndex6]);
            }
        });



        imageSwitchero7.setFactory(new ViewSwitcher.ViewFactory() {
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

        Animation slideInAnimation7 = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation slideOutAnimation7 = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        imageSwitchero7.setInAnimation(slideInAnimation7);
        imageSwitchero7.setOutAnimation(slideOutAnimation7);

        imageSwitchero7.setImageResource(images7[currentIndex7]);

        imageSwitchero7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex7++;
                if (currentIndex7 == images7.length) {
                    currentIndex7 = 0;
                }
                imageSwitchero7.setImageResource(images7[currentIndex7]);
            }
        });








        imageSwitchero8.setFactory(new ViewSwitcher.ViewFactory() {
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

        Animation slideInAnimation8 = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation slideOutAnimation8 = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        imageSwitchero8.setInAnimation(slideInAnimation8);
        imageSwitchero8.setOutAnimation(slideOutAnimation8);

        imageSwitchero8.setImageResource(images8[currentIndex8]);

        imageSwitchero8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex8++;
                if (currentIndex8 == images8.length) {
                    currentIndex8 = 0;
                }
                imageSwitchero8.setImageResource(images8[currentIndex8]);
            }
        });






        imageSwitchero1.setFactory(new ViewSwitcher.ViewFactory() {
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

        Animation slideInAnimation1 = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation slideOutAnimation1 = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        imageSwitchero1.setInAnimation(slideInAnimation1);
        imageSwitchero1.setOutAnimation(slideOutAnimation1);

        imageSwitchero1.setImageResource(images1[currentIndex1]);

        imageSwitchero1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex1++;
                if (currentIndex1 == images1.length) {
                    currentIndex1 = 0;
                }
                imageSwitchero1.setImageResource(images1[currentIndex1]);
            }
        });


        imageSwitchero2.setFactory(new ViewSwitcher.ViewFactory() {
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
        imageSwitchero2.setInAnimation(slideInAnimation2);
        imageSwitchero2.setOutAnimation(slideOutAnimation2);

        imageSwitchero2.setImageResource(images2[currentIndex2]);

        imageSwitchero2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex2++;
                if (currentIndex2 == images2.length) {
                    currentIndex2 = 0;
                }
                imageSwitchero2.setImageResource(images2[currentIndex2]);
            }
        });


        imageSwitchero3.setFactory(new ViewSwitcher.ViewFactory() {
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
        imageSwitchero3.setInAnimation(slideInAnimation3);
        imageSwitchero3.setOutAnimation(slideOutAnimation3);

        imageSwitchero3.setImageResource(images3[currentIndex3]);

        imageSwitchero3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex3++;
                if (currentIndex3 == images3.length) {
                    currentIndex3 = 0;
                }
                imageSwitchero3.setImageResource(images3[currentIndex3]);
            }
        });


        imageSwitchero4.setFactory(new ViewSwitcher.ViewFactory() {
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

        Animation slideInAnimation4 = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation slideOutAnimation4 = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        imageSwitchero4.setInAnimation(slideInAnimation4);
        imageSwitchero4.setOutAnimation(slideOutAnimation4);

        imageSwitchero4.setImageResource(images4[currentIndex4]);

        imageSwitchero4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex4++;
                if (currentIndex4 == images4.length) {
                    currentIndex4 = 0;
                }
                imageSwitchero4.setImageResource(images4[currentIndex4]);
            }
        });

    }
}
