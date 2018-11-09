package com.example.pitts.innovationproject.View;

import android.app.Notification;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.pitts.innovationproject.BaseActivity;
import com.example.pitts.innovationproject.R;

public class SettingActivity extends BaseActivity {

    private Toolbar mToolbar;
    private Switch mVoiceSwitch;
    private Switch mVibrationSwitch;
    private Switch mNewSwitch;
    private Switch mTaskSwitch;
    private Switch mAnswerSwitch;
    private Switch mGroupSwitch;
    private Switch mReceiverSwitch;
    private Switch mImageSwitch;
    private Switch mVideoSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mVoiceSwitch = (Switch)findViewById(R.id.voice_switch);
        mVibrationSwitch = (Switch)findViewById(R.id.vibration_switch);
        mNewSwitch = (Switch)findViewById(R.id.new_switch);
        mTaskSwitch = (Switch)findViewById(R.id.task_switch);
        mAnswerSwitch = (Switch)findViewById(R.id.answer_switch);
        mGroupSwitch = (Switch)findViewById(R.id.group_switch);
        mReceiverSwitch = (Switch)findViewById(R.id.receiver_switch);
        mImageSwitch = (Switch)findViewById(R.id.image_switch);
        mVideoSwitch = (Switch)findViewById(R.id.video_switch);

        initView();

        mVoiceSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                builder.setSound(null);
            }
        });

        mVibrationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                builder.setVibrate(null);
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

    public void initView(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("设置");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
