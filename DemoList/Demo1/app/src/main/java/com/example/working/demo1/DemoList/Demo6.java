package com.example.working.demo1.DemoList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.working.demo1.R;

/**
 * Created by Administrator on 2015/9/29 0029.
 */
public class Demo6 extends Activity {
    private int alpha = 255;
    int curentImg = 2;
    int[] images = new int[]{
            R.drawable.p1,
            R.drawable.p2,
            R.drawable.p3,
            R.drawable.p4,
            R.drawable.p5,
            R.drawable.p6,
            R.drawable.p7,
            R.drawable.p8,
            R.drawable.p9
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo6_layout);
        final Button AddAlpha = (Button)findViewById(R.id.demo6_btn1);
        final Button DelAlpha = (Button)findViewById(R.id.demo6_btn2);
        final Button next = (Button)findViewById(R.id.demo6_btn3);
        final ImageView _p1 = (ImageView)findViewById(R.id.demo6_image1);
        final ImageView _p2 = (ImageView)findViewById(R.id.demo6_image2);

        AddAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alpha += 20;
                if(alpha >= 255)
                {
                    alpha = 255;
                }
                _p1.setImageAlpha(alpha);
            }
        });
        DelAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alpha -= 20;
                if(alpha <= 0)
                {
                    alpha = 0;
                }
                _p1.setImageAlpha(alpha);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _p1.setImageResource(images[(curentImg++) % images.length]);
            }
        });
        _p1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //获取图片的Bitmap
                BitmapDrawable _bitmapdrawable = (BitmapDrawable)_p1.getDrawable();
                Bitmap _bitmap = _bitmapdrawable.getBitmap();
                //图片实际大小的缩放比例
                double scale = 1.0 * _bitmap.getHeight()/_p1.getHeight();
                int x = (int) (event.getX() * scale);
                int y = (int) (event.getY()*scale);
                //边界处理
                if(x + 120 > _bitmap.getWidth())
                {
                    x = _bitmap.getWidth() - 120;
                }
                if(y + 120 > _bitmap.getHeight())
                {
                    y = _bitmap.getHeight() - 120;
                }
                _p2.setImageBitmap(Bitmap.createBitmap(_bitmap, x, y, 120,120));
                _p2.setImageAlpha(alpha);
                return false;
            }
        });
    }
}
