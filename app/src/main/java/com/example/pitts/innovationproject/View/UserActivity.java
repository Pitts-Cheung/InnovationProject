package com.example.pitts.innovationproject.View;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.pitts.innovationproject.R;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity implements View.OnTouchListener,GestureDetector.OnGestureListener {
    private GestureDetector mGestureDetector;
    private LinearLayout mUserMainView;
    private LinearLayout mFollowButton;
    private LinearLayout mTokenTaskButton;
    private LinearLayout mMyAnswerButton;
    private LinearLayout mPaymentButton;
    private LinearLayout mUserButtonView;
    private LinearLayout mTopMargin;
    private TabLayout mTab;
    private ViewPager mTabContent;
    private ArrayList<String> mTabIndicators;
    private ArrayList<Fragment> mTagcontents;
    private AppBarLayout mAppBar;
    private CollapsingToolbarLayout mCollapsingToolBar;
    private LinearLayout mUserFirstView;
    private CoordinatorLayout mUserView;
    private Toolbar mToolBar;
    String tag = "me";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mGestureDetector = new GestureDetector((GestureDetector.OnGestureListener)this);
        mUserMainView = (LinearLayout) findViewById(R.id.user_main_view);
        mTopMargin = (LinearLayout)findViewById(R.id.top_margin);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabStar);
        mFollowButton = (LinearLayout) findViewById(R.id.follow_button);
        mTokenTaskButton = (LinearLayout)findViewById(R.id.token_task_button);
        mMyAnswerButton = (LinearLayout)findViewById(R.id.my_answer_button);
        mPaymentButton = (LinearLayout)findViewById(R.id.payment_button);
        mUserButtonView = (LinearLayout)findViewById(R.id.user_button_view);
        mTab = (TabLayout) findViewById(R.id.tab);
        mAppBar = (AppBarLayout)findViewById(R.id.appbar);
        mTabContent = (ViewPager) findViewById(R.id.tabcontent);
        mCollapsingToolBar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        mUserFirstView = (LinearLayout)findViewById(R.id.user_first_view);
        mUserView = (CoordinatorLayout)findViewById(R.id.user_view);
        mToolBar = (Toolbar)findViewById(R.id.toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo:收藏夹界面
            }
        });
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        mUserFirstView.setOnTouchListener(this);
        mUserFirstView.setLongClickable(true);

        int marginMiddle = (int)((getWindowHeightAndWeightAndDensity()[1] - 300.0)/3.0*getWindowHeightAndWeightAndDensity()[2]);
        setViewMargin(mFollowButton,0,0,marginMiddle,0);
        setViewMargin(mTokenTaskButton,0,0,marginMiddle,0);
        setViewMargin(mMyAnswerButton,0,0,marginMiddle,0);

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)mTopMargin.getLayoutParams();
        lp.height = getApplicationContext().getResources().getDimensionPixelSize(getApplicationContext().getResources().getIdentifier("status_bar_height", "dimen", "android"));
        mTopMargin.setLayoutParams(lp);

        initTab();
        intTabContent();

        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -mUserFirstView.getHeight() * 2 / 3) {
                    mCollapsingToolBar.setTitle("用户名");
                    mTopMargin.setVisibility(View.VISIBLE);
                } else {
                    mCollapsingToolBar.setTitle(" ");
                    mTopMargin.setVisibility(View.GONE);
                }
            }
        });

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

    public void initTab(){
        mTab.setTabTextColors(ContextCompat.getColor(this, R.color.darkGrey), ContextCompat.getColor(this, R.color.colorPrimary));
        mTab.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorPrimary));
        ViewCompat.setElevation(mTab, 5);
        mTab.setupWithViewPager(mTabContent);
    }

    public void intTabContent(){
        mTabIndicators = new ArrayList<>();
        mTabIndicators.add("我的任务");
        mTabIndicators.add("我的问题");
        mTabIndicators.add("我的组队");

        mTagcontents = new ArrayList<Fragment>();
        mTagcontents.add(new TaskFragment());
        mTagcontents.add(new QuestionFragment());
        mTagcontents.add(new GroupFragment());

        ContentPagerAdapter contentAdapter = new ContentPagerAdapter(getSupportFragmentManager(), mTabIndicators, mTagcontents);
        mTabContent.setAdapter(contentAdapter);
        mTab.setTabsFromPagerAdapter(contentAdapter);
    }

    class ContentPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<String> titleList;
        private ArrayList<Fragment> fragmentList;

        public ContentPagerAdapter(FragmentManager fm, ArrayList<String> titleList, ArrayList<Fragment> fragmentList) {
            super(fm);
            this.titleList = titleList;
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }
}
