package com.example.broilr;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Stack;

/**
 * Created by mattlauer on 2014-10-22.
 */
public class FoodProfileFragment extends Fragment implements OnFoodProfileStackReadyListener{

    FoodProfileStacker stacker = new FoodProfileStacker(this);
    ImageView foodProfileImage;
    TextView foodProfileText1, foodProfileText2, foodProfileText3, foodProfileText4;

    public FoodProfileFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.foodprofilefragment, container, false);
        foodProfileImage = (ImageView) view.findViewById(R.id.img_url);
        foodProfileText1 = (TextView) view.findViewById(R.id.name);
        foodProfileText2 = (TextView) view.findViewById(R.id.food_age);
        foodProfileText3 = (TextView) view.findViewById(R.id.last_active);
        foodProfileText4 = (TextView) view.findViewById(R.id.food_bio);

        return view; // Swap out with a view later
    }

    @Override
    public void onResume() {    // Useful for when the view is hidden and then shown again.
        super.onResume();
        stacker.refreshFoodProfiles();
    }

    @Override
    public void foodProfilesAreReady(Stack<FoodProfile> foodProfileStack) {
        FoodProfile thisFoodProfile = foodProfileStack.pop();

        String imgURL = thisFoodProfile.getImgURL();
        Picasso.with(getActivity()).load(imgURL).into(foodProfileImage);

        String name = thisFoodProfile.getName();
        foodProfileText1.setText(name);

        String food_age = thisFoodProfile.getAge();
        foodProfileText2.setText(food_age);

        String last_active = thisFoodProfile.getLastActive();
        foodProfileText3.setText(last_active);

        String food_bio = thisFoodProfile.getBio();
        foodProfileText4.setText(food_bio);
    }
}
