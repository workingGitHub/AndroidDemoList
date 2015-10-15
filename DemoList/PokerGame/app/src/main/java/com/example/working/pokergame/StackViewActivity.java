package com.example.working.pokergame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.StackView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/1 0001.
 */
public class StackViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poker_stack_show);
        List<Map<String, Object>> _listmap = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < PokerGame.m_pokerImages.length; i++) {
            Map<String, Object> _item = new HashMap<String, Object>();
            _item.put("Image", PokerGame.m_pokerImages[i]);
            _listmap.add(_item);
        }
        SimpleAdapter _sa = new SimpleAdapter(this, _listmap,
                R.layout.poker_item,
                new String[]{"Image"},
                new int[]{R.id.list_item_image});
        final StackView _sv = (StackView)findViewById(R.id.stack_view);
        _sv.setAdapter(_sa);
        Button _up = (Button)findViewById(R.id.stack_up);
        Button _down = (Button)findViewById(R.id.stack_down);
        _up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _sv.showPrevious();
            }
        });
        _down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _sv.showNext();
            }
        });
    }
}
