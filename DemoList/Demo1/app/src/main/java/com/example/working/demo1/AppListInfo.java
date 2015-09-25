package com.example.working.demo1;

import android.widget.SimpleAdapter;

import com.example.working.demo1.DemoList.Demo1;
import com.example.working.demo1.DemoList.Demo2;
import com.example.working.demo1.DemoList.Demo3;
import com.example.working.demo1.DemoList.Demo4;

import java.util.Map;

/**
 * Created by Administrator on 2015/9/15 0015.
 */
/*
class AppData{
    String[] _name;
    String[] _info;
    Class _class;
    AppData(String[] name,String[] info ,Class myClass)
    {
        _name = name;
        _info = info;
        _class = myClass;
    }
}*/

public class AppListInfo {
    static public String[] _applist = new String[]{
            "1.Hello Word",
            "2.Coder Hello Word",
            "3.SelfView",
            "4.layout"
    };
    static public String[] _appInfo = new String[]{
            "1.第一个实例，简单的HelloWorld界面。使用TextView控件",
            "2.使用代码完成UI的布局（P44）",
            "3.使用自定义控件，画一个跟随手指的小球（P48）",
            "4.显示各种布局的情况"
    };
    static public Class[] _classname = new Class[]{
            Demo1.class,
            Demo2.class,
            Demo3.class,
            Demo4.class
    };

}
