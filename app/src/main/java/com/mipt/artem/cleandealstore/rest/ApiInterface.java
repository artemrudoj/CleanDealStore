package com.mipt.artem.cleandealstore.rest;

import com.mipt.artem.cleandealstore.model.ItemInCart;
import com.mipt.artem.cleandealstore.model.User;
import com.mipt.artem.cleandealstore.network.UserVO;
import com.mipt.artem.cleandealstore.rest.responcedata.ItemsHolder;
import com.mipt.artem.cleandealstore.rest.responcedata.Category;

import java.util.List;
import java.util.Map;
import java.util.Objects;


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
    Observable<List<Category>> getSubcategories(@Field("category")Integer subcategory);

    @GET("/api/content/get_items/")
    Observable<ItemsHolder> getItems(@Query("name_full") String name,
                                     @Query("category") Integer subcategoryId,
                                     @Query("min_price") String minPrice,
                                     @Query("max_price") String maxPrice);

    @FormUrlEncoded
    @POST("/api/content/get_item_by_id/")
    Observable<Object> getItemById(@Field("id")Integer id);

    @FormUrlEncoded
    @POST("/api/content/get_parent_catigories/")
    Observable<List<Category>> getParentCategories(@Field("id")Integer id);


    @GET("/api/order/make_order/")
    Observable<Object> makeOrder();

    @FormUrlEncoded
    @POST("/api/order/edit_in_rack/")
    Observable<Map<Integer, ItemInCart>> editItemInShoppingCart(@Field("id") Integer id,
                                              @Field("in_rack") Boolean isInShoppingCart,
                                              @Field("count") Integer count,
                                              @Field("people_count") Integer peopleCount);
    @FormUrlEncoded
    @POST("/api/order/delete_from_rack/")
    Observable<Map<Integer, ItemInCart>> deleteItemFromShoppingCart(@Field("id") Integer id);


    @POST("/api/order/get_rack/")
    Observable<Map<Integer, ItemInCart>> getShoppingCart();

    @FormUrlEncoded
    @POST("/api/authentication/browser_login/")
    Observable<UserVO> loginAndRegistrationByCode(@Field("secret") String secret,
                                                  @Field("phone") String phone,
                                                  @Field("first_name") String first_name,
                                                  @Field("last_name") String last_name,
                                                  @Field("email") String email);
    @FormUrlEncoded
    @POST("/api/authentication/get_sms_confirmation_code/")
    Observable<UserVO> getConfirmationCode(@Field("phone") String phone);

    @GET("/logout/")
    Observable<Object> logout();


    @FormUrlEncoded
    @POST("/api/user/change_info/")
    Observable<User> changeUserInfo(@Field("first_name")String first_name,
                                    @Field("last_name")String last_name,
                                    @Field("email")String email,
                                    @Field("sex")Integer sex);
}
