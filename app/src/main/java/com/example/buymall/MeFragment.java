package com.example.buymall;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.buymall.bean.UserInfoBean;

public class MeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView user_photo = view.findViewById(R.id.userPhoto);
        TextView text_me_words = view.findViewById(R.id.text_me_words);
        View layout_about = view.findViewById(R.id.layout_about);
        UserInfoBean.UserBeanX.UserBean user = MyApplication.userInfo.getUser();
        Glide.with(this).load(user.getUserPhoto()).into(user_photo);
        text_me_words.setText(user.getUsername());
        layout_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.userInfo = null;
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
    }
}
