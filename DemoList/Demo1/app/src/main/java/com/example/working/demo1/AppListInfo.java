package com.example.working.demo1;

import android.widget.SimpleAdapter;

import com.example.working.demo1.DemoList.Demo1;

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
            "2.Hello Word",
    };
    static public String[] _appInfo = new String[]{
            "1.Hello Word",
            "2.Hello Word",
    };
    static public Class[] _classname = new Class[]{
            Demo1.class,
            Demo1.class
    };

}
