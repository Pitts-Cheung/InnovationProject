package com.example.pitts.innovationproject;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class UserActivity extends AppCompatActivity implements View.OnTouchListener,GestureDetector.OnGestureListener {
    private GestureDetector mGestureDetector;
    private LinearLayout mUserMainView;
    String tag = "me";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mGestureDetector = new GestureDetector((GestureDetector.OnGestureListener)this);
        mUserMainView = (LinearLayout) findViewById(R.id.userMainView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabStar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        mUserMainView.setOnTouchListener(this);
        mUserMainView.setLongClickable(true);
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

        if(arg0.getX()-arg1.getX()>fling_min_distance && Math.abs(arg0.getY()-arg1.getY())<fling_min_distance && Math.abs(arg2)>fling_min_velocity){
            this.finish();
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }

        return false;
    }

    @Override
    public boolean onTouch(View v,MotionEvent e){
        return mGestureDetector.onTouchEvent(e);
    }

}
