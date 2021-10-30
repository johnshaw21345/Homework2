package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {
    private LottieAnimationView animationView;
    private AnimatorSet animatorSet;
    private ListView listView;
    private String [] data = {"James","John","Peter","Philip","Bartholomew","Thomas","Thaddeus","Simon","James","Matthew","Matthias","James","John","Peter","Philip","Bartholomew","Thomas","Thaddeus","Simon","James","Matthew","Matthias"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        return inflater.inflate(R.layout.fragment_placeholder, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        animationView = getActivity().findViewById(R.id.animation_view);
        listView = getActivity().findViewById(R.id.listview);
        listView.setVisibility(View.INVISIBLE);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,data);
        listView.setAdapter(arrayAdapter);

        ObjectAnimator animator0 = ObjectAnimator.ofFloat(listView,"alpha",0);
        animator0.setDuration(0);
        animator0.start();

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                listView.setVisibility(View.VISIBLE);
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(animationView,"alpha",1,0);
                animator1.setDuration(200);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(listView,"alpha",0,1);
                animator2.setDuration(200);
                animatorSet = new AnimatorSet();
                animatorSet.playSequentially(animator1,animator2);
                animatorSet.start();
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入

            }
        }, 5000);
    }
}
