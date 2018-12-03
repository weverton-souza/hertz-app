package com.rent.hertz.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.rent.hertz.model.Manufacturer;
import com.rent.hertz.presenter.interfaces.IManufacturer;
import com.rent.hertz.utils.Queries;
import com.rent.hertz.utils.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManufacturerRepository extends DataBaseHelper {

    private Queries queries = new Queries("manufacturer");
    private RetrofitConfig retrofit;

    public ManufacturerRepository(Context context) {
        super(context);
    }

    public void saveOrUpdate(Manufacturer manufacturer) {
        if (manufacturer.getId() == null) {
            manufacturer.setId(UUID.randomUUID().toString());
            save(manufacturer);
        } else {
            update(manufacturer);
        }
    }

    private void save(final Manufacturer manufacturer){

        this.retrofit  = new RetrofitConfig();

        //Salvando no servidor.
        this.retrofit .getRetrofit()
                .create(IManufacturer.class).save(manufacturer)
                .enqueue(new Callback<Manufacturer>() {
                    @Override
                    public void onResponse(@NonNull Call<Manufacturer> call,
                                           @NonNull Response<Manufacturer> response) {

                        Manufacturer manufacturer = response.body();

                        if(manufacturer != null) {
                            getWritableDatabase().insert(TABLE_MANUFACTURE, null,
                                    createManufacturer(manufacturer));
                        }

                    }

                    @Override
                    public void onFailure(Call<Manufacturer> call, Throwable t) {
                        System.out.println("Deu erro!");
                    }
                });
    }

    private void update(final Manufacturer manufacturer){

        this.retrofit = new RetrofitConfig();

        //Salvando no servidor.
        this.retrofit .getRetrofit().create(IManufacturer.class).update(manufacturer.getId(), manufacturer)
                .enqueue(new Callback<Manufacturer>() {
                    @Override
                    public void onResponse(@NonNull Call<Manufacturer> call,
                                           @NonNull Response<Manufacturer> response) {

                        Manufacturer manufacturer = response.body();

                        if(manufacturer != null) {

                            String whereClause = "id = ?";
                            String[] whereParams = {manufacturer.getId()};

                            getWritableDatabase()
                                    .update(queries.getTable(), createManufacturer(manufacturer), whereClause,
                                            whereParams);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Manufacturer> call, @NonNull Throwable t) {

                    }
                });
    }

    public void deleteById(final String idManufacturer) {

        String whereClause = "id = ?";
        String[] whereParams = { idManufacturer };
        getReadableDatabase()
                .delete(queries.getTable(),whereClause, whereParams);
    }

    public List<Manufacturer> findAll() {
        Cursor cursor = getReadableDatabase()
                .rawQuery(queries.getQueryFindAll(), null);

        List<Manufacturer> categories = new ArrayList<>();
        while ( cursor.moveToNext() ) { categories.add( createManufacturer(cursor) ); }

        cursor.close();
        return categories;
    }

    private ContentValues createManufacturer(Manufacturer model) {
        ContentValues data = new ContentValues();

        if(model.getId() != null) {
            data.put("id", model.getId());
        }
        data.put("name", model.getName());
        data.put("description", model.getDescription());


        return data;
    }

    private Manufacturer createManufacturer(Cursor cursor) {
        String id =  cursor.getString(cursor.getColumnIndex("id"));
        String descpt = cursor.getString(cursor.getColumnIndex("description"));
        String name  = cursor.getString(cursor.getColumnIndex("name"));

        return new Manufacturer()
                .setId( id ).setName( name ).setDescription( descpt );
    }
}