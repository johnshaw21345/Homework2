package com.example.chapter3.homework;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用 ViewPager 和 Fragment 做一个简单版的好友列表界面
 * 1. 使用 ViewPager 和 Fragment 做个可滑动界面
 * 2. 使用 TabLayout 添加 Tab 支持
 * 3. 对于好友列表 Fragment，使用 Lottie 实现 Loading 效果，在 5s 后展示实际的列表，要求这里的动效是淡入淡出
 */


public class Ch3Ex3Activity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FragmentManager fm;
    private FragmentTransaction ft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch3ex3);



        // TODO: ex3-1. 添加 ViewPager 和 Fragment 做可滑动界面

        mViewPager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tablayout);
        ArrayList<String> titlelist = new ArrayList<>();

        titlelist.add("Hello 0");
        titlelist.add("Hello 1");
        titlelist.add("Hello 2");

        prepareViewPager(mViewPager,titlelist);

        mTabLayout.setupWithViewPager(mViewPager);


        // TODO: ex3-2, 添加 TabLayout 支持 Tab
    }


    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList){
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
        PlaceholderFragment fragment = new PlaceholderFragment();


        for (int i =0; i < arrayList.size();i++){
            Bundle bundle = new Bundle();
            bundle.putString("title", arrayList.get(i));
            fragment.setArguments(bundle);
            adapter.addFragment(fragment,arrayList.get(i));
            fragment = new PlaceholderFragment();

        }
        viewPager.setAdapter(adapter);
    }

    private class MainAdapter extends FragmentPagerAdapter{

        ArrayList<String> titlelist = new ArrayList<>();
        List<Fragment> list = new ArrayList<>();


        public void addFragment(Fragment fragment,String title){
            titlelist.add(title);
            list.add(fragment);
        }


        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        public CharSequence getPageTitle(int positon){
            return titlelist.get(positon);
        }
    }
}
