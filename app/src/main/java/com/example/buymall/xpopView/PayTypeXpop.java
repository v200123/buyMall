package com.example.buymall.xpopView;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.buymall.PayTypeCallback;
import com.example.buymall.R;
import com.lxj.xpopup.core.BottomPopupView;

public class PayTypeXpop extends BottomPopupView {
    private PayTypeCallback callback;
    public PayTypeXpop(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        findViewById(R.id.tv_zhifubao).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.doPay(1);
                dismiss();
            }
        });
        findViewById(R.id.tv_xianjin).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.doPay(2);
                dismiss();

            }
        });
    }

    public void setCallback(PayTypeCallback back){
        callback = back;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.pay_type;
    }
}
