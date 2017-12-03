package com.example.khang.reminder;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class MainActivity extends AppCompatActivity {
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private TextView statusDsplTxtView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        android.os.Debug.waitForDebugger();
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        initializeLogin();
        setupCallBack();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void initializeLogin(){
        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);
        statusDsplTxtView =  findViewById(R.id.textView);
    }

    private void setupCallBack(){
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                statusDsplTxtView.setText("Succeeded");
            }

            @Override
            public void onCancel() {
                statusDsplTxtView.setText("Cancelled");
            }

            @Override
            public void onError(FacebookException error) {
                statusDsplTxtView.setText("Error");
            }
        });
    }
}
