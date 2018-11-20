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
    private Long idCategory = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Long idCategory = null;
        setContentView(R.layout.activity_category);

        Intent intent = getIntent();
        Category category = (Category) intent.getSerializableExtra("category");

        Toolbar toolbar = findViewById(R.id.toolbar);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel = findViewById(R.id.btnCancel);
        this.txtDescriptionCategory = findViewById( R.id.edtTextDescription );
        this.txtPriceCategory =  findViewById( R.id.textInpEdtTextPrice );
        setSupportActionBar(toolbar);

        if(category != null) {
            this.idCategory = category.getId();
            this.txtDescriptionCategory.setText(category.getDescription());
            this.txtPriceCategory.setText(String.valueOf( category.getPrice()));
        }

        btnSave.setOnClickListener(v -> {

                this.categoryRepository = new CategoryRepository(this);
                this.categoryRepository.saveOrUpdate(
                    new Category()
                            .setId(this.idCategory)
                            .setDescription( this.txtDescriptionCategory.getText().toString() )
                            .setPrice( Double.valueOf( this.txtPriceCategory.getText().toString()) )
                );

                this.categoryRepository.close();

                startActivity(new Intent(CategoryActivity.this,
                        CategoryActivityList.class));

                Toast.makeText(CategoryActivity.this, "Categoria salva!",
                    Toast.LENGTH_SHORT).show();
            }
        );

        btnCancel.setOnClickListener( v -> {
                startActivity(new Intent(CategoryActivity.this,
                        CategoryActivityList.class));
            }
        );

    }
}
