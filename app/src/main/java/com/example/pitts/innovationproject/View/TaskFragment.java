package com.example.pitts.innovationproject.View;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pitts.innovationproject.R;
import com.example.pitts.innovationproject.Bean.TaskCard;

import java.util.ArrayList;
import java.util.List;


public class TaskFragment extends Fragment {
    private RecyclerView mTaskView;
    private RecyclerView.LayoutManager mLayoutManager;
    private TaskCardWaterFallAdapter mWaterFallAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        mTaskView = view.findViewById(R.id.task_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mWaterFallAdapter = new TaskCardWaterFallAdapter(getActivity(),buildData());

        mTaskView.setLayoutManager(mLayoutManager);
        mTaskView.setAdapter(mWaterFallAdapter);
        mTaskView.addItemDecoration(new TaskItemDecoration(32));
        //todo:瀑布信息流下拉刷新，上拉加载下一页

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public List<TaskCard> buildData(){
        int[] taskIds = {1,2,3,4,5,6,7,8,9};
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
        String[] taskTimes = {"2018/10/24",
        "2018/10/16",
        "2018/10/14",
        "2018/10/10",
        "2018/10/06",
        "2018/10/03",
        "2018/09/27",
        "2018/09/26",
        "2018/09/22"};
        String[] taskGifts = {"5元","8元","10元","14元","5元","8元","10元","14元","20元"};
        Boolean[] isImageDisplays = {true,true,false,true,false,false,false,true,true};
        List<TaskCard> list = new ArrayList<>();
        //todo:读取服务器中的数据并替换

        for(int i=0;i<9;i++){
            TaskCard tk = new TaskCard();
            tk.setTaskId(taskIds[i]);
            tk.setTaskTitile(taskTitiles[i]);
            tk.setTaskContext(taskContexts[i]);
            tk.setTaskTime(taskTimes[i]);
            tk.setTaskGift(taskGifts[i]);
            tk.setImageDisplay(isImageDisplays[i]);
            list.add(tk);
        }

        return list;
    }

    public class TaskCardWaterFallAdapter extends RecyclerView.Adapter {

        private Context mContext;
        private List<TaskCard> mData;

        public TaskCardWaterFallAdapter(Context context,List<TaskCard> data){
            mContext = context;
            mData = data;
        }

        @Override
        public TaskViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
            View view = LayoutInflater.from(mContext).inflate(R.layout.task_view_item, null);
            return new TaskViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){
            TaskViewHolder holder2 = (TaskViewHolder)holder;
            TaskCard taskCard = mData.get(position);
            holder2.getTaskTitle().setText(taskCard.getTaskTitile());
            holder2.getTaskContext().setText(taskCard.getTaskContext());
            holder2.getTaskTime().setText(taskCard.getTaskTime());
            holder2.getTaskGift().setText(taskCard.getTaskGift());
            holder2.getTaskImage().setVisibility(taskCard.isImageDisplay()? View.VISIBLE : View.GONE);
            holder2.getTaskCard().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(),TaskActivity.class);
                    //todo:传递task id
                    startActivity(i);
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

        public class TaskViewHolder extends RecyclerView.ViewHolder {
            private CardView taskCard;
            private TextView taskTitle;
            private TextView taskContext;
            private TextView taskTime;
            private TextView taskGift;
            private ImageView taskImage;

            public TaskViewHolder(View itemView) {
                super(itemView);
                taskCard = (CardView) itemView.findViewById(R.id.task_card);
                taskTitle = (TextView) itemView.findViewById(R.id.task_card_title);
                taskContext = (TextView) itemView.findViewById(R.id.task_card_context);
                taskTime = (TextView) itemView.findViewById(R.id.task_card_time);
                taskGift = (TextView) itemView.findViewById(R.id.task_card_gift);
                taskImage = (ImageView) itemView.findViewById(R.id.task_card_image);
            }

            public CardView getTaskCard() {
                return taskCard;
            }

            public void setTaskCard(CardView taskCard) {
                this.taskCard = taskCard;
            }

            public TextView getTaskTitle() {
                return taskTitle;
            }

            public void setTaskTitle(TextView taskTitle) {
                this.taskTitle = taskTitle;
            }

            public TextView getTaskContext() {
                return taskContext;
            }

            public void setTaskContext(TextView taskContext) {
                this.taskContext = taskContext;
            }

            public TextView getTaskTime() {
                return taskTime;
            }

            public void setTaskTime(TextView taskTime) {
                this.taskTime = taskTime;
            }

            public TextView getTaskGift() {
                return taskGift;
            }

            public void setTaskGift(TextView taskGift) {
                this.taskGift = taskGift;
            }

            public ImageView getTaskImage() {
                return taskImage;
            }

            public void setTaskImage(ImageView taskImage) {
                this.taskImage = taskImage;
            }
        }
    }

    class TaskItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public TaskItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.bottom = space;

            if (parent.getChildPosition(view) == 0) outRect.top = space;
        }
    }
}
