package com.app.instagramdemo.util;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.app.instagramdemo.R;
import com.google.android.material.snackbar.Snackbar;

public class Util {

    public static void showToast(Context context, String message, int duration) {
        Toast.makeText(context, message, duration).show();
    }

    public static Snackbar showSnake(Context context, View mainLayout, String message) {
        Snackbar snackbar = Snackbar.make(mainLayout, message, Snackbar.LENGTH_LONG);
        snackbar.setActionTextColor(context.getResources().getColor(R.color.colorPrimary));
        return snackbar;
    }
}
