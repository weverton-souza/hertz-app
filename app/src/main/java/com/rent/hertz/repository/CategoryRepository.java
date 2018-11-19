package com.rent.hertz.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rent.hertz.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository extends SQLiteOpenHelper {

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

        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = new ContentValues();

        data.put("description", category.getDescription());
        data.put("price", category.getPrice());

        db.insert("category", null, data);
    }

//    public void findById(final Long idCategory){
//        return categoryRepository.findById(idCategory);
//    }
//
//    public void findById(final Iterable<Long> idsCategories){
//        return categoryRepository.findAllById(idsCategories);
//    }
//
    public List<Category> findAll() {

        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM category", null);
        List<Category> categories = new ArrayList<>();

        while ( c.moveToNext() ) {

            Integer id = Integer.parseInt( c.getString(c.getColumnIndex("id")));
            String descpt = c.getString(c.getColumnIndex("description"));
            Double price  = Double.parseDouble( c.getString(c.getColumnIndex("price")));

            categories.add(
                    new Category()
                        .setId( id )
                        .setDescription( descpt )
                        .setPrice( price ) );
        }

        c.close();
        return categories;
    }

    public Category deleteById(final Long idCategory) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM category WHERE id = %d", null);

        Integer id = Integer.parseInt(c.getString(c.getColumnIndex("id")));
        String descpt = c.getString(c.getColumnIndex("description"));
        Double price = Double.parseDouble(c.getString(c.getColumnIndex("price")));

        c.close();
        return new Category()
                .setId(id)
                .setDescription(descpt)
                .setPrice(price);
    }
//
//    public void deleteAllById(final Iterable<Category> categories){
//        categoryRepository.deleteAll(categories);
//    }


}
