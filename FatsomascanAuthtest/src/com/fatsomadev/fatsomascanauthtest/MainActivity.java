package com.fatsomadev.fatsomascanauthtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.facebook.android.*;
import com.facebook.android.Facebook.*;

public class MainActivity extends Activity {

    Facebook facebook = new Facebook("155216064540397");

    public final static String EXTRA_FB_VALUES = "com.fatsomadev.fatsomascanauthtest.FB_VALUES";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    /** Callen when the user clicks the facebook login button */
    public void fbLogin(View view) {
        facebook.authorize(this, new DialogListener() {
            @Override
            public void onComplete(Bundle values) {}

            @Override
            public void onFacebookError(FacebookError error) {}

            @Override
            public void onError(DialogError e) {}

            @Override
            public void onCancel() {}
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        facebook.authorizeCallback(requestCode, resultCode, data);

        Bundle values = data.getExtras();
        Intent intent = new Intent(this, DisplayFacebookDetailsActivity.class);
        intent.putExtra(EXTRA_FB_VALUES, values);
        startActivity(intent);
    }
}
