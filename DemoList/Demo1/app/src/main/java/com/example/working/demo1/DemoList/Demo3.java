package com.example.working.demo1.DemoList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/9/19 0019.
 */
public class Demo3 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout _linearlayout = new LinearLayout(this);
        setContentView(_linearlayout);
        //设置布局方向
        _linearlayout.setOrientation(LinearLayout.VERTICAL);
        final TextView _textview = new TextView(this);
        _textview.setText("HelloWorld!!!   Coder.");
        _linearlayout.addView(_textview);
        final DrawView _dv = new DrawView(this);
        _linearlayout.addView(_dv);
    }
}
