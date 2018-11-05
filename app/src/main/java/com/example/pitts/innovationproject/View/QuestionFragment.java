package com.example.pitts.innovationproject.View;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import com.example.pitts.innovationproject.Bean.QuestionCard;
import com.example.pitts.innovationproject.Bean.QuestionCard;
import com.example.pitts.innovationproject.OverWrite.CardItemDecoration;
import com.example.pitts.innovationproject.R;

import java.util.ArrayList;
import java.util.List;

public class QuestionFragment extends Fragment {

    private RecyclerView mQuestionView;
    private RecyclerView.LayoutManager mLayoutManager;
    private QuestionFragment.QuestionCardWaterFallAdapter mWaterFallAdapter;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        mQuestionView = view.findViewById(R.id.question_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mWaterFallAdapter = new QuestionCardWaterFallAdapter(getActivity(),buildData());

        mQuestionView.setLayoutManager(mLayoutManager);
        mQuestionView.setAdapter(mWaterFallAdapter);
        mQuestionView.addItemDecoration(new CardItemDecoration(32));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public List<QuestionCard> buildData(){
        int[] questionIds = {1,2,3,4,5,6,7,8,9};
        String[] questionTitles = {"帮忙取快递",
                "帮忙拿外卖",
                "帮忙取东西",
                "帮忙寄东西",
                "帮忙……",
                "帮忙……",
                "帮忙……",
                "帮忙……",
                "帮忙……"};
        String[] questionContexts = {"详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",
                "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",
                "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",
                "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",
                "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",
                "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",
                "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",
                "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",
                "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情"};
        Boolean[] isImageDisplays = {true,true,false,true,false,false,false,true,true};
        List<QuestionCard> list = new ArrayList<>();
        //todo:读取服务器中的数据并替换

        for(int i=0;i<9;i++){
            QuestionCard tk = new QuestionCard();
            tk.setQuestionId(questionIds[i]);
            tk.setQuestionTitle(questionTitles[i]);
            tk.setQuestionContext(questionContexts[i]);
            tk.setImageDisplay(isImageDisplays[i]);
            list.add(tk);
        }

        return list;
    }

    public class QuestionCardWaterFallAdapter extends RecyclerView.Adapter {

        private Context mContext;
        private List<QuestionCard> mData;

        public QuestionCardWaterFallAdapter(Context context,List<QuestionCard> data){
            mContext = context;
            mData = data;
        }

        @Override
        public QuestionFragment.QuestionCardWaterFallAdapter.QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view = LayoutInflater.from(mContext).inflate(R.layout.question_view_item, null);
            return new QuestionFragment.QuestionCardWaterFallAdapter.QuestionViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){
            QuestionFragment.QuestionCardWaterFallAdapter.QuestionViewHolder holder2 = (QuestionFragment.QuestionCardWaterFallAdapter.QuestionViewHolder)holder;
            QuestionCard questionCard = mData.get(position);
            holder2.getQuestionTitle().setText(questionCard.getQuestionTitle());
            holder2.getQuestionContext().setText(questionCard.getQuestionContext());
            holder2.getQuestionImage().setVisibility(questionCard.isImageDisplay()? View.VISIBLE : View.GONE);
            holder2.getQuestionCard().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent i = new Intent(getActivity(),QuestionActivity.class);
                    //todo:传递question id
                    //startActivity(i);
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



        public class QuestionViewHolder extends RecyclerView.ViewHolder {
            private CardView questionCard;
            private TextView questionTitle;
            private TextView questionContext;
            private ImageView questionImage;

            public QuestionViewHolder(View itemView) {
                super(itemView);
                questionCard = (CardView) itemView.findViewById(R.id.question_card);
                questionTitle = (TextView) itemView.findViewById(R.id.question_card_title);
                questionContext = (TextView) itemView.findViewById(R.id.question_card_context);
                questionImage = (ImageView) itemView.findViewById(R.id.question_card_image);
            }

            public CardView getQuestionCard() {
                return questionCard;
            }

            public void setQuestionCard(CardView questionCard) {
                this.questionCard = questionCard;
            }

            public TextView getQuestionTitle() {
                return questionTitle;
            }

            public void setQuestionTitle(TextView questionTitle) {
                this.questionTitle = questionTitle;
            }

            public TextView getQuestionContext() {
                return questionContext;
            }

            public void setQuestionContext(TextView questionContext) {
                this.questionContext = questionContext;
            }

            public ImageView getQuestionImage() {
                return questionImage;
            }

            public void setQuestionImage(ImageView questionImage) {
                this.questionImage = questionImage;
            }
        }
    }
}
