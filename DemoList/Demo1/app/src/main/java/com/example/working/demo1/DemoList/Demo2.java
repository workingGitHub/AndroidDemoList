package com.example.working.demo1.DemoList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.working.demo1.R;

/**
 * Created by Administrator on 2015/9/19 0019.
 * ��ʹ��XML����ȫʹ�ô���ʵ�ֲ���
 */
public class Demo2 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout _linearlayout = new LinearLayout(this);
        setContentView(_linearlayout);
        //���ò��ַ���
        _linearlayout.setOrientation(LinearLayout.VERTICAL);
        final TextView _textview = new TextView(this);
        _textview.setText("HelloWorld!!!   Coder.");
        _linearlayout.addView(_textview);
        //setContentView(R.layout.demo1_layout);
        // int ifindex = savedInstanceState.getInt(MainActivity.APPIFINDEX);

    }
}
