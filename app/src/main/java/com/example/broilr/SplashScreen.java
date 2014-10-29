package com.example.broilr;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by anniedevine on 10/28/14.
 */
public class SplashScreen extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 5000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splashscreenfragment);
        TextView splashScreenText = (TextView) findViewById(R.id.heating_up);
        splashScreenText.setText(getString(R.string.splash_screen_text));
    }

    @Override
    public void onResume() {
        super.onResume();
        // Load the ImageView that will host the animation and
        // set its background to our AnimationDrawable XML resource.
        ImageView splashScreenImage = (ImageView)findViewById(R.id.burger_image_view);
        splashScreenImage.setBackgroundResource(R.drawable.burger_animation);

        // Get the background, which has been compiled to an AnimationDrawable object.
        AnimationDrawable frameAnimation = (AnimationDrawable) splashScreenImage.getBackground();

        // Start the animation (looped playback by default).
        frameAnimation.start();

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashScreen.this,MainActivity.class);
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}
