package com.example.pitts.innovationproject.Fragment;

import android.content.Context;
import android.content.Intent;
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
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.pitts.innovationproject.Activity.SettingActivity;
import com.example.pitts.innovationproject.R;

import java.util.ArrayList;

public class UserFragment extends Fragment {

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
    private ArrayList<Fragment> mTabcontents;
    private AppBarLayout mAppBar;
    private CollapsingToolbarLayout mCollapsingToolBar;
    private LinearLayout mUserFirstView;
    private CoordinatorLayout mUserView;
    private Toolbar mToolBar;
    private ImageButton mBackButton;
    private ImageButton mSettingButton;
    private LinearLayout mUserInfoEdit;
    private boolean isNotTouchingPageViewer;
    String tag = "me";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        mUserMainView = (LinearLayout) view.findViewById(R.id.user_main_view);
        mTopMargin = (LinearLayout)view.findViewById(R.id.top_margin);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fabStar);
        mFollowButton = (LinearLayout) view.findViewById(R.id.follow_button);
        mTokenTaskButton = (LinearLayout)view.findViewById(R.id.token_task_button);
        mMyAnswerButton = (LinearLayout)view.findViewById(R.id.my_answer_button);
        mPaymentButton = (LinearLayout)view.findViewById(R.id.payment_button);
        mUserButtonView = (LinearLayout)view.findViewById(R.id.user_button_view);
        mTab = (TabLayout) view.findViewById(R.id.tab);
        mAppBar = (AppBarLayout)view.findViewById(R.id.appbar);
        mTabContent = (ViewPager) view.findViewById(R.id.tabcontent);
        mCollapsingToolBar = (CollapsingToolbarLayout)view.findViewById(R.id.collapsing_toolbar);
        mUserFirstView = (LinearLayout)view.findViewById(R.id.user_first_view);
        mUserView = (CoordinatorLayout)view.findViewById(R.id.user_view);
        mToolBar = (Toolbar)view.findViewById(R.id.toolbar);
        mBackButton = (ImageButton)view.findViewById(R.id.back_button);
        mSettingButton = (ImageButton)view.findViewById(R.id.setting_button);
        mUserInfoEdit = (LinearLayout) view.findViewById(R.id.user_info_edit);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo:收藏夹界面
            }
        });

        initView();
        initTab();
        intTabContent();

        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -mUserFirstView.getHeight() * 2 / 3) {
                    mToolBar.setPadding(0,0,(int)((float)mToolBar.getTitleMarginStart()*getWindowHeightAndWeightAndDensity()[2]),0);
                    mSettingButton.setVisibility(View.GONE);
                    mTopMargin.setVisibility(View.VISIBLE);
                    mCollapsingToolBar.setTitle("用户名");
                    DrawerLock drawerLock = (DrawerLock)getActivity();
                    drawerLock.DrawerLockMode(true);
                } else {
                    mToolBar.setPadding(0,getActivity().getApplicationContext().getResources().getDimensionPixelSize(getActivity().getApplicationContext().getResources().getIdentifier("status_bar_height", "dimen", "android"))+(int)((float)mToolBar.getTitleMarginTop()*getWindowHeightAndWeightAndDensity()[2]),(int)((float)mToolBar.getTitleMarginStart()*getWindowHeightAndWeightAndDensity()[2]),0);
                    mSettingButton.setVisibility(View.VISIBLE);
                    mTopMargin.setVisibility(View.GONE);
                    mCollapsingToolBar.setTitle(" ");
                    DrawerLock drawerLock = (DrawerLock)getActivity();
                    drawerLock.DrawerLockMode(false);
                }
            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DrawerLayout)getActivity().findViewById(R.id.drawerLayout)).closeDrawer(GravityCompat.START);
            }
        });

        mSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),SettingActivity.class));
            }
        });

        mUserInfoEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo:编辑个人资料界面
            }
        });

        //todo:更换用户背景图片
        //todo:读取用户头像、资料并替换
        //todo:关注、事项、回答、支付界面

        return view;
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

    public interface DrawerLock {
        void DrawerLockMode(boolean isLock);
    }

    //初始化界面
    public void initView(){
        int marginMiddle = (int)((getWindowHeightAndWeightAndDensity()[1] - 300.0)/3.0*getWindowHeightAndWeightAndDensity()[2]);
        setViewMargin(mFollowButton,0,0,marginMiddle,0);
        setViewMargin(mTokenTaskButton,0,0,marginMiddle,0);
        setViewMargin(mMyAnswerButton,0,0,marginMiddle,0);

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)mTopMargin.getLayoutParams();
        lp.height = getActivity().getApplicationContext().getResources().getDimensionPixelSize(getActivity().getApplicationContext().getResources().getIdentifier("status_bar_height", "dimen", "android"));
        mTopMargin.setLayoutParams(lp);

        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolBar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowCustomEnabled(true);
    }

    //获取屏幕高度、宽度和density
    public float[] getWindowHeightAndWeightAndDensity(){
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        float[] temp = {(int) (dm.heightPixels * 160.0 / dm.densityDpi),(int) (dm.widthPixels * 160.0 / dm.densityDpi),dm.densityDpi/(float)160.0};

        return temp;
    }

    //设置控件的margin
    public void setViewMargin(View view, final int marginLeft, final int marginTop, final int marginRight, final int marginBottom){
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(view.getLayoutParams());
        lp.setMargins(marginLeft,marginTop,marginRight,marginBottom);
        view.setLayoutParams(lp);
    }

    //初始化tab栏
    public void initTab(){
        mTab.setTabTextColors(ContextCompat.getColor(getContext(), R.color.darkGrey), ContextCompat.getColor(getContext(), R.color.colorPrimary));
        mTab.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        ViewCompat.setElevation(mTab, 5);
        mTab.setupWithViewPager(mTabContent);
    }

    //初始化viewpager
    public void intTabContent(){
        mTabIndicators = new ArrayList<>();
        mTabIndicators.add("我的任务");
        mTabIndicators.add("我的问题");
        mTabIndicators.add("我的组队");

        mTabcontents = new ArrayList<Fragment>();
        mTabcontents.add(new TaskFragment());
        mTabcontents.add(new QuestionFragment());
        mTabcontents.add(new GroupFragment());

        UserFragment.ContentPagerAdapter contentAdapter = new UserFragment.ContentPagerAdapter(getActivity().getSupportFragmentManager(), mTabIndicators, mTabcontents);
        mTabContent.setAdapter(contentAdapter);
        mTab.setTabsFromPagerAdapter(contentAdapter);
    }

}
