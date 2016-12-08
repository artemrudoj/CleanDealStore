package com.mipt.artem.cleandealstore.rest;

import com.mipt.artem.cleandealstore.rest.responcedata.ItemsHolder;
import com.mipt.artem.cleandealstore.rest.responcedata.Category;

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

    @FormUrlEncoded
    @POST("/api/content/get_subcategories/")
    Observable<List<Category>> getSubcategories(@Field("category") Integer subcategory);

    @GET("/api/content/get_items/")
    Observable<ItemsHolder> getItems(@Query("name_full") String name,
                                     @Query("category") Integer subcategoryId);
}
