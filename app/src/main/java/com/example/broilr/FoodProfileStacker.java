package com.example.broilr;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.util.Stack;

/**
 * Created by mattlauer on 2014-10-22.
 */
public class FoodProfileStacker {

    public void refreshFoodProfiles() {
        new FetchFoodProfileTask().execute();
    }

    public class FetchFoodProfileTask extends AsyncTask<Void, Void, Stack<FoodProfile>> {

        @Override
        protected Stack<FoodProfile> doInBackground(Void... params) {
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            // Will contain the raw JSON response as a string.
            String foodProfileJsonString = null;



            return null;
        }

        private Stack<FoodProfile> getFoodProfilesFromJson(String foodProfileJsonString) {
            Stack<FoodProfile> foodProfileStack;

            return null; // Replace null later
        }

        @Override
        protected void onPostExecute(Stack<FoodProfile> foodProfileStack) {
            // Nothing yet, update later!
        }
    }
}
