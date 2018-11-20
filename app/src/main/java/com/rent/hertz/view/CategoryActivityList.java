package com.rent.hertz.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.rent.hertz.R;
import com.rent.hertz.model.Category;
import com.rent.hertz.repository.CategoryRepository;

public class CategoryActivityList extends AppCompatActivity {

    private ListView textView;
    private CategoryRepository categoryRepository;

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
        registerForContextMenu(this.textView);
        categoryRepository.close();
    }

    @Override
    protected void onResume()  {
        super.onResume();
        loadCategory();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        menu.add("Editar").setOnMenuItemClickListener(item -> {
            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo) menuInfo;
            Category category = (Category) this.textView.getItemAtPosition(info.position);

            Intent intent = new Intent(CategoryActivityList.this,
                    CategoryActivity.class);
            intent.putExtra("category", category);
            startActivity(intent);

            this.categoryRepository = new CategoryRepository(this);
            this.categoryRepository.saveOrUpdate(category);
            this.categoryRepository.close();

            return false;
        });

        menu.add("Deletar").setOnMenuItemClickListener(item -> {
            AdapterView.AdapterContextMenuInfo info =
                    (AdapterView.AdapterContextMenuInfo) menuInfo;
            Category category = (Category) this.textView.getItemAtPosition(info.position);

            this.categoryRepository = new CategoryRepository(this);
            this.categoryRepository.deleteById(category.getId());
            this.categoryRepository.close();
            loadCategory();

            Toast.makeText(CategoryActivityList.this, "Categoria deletada!",
                    Toast.LENGTH_SHORT).show();

            return false;
        });
    }
}
