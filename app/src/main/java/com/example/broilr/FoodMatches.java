package com.example.broilr;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.R.*;

/**
* Created by elyseturner on 10/30/14.
*/
public class FoodMatches extends ListFragment {
    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    List<FoodProfile> mNameList = new ArrayList<FoodProfile>();


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //here I am requesting the built in list from the list fragment, and saving the reference to it.
        ListView myListView = getListView();
        FavoritesAdapter adapter = new FavoritesAdapter(getActivity(), mNameList);
        myListView.setAdapter(adapter);

    }

    public class FavoritesAdapter extends ArrayAdapter<FoodProfile>{
        private List<FoodProfile> foodLikes;

        public FavoritesAdapter(Context context, List<FoodProfile> objects) {
            super(context, layout.simple_list_item_1, objects);
            foodLikes= objects;

        }

        @Override
        public int getCount() {
            return foodLikes.size();
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = LayoutInflater.from(getContext()).inflate(R.layout.favorite_food_row, parent, false);

            ImageView foodPic = (ImageView)rowView.findViewById(R.id.food_pic);
            TextView nameText = (TextView)rowView.findViewById(R.id.name_text);
            TextView lastActiveText = (TextView)rowView.findViewById(R.id.last_active_text);

            FoodProfile currentProfile = foodLikes.get(position);
            nameText.setText(currentProfile.getName());
            lastActiveText.setText(currentProfile.getLastActive());
            Picasso.with(getActivity()).load(currentProfile.getImgURL()).into(foodPic);

            return rowView;

        }
    }

}
