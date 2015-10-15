package com.example.working.pokergame;

import android.app.ActionBar;
import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Administrator on 2015/10/1 0001.
 */
public class AdapterViewFlipperActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poker_flipper_show);
        final AdapterViewFlipper _avf = (AdapterViewFlipper)findViewById(R.id.flipper_view);
        BaseAdapter _ba = new BaseAdapter() {
            @Override
            public int getCount() {
                //返回数组长度
                return PokerGame.m_pokerImages.length;
            }

            @Override
            public Object getItem(int position) {
                return PokerGame.m_pokerImages[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView _iv = new ImageView(AdapterViewFlipperActivity.this);
                _iv.setImageResource(PokerGame.m_pokerImages[position]);
                _iv.setScaleType(ImageView.ScaleType.FIT_XY);
                _iv.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return _iv;
            }
        };
        _avf.setAdapter(_ba);
        final Button _pre = (Button)findViewById(R.id.flipper_pre);
        final Button _next = (Button)findViewById(R.id.flipper_next);
        final Button _auto = (Button)findViewById(R.id.flipper_auto);


        _pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _avf.showPrevious();
                if(_auto.getText().equals("停止播放")) {
                    _auto.setText("开始播放");
                    _avf.stopFlipping();
                }
            }
        });
        _next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _avf.showNext();
                if(_auto.getText().equals("停止播放")) {
                    _auto.setText("开始播放");
                    _avf.stopFlipping();
                }
            }
        });
        _auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(_auto.getText().equals("停止播放")) {
                    _auto.setText("开始播放");
                    _avf.stopFlipping();
                }else
                {
                    _auto.setText("停止播放");
                    _avf.startFlipping();
                }
            }
        });
    }
}
