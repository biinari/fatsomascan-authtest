package com.fatsomadev.fatsomascanauthtest;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import com.facebook.android.*;
import com.facebook.android.Facebook.*;
import com.facebook.android.AsyncFacebookRunner.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

public class MainActivity extends Activity {

    Facebook facebook = new Facebook("155216064540397");

    private SharedPreferences mPrefs;

    private AsyncFacebookRunner mAsyncRunner;

    public final static String EXTRA_FB_VALUES = "com.fatsomadev.fatsomascanauthtest.FB_VALUES";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Get existing access_token if any
        mPrefs = getPreferences(MODE_PRIVATE);
        String access_token = mPrefs.getString("access_token", null);
        long expires = mPrefs.getLong("access_expires", 0);
        if (access_token != null) {
            facebook.setAccessToken(access_token);
        }
        if (expires != 0) {
            facebook.setAccessExpires(expires);
        }

        mAsyncRunner = new AsyncFacebookRunner(facebook);
    }

    /** Called when the user clicks the facebook login button */
    public void fbLogin(View view) {
        if (!facebook.isSessionValid()) {
            facebook.authorize(this, new String[] {}, new DialogListener() {
                @Override
                public void onComplete(Bundle values) {
                    SharedPreferences.Editor editor = mPrefs.edit();
                    editor.putString("access_token", facebook.getAccessToken());
                    editor.putLong("access_expires", facebook.getAccessExpires());
                    editor.commit();
                }

                @Override
                public void onFacebookError(FacebookError error) {}

                @Override
                public void onError(DialogError e) {}

                @Override
                public void onCancel() {}
            });
        }
    }

    /** Called when the user clicks the facebook logout button */
    public void fbLogout(View view) {
        mAsyncRunner.logout(this, new RequestListener() {
            @Override
            public void onComplete(String response, Object state) {}

            @Override
            public void onIOException(IOException e, Object state) {}

            @Override
            public void onFileNotFoundException(FileNotFoundException e,
                                                Object state) {}

            @Override
            public void onMalformedURLException(MalformedURLException e,
                                                Object state) {}

            @Override
            public void onFacebookError(FacebookError e, Object state) {}
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        facebook.authorizeCallback(requestCode, resultCode, data);
        showFacebookDetails(data.getExtras());
    }

    public void showFacebookDetails() {
        Bundle values = new Bundle();
        values.putString("access_token", facebook.getAccessToken());
        values.putLong("access_expires", facebook.getAccessExpires());
        showFacebookDetails(values);
    }

    public void showFacebookDetails(Bundle values) {
        Intent intent = new Intent(this, DisplayFacebookDetailsActivity.class);
        intent.putExtra(EXTRA_FB_VALUES, values);
        startActivity(intent);
    }
}
