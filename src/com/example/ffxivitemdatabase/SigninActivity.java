package com.example.ffxivitemdatabase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class SigninActivity  extends AsyncTask<String,Void,String>{
    private String queryType;
    private Context context;
    private TextView access;
    //private int byGetOrPost = 0; 

    //flag 0 means get and 1 means post.(By default it is get.)
    public SigninActivity(Context context, String query) {
        this.context = context;
        this.queryType = query;
    }

    protected void onPreExecute(){

    }

    @Override
    protected String doInBackground(String... arg0) {
        if(queryType.equals("Get")){ //means by Get Method

            try{
                String firstName = (String)arg0[0];
                String lastName = (String)arg0[1];
                String link = "http://jplanh.ddns.net:8080/FFXIV.php";
                ArrayList<NameValuePair> phpInfo = new ArrayList<NameValuePair>();
                phpInfo.add(new BasicNameValuePair("First_name", firstName));
                phpInfo.add(new BasicNameValuePair("Last_name", lastName));
                phpInfo.add(new BasicNameValuePair("action", "Get"));
                
                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                String paramString = URLEncodedUtils.format(phpInfo, "utf-8");
                link += "?" + paramString;
                Log.i("database Test", firstName + " " + lastName);
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                HttpEntity entity = response.getEntity();
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String line="";

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                in.close();
                return sb.toString();
            }

            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }else if (queryType.equals("sendList")){
            try{
                String firstName = (String)arg0[0];
                String lastName = (String)arg0[1];
                String itemName = (String)arg0[2];
                String link = "http://jplanh.ddns.net:8080/FFXIV.php";
                ArrayList<NameValuePair> phpInfo = new ArrayList<NameValuePair>();
                phpInfo.add(new BasicNameValuePair("First_name", firstName));
                phpInfo.add(new BasicNameValuePair("Last_name", lastName));
                phpInfo.add(new BasicNameValuePair("Item_name", itemName));
                phpInfo.add(new BasicNameValuePair("action", "sendList"));
                
                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                String paramString = URLEncodedUtils.format(phpInfo, "utf-8");
                link += "?" + paramString;
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                HttpEntity entity = response.getEntity();
                
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                Log.i("link", link);

                StringBuffer sb = new StringBuffer("");
                String line="";

                while ((line = in.readLine()) != null) {
                    Log.i("display", line);
                    sb.append(line);
                    break;
                }
                in.close();
                return sb.toString();
                
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }else if (queryType.equals("getList")){
            try{
            String link = "http://jplanh.ddns.net:8080/FFXIV.php";
            ArrayList<NameValuePair> phpInfo = new ArrayList<NameValuePair>();
            phpInfo.add(new BasicNameValuePair("action", "getList"));
            
            URL url = new URL(link);
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            String paramString = URLEncodedUtils.format(phpInfo, "utf-8");
            link += "?" + paramString;
            request.setURI(new URI(link));
          HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line="";

            while ((line = in.readLine()) != null) {
                Log.i("display", line);
                sb.append(line);
                break;
            }
            in.close();
            return sb.toString(); 
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }
        else{
            try{
                String username = (String)arg0[0];
                String password = (String)arg0[1];

                String link="http://jplanh.ddns.net:8080/test.php";
                String data  = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection(); 

                conn.setDoOutput(true); 
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream()); 

                wr.write( data ); 
                wr.flush(); 

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while((line = reader.readLine()) != null)
                {
                    sb.append(line);
                    break;
                }
                return sb.toString();
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }
    }

    @Override
    protected void onPostExecute(String getResult){
        if(queryType.equals("Get")){
            Intent getIntent = new Intent(context, MainActivity.class);
            getIntent.putExtra("String", getResult);
            
            
        } else {
            Log.i("database Test", "Result got 2");
        Log.i("database Test", getResult);
    
        }
    }
    
    public interface AsyncClass {
        void processFinish(String[] output);
   }
}
