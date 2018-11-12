package com.rent.hertz.presenter;


import android.support.annotation.NonNull;
import android.util.Log;

import com.rent.hertz.model.Category;
import com.rent.hertz.presenter.interfaces.ICategory;
import com.rent.hertz.utils.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPresenter {

    private RetrofitConfig retrofit;

    public CategoryPresenter() {
        this.retrofit = new RetrofitConfig();
    }

    public Call<Category> save(Category category) {
        return retrofit.getRetrofit()
                .create(ICategory.class)
                .save(category);
    }

    public Call<Category> update(Long idCategory, Category category) {
        return null;
    }

    public Call<List<Category>> findAll() {
        return null;
    }

    public Category findById(Long idCategory) {
        Call<Category> category =  retrofit.getRetrofit().create(ICategory.class)
                .findById(idCategory);
        final List<Category> cat = new ArrayList<>();

        category.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(@NonNull Call<Category> call,
                                   @NonNull Response<Category> response) {
                cat.add(response.body());
                Log.e("CategoryService   ", "Sucesso ao buscar o categoria:" + response.body());
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                Log.e("CategoryService   ", "Erro ao buscar o categoria:" + t.getMessage());
            }
        });

        return cat.get(0);
    }

    public Call<List<Category>> findById(List<Long> idsCategories) {
        return null;
    }

    public void deleteById(Long idCategory) {
    }

    public void deleteById(List<Category> categories) {

    }
}
