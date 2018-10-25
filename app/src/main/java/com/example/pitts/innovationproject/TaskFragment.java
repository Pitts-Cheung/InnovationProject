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


public class TaskFragment extends Fragment implements View.OnTouchListener,GestureDetector.OnGestureListener {
    private RecyclerView mTaskView;
    private RecyclerView.LayoutManager mLayoutManager;
    private WaterFallAdapter mWaterFallAdapter;
    private GestureDetector mGestureDetector;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        mTaskView = view.findViewById(R.id.task_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mWaterFallAdapter = new WaterFallAdapter(getActivity(),bulidData());
        mGestureDetector = new GestureDetector((GestureDetector.OnGestureListener)this);

        mTaskView.setOnTouchListener(this);
        mTaskView.setLongClickable(true);

        mTaskView.setLayoutManager(mLayoutManager);
        mTaskView.setAdapter(mWaterFallAdapter);
        RecyclerItemsOffset();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public boolean onDown(MotionEvent e){
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e){

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e){
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent arg0,MotionEvent arg1,float arg2,float arg3){
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e){

    }

    @Override
    public boolean onFling(MotionEvent arg0,MotionEvent arg1,float arg2,float arg3){
        final int fling_min_distance = 100;
        final int fling_min_velocity = 200;

        if(arg1.getX()-arg0.getX()>fling_min_distance && Math.abs(arg0.getY()-arg1.getY())<fling_min_distance && Math.abs(arg2)>fling_min_velocity){
            startActivity(new Intent(getActivity(),UserActivity.class));
            getActivity().overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        }

        return false;
    }

    @Override
    public boolean onTouch(View v,MotionEvent e){
        return mGestureDetector.onTouchEvent(e);
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
