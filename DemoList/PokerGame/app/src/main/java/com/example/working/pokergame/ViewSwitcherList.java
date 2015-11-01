package com.example.working.pokergame;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/10/16 0016.
 */
public class ViewSwitcherList extends Activity {
    //定义个常量，用于显示每瓶的应用程序数
    public static final int NUMBER_FER_SCREEN = 12;
    public static class DataItem
    {
        public String dataName;
        public Drawable drawable;
    }
    private int screeNo = 1;
    private ArrayList<DataItem> items = new ArrayList<DataItem>();
    private  int screenCount;
    ViewSwitcher _vs;
    LayoutInflater _li;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewswitcherlist_layout);
        Button _prev = (Button)findViewById(R.id.prev);
        Button _next = (Button)findViewById(R.id.next);
        _vs = (ViewSwitcher)findViewById(R.id.viewswitcher);
        _li = LayoutInflater.from(ViewSwitcherList.this);
        _prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prev(null);
            }
        });
        _next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(null);
            }
        });
        for(int i = 0 ; i < PokerGame.m_pokerImages.length; i++)
        {
            String _dataname = "" + i;
            Drawable _drawable = getResources().getDrawable(PokerGame.m_pokerImages[i]);
            DataItem _di = new DataItem();
            _di.dataName = _dataname;
            _di.drawable = _drawable;
            items.add(_di);
        }
        screenCount = ((items.size()%NUMBER_FER_SCREEN) == 0)?
                items.size()/NUMBER_FER_SCREEN:(items.size()/NUMBER_FER_SCREEN + 1);
        _vs.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                return _li.inflate(R.layout.gridview,null);
            }
        });

        next(null);
    }
    public void next(View v)
    {
        if(screeNo < screenCount - 1)
        {
            screeNo++;
            _vs.setInAnimation(this,R.anim.in_right);
            _vs.setOutAnimation(this, R.anim.out_left);
            ((GridView)_vs.getNextView()).setAdapter(_ba);
            _vs.showNext();
        }
    }
    public void prev(View v)
    {
        if(screeNo > 0)
        {
            screeNo--;
            _vs.setInAnimation(this, R.anim.in_left);
            _vs.setOutAnimation(this, R.anim.out_right);
            ((GridView)_vs.getNextView()).setAdapter(_ba);
            _vs.showPrevious();
        }
    }
    private BaseAdapter _ba = new BaseAdapter() {
        @Override
        public int getCount() {
            if((screeNo == screenCount - 1)
                &&(items.size()%NUMBER_FER_SCREEN != 0))
            {
                   return items.size()%NUMBER_FER_SCREEN;
            }
            return NUMBER_FER_SCREEN;
        }

        @Override
        public DataItem getItem(int position) {
            return items.get(screeNo*NUMBER_FER_SCREEN + position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View _v = convertView;
            if(convertView == null)
            {
                _v = _li.inflate(R.layout.item,null);
            }

            ImageView imageView = (ImageView)_v.findViewById(R.id.listimage);
            TextView textview = (TextView)_v.findViewById(R.id.listtext);
            imageView.setImageDrawable(getItem(position).drawable);
            textview.setText(getItem(position).dataName);

            return _v;
        }
    };
}
