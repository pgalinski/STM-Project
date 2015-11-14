package com.example.bartek.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
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
    ProgressBarFragment progressBarFragment;
    FragmentManager fm = getSupportFragmentManager();
    private int CalculateNewPoint(int end, int neww, int pointStart, int pointEnd)
    {
        float x1 = (float)neww/end;
        int x2 = pointEnd-pointStart;
        int result = Math.round(x1*x2);
        result = result + pointStart;
        return result;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Map Service");
        setContentView(R.layout.activity_fullscreen);


        Button showPopUpButton = (Button) findViewById(R.id.showPopUp);

        showPopUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LatLonFragment latLonFragment = new LatLonFragment()
                {
                    @Override
                    public void OnOk(LatLon topLeft, LatLon bottomRight) {

                           createImageGetter();
                           imageGetter.execute(new Object[]{topLeft, bottomRight});

                    }
                };
                // Show DialogFragment
                latLonFragment.show(fm, "Dialog Fragment");
            }
        });
        iv = (MyImageView) findViewById(R.id.imageView);
        downloadImageButton = (Button) findViewById(R.id.downloadImageButton);
        downloadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    int maxX = iv.getWidth();
                    int maxY = iv.getHeight();
                    if (maxX > 0 && maxY > 0 && endX > 0 && endY > 0) {
                        iStartX = CalculateNewPoint(maxX, Math.round(startX), actualStartX, actualEndX);
                        iEndX = CalculateNewPoint(maxX, Math.round(endX), actualStartX, actualEndX);
                        iStartY = CalculateNewPoint(maxY, Math.round(startY), actualStartY, actualEndY);
                        iEndY = CalculateNewPoint(maxY, Math.round(endY), actualStartY, actualEndY);
                    }

                       createImageGetter();

                       if ((iEndX - iStartX) > 0 && (iEndY - iStartY) > 0) {
                           imageGetter.execute(new Object[]{iStartX, iStartY, iEndX, iEndY});
                       } else {
                           imageGetter.execute();
                       }

                }
                catch (Exception e)
                {
                    e.toString();
                }
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
    private void createImageGetter(){
        progressBarFragment = new ProgressBarFragment();
        progressBarFragment.show(fm,"Pobieranie...");
        imageGetter = new InternetImageGetter() {
            @Override
            public void setImage(Bitmap _bmp, Point topLeft, Point bottomRight) {
                bmp = _bmp;
                iv.SetRectangleSize(0, 0, 0, 0);
                iv.setImageBitmap(_bmp);
                actualStartX = topLeft.x;
                actualStartY = topLeft.y;
                actualEndX = bottomRight.x;
                actualEndY = bottomRight.y;

                startX = 0;
                endX = 0;
                startY = 0;
                endY = 0;
                dismissProgressBar();
            }
        };
    }

    private void dismissProgressBar(){
        if(progressBarFragment != null)
        {
            progressBarFragment.dismiss();
            progressBarFragment = null;
        }
    }
    boolean isOnClick;
    Bitmap bmp = null;
    float startX;
    float startY;
    float endX;
    float endY;

    int iStartX;
    int iStartY;
    int iEndX;
    int iEndY;

    int actualStartX;
    int actualStartY;
    int actualEndX;
    int actualEndY;

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
