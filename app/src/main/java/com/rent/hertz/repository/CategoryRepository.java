package com.rent.hertz.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rent.hertz.model.Category;
import com.rent.hertz.presenter.interfaces.ICategory;
import com.rent.hertz.utils.Queries;
import com.rent.hertz.utils.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository extends SQLiteOpenHelper {

    private Queries queries = new Queries("Category");
    private RetrofitConfig retrofit;

    public CategoryRepository( Context context) {
        super(context, "hertzdb-mobile", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
               "CREATE TABLE category ( " +
                       "id CHAR(36) PRIMARY KEY, " +
                       "description TEXT DEFAULT NULL, " +
                       "price REAL DEFAULT NULL )";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS category";
        db.execSQL(sql);
        onCreate(db);
    }

    public void saveOrUpdate(Category category) {
        if (category.getId() == null) {
            category.setId(UUID.randomUUID().toString());
            save(category);
        } else {
            update(category);
        }
    }

    private void save(final Category category){

        RetrofitConfig retrofit = new RetrofitConfig();

        //Salvando no SQLite.
        getWritableDatabase().insert(queries.getTable(), null,
                this.createCategory(category));

        //Salvando no servidor.
        retrofit.getRetrofit()
                .create(ICategory.class)
                .save(category)
        .enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {

            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {

            }
        });
    }

    private void update(final Category category){

        RetrofitConfig retrofit = new RetrofitConfig();

        //Salvando no SQLite.
        String whereClause = "id = ?";
        String[] whereParams = { category.getId() };
        getWritableDatabase()
            .update(queries.getTable(), this.createCategory(category), whereClause, whereParams);

        //Salvando no servidor.
        retrofit.getRetrofit()
                .create(ICategory.class)
                .update(category.getId(), category)
        .enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {

            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {

            }
        });
    }

    public void deleteById(final String idCategory) {

        String whereClause = "id = ?";
        String[] whereParams = { idCategory };
        getReadableDatabase()
                .delete(queries.getTable(),whereClause, whereParams);
    }

    public List<Category> findAll() {
        Cursor cursor = getReadableDatabase()
                .rawQuery(queries.getQueryFindAll(), null);

        List<Category> categories = new ArrayList<>();
        while ( cursor.moveToNext() ) { categories.add( createCategory(cursor) ); }

        cursor.close();
        return categories;
    }

    private ContentValues createCategory(Category model) {
        ContentValues data = new ContentValues();

        if(model.getId() != null)
            data.put("id", model.getId());
        data.put("description", model.getDescription());
        data.put("price", model.getPrice());

        return data;
    }

    private Category createCategory(Cursor cursor) {
        String id =  cursor.getString(cursor.getColumnIndex("id"));
        String descpt = cursor.getString(cursor.getColumnIndex("description"));
        Double price  = Double.parseDouble( cursor.getString(cursor.getColumnIndex("price")));

        return new Category()
                    .setId( id ).setDescription( descpt ).setPrice( price );
    }
}