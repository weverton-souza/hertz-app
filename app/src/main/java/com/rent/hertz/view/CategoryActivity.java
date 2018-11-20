package com.rent.hertz.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rent.hertz.R;
import com.rent.hertz.model.Category;
import com.rent.hertz.repository.CategoryRepository;

public class CategoryActivity extends AppCompatActivity {

    private CategoryRepository categoryRepository;
    private  EditText txtDescriptionCategory;
    private EditText txtPriceCategory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = findViewById(R.id.toolbar);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel = findViewById(R.id.btnCancel);
        this.txtDescriptionCategory = findViewById( R.id.edtTextDescriptionCategory );
//        this.txtPriceCategory =  findViewById( R.id.edtTxtPriceCategory );
        setSupportActionBar(toolbar);

        btnSave.setOnClickListener( v -> {

                this.categoryRepository = new CategoryRepository(this);
                this.categoryRepository.save(
                    new Category()
                            .setDescription( this.txtDescriptionCategory.getText().toString() )
                            .setPrice( Double.valueOf( this.txtPriceCategory.getText().toString()) )
                );

                this.categoryRepository.close();
                Intent intent = new Intent(CategoryActivity.this,
                        CategoryActivityList.class);
                startActivity(intent);

                Toast.makeText(CategoryActivity.this, "Categoria salva!",
                    Toast.LENGTH_SHORT).show();
            }
        );

        btnCancel.setOnClickListener( v -> {
                Intent intent = new Intent(CategoryActivity.this,
                        CategoryActivityList.class);
                startActivity(intent);
            }
        );

        TextInputLayout til = findViewById(R.id.txtInputEdtTexPrice);
        TextInputEditText tiet = findViewById(R.id.textInpEdtTextPrice);

    }
}
