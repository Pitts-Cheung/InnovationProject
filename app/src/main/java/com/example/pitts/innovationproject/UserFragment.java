package com.example.pitts.innovationproject;

import android.content.Context;
import android.net.Uri;
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
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;

import static android.support.v4.view.GravityCompat.START;

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
    private ArrayList<Fragment> mTagcontents;
    private AppBarLayout mAppBar;
    private CollapsingToolbarLayout mCollapsingToolBar;
    private LinearLayout mUserFirstView;
    private CoordinatorLayout mUserView;
    private Toolbar mToolBar;
    private ImageButton mBackButton;
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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo:收藏夹界面
            }
        });

        int marginMiddle = (int)((getWindowHeightAndWeightAndDensity()[1] - 300.0)/3.0*getWindowHeightAndWeightAndDensity()[2]);
        setViewMargin(mFollowButton,0,0,marginMiddle,0);
        setViewMargin(mTokenTaskButton,0,0,marginMiddle,0);
        setViewMargin(mMyAnswerButton,0,0,marginMiddle,0);

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)mTopMargin.getLayoutParams();
        lp.height = getActivity().getApplicationContext().getResources().getDimensionPixelSize(getActivity().getApplicationContext().getResources().getIdentifier("status_bar_height", "dimen", "android"));
        mTopMargin.setLayoutParams(lp);

        initTab();
        intTabContent();

        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -mUserFirstView.getHeight() * 2 / 3) {
                    mCollapsingToolBar.setTitle("用户名");
                } else {
                    mCollapsingToolBar.setTitle(" ");
                }
            }
        });

        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolBar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowCustomEnabled(true);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DrawerLayout)getActivity().findViewById(R.id.drawerLayout)).closeDrawer(GravityCompat.START);
            }
        });

        //todo:更换用户背景图片
        //todo:读取用户头像、资料并替换
        //todo:编辑个人资料界面
        //todo:关注、事项、回答、支付界面

        return view;
    }

    public float[] getWindowHeightAndWeightAndDensity(){
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
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
        mTab.setTabTextColors(ContextCompat.getColor(getContext(), R.color.darkGrey), ContextCompat.getColor(getContext(), R.color.colorPrimary));
        mTab.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
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

        UserFragment.ContentPagerAdapter contentAdapter = new UserFragment.ContentPagerAdapter(getActivity().getSupportFragmentManager(), mTabIndicators, mTagcontents);
        mTabContent.setAdapter(contentAdapter);
        final OnDrawerPageChangeListener drawerPageChangeListener = (OnDrawerPageChangeListener) getActivity();
        mTabContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                if (position == mTagcontents.size() - 1) {
                    drawerPageChangeListener.onPageSelected(true);
                } else {
                    drawerPageChangeListener.onPageSelected(false);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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

    public interface OnDrawerPageChangeListener {
        void onPageSelected(boolean isLast);
    }

}
