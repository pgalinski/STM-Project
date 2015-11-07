package com.example.bartek.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by bartek on 10/4/2015.
 */
public class ImageGetter {
    Resources resources;
    public void setDrawableImage(Resources res)
    {
        resources = res;
    }


    public String DownloadImage(){
    //    String filePath;
  //      filePath= Environment.getExternalStorageDirectory()
//                + "/SaudiScore/temporary_holder.jpg";

        Bitmap selectedImage = null;// BitmapFactory.decodeFile(filePath);
       selectedImage = BitmapFactory.decodeResource(resources, R.drawable.test);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        String strBase64=Base64.encodeToString(byteArray, 0);
        return strBase64;
    }
    public Bitmap GetImage(){
        String strBase64 = DownloadImage();

        byte[] decodedString = Base64.decode(strBase64, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
        //image.setImageBitmap(decodedByte);
        //return null;
    }

}
