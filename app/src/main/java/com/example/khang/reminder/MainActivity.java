package com.example.khang.reminder;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.messenger.MessengerUtils;
import com.facebook.messenger.MessengerThreadParams;
import com.facebook.messenger.ShareToMessengerParams;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    private Timer timer;
    private TimerTask sendMessage;
    private Calendar firstDate;
    private long period;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupTimerTask();
        setupTimer();
    }

    private void setupTimerTask() {
        sendMessage = new TimerTask() {
            @Override
            public void run() {
                sendMessage();
            }
        };
    }


    private void setupTimer(){
        timer = new Timer();
        firstDate = new GregorianCalendar();
        firstDate.set(2017, 12, 03, 23, 0);
        period = 1000 * 60 * 60 * 24;
        timer.scheduleAtFixedRate(sendMessage, firstDate.getTime(), period);
    }

    private void sendMessage(){
        int REQUEST_CODE_SHARE_TO_MESSENGER = 1147;
        Uri uri = Uri.parse("android.resource://com.example.khang.reminder/" + R.drawable.cat_hi);

        String mimeType = "image/jpeg";

        ShareToMessengerParams shareToMessengerParams = ShareToMessengerParams
                .newBuilder(uri, mimeType)
                .setMetaData("{P")
                .build();

        MessengerUtils.shareToMessenger(
                this, REQUEST_CODE_SHARE_TO_MESSENGER, shareToMessengerParams);
    }


}
