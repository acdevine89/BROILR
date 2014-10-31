package com.example.broilr;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Stack;


public class MainActivity extends Activity implements OnFoodProfileStackReadyListener {

    Button mLikeButton;
    Button mDislikeButton;
    Stack<FoodProfile> foodProfileStack = new Stack<FoodProfile>();
    FoodProfileStacker stacker = new FoodProfileStacker(this);
    FragmentManager fm = getFragmentManager();
    FoodProfileFragment currentFoodProfileFragment = (FoodProfileFragment) fm.findFragmentById(R.id.foodProfileContainer);
    FoodProfileFragment nextFoodProfileFragment;
    FoodProfile currentFoodProfile = new FoodProfile();
    ArrayList<FoodProfile> likedFoodProfiles = new ArrayList<FoodProfile>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stacker.fetchFoodProfiles();
        setContentView(R.layout.activity_main);


        // fragmentBoss.initFoodProfileStack();
        if (currentFoodProfileFragment == null) {
            currentFoodProfileFragment = new FoodProfileFragment();
            fm.beginTransaction()
                    .add(R.id.foodProfileContainer, currentFoodProfileFragment)
                    .commit();
        }

        mLikeButton = (Button) findViewById(R.id.like_button);
        mDislikeButton = (Button) findViewById(R.id.dislike_button);

        mDislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (foodProfileStack.size() > 0) {
                    Toast dislikeToast = Toast.makeText(MainActivity.this, R.string.dislike_toast, Toast.LENGTH_SHORT);
                    Utils.showCustomToast(dislikeToast);

                    //sendFragmentLeft();

                    if (nextFoodProfileFragment == null) {
                        nextFoodProfileFragment = (FoodProfileFragment) fm.findFragmentById(R.id.foodProfileContainer);
                        fm.beginTransaction()
                                .replace(R.id.foodProfileContainer, nextFoodProfileFragment)
                                .commit();
                    }

                    currentFoodProfile = getFoodProfileFromStack();
                    nextFoodProfileFragment.fillFoodProfileFragment(currentFoodProfile);
                    //fadeFragmentIn();
                }
                else {
                    // Launch end page activity.
                }
            }
        });

        mLikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (foodProfileStack.size() > 0) {
                    Toast likeToast = Toast.makeText(MainActivity.this, R.string.like_toast, Toast.LENGTH_SHORT);
                    Utils.showCustomToast(likeToast);

                    //sendFragmentRight();

                    likedFoodProfiles.add(currentFoodProfile);

                    if (nextFoodProfileFragment == null) {
                        nextFoodProfileFragment = (FoodProfileFragment) fm.findFragmentById(R.id.foodProfileContainer);
                        fm.beginTransaction()
                                .replace(R.id.foodProfileContainer, nextFoodProfileFragment)
                                .commit();
                    }
                    currentFoodProfile = getFoodProfileFromStack();
                    nextFoodProfileFragment.fillFoodProfileFragment(currentFoodProfile);
                    //fadeFragmentIn();
                }
                else {
                    // Launch end page activity.
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem matchesItem = menu.findItem(R.id.matches_button);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.matches_button) {
            // Launch view matches activity from here
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void foodProfilesAreReady(Stack<FoodProfile> firstFoodProfileStack) {
        foodProfileStack = firstFoodProfileStack;
        // Only works if stack is set.
        currentFoodProfile = getFoodProfileFromStack();
        currentFoodProfileFragment.fillFoodProfileFragment(currentFoodProfile);
    }

    public void sendFragmentLeft() {
        ScrollView profileScrollView = (ScrollView) findViewById(R.id.fragment_wrapper);
        profileScrollView.animate().x(-1000);
    }

    public void sendFragmentRight() {
        ScrollView profileScrollView = (ScrollView) findViewById(R.id.fragment_wrapper);
        profileScrollView.animate().x(1000);
    }

    public void fadeFragmentIn() {
        ScrollView profileScrollView = (ScrollView) findViewById(R.id.fragment_wrapper);
        profileScrollView.animate().alpha(1);
    }

    public FoodProfile getFoodProfileFromStack() {
        if (foodProfileStack.size() > 0) {
            return foodProfileStack.pop();
        }
        return null;
    }

}
