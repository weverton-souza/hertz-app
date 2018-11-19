package com.rent.hertz.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rent.hertz.model.Category;
import com.rent.hertz.utils.QueriesUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository extends SQLiteOpenHelper {

    private QueriesUtils qUtils = new QueriesUtils("Category");
    public CategoryRepository( Context context) {
        super(context, "hertzdb-mobile", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
               "CREATE TABLE category ( " +
                       "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
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

    public void save(final Category category){
        getWritableDatabase()
                .insert(qUtils.getTable(), null, this.createCategory(category));
    }

    public void update(final Category category){
        getWritableDatabase()
                .update(qUtils.getTable(), this.createCategory(category),
                        "id=" + category.getId(), null);
    }

    public void deleteById(final Long idCategory) {
        getReadableDatabase()
                .delete(qUtils.getTable(),"id=" + idCategory, null);
    }

    public List<Category> findAll() {
        Cursor cursor = getReadableDatabase()
                .rawQuery(qUtils.getQueryFindAll(), null);

        List<Category> categories = new ArrayList<>();

        while ( cursor.moveToNext() ) { categories.add( createCategory(cursor) ); }

        cursor.close();
        return categories;
    }

    public Category findById(final Long idCategory){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery( qUtils
                .getQueryFindById(idCategory), null);

        cursor.moveToFirst();
        Category category = createCategory(cursor);
        cursor.close();

        return category;
    }

    private ContentValues createCategory(Category model) {
        ContentValues data = new ContentValues();

        if(model.getId() != 0)
            data.put("id", model.getId());
        data.put("description", model.getDescription());
        data.put("price", model.getPrice());

        return data;
    }

    private Category createCategory(Cursor cursor) {
        Integer id = Integer.parseInt( cursor.getString(cursor.getColumnIndex("id")));
        String descpt = cursor.getString(cursor.getColumnIndex("description"));
        Double price  = Double.parseDouble( cursor.getString(cursor.getColumnIndex("price")));

        return new Category()
                    .setId( id ).setDescription( descpt ).setPrice( price );
    }
}