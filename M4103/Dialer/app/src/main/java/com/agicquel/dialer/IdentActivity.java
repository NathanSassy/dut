package com.agicquel.dialer;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IdentActivity extends AppCompatActivity {
    // DATA
    static final int LOGIN_REQUEST = 1;
    static final String USER_INPUT = "USER_INPUT";
    static final String PASSWORD_INPUT = "PASSWORD_INPUT";
    // GUI
    private EditText mUsernameInput;
    private EditText mPasswordInput;
    private Button mIdButton;
    private Button mCallButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ident);

        mUsernameInput = findViewById(R.id.edt_text_user);
        mPasswordInput = findViewById(R.id.edt_text_pws);
        mIdButton = findViewById(R.id.btn_id);
        mCallButton = findViewById(R.id.btn_call);

        mIdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mUsernameInput.getText().toString();
                String password = mPasswordInput.getText().toString();
                Intent intent = new Intent(IdentActivity.this, LoginActivity.class);
                intent.putExtra(USER_INPUT, user);
                intent.putExtra(PASSWORD_INPUT, password);
                startActivityForResult(intent, LOGIN_REQUEST);
            }
        });

        mCallButton.setEnabled(false);
        mCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IdentActivity.this, DialActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_CANCELED)
            return;

        if(requestCode == LOGIN_REQUEST) {
            if(resultCode == Activity.RESULT_OK){
                boolean ok = data.getBooleanExtra(LoginActivity.LOGIN_OK, false);

                if(ok) {
                    mCallButton.setEnabled(true);
                    Toast.makeText(this,
                            "Identifiants corrects",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    mCallButton.setEnabled(false);
                    Toast.makeText(this,
                            "Identifiants incorrects",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
