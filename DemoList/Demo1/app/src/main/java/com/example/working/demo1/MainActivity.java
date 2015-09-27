package com.example.working.demo1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
//废弃掉了，使用ListPerfenceActivity
/*
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取ListView组件信息
        ListView _listview = (ListView)findViewById(R.id.DemoList);
        ArrayAdapter<String> _adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 ,AppListInfo._applist);
        _listview.setAdapter(_adapter);

        _listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, String.format("Click:ifIndex:%d", position), Toast.LENGTH_SHORT).show();
                if (position < AppListInfo._classname.length) {
                    Intent _intent = new Intent(MainActivity.this, AppListInfo._classname[position]);
                    startActivity(_intent);
                }

            }
        });
        _listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //长按查看详细信息
                if(position < AppListInfo._appInfo.length) {
                    String _info = AppListInfo._appInfo[position];
                    Toast.makeText(MainActivity.this, String.format("%s", _info), Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });

    }


}
*/
