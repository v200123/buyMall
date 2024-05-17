package com.example.buymall;

import com.example.buymall.bean.OrderBean;
import com.example.buymall.bean.OrderDetailInfoBean;
import com.example.buymall.bean.PageBean;
import com.example.buymall.bean.UserInfoBean;
import com.example.buymall.bean.createOrderBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServer {
    @GET("user/loginPassword")
    public Call<UserInfoBean> login(@Query("phone")String phone, @Query("password")String password);

    @GET("menu/goodsLists")
    public Call<OrderBean> getOrderList(@Query("cId")String userId);

    @GET("menu/searchGoods")
    public Call<OrderBean> searchGoods(@Query("key")String key);

    @POST("order/create")
    public Call<OrderBean> createOrder(@Body createOrderBean bean);

    @POST("admin/order/list")
    public Call<OrderDetailInfoBean> getOrder(@Body PageBean pageBean);

}
