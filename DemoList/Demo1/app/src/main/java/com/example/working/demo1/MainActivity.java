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


public class MainActivity extends Activity {
    static public String APPIFINDEX = "appifindex";
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
                Toast.makeText(MainActivity.this,String.format("Click:ifIndex:%d",position),Toast.LENGTH_SHORT).show();
                Intent _intent = new Intent(MainActivity.this,AppListInfo._classname[position]);
                _intent.putExtra(APPIFINDEX, position);

                startActivity(_intent);

            }
        });
        _listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //长按查看详细信息
                String _info = AppListInfo._appInfo[position];
                Toast.makeText(MainActivity.this,String.format("LongClick:ifIndex:%d [%s]",position,_info),Toast.LENGTH_LONG).show();

                return false;
            }
        });

    }


}
