package com.example.buymall.xpopView;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.buymall.R;
import com.example.buymall.bean.OrderBean;
import com.lxj.xpopup.core.AttachPopupView;
import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.impl.AttachListPopupView;
import com.lxj.xpopup.impl.FullScreenPopupView;

public class RecycleViewXpop extends BottomPopupView {
    public  RecyclerView viewById;

    private RecyclerView.Adapter mAdapter;
    public RecycleViewXpop(@NonNull Context context) {
        super(context);
    }


    @Override
    protected void onCreate() {
        super.onCreate();
        viewById = findViewById(R.id.rv_empty);
        viewById.setLayoutManager(new LinearLayoutManager(getContext()));
        viewById.setAdapter(mAdapter);
    }
    public void setRvAdapter(RecyclerView.Adapter adapter){
        mAdapter = adapter;

    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.emoty_recycleview;
    }


}
