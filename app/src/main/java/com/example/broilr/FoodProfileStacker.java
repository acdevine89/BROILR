package com.example.broilr;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Stack;

/**
 * Created by mattlauer on 2014-10-22.
 *
 * FoodProfileStacker prepares a stack of FoodProfiles to use in other pieces of code.
 * It collects data from the Internet and loads it all into a stack of FoodProfiles that it then passes back.
 * This is mostly a background Java class to do the work.
 * It connects with an OnFoodProfileStackReadyListener, which can be implemented in an Activity.
 *
 */
public class FoodProfileStacker {
    private OnFoodProfileStackReadyListener listener;

    public FoodProfileStacker(OnFoodProfileStackReadyListener listener) {
        this.listener = listener;
    }

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
            // Includes try, catch, and finally for IOException
            // If this code works, it fills foodProfileJsonString with a string representation
            // of ALL our JSON data.

            try {
                // Currently our FoodProfile data is all on Apiary, at the URL below.
                final String BASE_URL = "http://private-e083d-broilrspec.apiary-mock.com/profiles";

                URL url = new URL(BASE_URL);

                // Create the request to Apiary, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty. No point in parsing.
                    return null;
                }
                foodProfileJsonString = buffer.toString();

                Log.v(LOG_TAG, "FoodProfile JSON String: " + foodProfileJsonString);

            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attempting
                // to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

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

            JSONArray jsonArray = new JSONArray(foodProfileJsonString);

            // Create an empty stack of food profiles that we'll fill.
            Stack<FoodProfile> foodProfileStack = new Stack<FoodProfile>();


            // 2. Iterate the JSONArray to find a JSONObject at each index,
            //    then start filling FoodProfile objects with the data you find.
            //    The format in pseudocode is JSONObject foodProfileObject = JSONArray.getJSONObject(i);
            //    data = foodProfileObject.getString(KEY);
            //    FoodProfile.setData(data);

            for (int i = 0; i < jsonArray.length(); i++) {
                FoodProfile thisFoodProfile = new FoodProfile();
                JSONObject jsonFoodProfile = jsonArray.getJSONObject(i);

                int profileID = jsonFoodProfile.getInt(API_BROILR_PROFILEID);
                thisFoodProfile.setProfileID(profileID);

                String imgURL = jsonFoodProfile.getString(API_BROILR_IMGURL);
                thisFoodProfile.setImgURL(imgURL);

                String name = jsonFoodProfile.getString(API_BROILR_NAME);
                thisFoodProfile.setName(name);

                String age = jsonFoodProfile.getString(API_BROILR_AGE);
                thisFoodProfile.setAge(age);

                String lastActive = jsonFoodProfile.getString(API_BROILR_LASTACTIVE);
                thisFoodProfile.setLastActive(lastActive);

                String bio = jsonFoodProfile.getString(API_BROILR_BIO);
                thisFoodProfile.setBio(bio);

                foodProfileStack.push(thisFoodProfile);
            }

            return foodProfileStack; // Replace null later with foodProfileStack

        }

        @Override
        protected void onPostExecute(Stack<FoodProfile> foodProfileStack) {
            if (FoodProfileStacker.this.listener != null) {
                FoodProfileStacker.this.listener.foodProfilesAreReady(foodProfileStack);
            }
        }
    }
}

