package com.example.working.pokergame;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2015/9/30 0030.
 */
public class ListViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poker_list_show);
        List<Map<String,Object>> _listmap = new ArrayList<Map<String,Object>>();
        Log.d("pokerShow",String.format("Num:[%d]",PokerGame.m_pokerImages.length));
        for(int i = 0 ;i < PokerGame.m_pokerImages.length ; i++)
        {
            Map<String,Object> _item = new HashMap<String, Object>();
            _item.put("Image",PokerGame.m_pokerImages[i]);
            _item.put("Name",PokerGame.m_pokerNames[i]);
            Log.d("pokerShow",String.format("Name:[%d][%s]",i, PokerGame.m_pokerNames[i]));
            _listmap.add(_item);
        }
        SimpleAdapter _sa = new SimpleAdapter(this,_listmap,
                R.layout.poker_item,
                new String[]{"Image","Name"},
                new int[]{R.id.list_item_image,R.id.list_item_text});
        ListView list = (ListView)findViewById(R.id.pokerlistview);
        list.setAdapter(_sa);
    }
}
