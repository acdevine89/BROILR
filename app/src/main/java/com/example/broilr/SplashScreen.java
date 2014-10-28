package com.example.broilr;

import android.app.Activity;
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
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splashscreenfragment);

        ImageView splashScreenImage = (ImageView) findViewById(R.id.burger_gif);
        TextView splashScreenText = (TextView) findViewById(R.id.heating_up);

        splashScreenText.setText(getString(R.string.splash_screen_text));
        splashScreenImage.setImageResource(R.drawable.burger);

//        View splashScreenView = getLayoutInflater().inflate(R.layout.splashscreenfragment, get, false);
//        splashScreenImage = (ImageView) splashScreenView.findViewById(R.id.burger_gif);
//        splashScreenText = (TextView) splashScreenView.findViewById(R.id.heating_up);

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
