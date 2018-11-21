package com.rent.hertz.presenter.interfaces;

import com.rent.hertz.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

public interface ICategory {

    @POST(value="category/save")
    Call<Category> save(@Body final Category category);

    @PUT(value="category/{idCategory}/update")
    Call<Category> update(@Path("idCategory") final String idCategory, @Body final Category category);

    @GET(value="category/list-all")
    Observable<List<Category>> findAll();

    @GET(value="category/{idCategory}/find")
    Observable<Category> findById(@Path("idCategory") final Long idCategory);

    @GET(value="/{idsCategories}/find-all")
    Observable<List<Category>> findById(@Path("idsCategories") final List<Long> idsCategories);

    @DELETE(value="/{idCategory}/delete")
    void deleteById(@Path("idCategory") final Long idCategory);

    @DELETE(value="/delete-all")
    void deleteById(@Body final List<Category> categories);
}
