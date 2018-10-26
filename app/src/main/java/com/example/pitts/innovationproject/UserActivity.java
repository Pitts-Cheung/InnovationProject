package com.example.pitts.innovationproject;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class UserActivity extends AppCompatActivity implements View.OnTouchListener,GestureDetector.OnGestureListener {
    private GestureDetector mGestureDetector;
    private LinearLayout mUserMainView;
    private LinearLayout mFollowButton;
    private LinearLayout mTokenTaskButton;
    private LinearLayout mMyAnswerButton;
    private LinearLayout mPaymentButton;
    private LinearLayout mUserButtonView;
    String tag = "me";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mGestureDetector = new GestureDetector((GestureDetector.OnGestureListener)this);
        mUserMainView = (LinearLayout) findViewById(R.id.userMainView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabStar);
        mFollowButton = (LinearLayout) findViewById(R.id.follow_button);
        mTokenTaskButton = (LinearLayout)findViewById(R.id.token_task_button);
        mMyAnswerButton = (LinearLayout)findViewById(R.id.my_answer_button);
        mPaymentButton = (LinearLayout)findViewById(R.id.payment_button);
        mUserButtonView = (LinearLayout)findViewById(R.id.user_button_view);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo:收藏夹界面
            }
        });
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        mUserMainView.setOnTouchListener(this);
        mUserMainView.setLongClickable(true);

        int marginMiddle = (int)((getWindowHeightAndWeightAndDensity()[1] - 300.0)/3.0*getWindowHeightAndWeightAndDensity()[2]);
        setViewMargin(mFollowButton,0,0,marginMiddle,0);
        setViewMargin(mTokenTaskButton,0,0,marginMiddle,0);
        setViewMargin(mMyAnswerButton,0,0,marginMiddle,0);

        //todo:更换用户背景图片
        //todo:读取用户头像、资料并替换
        //todo:编辑个人资料界面
        //todo:关注、事项、回答、支付界面
    }

    @Override
    public void onBackPressed(){

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

    public float[] getWindowHeightAndWeightAndDensity(){
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        float[] temp = {(int) (dm.heightPixels * 160.0 / dm.densityDpi),(int) (dm.widthPixels * 160.0 / dm.densityDpi),dm.densityDpi/(float)160.0};

        Log.i("tag",""+(dm.heightPixels * 160.0 / dm.densityDpi));
        Log.i("tag",""+(dm.widthPixels * 160.0 / dm.densityDpi));
        Log.i("tag",""+dm.heightPixels);
        Log.i("tag",""+dm.widthPixels);
        Log.i("tag",""+dm.densityDpi);
        Log.i("tag",""+(dm.densityDpi/160.0));
        Log.i("tag",""+dm.density);

        return temp;
    }

    public void setViewMargin(View view, final int marginLeft, final int marginTop, final int marginRight, final int marginBottom){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(view.getLayoutParams());
        lp.setMargins(marginLeft,marginTop,marginRight,marginBottom);
        view.setLayoutParams(lp);
    }

}
