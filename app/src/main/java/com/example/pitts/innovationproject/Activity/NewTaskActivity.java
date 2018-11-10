package com.example.pitts.innovationproject.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pitts.innovationproject.BaseActivity;
import com.example.pitts.innovationproject.Bean.Task;
import com.example.pitts.innovationproject.OverWrite.MyGlideEngine;
import com.example.pitts.innovationproject.R;
import com.example.pitts.innovationproject.Utils.CommonUtil;
import com.example.pitts.innovationproject.Utils.ImageUtils;
import com.example.pitts.innovationproject.Utils.SDCardUtil;
import com.sendtion.xrichtext.RichTextEditor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.pitts.innovationproject.Utils.TimeUtils.getCurrentTime;

public class NewTaskActivity extends BaseActivity {
    private static final int REQUEST_CODE_CHOOSE = 23;
    private FloatingActionButton fab;
    private Toolbar mToolbar;
    private AppCompatSpinner mTaskTypeSpinner;
    private RichTextEditor mTaskEdit;
    private List<String> mTaskTypes;
    private ArrayAdapter<String> mTaskTypeAdapters;
    private EditText mTaskTitle;
    private ImageView mImageButton;
    private ImageView mTimeButton;
    private ImageView mGiftButton;
    private LinearLayout mGiftEditView;
    private EditText mGiftEdit;
    private ImageButton mGiftEditCompleted;
    private TextView mGift;
    private TextView mDate;
    private TextView mTime;
    private ArrayList<Integer> mDDLDate;
    private ArrayList<Integer> mDDLTime;
    private int mScreenWidth;
    private int mScreenHeight;
    private Task mNewTask;
    private ProgressDialog insertDialog;
    private final static int MY_PERMISSIONS_REQUEST_CALL_PHONE = 7;
    private int mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        mTaskTypeSpinner = (AppCompatSpinner)findViewById(R.id.task_type);
        mTaskEdit = (RichTextEditor)findViewById(R.id.task_edit);
        mImageButton = (ImageView)findViewById(R.id.image_button);
        mTimeButton = (ImageView)findViewById(R.id.time_button);
        mGiftButton = (ImageView)findViewById(R.id.gift_button);
        mGiftEditView = (LinearLayout)findViewById(R.id.gift_edit_view);
        mGiftEdit = (EditText)findViewById(R.id.gift_edit);
        mGiftEditCompleted = (ImageButton)findViewById(R.id.gift_edit_completed);
        mGift = (TextView)findViewById(R.id.gift);
        mDate = (TextView)findViewById(R.id.date);
        mTime = (TextView)findViewById(R.id.time);
        mTaskTypes = new ArrayList<String>();
        mTaskTypes.add("类型一");
        mTaskTypes.add("类型二");
        mTaskTypes.add("类型三");
        mTaskTypes.add("类型四");
        mTaskTitle = (EditText)findViewById(R.id.task_title);
        mDDLDate = new ArrayList<Integer>();
        mDDLTime = new ArrayList<Integer>();
        mScreenWidth = CommonUtil.getScreenWidth(this);
        mScreenHeight = CommonUtil.getScreenHeight(this);
        insertDialog = new ProgressDialog(this);

        initView();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNewTask = new Task(mTaskTitle.getText().toString(),
                        getEditData(),mUserId,getCurrentTime(),Integer.parseInt(mGiftEdit.getText().toString()),
                        mDDLDate,mDDLTime,(String)mTaskTypeSpinner.getSelectedItem());
                //todo:上传到数据库
                finish();
            }
        });

        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(NewTaskActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(NewTaskActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
                } else {
                    callGallery();
                }
            }
        });

        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(NewTaskActivity.this,mDDLDate,mDDLTime,Calendar.getInstance());
            }
        });

        mGiftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGiftEditView.setVisibility(View.VISIBLE);
            }
        });

        mGiftEditCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGiftEditView.setVisibility(View.INVISIBLE);
                CommonUtil.hideSoftInput(mGiftEditView);
                mGift.setText(mGiftEdit.getText().toString()+"元");
                mGift.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callGallery();
            }
            else {
                // Permission Denied
                Toast.makeText(NewTaskActivity.this, "请开启读取相册权限", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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

    public void initView(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("新建任务");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTaskTypeAdapters = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,mTaskTypes);
        mTaskTypeAdapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTaskTypeSpinner.setAdapter(mTaskTypeAdapters);

        insertDialog.setMessage("正在插入图片...");
        insertDialog.setCanceledOnTouchOutside(false);

        mGiftEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    private void callGallery(){
//        //调用系统图库
//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");// 相片类型
//        startActivityForResult(intent, 1);

        Matisse.from(this)
                .choose(MimeType.of(MimeType.JPEG, MimeType.PNG, MimeType.GIF))//照片视频全部显示MimeType.allOf()
                .countable(true)//true:选中后显示数字;false:选中后显示对号
                .maxSelectable(3)//最大选择数量为9
                //.addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))//图片显示表格的大小
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)//图像选择和预览活动所需的方向
                .thumbnailScale(0.85f)//缩放比例
                .theme(R.style.Matisse_Zhihu)//主题  暗色主题 R.style.Matisse_Dracula
                .imageEngine(new MyGlideEngine())//图片加载方式，Glide4需要自定义实现
                .capture(true) //是否提供拍照功能，兼容7.0系统需要下面的配置
                //参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                .captureStrategy(new CaptureStrategy(true,"com.sendtion.matisse.fileprovider"))//存储到哪里
                .forResult(REQUEST_CODE_CHOOSE);//请求码
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                if (requestCode == 1){
                    //处理调用系统图库
                } else if (requestCode == REQUEST_CODE_CHOOSE){
                    //异步方式插入图片
                    insertImagesSync(data);
                }
            }
        }
    }

    /**
     * 异步方式插入图片
     * @param data
     */
    private void insertImagesSync(final Intent data){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) {
                try{
                    mTaskEdit.measure(0, 0);
                    List<Uri> mSelected = Matisse.obtainResult(data);
                    // 可以同时插入多张图片
                    for (Uri imageUri : mSelected) {
                        String imagePath = SDCardUtil.getFilePathFromUri(NewTaskActivity.this,  imageUri);
                        //Log.e(TAG, "###path=" + imagePath);
                        Bitmap bitmap = ImageUtils.getSmallBitmap(imagePath, mScreenWidth, mScreenHeight);//压缩图片
                        //bitmap = BitmapFactory.decodeFile(imagePath);
                        imagePath = SDCardUtil.saveToSdCard(bitmap);
                        //Log.e(TAG, "###imagePath="+imagePath);
                        e.onNext(imagePath);
                    }

                    // 测试插入网络图片 http://p695w3yko.bkt.clouddn.com/18-5-5/44849367.jpg
                    //subscriber.onNext("http://p695w3yko.bkt.clouddn.com/18-5-5/30271511.jpg");

                    e.onComplete();
                }catch (Exception error){
                    error.printStackTrace();
                    e.onError(error);
                }
            }
        })
                .subscribeOn(Schedulers.io())//生产事件在io
                .observeOn(AndroidSchedulers.mainThread())//消费事件在UI线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d){

                    }

                    @Override
                    public void onComplete() {
                        if (insertDialog != null && insertDialog.isShowing()) {
                            insertDialog.dismiss();
                        }
                        Toast.makeText(NewTaskActivity.this, "图片插入成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (insertDialog != null && insertDialog.isShowing()) {
                            insertDialog.dismiss();
                        }
                        Toast.makeText(NewTaskActivity.this, "图片插入失败:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(String imagePath) {
                        mTaskEdit.insertImage(imagePath, mTaskEdit.getMeasuredWidth());
                    }
                });
    }

    private String getEditData() {
        List<RichTextEditor.EditData> editList = mTaskEdit.buildEditData();
        StringBuffer content = new StringBuffer();
        for (RichTextEditor.EditData itemData : editList) {
            if (itemData.inputStr != null) {
                content.append(itemData.inputStr);
            } else if (itemData.imagePath != null) {
                content.append("<img src=\"").append(itemData.imagePath).append("\"/>");
            }
        }
        return content.toString();
    }

    public void showDatePickerDialog(final Activity activity, final ArrayList<Integer> date, final ArrayList<Integer> time, final Calendar calendar) {

        new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.clear();
                time.clear();
                date.add(year);
                date.add(monthOfYear);
                date.add(dayOfMonth);
                mDate.setText(year+"年"+monthOfYear+"月"+dayOfMonth+"日 ");
                showTimePickerDialog(activity,time,calendar);
            }
        }
                ,calendar.get(Calendar.YEAR)
                ,calendar.get(Calendar.MONTH)
                ,calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void showTimePickerDialog(Activity activity, final ArrayList<Integer> time, Calendar calendar) {
        new TimePickerDialog( activity, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time.add(hourOfDay);
                time.add(minute);
                mTime.setText(hourOfDay+":"+minute+":");
            }
        }
                , calendar.get(Calendar.HOUR_OF_DAY)
                , calendar.get(Calendar.MINUTE)
                ,true).show();
    }

}
