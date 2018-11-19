package com.rent.hertz.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.rent.hertz.R;
import com.rent.hertz.model.Category;
import com.rent.hertz.repository.CategoryRepository;

public class CategoryActivity extends AppCompatActivity {

    private CategoryRepository categoryRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener( v -> {

                this.categoryRepository = new CategoryRepository(this);
                final EditText txtDescriptionCategory = findViewById( R.id.edtTxtDescripCategory );
                final EditText txtPriceCategory =  findViewById( R.id.edtTxtPriceCategory );

                this.categoryRepository.save(
                    new Category()
                        .setDescription( txtDescriptionCategory.getText().toString() )
                        .setPrice( Double.valueOf(txtPriceCategory.getText().toString()) )
                );

                this.categoryRepository.close();
                Intent intent = new Intent(CategoryActivity.this,
                        CategoryActivityList.class);
                startActivity(intent);

                Toast.makeText(CategoryActivity.this, "Categoria salva!",
                    Toast.LENGTH_SHORT).show();

                return true;
            }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
