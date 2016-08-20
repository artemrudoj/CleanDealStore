package com.mipt.artem.cleandealstore.rest;

import com.mipt.artem.cleandealstore.rest.responcedata.Category;
import com.mipt.artem.cleandealstore.rest.responcedata.ItemsHolder;
import com.mipt.artem.cleandealstore.rest.responcedata.Subcategory;

import java.util.List;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by artem on 12.08.16.
 */
public interface ApiInterface {


    @POST("/api/site/get_categories/")
    Observable<List<Category>> getCategories();

    @FormUrlEncoded
    @POST("/api/site/get_subcategories/")
    Observable<List<Subcategory>> getSubcategories(@Field("category") int subcategory);

    @GET("/api/site/get_items/")
    Observable<ItemsHolder> getItems(@Query("name_full") String name,
                                     @Query("subcategory") int subcategoryId);
}
