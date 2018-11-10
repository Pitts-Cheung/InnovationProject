package com.example.pitts.innovationproject.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pitts.innovationproject.R;

import java.util.ArrayList;


public class ChatFragment extends Fragment {

    private TabLayout mTab;
    private ViewPager mTabContext;
    private ArrayList<String> mTabIndicators;
    private ArrayList<Fragment> mTabContexts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        mTab = view.findViewById(R.id.tab);
        mTabContext = view.findViewById(R.id.tab_context);

        mTab.setTabTextColors(ContextCompat.getColor(getContext(), R.color.darkGrey), ContextCompat.getColor(getContext(), R.color.colorPrimary));
        mTab.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        ViewCompat.setElevation(mTab, 5);
        mTab.setupWithViewPager(mTabContext);

        mTabIndicators = new ArrayList<String>();
        mTabIndicators.add("聊天");
        mTabIndicators.add("群组");

        mTabContexts = new ArrayList<Fragment>();
        mTabContexts.add(new UserChatFragment());
        mTabContexts.add(new GroupChatFragment());

        ContentPagerAdapter contentAdapter = new ContentPagerAdapter(getActivity().getSupportFragmentManager(), mTabIndicators, mTabContexts);
        mTabContext.setAdapter(contentAdapter);
        mTab.setTabsFromPagerAdapter(contentAdapter);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
