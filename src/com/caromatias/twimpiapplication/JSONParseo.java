package com.caromatias.twimpiapplication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class JSONParseo {
     static InputStream is=null;
     static JSONObject jobj=null;
     static String textoj;
     
     public JSONParseo(){
    	 
    	 
     }
	 public JSONObject recibir(String URL,String Metodo,ArrayList parametros){
		 ArrayList nameValuePairs;
		 try{
			if (Metodo=="get"){
				//listado
				DefaultHttpClient httpClient=new DefaultHttpClient();
				String parametroString=URLEncodedUtils.format(parametros, "utf-8");
				URL+="?"+parametroString;
				HttpGet httpget=new HttpGet(URL);
				
				HttpResponse httpresponse=httpClient.execute(httpget);
				HttpEntity httpentity=httpresponse.getEntity();
				is=httpentity.getContent();
			} else if(Metodo=="post"){
				//grabar
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(URL);
				nameValuePairs = new ArrayList();

				if (parametros != null) {
					for (int i = 0; i < parametros.size() - 1; i += 2) {
						nameValuePairs.add(new BasicNameValuePair((String) parametros.get(i), (String) parametros.get(i + 1)));
					}
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				}
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				is = entity.getContent();
				
			}
		 
		 }catch(Exception error){
			// Toast.makeText(getAplicationContext(), "Error:"+error.getMessage(),Toast.LENGTH_LONG).show();
			 Log.e("Buffer Error", "Error converting resultado " + error.toString());
		 }
		 
		 try{
			 BufferedReader br=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			 StringBuilder sb=new StringBuilder();
			 String Linea=null;
			 while((Linea=br.readLine())!=null){
				 sb.append(Linea+"\n");
			 }
			 is.close();
			 jobj=new JSONObject(sb.toString());
			 
		 }catch(Exception error){
			 Log.e("Buffer Error", "Error converting result " + error.toString());
		 }
		 
		return jobj;
		 
		 
	 }
	private Context getAplicationContext() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
