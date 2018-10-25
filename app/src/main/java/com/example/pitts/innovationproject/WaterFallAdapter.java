package com.example.pitts.innovationproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class WaterFallAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<TaskCard> mData;

    public WaterFallAdapter(Context context,List<TaskCard> data){
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
        holder2.taskTitle.setText(taskCard.taskTitile);
        holder2.taskContext.setText(taskCard.taskContext);

    }

    @Override
    public int getItemCount(){
        if(mData!=null){
            return mData.size();
        }
        return 0;
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        public TextView taskTitle;
        public TextView taskContext;
        public TaskViewHolder(View itemView) {
            super(itemView);
            taskTitle = (TextView) itemView.findViewById(R.id.task_card_title);
            taskContext = (TextView) itemView.findViewById(R.id.task_card_context);
        }
    }
}
