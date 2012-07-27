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
        Set<String> keys = values.keySet();
        String details = "";
        String empty = "";

        try {
            for (String key : keys) {
                details += "key: " + key + "\n";
                    details += values.getString(key, empty) + "\n";
            }
        } catch (Exception e) {
            details += e.getMessage();
        }

        if (values.containsKey("access_token")) {
            details += "\naccess_token = " + values.getString("access_token");
        }
        if (values.containsKey("expires_in")) {
            details += "\nexpires_in = " + values.getString("expires_in");
        }
        if (values.containsKey("code")) {
            details += "\ncode = " + values.getString("code");
        }

        // Put in a text view
        TextView textView = new TextView(this);
        textView.setTextSize(16);
        textView.setText(details);

        setContentView(textView);
    }
}
