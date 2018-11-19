package com.rent.hertz.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rent.hertz.R;
import com.rent.hertz.model.Category;
import com.rent.hertz.repository.CategoryRepository;

public class MenuRegisterActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_menu_register );

        findViewById( R.id.btnRegisterCategory )
        .setOnClickListener(v -> {
                Intent intent = new Intent(MenuRegisterActivity.this,
                        CategoryActivity.class);

                startActivity(intent);
            }
        );

    }
}
