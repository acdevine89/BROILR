package com.example.broilr;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

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

        private final String LOG_TAG = FetchFoodProfileTask.class.getSimpleName();

        @Override
        protected Stack<FoodProfile> doInBackground(Void... params) {
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            // Will contain the raw JSON response as a string.
            String foodProfileJsonString = null;
            Stack<FoodProfile> foodProfileStack;

            // DO THE WORK OF SETTING UP A URL CONNECTION, see ForecastFragment.java in Sunshine
            // Inludes try, catch, and finally for IOException
            // If this code works, it fills foodProfileJsonString with a string represenation
            // of ALL our JSON data.

            // Now do a try, catch on getting meaningful data from JSON,
            // and setting it to attributes of FoodProfiles in a stack.
            try {
                foodProfileStack = getFoodProfilesFromJson(foodProfileJsonString);
            }
            catch (JSONException e)  {
                Log.e(LOG_TAG, "Error ", e);
                return null;
            }

            return foodProfileStack;
        }

        private Stack<FoodProfile> getFoodProfilesFromJson(String foodProfileJsonString)
                throws JSONException {

            // These are the names of the JSON objects that need to be extracted.
            final String API_BROILR_PROFILEID = "profileID";
            final String API_BROILR_IMGURL = "imgURL";
            final String API_BROILR_NAME = "name";
            final String API_BROILR_AGE = "age";
            final String API_BROILR_LASTACTIVE = "lastActive";
            final String API_BROILR_BIO = "bio";

            // 1. Turn foodProfileJsonString to JSONArray
            // WHY?!?! Because JSONArrays and JSONObjects have tools built in to help us find data
            // by the keys we described above.

            // Create an empty stack of food profiles that we'll fill.
            Stack<FoodProfile> foodProfileStack;
            // 2. Iterate the JSONArray to find a JSONObject at each index,
            //    then start filling FoodProfile objects with the data you find.
            //    The format in pseudocode is JSONObject foodProfileObject = JSONArray.getJSONObject(i);
            //    data = foodProfileObject.getString(KEY);
            //    FoodProfile.setData(data);

            return null; // Replace null later with foodProfileStack
        }

        @Override
        protected void onPostExecute(Stack<FoodProfile> foodProfileStack) {
            // Nothing yet, update later!
        }
    }
}
