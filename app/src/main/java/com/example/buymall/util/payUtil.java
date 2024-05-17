package com.example.buymall.util;

import static com.example.buymall.AlipayConfig.merchant_private_key;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alipay.sdk.app.PayTask;
import com.example.buymall.AlipayConfig;
import com.example.buymall.paySuccessCallback;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Map;

public class payUtil {

    public static void pay(Context con, String money, paySuccessCallback callback){
        boolean rsa2 = (merchant_private_key.length() > 0);
//        String money = new BigDecimal(s.getPrice()).multiply(new BigDecimal(bug_good_num)).setScale(2, RoundingMode.HALF_UP).toString();
        String outTradeNo = OrderInfoUtil2_0.getOutTradeNo();
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(AlipayConfig.app_id,money,"聚合点餐", rsa2,outTradeNo);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        String privateKey = merchant_private_key;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(((AppCompatActivity)con));
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());
                String result1 = result.get("result");
                if(result1 == null || result1.isEmpty()){
                    Looper.prepare();
                    Toast.makeText(con, "支付失败，可能没有安装支付宝", Toast.LENGTH_SHORT).show();
                    return;
                }
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(result1, JsonObject.class);
                String code = jsonObject.get("alipay_trade_app_pay_response").getAsJsonObject().get("code").getAsString();
                if (code.equals("10000")){
                    ((AppCompatActivity)con).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(con, "支付成功", Toast.LENGTH_SHORT).show();
                            callback.paySuccess(outTradeNo);
                        }
                    });


                }
//                                        Message msg = new Message();
//                                        msg.what = 2;
//                                        msg.obj = result;
//                                        mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }
}

