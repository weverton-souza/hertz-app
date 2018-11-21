package com.rent.hertz.presenter;


import com.rent.hertz.model.Category;
import com.rent.hertz.presenter.interfaces.ICategory;
import com.rent.hertz.utils.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;

public class CategoryPresenter {

    private RetrofitConfig retrofit;

    public CategoryPresenter() {
        this.retrofit = new RetrofitConfig();
    }

    public void save(Category category) {
        retrofit.getRetrofit()
                        .create(ICategory.class)
                        .save(category);
    }

    public void update(Long idCategory, Category category) {
//        retrofit.getRetrofit()
//                .create(ICategory.class)
//                .update(idCategory, category);
    }

    public Call<List<Category>> findAll() {
        return null;
    }

    public List<Category> findById(Long idCategory) {
        ICategory iCategory = new RetrofitConfig().getRetrofit().create(ICategory.class);
        Observable<List<Category>> categoryObservable = iCategory.findAll();
        final List<Category> cats = new ArrayList<>();

        categoryObservable.subscribe(
                System.out::println,
                Throwable::printStackTrace,
                () -> System.out.println("Void, void, void"));

        return cats;
    }

    public Call<List<Category>> findById(List<Long> idsCategories) {
        return null;
    }

    public void deleteById(Long idCategory) {
    }

    public void deleteById(List<Category> categories) {

    }
}
