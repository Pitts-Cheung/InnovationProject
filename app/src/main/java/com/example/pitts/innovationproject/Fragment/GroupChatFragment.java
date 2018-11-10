package com.example.pitts.innovationproject.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pitts.innovationproject.Bean.GroupChatCard;
import com.example.pitts.innovationproject.R;
import com.example.pitts.innovationproject.Utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

public class GroupChatFragment extends Fragment {

    RecyclerView mChatView;
    private RecyclerView.LayoutManager mLayoutManager;
    private GroupChatCardWaterFallAdapter mWaterFallAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group_chat, container, false);

        mChatView = (RecyclerView)view.findViewById(R.id.chat_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mWaterFallAdapter = new GroupChatCardWaterFallAdapter(getActivity(),buildData());

        mChatView.setLayoutManager(mLayoutManager);
        mChatView.setAdapter(mWaterFallAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public List<GroupChatCard> buildData(){
        String[] groupChatGroupNames = {"约球群","约饭群","约自习群","约爬山群","约炮群","约唱歌群","约探店群","约火锅群","约酒群"};
        List<GroupChatCard> list = new ArrayList<>();
        //todo:读取服务器中的数据并替换

        for(int i=0;i<9;i++){
            GroupChatCard tk = new GroupChatCard();
            tk.setGroupChatGroupName(groupChatGroupNames[i]);
            list.add(tk);
        }

        return list;
    }

    public class GroupChatCardWaterFallAdapter extends RecyclerView.Adapter {

        private Context mContext;
        private List<GroupChatCard> mData;

        public GroupChatCardWaterFallAdapter(Context context,List<GroupChatCard> data){
            mContext = context;
            mData = data;
        }

        @Override
        public GroupChatViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
            View view = LayoutInflater.from(mContext).inflate(R.layout.group_chat_view_item, null);
            return new GroupChatViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){
            GroupChatViewHolder holder2 = (GroupChatViewHolder)holder;
            GroupChatCard groupChatCard = mData.get(position);
            holder2.getGroupChatCard().setMinimumWidth(CommonUtil.getScreenWidth(getActivity()));
            holder2.getGroupChatGroupName().setText(groupChatCard.getGroupChatGroupName());
            holder2.getGroupChatCard().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //todo:传递groupChat id
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

        public class GroupChatViewHolder extends RecyclerView.ViewHolder {
            private LinearLayout groupChatCard;
            private TextView groupChatGroupName;
            private ImageView groupChatGroupIcon;

            public GroupChatViewHolder(View itemView) {
                super(itemView);
                groupChatCard = (LinearLayout) itemView.findViewById(R.id.group_chat_card);
                groupChatGroupName = (TextView) itemView.findViewById(R.id.group_chat_group_name);
                groupChatGroupIcon = (ImageView) itemView.findViewById(R.id.group_chat_group_icon);
            }

            public LinearLayout getGroupChatCard() {
                return groupChatCard;
            }

            public void setGroupChatCard(LinearLayout groupChatCard) {
                this.groupChatCard = groupChatCard;
            }

            public TextView getGroupChatGroupName() {
                return groupChatGroupName;
            }

            public void setGroupChatGroupName(TextView groupChatGroupName) {
                this.groupChatGroupName = groupChatGroupName;
            }

            public ImageView getGroupChatGroupIcon() {
                return groupChatGroupIcon;
            }

            public void setGroupChatGroupIcon(ImageView groupChatGroupIcon) {
                this.groupChatGroupIcon = groupChatGroupIcon;
            }
        }
    }
}
