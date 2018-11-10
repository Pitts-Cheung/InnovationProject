package com.example.pitts.innovationproject.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.pitts.innovationproject.BaseActivity;
import com.example.pitts.innovationproject.R;
import com.example.pitts.innovationproject.Fragment.ChatFragment;
import com.example.pitts.innovationproject.Fragment.GroupFragment;
import com.example.pitts.innovationproject.Fragment.QuestionFragment;
import com.example.pitts.innovationproject.Fragment.TaskFragment;
import com.example.pitts.innovationproject.Fragment.UserFragment;

import static android.support.v4.view.GravityCompat.START;

public class MainActivity extends BaseActivity implements UserFragment.DrawerLock {

    private DrawerLayout mDrawerLayout;
    private LinearLayout mMainView;
    private TaskFragment mTaskFragment;
    private QuestionFragment mQuestionFragment;
    private GroupFragment mGroupFragment;
    private ChatFragment mChatFragment;
    private UserFragment mUserFragment;
    private BottomNavigationView mNavigation;
    private LinearLayout mSearchView;
    private LinearLayout mSearchView2;
    private ImageView mUserHeadshot;
    private EditText mSearchBar;
    private EditText mSearchBarClick;
    private FloatingActionButton mFabAdd;
    private Fragment[] mFragments;
    private int mLastfragment;
    private InputMethodManager mInputMethodManager;
    private ImageButton mSearchButton;
    private LinearLayout mDrawer;
    private static final String PAGE_INDEX = "page_index";
    private String mUserName;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_task:
                    if(mLastfragment != 0){
                        mSearchView.setVisibility(View.VISIBLE);
                        mFabAdd.show();
                        switchFragment(mLastfragment,0);
                        mLastfragment = 0;
                    }
                    return true;
                case R.id.navigation_question:
                    if(mLastfragment != 1){
                        mSearchView.setVisibility(View.VISIBLE);
                        mFabAdd.show();
                        switchFragment(mLastfragment,1);
                        mLastfragment = 1;
                    }
                    return true;
                case R.id.navigation_group:
                    if(mLastfragment != 2){
                        mSearchView.setVisibility(View.VISIBLE);
                        mFabAdd.show();
                        switchFragment(mLastfragment,2);
                        mLastfragment = 2;
                    }
                    return true;
                case R.id.navigation_chat:
                    if(mLastfragment != 3){
                        mSearchView.setVisibility(View.GONE);
                        mFabAdd.hide();
                        switchFragment(mLastfragment,3);
                        mLastfragment = 3;
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigation = (BottomNavigationView) findViewById(R.id.navigation);
        mMainView = (LinearLayout) findViewById(R.id.mainView);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mSearchView = (LinearLayout)findViewById(R.id.search_view);
        mSearchView2 = (LinearLayout)findViewById(R.id.search_view_2);
        mUserHeadshot = (ImageView)findViewById(R.id.userHeadshot);
        mSearchBar = (EditText)findViewById(R.id.search_bar);
        mSearchBarClick = (EditText)findViewById(R.id.search_bar_click);
        mFabAdd = (FloatingActionButton)findViewById(R.id.fabAdd);
        mSearchButton = (ImageButton)findViewById(R.id.search_button);
        mDrawer = (LinearLayout)findViewById(R.id.drawer);

        initView();
        initFragment(mLastfragment);
        initDrawer();

        //点击搜索框后显示蒙版
        mSearchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

                mSearchView.setVisibility(View.INVISIBLE);
                mSearchView2.setVisibility(View.VISIBLE);
                mSearchBarClick.requestFocus();
                mInputMethodManager.showSoftInput(mSearchBarClick,0);
            }
        });

        //蒙版中点击搜索框后隐藏蒙版
        mSearchView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

                mSearchView2.setVisibility(View.INVISIBLE);
                mSearchView.setVisibility(View.VISIBLE);
                mInputMethodManager.hideSoftInputFromWindow(mMainView.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        //点击头像后显示个人主页
        mUserHeadshot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(START);
            }
        });


        //todo:搜索功能

        mFabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (mLastfragment){
                    case 0:
                        startActivity(new Intent(MainActivity.this,NewTaskActivity.class));
                        return;
                    case 1:
                        startActivity(new Intent(MainActivity.this,NewQuestionActivity.class));
                        return;
                }
            }
        });
    }

    @Override
    public void onBackPressed(){
        if(((DrawerLayout)findViewById(R.id.drawerLayout)).isDrawerOpen(START)){
            ((DrawerLayout)findViewById(R.id.drawerLayout)).closeDrawer(START);
        } else {
            if(findViewById(R.id.search_view).getVisibility() == View.VISIBLE){
                super.onBackPressed();
            }
            else if(findViewById(R.id.search_view_2).getVisibility() == View.VISIBLE){
                findViewById(R.id.search_view_2).setVisibility(View.GONE);
                findViewById(R.id.search_view).setVisibility(View.VISIBLE);
            }
            else if(findViewById(R.id.search_view_2).getVisibility() == View.GONE){
                super.onBackPressed();
            }
        }
    }

    //初始化界面
    private void initView(){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mSearchView.setVisibility(View.VISIBLE);
        mSearchView2.setVisibility(View.INVISIBLE);
    }

    //初始化fragment
    private void initFragment(int lastFragment){
        mTaskFragment = new TaskFragment();
        mQuestionFragment = new QuestionFragment();
        mGroupFragment = new GroupFragment();
        mChatFragment = new ChatFragment();
        mFragments = new Fragment[]{mTaskFragment,mQuestionFragment,mGroupFragment,mChatFragment};
        mNavigation.setSelectedItemId(lastFragment);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainView,mFragments[lastFragment]).show(mFragments[lastFragment]).commit();
    }

    //初始化drawer
    private void initDrawer(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        mUserFragment = new UserFragment();
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams)mDrawer.getLayoutParams();
        lp.width = metric.widthPixels;
        mDrawer.setLayoutParams(lp);
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
            }

            @Override public void onDrawerClosed(View drawerView) {
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }

            @Override public void onDrawerStateChanged(int newState) {

            }
        });
        transaction.add(R.id.drawer,mUserFragment);
        transaction.show(mUserFragment).commitAllowingStateLoss();
    }

    //切换fragment
    private void switchFragment(int lastFragment,int index){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(mFragments[lastFragment]);
        if(mFragments[index].isAdded()==false)
        {
            transaction.add(R.id.mainView,mFragments[index]);
        }
        transaction.show(mFragments[index]).commitAllowingStateLoss();
    }

    @Override
    public void DrawerLockMode(boolean isLock){
        if (!isLock) {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        } else {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
        }
    }

}
