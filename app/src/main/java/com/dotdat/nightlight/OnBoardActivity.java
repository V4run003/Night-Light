package com.dotdat.nightlight;

import androidx.annotation.RequiresApi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public  class OnBoardActivity extends TutorialActivity {


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //slide one

        addFragment(new Step.Builder().setTitle("What is Blue Light?")
                .setContent("It's part of the visible spectrum and" +
                        "affects the sleeping rhythm." +
                        "Migraines and blurry vision are frequent" +
                        "consequences of blue light exposure," +
                        "and can become a chronic problem.")
                .setBackgroundColor(Color.parseColor("#2456B3")) // int background color
                .setDrawable(R.drawable.blue) // int top drawable
                .setSummary("Continue reading..")
                .build());


        //slide two
        addFragment(new Step.Builder().setTitle("Effects of Blue Light in our health")
                .setContent("Blue light increases the risk of macular degeneration, glaucoma, cataract," +
                        "and other health issues." +
                        "Also, inhibits melatonin a hormone important " +
                        "in the regulation pf sleep-wake cycles")
                .setBackgroundColor(Color.parseColor("#FFAE2C")) // int background color
                .setDrawable(R.drawable.watch) // int top drawable
                .setSummary("Blue Light is harmful for your eyes.")
                .build());
        //slide three

        addFragment(new Step.Builder().setTitle("We will protect you from this")
                .setContent("The night Light is the ideal Blue Light Filter app because" +
                        "it was especially created to filter screen light" +
                        "in order to protect your eyes from blue light." +
                        "And lower your eye stress. " +
                        "From now on your Mobile won't interfere with your sleep-cycles")
                .setBackgroundColor(Color.parseColor("#789DC1")) // int background color
                .setDrawable(R.drawable.sleep) // int top drawable
                .setSummary("From now on you can have a good night sleep!")
                .build());
        //slide 4
        addFragment(new Step.Builder().setTitle("Permission Needed")
                .setContent("Please provide 'Display over other apps' Permisson." +
                        "This permission is needed to Display the screen filter overlay.")
                .setBackgroundColor(Color.parseColor("#0288D1")) // int background color
                .setDrawable(R.drawable.perm) // int top drawable
                .setSummary("")
                .build());


    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void finishTutorial() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        requestPerm();
    }

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestPerm() {


        if (!Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 0);
        }
    }



    @Override
    public void currentFragmentPosition(int position) {

    }
}
