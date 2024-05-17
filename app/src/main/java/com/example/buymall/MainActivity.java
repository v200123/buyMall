package com.example.buymall;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.alipay.sdk.app.EnvUtils;
import com.angcyo.rcode.ScanActivity;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends BaseActivity {
    public BottomNavigationView bottomNav;
    private IndexFragment indexFragment;
    private MeFragment meFragment;
    private OrderDetailFragment orderDetailFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.main_bottom_nav);
        indexFragment = new IndexFragment();
        meFragment = new MeFragment();
        orderDetailFragment = new OrderDetailFragment();
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);



// this是Activity或其它Context类

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if(itemId == R.id.bottom_main){
                    switchFragment(indexFragment);
                }else if(itemId==R.id.bottom_menu){
                    switchFragment(orderDetailFragment);
                    orderDetailFragment.refresh();
                }else{
                    switchFragment(meFragment);
                }
                return true;
            }
        });
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_above_container, indexFragment).show(indexFragment).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       new AlertDialog.Builder(this).setTitle("肚子空空哦~")
                .setMessage("这么多可口的美食，饥不可耐T-T")
                .setPositiveButton("溜一会儿", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                }).setNegativeButton("朕手滑了", null).show();
    }

    private void switchFragment(Fragment changeFragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(changeFragment instanceof IndexFragment) {
            fragmentTransaction.hide(meFragment);
            fragmentTransaction.hide(orderDetailFragment);
        }else if(changeFragment instanceof OrderDetailFragment){
            fragmentTransaction.hide(indexFragment);
            fragmentTransaction.hide(meFragment);
        }
        else{
            fragmentTransaction.hide(orderDetailFragment);
            fragmentTransaction.hide(indexFragment);
        }
        if (!changeFragment.isAdded()) {
            fragmentTransaction.add(R.id.main_above_container, changeFragment);
        }
        fragmentTransaction.show(changeFragment).commitAllowingStateLoss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String s = ScanActivity.onResult(requestCode, resultCode, data);
        if(s!=null&&!s.isEmpty()){
            indexFragment.setBindNum(s);
        }

    }
}