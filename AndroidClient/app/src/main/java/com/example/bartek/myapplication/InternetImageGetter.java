package com.example.bartek.myapplication;

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
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by bartek on 11/12/2015.
 */
public class InternetImageGetter extends AsyncTask<String, Void, String> {

    String imageBase64 = "";
    private Exception exception;
    private static final String NAMESPACE = "namespaceMapService";
    private static String URL="http://192.168.1.107/MapService?wsdl";
    private static final String METHOD_NAME = "getImage";
    private static final String SOAP_ACTION =  "getImage";
    protected String doInBackground(String... urls) {

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        Point p = new Point();
        p.x = 0;
        p.y = 0;




            PropertyInfo propInfo=new PropertyInfo();
            propInfo.name="topLeft";
            propInfo.type= p.getClass();
            propInfo.setValue(p);
            request.addProperty(propInfo);

        Point p2 = new Point();
        p2.x = 100;
        p2.y = 100;
        PropertyInfo propInfo2=new PropertyInfo();
        propInfo2.name="bottomRight";
        propInfo2.type= p.getClass();
        propInfo2.setValue(p2);
        request.addProperty(propInfo2);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            try {
                androidHttpTransport.call(SOAP_ACTION, envelope);
                SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
                imageBase64  = resultsRequestSOAP.toString();
            }
            catch (XmlPullParserException ex)
            {
                String message = ex.getDetail().toString();
                message.toString();
            }
            catch (Exception ex) {
                ex.toString();

            }

            return imageBase64 ;

    }

    protected void onPostExecute(String feed) {
        // TODO: check this.exception
        // TODO: do something with the feed
        try {
            setImage(getImage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    public Bitmap getImage() throws IOException, XmlPullParserException {
        String image = imageBase64;
        imageBase64 = "";
        byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }
    public void setImage(Bitmap bmp)
    {
    }
}
