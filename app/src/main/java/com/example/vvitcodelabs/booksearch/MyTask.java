package com.example.vvitcodelabs.booksearch;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyTask extends AsyncTask<String,Void,String> {
    Context ct;
    TextView myTv;
    ProgressDialog pd;
    public MyTask(MainActivity mainActivity, TextView tv) {
        ct=mainActivity;
        myTv=tv;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        pd=new ProgressDialog(ct);
        pd.setMessage("Please wait..........");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        String myBookName=strings[0];
        String url="https://www.googleapis.com/books/v1/volumes?q="+myBookName;
        try {
            URL u=new URL(url);
            HttpURLConnection connection= (HttpURLConnection) u.openConnection();
            InputStream is=connection.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(is));
            StringBuilder builder=new StringBuilder();
            String line="";
            while ((line=reader.readLine())!=null){
                builder.append(line);
            }
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(String s){
        super.onPostExecute(s);
        //Toast.makeText(ct,s,Toast.LENGTH_SHORT).show();
        pd.dismiss();
        try {
            JSONObject root=new JSONObject(s);
            JSONArray itemsArray=root.getJSONArray("items");
            JSONObject indexObject=itemsArray.getJSONObject(0);
            JSONObject volumeInfoJsonObject=indexObject.getJSONObject("volumeInfo");
            String bookTitle=volumeInfoJsonObject.getString("title");
            String authors=volumeInfoJsonObject.getString("authors");
            myTv.setText("Book Title  "+bookTitle+"\nAuthors  S"+authors);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
