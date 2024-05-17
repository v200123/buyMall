package com.example.buymall;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.buymall.bean.UserInfoBean;
import com.hjq.toast.Toaster;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {
    private EditText entered_phone, entered_password;
    private Button agree_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_password);
        entered_phone = findViewById(R.id.entered_phone);
        entered_password = findViewById(R.id.entered_password);
        agree_login = findViewById(R.id.agree_login);

        agree_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = entered_phone.getText().toString();
                String password = entered_password.getText().toString();
                if (phone.isEmpty() || password.isEmpty()) {
                    Toaster.showShort("请输入账号和密码");
                } else {
                    MyApplication.getApiServer().login(phone, password).enqueue(new Callback<UserInfoBean>() {
                        @Override
                        public void onResponse(Call<UserInfoBean> call, Response<UserInfoBean> response) {

                            String code = response.body().getCode();
                            if (code.equals("ok")) {
                                UserInfoBean.UserBeanX user = response.body().getUser();
                                if (
                                        user.getStatus().equals("4")

                                ) {
                                    Toaster.showShort("密码错误");

                                } else {
                                    Toaster.showShort("登录成功");
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    MyApplication.userInfo = user;
                                }
                            }
                        }
                        @Override
                        public void onFailure(Call<UserInfoBean> call, Throwable throwable) {
                            Log.e("result", "onFailure: "+throwable.getMessage() );
                        }
                    });
                }

                //todo 登录成功后的逻辑


            }
        });
    }
}
