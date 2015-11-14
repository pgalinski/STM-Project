package com.example.bartek.myapplication;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by bartek on 10/18/2015.
 */
public class MyImageView extends ImageView{

    float _leftx=0;
    float _topy=0;
    float _rightx=0;
    float _bottomy=0;

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }
    public MyImageView(Context context) {
        super(context);

    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (_leftx > 0 && _rightx > 0 && _bottomy > 0 && _topy > 0) {
            Paint paint = new Paint();
            paint.setColor(Color.GRAY);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            canvas.drawRect(_leftx, _topy, _rightx, _bottomy, paint);
        }
    }

    public void SetRectangleSize(float leftx,
            float topy,
            float rightx,
            float bottomy)
    {
        _leftx = leftx;
        _topy = topy;
        _rightx = rightx;
        _bottomy = bottomy;
    }
}
