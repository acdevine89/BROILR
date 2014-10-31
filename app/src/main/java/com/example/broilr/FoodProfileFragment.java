package com.example.broilr;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Stack;

/**
 * Created by mattlauer on 2014-10-22.
 */
public class FoodProfileFragment extends Fragment {

    ImageView foodProfileImage;
    TextView foodProfileName, foodProfileAge, foodProfileLastActive, foodProfileBio;

    public FoodProfileFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.foodprofilefragment, container, false);
        foodProfileImage = (ImageView) view.findViewById(R.id.profile_img);
        foodProfileName = (TextView) view.findViewById(R.id.name);
        foodProfileAge = (TextView) view.findViewById(R.id.food_age);
        foodProfileLastActive = (TextView) view.findViewById(R.id.last_active);
        foodProfileBio = (TextView) view.findViewById(R.id.food_bio);

        return view; // Swap out with a view later
    }

    public void fillFoodProfileFragment(FoodProfile thisFoodProfile) {
        String imgURL = thisFoodProfile.getImgURL();
        Picasso.with(getActivity()).load(imgURL).into(foodProfileImage);

        String name = thisFoodProfile.getName();
        foodProfileName.setText(name);

        String food_age = thisFoodProfile.getAge();
        foodProfileAge.setText(food_age + " old");

        String last_active = thisFoodProfile.getLastActive();
        foodProfileLastActive.setText("Last active " + last_active + " ago");

        String food_bio = thisFoodProfile.getBio();
        foodProfileBio.setText(food_bio);
    }
}
