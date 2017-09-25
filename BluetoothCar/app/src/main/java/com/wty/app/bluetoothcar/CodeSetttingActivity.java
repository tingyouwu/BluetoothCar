package com.wty.app.bluetoothcar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wty.app.bluetoothcar.utils.PreferenceUtil;

import java.util.LinkedHashMap;
import java.util.Map;

public class CodeSetttingActivity extends AppCompatActivity {

    EditText et_stop,et_up,et_down,et_left,et_right;
    EditText et_leftup,et_leftdown,et_rightup,et_rightdown;
    Button bt_submit;
    LinearLayout layout_about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("自定义编码");

        et_stop = (EditText) findViewById(R.id.et_stop);
        et_up = (EditText) findViewById(R.id.et_go);
        et_left = (EditText) findViewById(R.id.et_left);
        et_right = (EditText) findViewById(R.id.et_right);
        et_down = (EditText) findViewById(R.id.et_back);
        bt_submit = (Button) findViewById(R.id.btn_submit);
        layout_about = (LinearLayout) findViewById(R.id.layout_about);

        et_leftup = (EditText) findViewById(R.id.et_leftup);
        et_leftdown = (EditText) findViewById(R.id.et_leftdown);
        et_rightup = (EditText) findViewById(R.id.et_rightup);
        et_rightdown = (EditText) findViewById(R.id.et_rightdown);

        et_down.setText(PreferenceUtil.getInstance().getDownCode());
        et_up.setText(PreferenceUtil.getInstance().getUpCode());
        et_left.setText(PreferenceUtil.getInstance().getLeftCode());
        et_right.setText(PreferenceUtil.getInstance().getRightCode());
        et_stop.setText(PreferenceUtil.getInstance().getStopCode());

        et_leftup.setText(PreferenceUtil.getInstance().getLeftUpCode());
        et_leftdown.setText(PreferenceUtil.getInstance().getLeftDownCode());
        et_rightup.setText(PreferenceUtil.getInstance().getRightUpCode());
        et_rightdown.setText(PreferenceUtil.getInstance().getRightDownCode());

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = saveCode();
                if(!TextUtils.isEmpty(result)){
                    Toast.makeText(CodeSetttingActivity.this, result, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CodeSetttingActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        layout_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CodeSetttingActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * @Decription 保存编码
     **/
    private void saveToPreference(String down,String up,String left,String right,String stop,
                                  String leftup,String leftdown,String rightup,String rightdown){
        PreferenceUtil.getInstance().writePreferences(PreferenceUtil.DOWN_CODE,down);
        PreferenceUtil.getInstance().writePreferences(PreferenceUtil.UP_CODE,up);
        PreferenceUtil.getInstance().writePreferences(PreferenceUtil.LEFT_CODE,left);
        PreferenceUtil.getInstance().writePreferences(PreferenceUtil.RIGHT_CODE,right);
        PreferenceUtil.getInstance().writePreferences(PreferenceUtil.STOP_CODE,stop);

        PreferenceUtil.getInstance().writePreferences(PreferenceUtil.LEFT_DOWN_CODE,leftdown);
        PreferenceUtil.getInstance().writePreferences(PreferenceUtil.LEFT_UP_CODE,leftup);
        PreferenceUtil.getInstance().writePreferences(PreferenceUtil.RIGHT_DOWN_CODE,rightdown);
        PreferenceUtil.getInstance().writePreferences(PreferenceUtil.RIGHT_UP_CODE,rightup);
    }

    /**
     * @Decription 判断一下编码是否符合规则
     **/
    private String saveCode(){
        //判断是否存在空值
        String down = et_down.getText().toString();
        String up = et_up.getText().toString();
        String left = et_left.getText().toString();
        String right = et_right.getText().toString();
        String stop = et_stop.getText().toString();

        String leftup = et_leftup.getText().toString();
        String leftdown = et_leftdown.getText().toString();
        String rightup = et_rightup.getText().toString();
        String rightdown = et_rightdown.getText().toString();

        if(TextUtils.isEmpty(down) || TextUtils.isEmpty(up) || TextUtils.isEmpty(left) || TextUtils.isEmpty(right) || TextUtils.isEmpty(stop)
                ||TextUtils.isEmpty(leftup) || TextUtils.isEmpty(leftdown) || TextUtils.isEmpty(rightup) || TextUtils.isEmpty(rightdown) ){
            return "编码不能为空";
        }

        Map<String,String> map = new LinkedHashMap<>();
        map.put(down,down);
        map.put(up,up);
        map.put(left,left);
        map.put(right,right);
        map.put(stop,stop);
        map.put(leftup,leftup);
        map.put(leftdown,leftdown);
        map.put(rightup,rightup);
        map.put(rightdown,rightdown);

        if(map.size()<9){
            return "编码不能相同";
        }

        saveToPreference(down,up,left,right,stop,leftup,leftdown,rightup,rightdown);

        return "";
    }

}
