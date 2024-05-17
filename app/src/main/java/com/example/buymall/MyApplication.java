package com.example.buymall;

import android.app.Application;

import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.example.buymall.bean.UserInfoBean;
import com.hjq.toast.Toaster;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {
    public static  EventManager asr;
    public static ApiServer apiServer;
    public static String server_ip="192.168.31.144";
    public static UserInfoBean.UserBeanX userInfo;
    @Override
    public void onCreate() {
        super.onCreate();
        Toaster.init(this);

    }


    public static ApiServer getApiServer(){
        if(apiServer == null)
            apiServer =  new Retrofit.Builder().baseUrl("http://"+server_ip+":8081")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiServer.class);
      return  apiServer;
    }

}
