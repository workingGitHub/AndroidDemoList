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
            "1.��һ��ʵ�����򵥵�HelloWorld���档ʹ��TextView�ؼ�",
            "2.ʹ�ô������UI�Ĳ��֣�P44��",
            "3.ʹ���Զ���ؼ�����һ��������ָ��С��P48��",
            "4.��ʾ���ֲ��ֵ����"
    };
    static public Class[] _classname = new Class[]{
            Demo1.class,
            Demo2.class,
            Demo3.class,
            Demo4.class
    };

}
