package com.rent.hertz.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.rent.hertz.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, CategoryActivityList.class);
        startActivity(intent);
    }
}
