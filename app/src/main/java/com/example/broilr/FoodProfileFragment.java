package com.example.broilr;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Stack;

/**
 * Created by mattlauer on 2014-10-22.
 */
public class FoodProfileFragment extends Fragment implements OnFoodProfileStackReadyListener{

    FoodProfileStacker stacker = new FoodProfileStacker(this);

    public FoodProfileFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null; // Swap out with a view later
    }

    @Override
    public void onResume() {    // Useful for when the view is hidden and then shown again.
        super.onResume();
        stacker.refreshFoodProfiles();
    }

    @Override
    public void foodProfilesAreReady(Stack<FoodProfile> foodProfileStack) {

    }
}
