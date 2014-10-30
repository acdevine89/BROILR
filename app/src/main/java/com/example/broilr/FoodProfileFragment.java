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
public class FoodProfileFragment extends Fragment implements OnFoodProfileStackReadyListener{

    FoodProfileStacker stacker = new FoodProfileStacker(this);
    ImageView foodProfileImage;
    TextView foodProfileText1, foodProfileText2, foodProfileText3, foodProfileText4;
    Stack<FoodProfile> foodProfileStack = new Stack<FoodProfile>();

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
        // Note that we'll want to edit this to bring back the current viewed profile, not reload everything.
        // Likewise, we'll want to use another lifecycle method for minimizing the app.
        super.onResume();
        stacker.refreshFoodProfiles();
    }

    @Override
    public void foodProfilesAreReady(Stack<FoodProfile> firstFoodProfileStack) {
        foodProfileStack = firstFoodProfileStack;
        FoodProfile thisFoodProfile = foodProfileStack.pop();
        fillFoodProfile(thisFoodProfile);
    }

    public void loadNewProfile() {
        if (foodProfileStackSize() > 0) {
            FoodProfile nextFoodProfile = foodProfileStack.pop();
            fillFoodProfile(nextFoodProfile);
        }
        else {
            Toast noProfilesToast = Toast.makeText(getActivity(), "No new profiles!", Toast.LENGTH_LONG);
            Utils.showCustomToast(noProfilesToast);
        }
    }

    public int foodProfileStackSize() {
        return foodProfileStack.size();
    }

    private void fillFoodProfile(FoodProfile thisFoodProfile) {
        String imgURL = thisFoodProfile.getImgURL();
        Picasso.with(getActivity()).load(imgURL).into(foodProfileImage);

        String name = thisFoodProfile.getName();
        foodProfileText1.setText(name);

        String food_age = thisFoodProfile.getAge();
        foodProfileText2.setText(food_age + " old");

        String last_active = thisFoodProfile.getLastActive();
        foodProfileText3.setText("Last active " + last_active + " ago");

        String food_bio = thisFoodProfile.getBio();
        foodProfileText4.setText(food_bio);
    }
}
