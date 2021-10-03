package com.example.carcontrolapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et;
    Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9;
    WebView web;
    public static final String LAST_TEXT = "";
    public static String IP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web = findViewById(R.id.webView);
        et = findViewById(R.id.editTextTextPersonName);
        bt1 = findViewById(R.id.button3);
        bt2 = findViewById(R.id.button2);
        bt3 = findViewById(R.id.button4);
        bt4 = findViewById(R.id.button);
        bt5 = findViewById(R.id.button5);
        bt6 = findViewById(R.id.button7);
        bt7 = findViewById(R.id.button6);
        bt8 = findViewById(R.id.button8);
        bt9 = findViewById(R.id.button9);

        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    InputMethodManager inm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inm.hideSoftInputFromWindow(v.getWindowToken(),0);
                }
            }
        });

        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        et.setText(pref.getString(LAST_TEXT, ""));
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                pref.edit().putString(LAST_TEXT, s.toString()).commit();


            }
        });

        ConnectivityManager con=(ConnectivityManager)getSystemService(Activity.CONNECTIVITY_SERVICE);
        boolean wifi=con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        boolean internet=con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        //check Internet connection
        if(internet||wifi)  //your code
        {
            bt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    forward();
                }
            });
            bt2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backward();
                }
            });
            bt3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    left();
                }
            });
            bt4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    right();
                }
            });
            bt5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fwdRight();
                }
            });
            bt6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fwdLeft();
                }
            });
            bt7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backRight();
                }
            });
            bt8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backLeft();
                }
            });
            bt9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stop();
                }
            });
        }

        else{
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.title)
                    .setTitle("No internet connection")
                    .setMessage("Please turn on mobile data")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //code for exit
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }

                    })
                    .show();
        }
    }
    @SuppressLint("SetJavaScriptEnabled")
    public void forward(){
        IP = et.getText().toString().replaceAll("\\s", "");
        String URL = "http://" + IP + "/forward";
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        web.loadUrl(URL);
    }
    @SuppressLint("SetJavaScriptEnabled")
    public void backward(){
        IP = et.getText().toString().replaceAll("\\s", "");
        String URL = "http://" + IP + "/backward";
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        web.loadUrl(URL);
    }
    @SuppressLint("SetJavaScriptEnabled")
    public void left(){
        IP = et.getText().toString().replaceAll("\\s", "");
        String URL = "http://" + IP + "/left";
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        web.loadUrl(URL);
    }
    @SuppressLint("SetJavaScriptEnabled")
    public void right(){
        IP = et.getText().toString().replaceAll("\\s", "");
        String URL = "http://" + IP + "/right";
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        web.loadUrl(URL);
    }
    @SuppressLint("SetJavaScriptEnabled")
    public void fwdRight(){
        IP = et.getText().toString().replaceAll("\\s", "");
        String URL = "http://" + IP + "/goAheadRight";
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        web.loadUrl(URL);
    }
    @SuppressLint("SetJavaScriptEnabled")
    public void fwdLeft(){
        IP = et.getText().toString().replaceAll("\\s", "");
        String URL = "http://" + IP + "/goAheadLeft";
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        web.loadUrl(URL);
    }
    @SuppressLint("SetJavaScriptEnabled")
    public void backRight(){
        IP = et.getText().toString().replaceAll("\\s", "");
        String URL = "http://" + IP + "/goBackRight";
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        web.loadUrl(URL);
    }
    @SuppressLint("SetJavaScriptEnabled")
    public void backLeft(){
        IP = et.getText().toString().replaceAll("\\s", "");
        String URL = "http://" + IP + "/goBackLeft";
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        web.loadUrl(URL);
    }
    @SuppressLint("SetJavaScriptEnabled")
    public void stop(){
        IP = et.getText().toString().replaceAll("\\s", "");
        String URL = "http://" + IP + "/stop";
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        web.loadUrl(URL);
    }
}