package com.example.bartek.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    MyImageView iv;
    Button downloadImageButton;
    InternetImageGetter imageGetter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
imageGetter = new InternetImageGetter() {
    @Override
    public void setImage(Bitmap _bmp) {
        bmp = _bmp;
        iv.SetRectangleSize(0, 0, 0, 0);
        iv.setImageBitmap(_bmp);
    }
};
        setContentView(R.layout.activity_fullscreen);

        iv = (MyImageView) findViewById(R.id.imageView);
        downloadImageButton = (Button) findViewById(R.id.downloadImageButton);
        downloadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageGetter.execute("test");
            }
        });

        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        isOnClick = true;
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        if (isOnClick) {
                            endX = event.getX();
                            endY = event.getY();
                            isOnClick = false;
                            redraw();
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (isOnClick) {
                            endX = event.getX();
                            endY = event.getY();

                            redraw();
                        }
                        break;

                    default:
                        break;
                }

                return true;

            }
        });
    }

    boolean isOnClick;
    Bitmap bmp = null;
    float startX;
    float startY;
    float endX;
    float endY;

    private void redraw() {
        if (iv != null && bmp != null) {
            float leftX = 0;
            float topY = 0;
            float rightX = 0;
            float bottomY = 0;
            if (startX < endX) {
                leftX = startX;
                rightX = endX;
            } else {
                rightX = startX;
                leftX = endX;
            }

            if (startY < endY) {
                topY = startY;
                bottomY = endY;
            } else {

                topY = endY;
                bottomY = startY;
            }

            iv.SetRectangleSize(leftX, topY, rightX, bottomY);
            iv.setImageBitmap(bmp);
        }
    }
}
