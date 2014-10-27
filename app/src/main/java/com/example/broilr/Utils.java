package com.example.broilr;

import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by mattlauer on 2014-10-27.
 */
public class Utils {
    public static void showCustomToast(Toast toast) {
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 500);
        LinearLayout toastLayout = (LinearLayout) toast.getView();
        TextView toastTV = (TextView) toastLayout.getChildAt(0);
        toastTV.setTextSize(30);
        toast.show();
    }
}
