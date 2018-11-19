package com.rent.hertz.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rent.hertz.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CustomerRepository extends SQLiteOpenHelper {

    public CustomerRepository(Context context) {
        super(context, "hertzdb-mobile", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
                "CREATE TABLE customer ( " +
                        "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                        "description TEXT DEFAULT NULL, " +
                        "price REAL DEFAULT NULL )";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS customer";
        db.execSQL(sql);
        onCreate(db);
    }

    public void save(final Customer customer){
        getWritableDatabase()
                .insert("customer", null, createCustomer(customer));
    }

    public void update(final Customer customer){
        getWritableDatabase()
            .update("customer", createCustomer(customer),"id=" + customer.getId(), null);
    }

    public void deleteById(final Long idCustomer) {
        getReadableDatabase()
                .delete("customer","id=" + idCustomer, null);
    }

    public List<Customer> findAll() {

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM customer", null);
        List<Customer> customers = new ArrayList<>();

        while ( cursor.moveToNext() ) {
            customers.add( createCustomer(cursor) );
        }

        cursor.close();
        return customers;
    }

    public Customer findById(final Long idCategory){
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM customer WHERE id = " + idCategory, null);

        cursor.moveToFirst();
        Customer customer = createCustomer(cursor) ;
        cursor.close();

        return customer;
    }

    private ContentValues createCustomer(Customer model) {
        ContentValues data = new ContentValues();

        if(model.getId() != 0)
            data.put("id", model.getId());

        data.put("name", model.getName());
        data.put("document", model.getDocument());

        return data;
    }

    private Customer createCustomer(Cursor cursor) {
        Integer id = Integer.parseInt( cursor.getString(cursor.getColumnIndex("id")));
        String name = cursor.getString(cursor.getColumnIndex("description"));
        String doc  = cursor.getString(cursor.getColumnIndex("price"));

        return new Customer()
                .setId( id ).setName( name ).setDocument( doc );
    }
}