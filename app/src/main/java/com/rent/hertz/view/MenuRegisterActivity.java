package com.rent.hertz.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.rent.hertz.R;

public class MenuRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_menu_register );

        findViewById( R.id.btnRegisterCategory )
        .setOnClickListener(v -> {
                Intent intent = new Intent(MenuRegisterActivity.this,
                        CategoryActivityList.class);

                startActivity(intent);
            }
        );

    }
}
