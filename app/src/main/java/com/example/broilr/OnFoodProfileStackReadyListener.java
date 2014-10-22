package com.example.broilr;

import java.util.Stack;

/**
 * Created by mattlauer on 2014-10-22.
 */
public interface OnFoodProfileStackReadyListener {
    public void foodProfilesAreReady(Stack<FoodProfile> foodProfileStack);
}
