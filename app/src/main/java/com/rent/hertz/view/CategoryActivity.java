package com.rent.hertz.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.rent.hertz.R;
import com.rent.hertz.model.Category;
import com.rent.hertz.repository.CategoryRepository;

public class CategoryActivity extends Activity {

    private CategoryRepository categoryRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        findViewById( R.id.btnSaveCategory )
        .setOnClickListener( v -> {
                this.categoryRepository = new CategoryRepository(this);
                final EditText txtDescriptionCategory = findViewById( R.id.edtTxtDescripCategory );
                final EditText txtPriceCategory =  findViewById( R.id.edtTxtPriceCategory );

                this.categoryRepository.save(
                        new Category()
                            .setDescription( txtDescriptionCategory.getText().toString() )
                            .setPrice( Double.valueOf(txtPriceCategory.getText().toString()) )
                );

                this.categoryRepository.close();
            }
        );
    }
}
