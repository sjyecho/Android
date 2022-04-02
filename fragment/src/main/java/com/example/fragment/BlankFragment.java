package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * 实现了Fragment中的跳转Activity,设置文本值
 *
 * onAttach方法:时Fragment和Activity建立关联时调用
 * onDestoryView方法:当Fragment中的布局被移除时调用
 * onDetach方法:是Fragment和Activity解除关联的时候调用
 *
 * Fragment生命周期:
 *      onAttach
 *      onCreate
 *      onCreateView
 *      onActivityCreated
 *      onStart
 *      onResume
 *      (Fragment is active)
 *      onPause
 *      onStop
 *      onDestoryView
 *      onDestory
 *      onDetach
 *      (Fragment is destoryed)
 */
public class BlankFragment extends Fragment {

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * 为Fragment加载布局时调用
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_blank,container,false);
        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                    跳转Activity
                    getActivity():返回一个和此fragment绑定的FragmentActivity或者其子类的实例
                    Context -Fragment里传Context 参数统一用getActivity
                */
                Intent intent=new Intent(getActivity(),MainActivity2.class);
                startActivity(intent);
            }
        });
        return view;
    }

    /**
     * 当Activity中的onCreate方法执行完后调用
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView textView =getActivity().findViewById(R.id.textView);
        textView.setText("哈哈哈哈哈哈哈哈");
    }
}