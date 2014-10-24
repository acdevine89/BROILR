package com.example.broilr;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.Stack;

/**
 * Created by mattlauer on 2014-10-22.
 */
public class FoodProfileFragment extends Fragment implements OnFoodProfileStackReadyListener{

    public FoodProfileFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return null; // Swap out with a view later
    }

    @Override
    public void foodProfilesAreReady(Stack<FoodProfile> foodProfileStack) {

        String imgURL = thisFoodProfile.getImgURL();
        Picasso.with(context).load(imgURL).into(imageView);
    }
}
