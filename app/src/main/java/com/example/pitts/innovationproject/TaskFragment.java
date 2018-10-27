package com.example.pitts.innovationproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class TaskFragment extends Fragment {
    private RecyclerView mTaskView;
    private RecyclerView.LayoutManager mLayoutManager;
    private WaterFallAdapter mWaterFallAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        mTaskView = view.findViewById(R.id.task_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mWaterFallAdapter = new WaterFallAdapter(getActivity(),bulidData());

        mTaskView.setLayoutManager(mLayoutManager);
        mTaskView.setAdapter(mWaterFallAdapter);
        RecyclerItemsOffset();
        //todo:瀑布信息流下拉刷新，上拉加载下一页

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public List<TaskCard> bulidData(){
        String[] taskTitiles = {"帮忙取快递",
        "帮忙拿外卖",
        "帮忙取东西",
        "帮忙寄东西",
        "帮忙……",
        "帮忙……",
        "帮忙……",
        "帮忙……",
        "帮忙……"};
        String[] taskContexts = {"详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",
        "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",
        "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",
        "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",
        "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",
        "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",
        "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",
        "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",
        "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情"};
        List<TaskCard> list = new ArrayList<>();
        //todo:读取服务器中的数据并替换

        for(int i=0;i<9;i++){
            TaskCard tk = new TaskCard();
            tk.taskTitile = taskTitiles[i];
            tk.taskContext = taskContexts[i];
            list.add(tk);
        }

        return list;
    }

    public void RecyclerItemsOffset() {
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.BOTTOM_DECORATION,32);
        mTaskView.addItemDecoration(new RecyclerViewSpacesItemDecoration(stringIntegerHashMap));
    }
}
