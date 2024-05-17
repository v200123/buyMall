package com.example.buymall;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.angcyo.rcode.ScanActivity;
import com.baidu.speech.EventListener;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.buymall.bean.OrderBean;
import com.example.buymall.bean.OrderDetail;
import com.example.buymall.bean.createOrderBean;
import com.example.buymall.util.payUtil;
import com.example.buymall.xpopView.PayTypeXpop;
import com.example.buymall.xpopView.RecycleViewXpop;
import com.hjq.toast.Toaster;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnInputConfirmListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IndexFragment extends Fragment implements EventListener {
    private TextView category_homely, category_pot, category_snack, category_drink;
    private TextView tv_wm, tv_ts, tv_yy, textView, tv_shop_number, tv_change_style;
    private View bind_number_Container, tv_pay, iv_yuyinshibie, shapeImageView;
    private Adapter mAdapter;
    private String TAG = "result";
    private RecyclerView menu_lists;
    private EditText search_edit_text;
    private String bind_num = "-1";
    private String choice_time = "";
    private Date choice_Data ;
    private Boolean isHozi = true;
    private Map<OrderBean.GoodsBean, Integer> good_list = new HashMap<>();
    private Boolean is_shibie_start = false;
    private int useType = 1;//外卖 预约 堂食
    private String good_type = "1";
    private String address="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_menu, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyApplication.asr.unregisterListener(this);
        MyApplication.asr.send(SpeechConstant.ASR_CANCEL, null, null, 0, 0); //


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MyApplication.asr = EventManagerFactory.create(getContext(), "asr");
        MyApplication.asr.registerListener(this);

        category_homely = view.findViewById(R.id.category_homely);
        iv_yuyinshibie = view.findViewById(R.id.iv_yuyinshibie);
        category_pot = view.findViewById(R.id.category_pot);
        search_edit_text = view.findViewById(R.id.search_edit_text);
        tv_change_style = view.findViewById(R.id.tv_change_style);
        tv_pay = view.findViewById(R.id.tv_pay);
        shapeImageView = view.findViewById(R.id.shapeImageView);
        tv_shop_number = view.findViewById(R.id.tv_shop_number);
        category_snack = view.findViewById(R.id.category_snack);
        category_drink = view.findViewById(R.id.category_drink);
        bind_number_Container = view.findViewById(R.id.bind_number);
        textView = view.findViewById(R.id.textView);
        tv_wm = view.findViewById(R.id.tv_wm);
        tv_ts = view.findViewById(R.id.tv_ts);
        tv_yy = view.findViewById(R.id.tv_yy);
        search_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 文本变化之前的回调
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 文本变化时的回调
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 文本变化之后的回调
                String key = s.toString();
                if (!key.isEmpty()) {
                    MyApplication.getApiServer().searchGoods(key).enqueue(new Callback<OrderBean>() {
                        @Override
                        public void onResponse(Call<OrderBean> call, Response<OrderBean> response) {
                            mAdapter.setNewInstance(response.body().getGoods());
                        }

                        @Override
                        public void onFailure(Call<OrderBean> call, Throwable throwable) {

                        }
                    });
                } else {
                    getMenuData(good_type);
                }
            }
        });
        shapeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecycleViewXpop recycleViewXpop = new RecycleViewXpop(getContext());
                ItemDetailAdapter itemDetailAdapter = new ItemDetailAdapter();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                    itemDetailAdapter.setNewInstance(good_list.keySet().stream().toList());
                }
                recycleViewXpop.setRvAdapter(itemDetailAdapter);
                new XPopup.Builder(getContext()).atView(bind_number_Container).asCustom(recycleViewXpop).show();
            }
        });
        tv_change_style.setOnClickListener(v -> {
            if (isHozi) {
                menu_lists.setLayoutManager(new GridLayoutManager(getContext(), 2));
                mAdapter.getData().forEach(orderBean -> orderBean.ItemType = 0);

            } else {
                mAdapter.getData().forEach(orderBean -> orderBean.ItemType = 1);
                menu_lists.setLayoutManager(new LinearLayoutManager(getContext()));
            }
            mAdapter.notifyDataSetChanged();
            isHozi = !isHozi;
        });
        category_homely.setBackgroundColor(Color.parseColor("#ffffff"));
        mAdapter = new Adapter();
        menu_lists = view.findViewById(R.id.menu_lists);
        menu_lists.setLayoutManager(new LinearLayoutManager(getContext()));
        menu_lists.setAdapter(mAdapter);
        getMenuData("1");
        iv_yuyinshibie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!is_shibie_start)
                    start_record();
                else {
                    stop_record();
                }
            }
        });
        tv_wm.setOnClickListener(v -> {
            useType = 3;
            if (address.isEmpty())
                textView.setText("选择地址");
            else {
                textView.setText(address);
            }
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new XPopup.Builder(getContext()).asInputConfirm("输入地址", "请输入内容。",
                                    text -> {
                                        address = text;
                                        textView.setText(address);

                                    }
                            )
                            .show();
                }
            });
            tv_wm.setSelected(true);
            tv_ts.setSelected(false);
            tv_yy.setSelected(false);
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScanActivity.start(getActivity());
            }
        });
        tv_ts.setOnClickListener(v -> {
            useType = 1;
            if (bind_num.equals("-1")) {
                textView.setText("暂未绑定桌号");
                tv_pay.setEnabled(false);

            } else {
                tv_pay.setEnabled(true);

                textView.setText("桌号:" + bind_num);
            }
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ScanActivity.start(getActivity());
                }
            });
            tv_ts.setSelected(true);
            tv_wm.setSelected(false);
            tv_yy.setSelected(false);
        });

        tv_yy.setOnClickListener(v -> {
            useType = 2;

            if (choice_time.isEmpty()) {
                textView.setText("选择预约时间");
                tv_pay.setEnabled(false);
            } else {
                textView.setText("预约时间:" + choice_time);
                tv_pay.setEnabled(true);

            }
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TimePickerView build = new TimePickerBuilder(getContext(), new OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {//选中事件回调
                            choice_Data = date;
                            choice_time = new SimpleDateFormat("HH:mm").format(date);
                            textView.setText("预约时间:" + choice_time);
                            tv_pay.setEnabled(true);

                        }
                    })
                            .setType(new boolean[]{false, false, false, true, true, false})// 默认全部显示
                            .setCancelText("Cancel")//取消按钮文字
                            .setSubmitText("Sure")//确认按钮文字
                            .setTitleSize(20)//标题文字大小
                            .setTitleText("Title")//标题文字
                            .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                            .isCyclic(true)//是否循环滚动
                            .setTitleColor(Color.WHITE)//标题文字颜色
                            .setSubmitColor(Color.BLACK)//确定按钮文字颜色
                            .setCancelColor(Color.BLACK)//取消按钮文字颜色
                            .setBgColor(Color.WHITE)
                            .setTitleBgColor(0xFF666666)//标题背景颜色 Night mode
                            .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                            .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                            .isDialog(false)//是否显示为对话框样式
                            .build();
                    build.show();
                }
            });
            tv_yy.setSelected(true);
            tv_ts.setSelected(false);
            tv_wm.setSelected(false);
        });
        tv_ts.setSelected(true);
        mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                OrderBean.GoodsBean itemOrNull = mAdapter.getItemOrNull(position);

                if (view.getId() == R.id.iv_minue) {
                    remoGoodFromList(itemOrNull);
                } else {
                    addGoodToList(itemOrNull);
                }
                mAdapter.notifyItemChanged(position);
            }
        });
        category_homely.setOnClickListener(v -> {
            getMenuData("1");
            category_pot.setBackgroundColor(Color.parseColor("#F2F3F8"));
            category_homely.setBackgroundColor(Color.parseColor("#ffffff"));
            category_snack.setBackgroundColor(Color.parseColor("#F2F3F8"));
            category_drink.setBackgroundColor(Color.parseColor("#F2F3F8"));
        });

        category_pot.setOnClickListener(v -> {
            getMenuData("2");

            category_pot.setBackgroundColor(Color.parseColor("#ffffff"));
            category_homely.setBackgroundColor(Color.parseColor("#F2F3F8"));
            category_snack.setBackgroundColor(Color.parseColor("#F2F3F8"));
            category_drink.setBackgroundColor(Color.parseColor("#F2F3F8"));
        });

        tv_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayTypeXpop payTypeXpop = new PayTypeXpop(getContext());
                new XPopup.Builder(getContext()).asCustom(payTypeXpop).show();


                final Double[] total_price_all = {0.0};
                //完成转换
                List<OrderDetail> collect = good_list.keySet().stream().map(new Function<OrderBean.GoodsBean, OrderDetail>() {
                    @Override
                    public OrderDetail apply(OrderBean.GoodsBean goodsBean) {
                        OrderDetail orderDetail = new OrderDetail();
                        orderDetail.setId(goodsBean.getId());
                        orderDetail.setName(goodsBean.getName());
                        orderDetail.setImage(goodsBean.getPic());
                        String number = good_list.get(goodsBean).toString();
                        orderDetail.setQuantity(number);
                        double total_price = new BigDecimal(number).multiply(new BigDecimal(goodsBean.getPrice())).setScale(2, RoundingMode.HALF_UP).doubleValue();
                        orderDetail.setTotal_price(total_price);
                        total_price_all[0] += total_price;
                        return orderDetail;
                    }
                }).collect(Collectors.toList());
                createOrderBean createOrderBean = new createOrderBean();
                createOrderBean.setGoods_list(collect);
                if (useType == 1) {
                    createOrderBean.setTable_number(bind_num);
                } else if (useType == 2) {
                    createOrderBean.setIs_appointment(1);
                        createOrderBean.setAppointment_time(choice_Data);
                } else {
                    createOrderBean.setIs_takeout_order(1);
                    createOrderBean.setTakeout_address(address);
                }
                createOrderBean.setIs_takeout_order(0);
                createOrderBean.setSett_amount(total_price_all[0]);

                payTypeXpop.setCallback(new PayTypeCallback() {
                    @Override
                    public void doPay(int type) {
                        if(type ==1){
                            payUtil.pay(getActivity(), total_price_all[0].toString(), new paySuccessCallback() {
                                @Override
                                public void paySuccess(String orderNo) {
                                    MyApplication.getApiServer().createOrder(createOrderBean).enqueue(new Callback<OrderBean>() {
                                        @Override
                                        public void onResponse(Call<OrderBean> call, Response<OrderBean> response) {
                                            Toaster.show("支付成功");
                                        }

                                        @Override
                                        public void onFailure(Call<OrderBean> call, Throwable throwable) {

                                        }
                                    });
                                }
                            });

                        }else{
                            MyApplication.getApiServer().createOrder(createOrderBean).enqueue(new Callback<OrderBean>() {
                                @Override
                                public void onResponse(Call<OrderBean> call, Response<OrderBean> response) {
                                    Toaster.show("支付成功");
                                }

                                @Override
                                public void onFailure(Call<OrderBean> call, Throwable throwable) {

                                }
                            });
                        }
                        good_list.clear();
                        tv_shop_number.setText("0");
                        tv_shop_number.setVisibility(View.GONE);
                        mAdapter.getData().forEach(new Consumer<OrderBean.GoodsBean>() {
                            @Override
                            public void accept(OrderBean.GoodsBean goodsBean) {
                                goodsBean.setGood_num(0);
                            }
                        });
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

        category_snack.setOnClickListener(v -> {
            getMenuData("3");

            category_pot.setBackgroundColor(Color.parseColor("#F2F3F8"));
            category_homely.setBackgroundColor(Color.parseColor("#F2F3F8"));
            category_snack.setBackgroundColor(Color.parseColor("#ffffff"));
            category_drink.setBackgroundColor(Color.parseColor("#F2F3F8"));
        });

        category_drink.setOnClickListener(v -> {
            getMenuData("4");

            category_pot.setBackgroundColor(Color.parseColor("#F2F3F8"));
            category_homely.setBackgroundColor(Color.parseColor("#F2F3F8"));
            category_snack.setBackgroundColor(Color.parseColor("#F2F3F8"));
            category_drink.setBackgroundColor(Color.parseColor("#ffffff"));
        });

    }

    private void getMenuData(String number) {
        good_type = number;
        MyApplication.getApiServer().getOrderList(number).enqueue(new Callback<OrderBean>() {
            @Override
            public void onResponse(Call<OrderBean> call, Response<OrderBean> response) {
                if (response.body().getCode().equals("ok")) {
                    List<OrderBean.GoodsBean> goods = response.body().getGoods();
                    mAdapter.setNewInstance(goods);
                }
            }

            @Override
            public void onFailure(Call<OrderBean> call, Throwable throwable) {

            }
        });
    }

    private void stop_record() {
        is_shibie_start = false;
        Log.d(TAG, "start_record:关闭录制");

        MyApplication.asr.send(SpeechConstant.ASR_STOP, null, null, 0, 0); //
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPermission();
    }

    private void initPermission() {
        String permissions[] = {android.Manifest.permission.RECORD_AUDIO,
                android.Manifest.permission.ACCESS_NETWORK_STATE,
                android.Manifest.permission.INTERNET,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(getContext(), perm)) {
                toApplyList.add(perm);
                // 进入到这里代表没有权限.

            }
        }
        String tmpList[] = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), toApplyList.toArray(tmpList), 123);
        }

    }

    private void start_record() {
        is_shibie_start = true;
        Log.d(TAG, "start_record:开始录制");
        Map<String, Object> params = AuthUtil.getParam();
        String event = null;
        event = SpeechConstant.ASR_START; // 替换成测试的event
//        params.put(SpeechConstant.ACCEPT_AUDIO_VOLUME, false);
        params.put(SpeechConstant.PID, 15374);
        params.put(SpeechConstant.VAD, SpeechConstant.VAD_TOUCH);
        String json = null; // 可以替换成自己的json
        json = new JSONObject(params).toString(); // 这里可以替换成你需要测试的json
        MyApplication.asr.send(event, json, null, 0, 0);
        Log.d("TAG", "输入参数：" + json);
    }

    @Override
    public void onEvent(String s, String s1, byte[] bytes, int i, int i1) {
        if (s.equals(SpeechConstant.CALLBACK_EVENT_ASR_READY)) {
            // 引擎就绪，可以说话，一般在收到此事件后通过UI通知用户可以说话了
            Toaster.show("可以进行识别");
        }
        if (s.equals(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL)) {
            // 一句话的临时结果，最终结果及语义结果
        }
        Log.e("result", "CALLBACK_EVENT_ASR_PARTIAL有结果" + s1);

        if (s.equals(SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL)) {

            if (s1 == null || s1.isEmpty()) {
                return;
            }
            if (s1.contains("\"final_result\"")) {
                // 一句话的最终识别结果
                try {
                    JSONObject json = new JSONObject(s1);
                    String bestResult = json.getString("best_result");
                    String chineseOnly = keepChineseCharacters(bestResult);
                    search_edit_text.setText(chineseOnly);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                Log.e("result", "onEvent:最终识别结果：" + s1);
            }
        }
    }

    class Adapter extends BaseMultiItemQuickAdapter<OrderBean.GoodsBean, BaseViewHolder> {

        public Adapter() {
            addItemType(0, R.layout.menu_item);
            addItemType(1, R.layout.menu_item_hozi);
            addChildClickViewIds(R.id.goods_add_pic);
            addChildClickViewIds(R.id.iv_add);
            addChildClickViewIds(R.id.iv_minue);

        }

        @Override
        protected void convert(@NonNull BaseViewHolder baseViewHolder, OrderBean.GoodsBean o) {
            int itemViewType = baseViewHolder.getItemViewType();
            if (itemViewType == 0) {
                baseViewHolder.setText(R.id.goods_show_name, o.getName());
                baseViewHolder.setText(R.id.goods_show_price, "￥" + o.getPrice());
                baseViewHolder.setText(R.id.goods_show_desc, o.getDescription());
                ImageView imageView = (ImageView) baseViewHolder.getView(R.id.goods_show_pic);
                Glide.with(this.getContext()).load(o.getPic().replaceAll("localhost", MyApplication.server_ip)).into(imageView);
            } else {
                baseViewHolder.setText(R.id.tv_order_name, o.getName());
                baseViewHolder.setText(R.id.tv_order_price, "￥" + o.getPrice());
                baseViewHolder.setText(R.id.tv_order_content, o.getDescription());
                if (good_list.containsKey(o)) {
                    baseViewHolder.setText(R.id.tv_order_number, good_list.get(o) + "");
                }
                ImageView imageView = (ImageView) baseViewHolder.getView(R.id.iv_order_image);
                Glide.with(this.getContext()).load(o.getPic().replaceAll("localhost", MyApplication.server_ip)).into(imageView);
            }
        }
    }

    public String keepChineseCharacters(String input) {
        return input.replaceAll("[^\\u4E00-\\u9FA5]", "");
    }

    private void addGoodToList(OrderBean.GoodsBean bean) {
        if (good_list.containsKey(bean)) {
            int good_number = good_list.get(bean) + 1;
            good_list.put(bean, good_number);
            bean.setGood_num(good_number);
        } else {
            good_list.put(bean, 1);
            bean.setGood_num(1);
        }
        tv_shop_number.setText(getGoodNum() + "");
        tv_shop_number.setVisibility(View.VISIBLE);
        tv_pay.setEnabled(true);
    }

    private void remoGoodFromList(OrderBean.GoodsBean bean) {
        if (good_list.containsKey(bean)) {
            int good_number = good_list.get(bean) - 1;
            good_list.put(bean, good_number);
            bean.setGood_num(good_number);
            if (good_number == 0) {
                good_list.remove(bean);
            }
        }

        tv_shop_number.setText(getGoodNum() + "");
        if (good_list.isEmpty()) {
            tv_pay.setEnabled(false);
        }
    }

    private int getGoodNum() {
        int num = 0;
        for (Map.Entry<OrderBean.GoodsBean, Integer> entry : good_list.entrySet()) {
            num += entry.getValue();
        }
        return num;
    }


    public void setBindNum(String bindNum) {
        this.bind_num = bindNum;
        textView.setText("桌号:" + bind_num);

    }

    class ItemDetailAdapter extends BaseQuickAdapter<OrderBean.GoodsBean, BaseViewHolder> {
        public ItemDetailAdapter() {
            super(R.layout.item_order_detail);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder baseViewHolder, OrderBean.GoodsBean goodsBean) {
            ImageView photo = baseViewHolder.getView(R.id.iv_order_detail_photo);
            Glide.with(getContext()).load(goodsBean.getPic().replaceAll("localhost", MyApplication.server_ip)).into(photo);
            baseViewHolder.setText(R.id.tv_order_detail_photo, goodsBean.getName())
                    .setText(R.id.tv_order_detail_price, "￥" + goodsBean.getPrice());
            if (good_list.containsKey(goodsBean)) {
                baseViewHolder.setText(R.id.tv_order_detail_number, "数量:" + good_list.get(goodsBean));
            }
        }
    }


}
