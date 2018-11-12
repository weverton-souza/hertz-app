package com.rent.hertz.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.rent.hertz.R;
import com.rent.hertz.model.Category;
import com.rent.hertz.presenter.CategoryPresenter;

import java.util.Optional;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CategoryPresenter presenter = new CategoryPresenter();
        presenter.findById(1L);
    }
}
