package com.example.lesinterfacesdeprojetl3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class EntroScreen extends AppCompatActivity {
    private ViewPager screenpager;
    introViewPagerAdapter intViewPagerAdapter;
    TabLayout tabLay;
    Button btnNxt;
    int position = 0;
    Button btnPUser;
    Button btnAno;
    Animation btnAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //check if opened before launch
        if (restorePrefData()) {
            Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainActivity);
            finish();
        }

        setContentView(R.layout.activity_entro_screen);
        //getSupportActionBar().hide();

        //views
        tabLay = (TabLayout) findViewById(R.id.tab_indicat);
        btnNxt = findViewById(R.id.btnNext);
        btnPUser = findViewById(R.id.btnBeUser);
        btnAno = findViewById(R.id.btnAnonym);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.btn_animation);


        //fils list screen
        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("réservez votre parking dans plus de 39 Wilaya rapidement et facilement avec blasti", R.drawable.parking));
        mList.add(new ScreenItem("trouver et filtrer les parkings disponibles.", R.drawable.location_search));
        mList.add(new ScreenItem("réservez votre place de parking pour quelques heures ou pour une location longue durée.", R.drawable.city_driver));

        //nwjdouhm
        screenpager = (ViewPager) findViewById(R.id.screenViewpager);
        intViewPagerAdapter = new introViewPagerAdapter(this, mList);
        screenpager.setAdapter(intViewPagerAdapter);
        //tab layout with viewpager
        tabLay.setupWithViewPager(screenpager);

        //button suivant
        btnNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = screenpager.getCurrentItem();
                if (position < mList.size()) {
                    position++;
                    screenpager.setCurrentItem(position);
                }
                if (position == mList.size() - 1) {
                    loaddLastScreen();
                }
            }


        });

        // add changeListener
        tabLay.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size() - 1) {
                    loaddLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //btn beUser click  listener
        btnPUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to mainAct
                Intent login = new Intent(getApplicationContext(), Login.class);
                startActivity(login);
                //already chafha
                savePrefsData();
                finish();
            }
        });

        btnAno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to mainAct
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
                savePrefsData();
                finish();
            }
        });
    }

    private boolean restorePrefData() {
        SharedPreferences pre = getApplicationContext().getSharedPreferences("Uprefs", MODE_PRIVATE);
        Boolean IntroOpenedBefore = pre.getBoolean("intoOpened", false);
        return IntroOpenedBefore;

    }

    private void savePrefsData() {
        SharedPreferences pre = getApplicationContext().getSharedPreferences("Uprefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        editor.putBoolean("intoOpened", true);
        editor.apply();
    }

    private void loaddLastScreen() {
        btnNxt.setVisibility(View.INVISIBLE);
        btnPUser.setVisibility(View.VISIBLE);
        btnAno.setVisibility(View.VISIBLE);
        tabLay.setVisibility(View.INVISIBLE);
        //animation to beUser btn

        //setup Animation
        btnPUser.setAnimation(btnAnim);
        btnAno.setAnimation(btnAnim);
    }
}