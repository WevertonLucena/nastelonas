package br.com.lucenasistemas.nastelonas.util;

import android.content.ContentValues;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Map;

/**
 * Created by Weverton on 05/11/2015.
 */
public class CallWebService {
    public static final String LOG_TAG = CallWebService.class.getSimpleName();
    private static final String USER_AGENT = "Mozilla/5.0";

    public static String sendGet(ContentValues values,String strUrl) {

        if (values.size() != 0) {
            StringBuilder sb =  new StringBuilder();
            sb.append(strUrl);
            sb.append("?");
            int countParams = 0;
            for (Map.Entry<String, Object> entry : values.valueSet()) {
                String key = Uri.encode(entry.getKey()); // name
                String value = Uri.encode(entry.getValue().toString()); // value
                if (countParams == 0)
                    sb.append(String.format("%s=%s",key,value));
                else
                    sb.append(String.format("&%s=%s",key,value));
                countParams++;
            }
            strUrl = sb.toString();
        }
        try {
            URL url = new URL(strUrl);
            Log.i("App", "GET URL: " + strUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setReadTimeout(5000);
            con.setConnectTimeout(5000);
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK){
                return  null;
            }
            InputStream in = new BufferedInputStream(con.getInputStream());

            String result  = readStream(in);
            in.close();
            if (result != null) {
                Log.i(LOG_TAG, "GET Retorno do webservice: " + result);
            }
            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (ProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (SocketTimeoutException e){
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String sendPost(ContentValues values,String strUrl) throws IllegalArgumentException{
        try {
            URL url = new URL(strUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            String params = "";

            if (values.size() != 0) {
                Uri.Builder builder = new Uri.Builder();
                for (Map.Entry<String, Object> entry : values.valueSet()) {
                    String key = entry.getKey(); // name
                    String value = entry.getValue().toString(); // value
                    builder.appendQueryParameter(key, value);
                }
                params = builder.build().getEncodedQuery();
            }else{
                throw  new MalformedURLException("Nenhum valor enviado");
            }
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(params);
            writer.flush();
            writer.close();
            os.close();
            connection.connect();
            InputStream in = new BufferedInputStream(connection.getInputStream());
            String result  = readStream(in);
            in.close();
            if (result != null) {
                Log.i("App", "POST Retorno do webservice: " + result);
            }
            return result;

        }catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (ProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String readStream(InputStream in) throws IOException{
        byte[] buffer = new byte[1024];
        int bytesLidos;
        StringBuilder sb = new StringBuilder();
        while ((bytesLidos = in.read(buffer) )> -1){
            sb.append(new String(buffer,0,bytesLidos));
        }
        return sb.toString();
    }
}
