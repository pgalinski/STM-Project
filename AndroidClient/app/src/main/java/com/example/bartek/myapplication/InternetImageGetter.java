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
public class InternetImageGetter extends AsyncTask<Object, Void, MapResponse> {

    private Exception exception;
    private static final String NAMESPACE = "namespaceMapService";
    //private static String URL="http://192.168.1.107/MapService?wsdl";
    private static String URL="http://192.168.43.114/MapService?wsdl";
    private static final String getImage = "getImage";
    private static final String getImageByLatLon =  "getImageByLatLon";
    private static final String getWholeImage =  "getWholeImage";

    protected MapResponse doInBackground(Object... urls) {
        String methodName;
        MapResponse mapResponse = new MapResponse();
        if(urls.length == 4)
            methodName = getImage;
        else if(urls.length == 2)
           methodName = getImageByLatLon;
        else
            methodName = getWholeImage;

        SoapObject request = new SoapObject(NAMESPACE, methodName);
        if(urls.length == 4) {
            addPointToRequest(urls[0], urls[1], request, "topLeft");
            addPointToRequest(urls[2], urls[3], request, "bottomRight");
        }
        else if (urls.length ==2)
        {
            addLatLonToRequest(urls[0], request,"topLeft");
            addLatLonToRequest(urls[1], request,"bottomRight");
        }


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.addMapping(NAMESPACE, "mapResponse", MapResponse.class);
        MarshalDouble md = new MarshalDouble();
        md.register(envelope);
        envelope.setOutputSoapObject(request);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            try {
                androidHttpTransport.call(methodName, envelope);
                SoapObject resultsRequestSOAP = (SoapObject) envelope.getResponse();

                mapResponse.base64Map = resultsRequestSOAP.getProperty("base64Map").toString();
                mapResponse.topLeft = new Point();
                SoapObject  topLeft = (SoapObject)resultsRequestSOAP.getProperty("topLeft");
                mapResponse.topLeft.setProperty(0, topLeft.getProperty("x"));
                mapResponse.topLeft.setProperty(1, topLeft.getProperty("y"));


                mapResponse.bottomRight = new Point();

                SoapObject  bottomRight = (SoapObject)resultsRequestSOAP.getProperty("bottomRight");
                mapResponse.bottomRight .setProperty(0, bottomRight.getProperty("x"));
                mapResponse.bottomRight .setProperty(1, bottomRight.getProperty("y"));

            }
            catch (XmlPullParserException ex)
            {
                String message = ex.getDetail().toString();
                message.toString();
            }
            catch (Exception ex) {
                ex.toString();
            }

            return mapResponse;

    }

    private void addLatLonToRequest(Object latLon, SoapObject request, String propertyName) {
        PropertyInfo propInfo = new PropertyInfo();
        propInfo.name = propertyName;
        propInfo.type = latLon.getClass();
        propInfo.setValue(latLon);
        request.addProperty(propInfo);
    }

    private void addPointToRequest(Object x, Object y, SoapObject request, String propertyName){
    Point p = new Point();
    p.x = (int)x;
    p.y = (int)y;
    PropertyInfo propInfo = new PropertyInfo();
    propInfo.name = propertyName;
    propInfo.type = p.getClass();
    propInfo.setValue(p);
    request.addProperty(propInfo);

}
    protected void onPostExecute(MapResponse mapResponse) {
        // TODO: check this.exception
        // TODO: do something with the feed
        try {
            setImage(getImage(mapResponse.base64Map),mapResponse.topLeft, mapResponse.bottomRight);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    public Bitmap getImage(String image) throws IOException, XmlPullParserException {
        byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }
    public void setImage(Bitmap bmp, Point topLeft, Point bottomRight)
    {
    }
}
