package com.example.bartek.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by bartek on 10/4/2015.
 */
public class ImageGetter {
    Resources resources;
    public void setDrawableImage(Resources res)
    {
        resources = res;
    }
    private static final String NAMESPACE = "http://service/";
    private static String URL="http://192.168.1.107/mapservice?wsdl";
    private static final String METHOD_NAME = "hello";
    private static final String SOAP_ACTION =  "http://service/mapservice/hello";

    private void x() throws IOException, XmlPullParserException {
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        PropertyInfo propInfo=new PropertyInfo();
        propInfo.name="arg0";
        propInfo.type= PropertyInfo.STRING_CLASS;
        request.addProperty(propInfo, "John Smith");

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
        envelope.setOutputSoapObject(request);
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
            resultsRequestSOAP.toString();
        }
        catch (Exception ex) {
        ex.toString();
        }
        AsyncTask<String, Void, String> result = new InternetImageGetter().execute("test");
        String hello = result.toString();
        hello.toString();
    }
    public String DownloadImage() throws IOException, XmlPullParserException {
        x();
        Bitmap selectedImage = null;// BitmapFactory.decodeFile(filePath);
       selectedImage = BitmapFactory.decodeResource(resources, R.drawable.test);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        String strBase64=Base64.encodeToString(byteArray, 0);
        return strBase64;
    }
    public Bitmap GetImage() throws IOException, XmlPullParserException {
        String strBase64 = DownloadImage();

        byte[] decodedString = Base64.decode(strBase64, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
        //image.setImageBitmap(decodedByte);
        //return null;
    }

}
