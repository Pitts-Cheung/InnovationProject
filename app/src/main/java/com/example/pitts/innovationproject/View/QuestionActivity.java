package com.example.pitts.innovationproject.View;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pitts.innovationproject.BaseActivity;
import com.example.pitts.innovationproject.Bean.AnswerCard;
import com.example.pitts.innovationproject.OverWrite.CardItemDecoration;
import com.example.pitts.innovationproject.R;
import com.example.pitts.innovationproject.Utils.CommonUtil;
import com.sendtion.xrichtext.RichTextView;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends BaseActivity {
    private Toolbar mToolbar;
    private FloatingActionButton fab;
    private RecyclerView mAnswerList;
    private RichTextView mQuestionContext;
    private ProgressDialog loadingDialog;
    private RecyclerView.LayoutManager mLayoutManager;
    private AnswerCardWaterFallAdapter mWaterFallAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        mAnswerList = (RecyclerView) findViewById(R.id.answer_list);
        mQuestionContext = (RichTextView) findViewById(R.id.question_context);
        loadingDialog = new ProgressDialog(this);
        mLayoutManager = new LinearLayoutManager(this);
        mWaterFallAdapter = new AnswerCardWaterFallAdapter(this,buildData());

        initView();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuestionActivity.this,NewAnswerActivity.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling,menu);
        return true;
    }

    public void initView(){
        //todo:接收传进来的answer id
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("问题详情");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*loadingDialog.setMessage("数据加载中...");
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.show();*/
        //todo:从数据库中调取数据并显示

        mAnswerList.setLayoutManager(mLayoutManager);
        mAnswerList.setAdapter(mWaterFallAdapter);
        mAnswerList.addItemDecoration(new CardItemDecoration(0,32,0,0));
    }

    public List<AnswerCard> buildData(){
        //todo:从数据库中调取回答数据
        int[] answerIds = {1,2,3,4,5,6,7,8,9};
        String[] answerContexts = {"详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",
                "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",
                "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",
                "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",
                "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",
                "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",
                "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",
                "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情",
                "详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情详情"};
        String[] answerTimes = {"2018/10/24",
                "2018/10/16",
                "2018/10/14",
                "2018/10/10",
                "2018/10/06",
                "2018/10/03",
                "2018/09/27",
                "2018/09/26",
                "2018/09/22"};
        Boolean[] isImageDisplays = {true,true,false,true,false,false,false,true,true};
        ArrayList<AnswerCard> answerCards = new ArrayList<AnswerCard>();

        for(int i=0;i<9;i++){
            AnswerCard ak = new AnswerCard();
            ak.setAnswerId(answerIds[i]);
            ak.setAnswerContext(answerContexts[i]);
            ak.setAnswerTime(answerTimes[i]);
            ak.setImageDisplay(isImageDisplays[i]);
            answerCards.add(ak);
        }

        return answerCards;
    }

    public class AnswerCardWaterFallAdapter extends RecyclerView.Adapter {

        private Context mContext;
        private List<AnswerCard> mData;

        public AnswerCardWaterFallAdapter(Context context,List<AnswerCard> data){
            mContext = context;
            mData = data;
        }

        @Override
        public AnswerCardWaterFallAdapter.AnswerViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view = LayoutInflater.from(mContext).inflate(R.layout.answer_view_item, null);
            return new AnswerCardWaterFallAdapter.AnswerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){
            AnswerCardWaterFallAdapter.AnswerViewHolder holder2 = (AnswerCardWaterFallAdapter.AnswerViewHolder)holder;
            AnswerCard answerCard = mData.get(position);
            holder2.getAnswerCard().setMinimumWidth(CommonUtil.getScreenWidth(QuestionActivity.this));
            holder2.getAnswerContext().setText(answerCard.getAnswerContext());
            holder2.getAnswerTime().setText(answerCard.getAnswerTime());
            holder2.getAnswerImage().setVisibility(answerCard.isImageDisplay()? View.VISIBLE : View.GONE);
            holder2.getAnswerCard().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(QuestionActivity.this,AnswerActivity.class);
                    //todo:传递answer id
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

        public class AnswerViewHolder extends RecyclerView.ViewHolder {
            private CardView answerCard;
            private ImageView answerUserIcon;
            private TextView answerUserName;
            private TextView answerContext;
            private TextView answerTime;
            private ImageView answerImage;
            private TextView answerAgreement;
            private TextView answerComment;

            public AnswerViewHolder(View itemView) {
                super(itemView);
                answerCard = (CardView) itemView.findViewById(R.id.answer_card);
                answerUserIcon = (ImageView) itemView.findViewById(R.id.answer_card_usericon);
                answerUserName = (TextView)itemView.findViewById(R.id.answer_card_username);
                answerContext = (TextView) itemView.findViewById(R.id.answer_card_context);
                answerTime = (TextView) itemView.findViewById(R.id.answer_card_time);
                answerImage = (ImageView) itemView.findViewById(R.id.answer_card_image);
                answerAgreement = (TextView)itemView.findViewById(R.id.answer_card_agreement);
                answerComment = (TextView)itemView.findViewById(R.id.answer_card_comment);
            }

            public CardView getAnswerCard() {
                return answerCard;
            }

            public void setAnswerCard(CardView answerCard) {
                this.answerCard = answerCard;
            }

            public TextView getAnswerContext() {
                return answerContext;
            }

            public void setAnswerContext(TextView answerContext) {
                this.answerContext = answerContext;
            }

            public TextView getAnswerTime() {
                return answerTime;
            }

            public void setAnswerTime(TextView answerTime) {
                this.answerTime = answerTime;
            }

            public ImageView getAnswerImage() {
                return answerImage;
            }

            public void setAnswerImage(ImageView answerImage) {
                this.answerImage = answerImage;
            }

            public ImageView getAnswerUserIcon() {
                return answerUserIcon;
            }

            public void setAnswerUserIcon(ImageView answerUserIcon) {
                this.answerUserIcon = answerUserIcon;
            }

            public TextView getAnswerUserName() {
                return answerUserName;
            }

            public void setAnswerUserName(TextView answerUserName) {
                this.answerUserName = answerUserName;
            }

            public TextView getAnswerAgreement() {
                return answerAgreement;
            }

            public void setAnswerAgreement(TextView answerAgreement) {
                this.answerAgreement = answerAgreement;
            }

            public TextView getAnswerComment() {
                return answerComment;
            }

            public void setAnswerComment(TextView answerComment) {
                this.answerComment = answerComment;
            }
        }
    }
}
