package com.rent.hertz.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rent.hertz.R;
import com.rent.hertz.model.Category;
import com.rent.hertz.repository.CategoryRepository;

public class CategoryActivityList extends Activity {

    private CategoryRepository categoryRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        this.categoryRepository = new CategoryRepository(this);

        ArrayAdapter<Category> arrayAdapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        this.categoryRepository.findAll());

        ListView textView = findViewById(R.id.txtViewCategories);
        textView.setAdapter(arrayAdapter);

        this.categoryRepository.close();
    }
}
