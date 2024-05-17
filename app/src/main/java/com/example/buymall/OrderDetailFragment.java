package com.example.buymall;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.buymall.bean.OrderDetailInfoBean;
import com.example.buymall.bean.PageBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailFragment  extends Fragment {
    public Adapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycleview,container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView detail = view.findViewById(R.id.rv_empty_detail);
        detail.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter =new Adapter();
        detail.setAdapter(mAdapter);



    }

    public void refresh(){
        MyApplication.apiServer.getOrder(new PageBean()).enqueue(new Callback<OrderDetailInfoBean>() {
            @Override
            public void onResponse(Call<OrderDetailInfoBean> call, Response<OrderDetailInfoBean> response) {
                if (response.body() != null) {
                    List<OrderDetailInfoBean.OrderListBean> orderInfo = response.body().getOrderList();
                    mAdapter.setNewInstance(orderInfo);
                    Log.d("result", "onResponse: ");
                }
            }

            @Override
            public void onFailure(Call<OrderDetailInfoBean> call, Throwable throwable) {

            }
        });

    }

    class Adapter extends BaseQuickAdapter<OrderDetailInfoBean.OrderListBean, BaseViewHolder>{

        public Adapter() {
            super(R.layout.item_order_detail);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder baseViewHolder, OrderDetailInfoBean.OrderListBean bean) {
                if(bean.getAppointment_time()!=null&&!bean.getAppointment_time().isEmpty()){
                    baseViewHolder.setText(R.id.tv_order_detail_photo,"预约单").setText(R.id.tv_other_info,"预约时间:"+bean.getAppointment_time()).setVisible(R.id.tv_other_info,true);

                }else if(bean.getTakeout_address()!=null&&!bean.getTakeout_address().isEmpty()){
                    baseViewHolder.setText(R.id.tv_order_detail_photo,"外卖单").setText(R.id.tv_other_info,"外卖地址:"+bean.getTakeout_address()).setVisible(R.id.tv_other_info,true);;
                }else{
                    baseViewHolder.setText(R.id.tv_order_detail_photo,"堂食订单").setText(R.id.tv_other_info,"桌号："+bean.getTable_number()).setVisible(R.id.tv_other_info,true);
                }
                baseViewHolder.setText(R.id.tv_order_detail_price,"￥"+bean.getSett_amount()+"");
        }
    }
}
