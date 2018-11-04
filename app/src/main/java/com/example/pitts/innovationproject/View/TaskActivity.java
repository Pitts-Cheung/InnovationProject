package com.example.pitts.innovationproject.View;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.pitts.innovationproject.R;
import com.example.pitts.innovationproject.Utils.StringUtils;
import com.sendtion.xrichtext.RichTextView;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TaskActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private FloatingActionButton mFab;
    private RichTextView mTaskContext;
    private Subscription subsLoading;
    private ProgressDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mTaskContext = (RichTextView) findViewById(R.id.task_context);
        loadingDialog = new ProgressDialog(this);

        initView();

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo:领取任务
            }
        });
    }

    public void initView(){
        //todo:接收传进来的task id
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("任务详情");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadingDialog.setMessage("数据加载中...");
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.show();
        //todo:从数据库中调取数据并显示
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling,menu);
        return true;
    }

    /**
     * 异步方式显示数据
     * @param html
     */
    private void showDataSync(final String html){

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) {
                showEditData(e, html);
            }
        })
                .subscribeOn(Schedulers.io())//生产事件在io
                .observeOn(AndroidSchedulers.mainThread())//消费事件在UI线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable e){

                    }

                    @Override
                    public void onComplete() {
                        if (loadingDialog != null){
                            loadingDialog.dismiss();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (loadingDialog != null){
                            loadingDialog.dismiss();
                        }
                        Toast.makeText(TaskActivity.this, "解析错误：图片不存在或已损坏", Toast.LENGTH_SHORT).show();
                        Log.e("TaskImage", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(String text) {
                        if (text.contains("<img") && text.contains("src=")) {
                            //imagePath可能是本地路径，也可能是网络地址
                            String imagePath = StringUtils.getImgSrc(text);
                            mTaskContext.addImageViewAtIndex(mTaskContext.getLastIndex(), imagePath);
                        } else {
                            mTaskContext.addTextViewAtIndex(mTaskContext.getLastIndex(), text);
                        }
                    }
                });

    }

    /**
     * 显示数据
     * @param html
     */
    private void showEditData(ObservableEmitter<? super String> e, String html) {
        try {
            List<String> textList = StringUtils.cutStringByImgTag(html);
            for (int i = 0; i < textList.size(); i++) {
                String text = textList.get(i);
                e.onNext(text);
            }
            e.onComplete();
        } catch (Exception error){
            error.printStackTrace();
            e.onError(error);
        }
    }
}
