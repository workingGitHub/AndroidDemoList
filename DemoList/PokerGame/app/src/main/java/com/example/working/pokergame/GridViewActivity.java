package com.example.working.pokergame;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/1 0001.
 */
public class GridViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poker_grid_show);
        List<Map<String, Object>> _listmap = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 4; i++) {
            Map<String, Object> _item = new HashMap<String, Object>();
            _item.put("Image", PokerGame.m_pokerImages[i]);
            _listmap.add(_item);
        }
        SimpleAdapter _sa = new SimpleAdapter(this, _listmap,
                R.layout.poker_item,
                new String[]{"Image"},
                new int[]{R.id.list_item_image});

        GridView _gridview = (GridView) findViewById(R.id.gridview);
        _gridview.setAdapter(_sa);
        final ImageView _imageview = (ImageView)findViewById(R.id.grid_imageview);
        _gridview.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                _imageview.setImageResource(PokerGame.m_pokerImages[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        _gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                _imageview.setImageResource(PokerGame.m_pokerImages[position]);
            }
        });
    }
}
