package com.fatsomadev.fatsomascanauthtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.facebook.android.*;
import com.facebook.android.Facebook.*;
import java.util.*;

public class DisplayFacebookDetailsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the values from the intent
        Intent intent = getIntent();
        Bundle values = intent.getBundleExtra(MainActivity.EXTRA_FB_VALUES);
        Set keys = values.keySet();
        String details = "";

        // Put in a text view
        TextView textView = new TextView(this);
        textView.setTextSize(16);
        textView.setText(details);

        setContentView(textView);
    }
}
