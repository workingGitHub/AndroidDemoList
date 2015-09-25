package com.example.working.demo1.DemoList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2015/9/19 0019.
 */
public class DrawView extends View {
    public float _x = 40;
    public float _y = 50;
    Paint _p = new Paint();
    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        _p.setColor(Color.RED);
        canvas.drawCircle(_x,_y,15,_p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        _x = event.getX();
        _y = event.getY();
        invalidate();
        return true;
    }
}
