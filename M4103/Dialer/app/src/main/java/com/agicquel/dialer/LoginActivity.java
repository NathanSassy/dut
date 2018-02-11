package com.agicquel.dialer;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {
    static final String LOGIN_OK = "LOGIN_OK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Intent returnIntent = new Intent();

        String user = intent.getStringExtra(IdentActivity.USER_INPUT);
        String password = intent.getStringExtra(IdentActivity.PASSWORD_INPUT);

        boolean userValid = getString(R.string.app_user).equals(user);
        boolean passwordValid = getString(R.string.app_pwd).equals(password);
        boolean ok = userValid && passwordValid;

        returnIntent.putExtra(LOGIN_OK, ok);
        setResult(Activity.RESULT_OK, returnIntent);

        finish();
    }
}
