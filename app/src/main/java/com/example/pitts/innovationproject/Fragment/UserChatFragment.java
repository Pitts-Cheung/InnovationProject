package com.example.pitts.innovationproject.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pitts.innovationproject.Bean.UserChatCard;
import com.example.pitts.innovationproject.OverWrite.CardItemDecoration;
import com.example.pitts.innovationproject.OverWrite.RecyclerViewSpacesItemDecoration;
import com.example.pitts.innovationproject.R;
import com.example.pitts.innovationproject.Utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

public class UserChatFragment extends Fragment {

    RecyclerView mChatView;
    private RecyclerView.LayoutManager mLayoutManager;
    private UserChatCardWaterFallAdapter mWaterFallAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_chat, container, false);

        mChatView = (RecyclerView)view.findViewById(R.id.chat_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mWaterFallAdapter = new UserChatCardWaterFallAdapter(getActivity(),buildData());

        mChatView.setLayoutManager(mLayoutManager);
        mChatView.setAdapter(mWaterFallAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    
    public List<UserChatCard> buildData(){
        String[] userChatUserNames = {"大壮",
                "二胖",
                "张三",
                "赵四",
                "刘能",
                "谢大脚",
                "王小蒙",
                "刘永强",
                "王大拿"};
        String[] userChatContexts = {"我们不一样","干你啊","你瞅啥呢？","什么是灵魂舞步?就是一杯热水倒进了裤裆里","老老老老老老老四啊","你俩别在那磨磨叽叽的","","",""};
        String[] userChatTimes = {"10:03",
                "昨天",
                "10/14",
                "10/10",
                "10/06",
                "10/03",
                "09/27",
                "09/26",
                "09/22"};
        List<UserChatCard> list = new ArrayList<>();
        //todo:读取服务器中的数据并替换

        for(int i=0;i<9;i++){
            UserChatCard tk = new UserChatCard();
            tk.setUserChatUserName(userChatUserNames[i]);
            tk.setUserChatContext(userChatContexts[i]);
            tk.setUserChatTime(userChatTimes[i]);
            list.add(tk);
        }

        return list;
    }
    
    public class UserChatCardWaterFallAdapter extends RecyclerView.Adapter {

        private Context mContext;
        private List<UserChatCard> mData;

        public UserChatCardWaterFallAdapter(Context context,List<UserChatCard> data){
            mContext = context;
            mData = data;
        }

        @Override
        public UserChatViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
            View view = LayoutInflater.from(mContext).inflate(R.layout.user_chat_view_item, null);
            return new UserChatViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){
            UserChatViewHolder holder2 = (UserChatViewHolder)holder;
            UserChatCard userChatCard = mData.get(position);
            holder2.getUserChatCard().setMinimumWidth(CommonUtil.getScreenWidth(getActivity()));
            holder2.getUserChatUserName().setText(userChatCard.getUserChatUserName());
            holder2.getUserChatContext().setText(userChatCard.getUserChatContext());
            holder2.getUserChatTime().setText(userChatCard.getUserChatTime());
            holder2.getUserChatCard().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //todo:传递userChat id
                }
            });
        }

        @Override
        public int getItemCount(){
            if(mData!=null){
                return mData.size();
            }
            return 0;
        }

        public class UserChatViewHolder extends RecyclerView.ViewHolder {
            private LinearLayout userChatCard;
            private TextView userChatUserName;
            private TextView userChatContext;
            private TextView userChatTime;
            private ImageView userChatUserIcon;

            public UserChatViewHolder(View itemView) {
                super(itemView);
                userChatCard = (LinearLayout) itemView.findViewById(R.id.user_chat_card);
                userChatUserName = (TextView) itemView.findViewById(R.id.user_chat_user_name);
                userChatContext = (TextView) itemView.findViewById(R.id.user_chat_context);
                userChatTime = (TextView) itemView.findViewById(R.id.user_chat_time);
                userChatUserIcon = (ImageView) itemView.findViewById(R.id.user_chat_user_icon);
            }

            public LinearLayout getUserChatCard() {
                return userChatCard;
            }

            public void setUserChatCard(LinearLayout userChatCard) {
                this.userChatCard = userChatCard;
            }

            public TextView getUserChatContext() {
                return userChatContext;
            }

            public void setUserChatContext(TextView userChatContext) {
                this.userChatContext = userChatContext;
            }

            public TextView getUserChatTime() {
                return userChatTime;
            }

            public void setUserChatTime(TextView userChatTime) {
                this.userChatTime = userChatTime;
            }

            public TextView getUserChatUserName() {
                return userChatUserName;
            }

            public void setUserChatUserName(TextView userChatUserName) {
                this.userChatUserName = userChatUserName;
            }

            public ImageView getUserChatUserIcon() {
                return userChatUserIcon;
            }

            public void setUserChatUserIcon(ImageView userChatUserIcon) {
                this.userChatUserIcon = userChatUserIcon;
            }
        }
    }

}
