package com.example.broilr;

import android.app.Activity;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by anniedevine on 10/28/14.
 */
public class SplashScreen extends Activity {

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            InputStream stream = null;
            try {
                stream = getAssets().open("kissburger.png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            GifWebView view = new GifWebView(this, "file:///android_asset    /kissburger.png");

            setContentView(view);
        }
}
