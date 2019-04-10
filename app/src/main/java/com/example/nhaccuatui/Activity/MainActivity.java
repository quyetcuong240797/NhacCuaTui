 package com.example.nhaccuatui.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nhaccuatui.Adapter.MainViewPagerAdapter;
import com.example.nhaccuatui.Fragment.FragmentPersonal;
import com.example.nhaccuatui.Fragment.FragmentTimKiem;
import com.example.nhaccuatui.Fragment.FramentTrangChu;
import com.example.nhaccuatui.R;

 public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        init();
    }

     private void init() {
         MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
         mainViewPagerAdapter.addFragment(new FragmentPersonal(),"Cá Nhân");
         mainViewPagerAdapter.addFragment(new FramentTrangChu(),"Trang Chủ");
         mainViewPagerAdapter.addFragment(new FragmentTimKiem(),"Tìm Kiếm");
         viewPager.setAdapter(mainViewPagerAdapter);
         tabLayout.setupWithViewPager(viewPager);
         tabLayout.getTabAt(0).setIcon(R.drawable.iconcanhan);
         tabLayout.getTabAt(1).setIcon(R.drawable.ic_home);
         tabLayout.getTabAt(2).setIcon(R.drawable.ic_search);
     }

     private void anhxa() {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
     }
 }
