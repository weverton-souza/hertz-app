package com.rent.hertz.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rent.hertz.R;
import com.rent.hertz.model.Category;
import com.rent.hertz.repository.CategoryRepository;

public class CategoryActivityList extends AppCompatActivity {

    private ListView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        this.textView = findViewById(R.id.txtViewCategories);

        findViewById( R.id.btnAddNewCategory )
            .setOnClickListener( v -> {
                loadCategory();
                Intent intent = new Intent(CategoryActivityList.this, CategoryActivity.class);
                startActivity(intent);
            }
        );
    }

    private void loadCategory() {
        CategoryRepository categoryRepository = new CategoryRepository(this);

        ArrayAdapter<Category> arrayAdapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        categoryRepository.findAll());

        this.textView.setAdapter(arrayAdapter);
        categoryRepository.close();
    }

    @Override
    protected void onResume()  {
        super.onResume();
        loadCategory();
    }
}
