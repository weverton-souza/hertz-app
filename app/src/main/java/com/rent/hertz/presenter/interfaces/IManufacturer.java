package com.rent.hertz.presenter.interfaces;

import com.rent.hertz.model.Manufacturer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

public interface IManufacturer {

    @POST(value="manufacturer/save")
    Call<Manufacturer> save(@Body final Manufacturer manufacturer);

    @PUT(value="manufacturer/{idManufacturer}/update")
    Call<Manufacturer> update(@Path("idManufacturer") final String idManufacturer,
                          @Body final Manufacturer manufacturer);

    @GET(value="manufacturer/list-all")
    Observable<List<Manufacturer>> findAll();

    @GET(value="manufacturer/{idManufacturer}/find")
    Observable<Manufacturer> findById(@Path("idManufacturer") final Long idManufacturer);

    @GET(value="/{idsManufacturers}/find-all")
    Observable<List<Manufacturer>> findById(@Path("idsManufacturers") final List<String> idsManufacturers);

    @DELETE(value="/{idManufacturer}/delete")
    void deleteById(@Path("idManufacturer") final Long idManufacturer);

    @DELETE(value="/delete-all")
    void deleteById(@Body final List<Manufacturer> categories);
}
