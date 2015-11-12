package com.example.bartek.myapplication;

import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParserException;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by bartek on 11/12/2015.
 */
public class InternetImageGetter extends AsyncTask<String, Void, String> {

    private Exception exception;
    private static final String NAMESPACE = "namespaceMapService";
    private static String URL="http://192.168.1.107/MapService?wsdl";
    private static final String METHOD_NAME = "hello";
    private static final String SOAP_ACTION =  "hello";
    protected String doInBackground(String... urls) {
        try {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
//            PropertyInfo propInfo=new PropertyInfo();
//            propInfo.name="arg0";
//            propInfo.type= PropertyInfo.STRING_CLASS;
//            propInfo.setValue("test");
//            request.addProperty(propInfo);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            try {
                androidHttpTransport.call(SOAP_ACTION, envelope);
                SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
                return resultsRequestSOAP.toString();
            }
            catch (XmlPullParserException ex)
            {
                String message = ex.getDetail().toString();
                message.toString();
            }
            catch (Exception ex) {
                ex.toString();

            }

            return "";
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
    }

    protected void onPostExecute(String feed) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }
}
