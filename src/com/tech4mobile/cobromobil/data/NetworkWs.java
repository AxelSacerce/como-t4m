package com.tech4mobile.cobromobil.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

//import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class NetworkWs {
	
	/*
	 *  Inician Métodos que se enviarán a WS para capturar, insertar y enviar información 
	 */
	
	// Obtener listado de contratos
	
	public static String ListadoPendientes()
	{
		String result="";
		ArrayList<NameValuePair> params;
		params = null;
				
		result = GETData("http://dev-wagadelta.c9.io/api/cobros",params);
		return result;
	}
	
	
	
	
	
	

	/*
	 *  Terminan Métodos que se enviarán a WS para capturar, insertar y enviar información 
	 */
	
	
	
	/*  Inician verbos HTTP utilizados por los métdos  */
	
	
	/* Verbo Post HTTP para envío de datos */
	private static String POSTData(String url, ArrayList<NameValuePair> params) 
	{
		String datos="";
		String linea;
		HttpContext mHttpContext = new BasicHttpContext();
		DefaultHttpClient mHttpClient = new DefaultHttpClient();
		HttpPost mHttpPost = null;
		mHttpPost = new HttpPost(url);
		try {
			if (params!= null) {
				
				mHttpPost.setEntity(new UrlEncodedFormEntity(params));
			}
			BasicHttpResponse response = (BasicHttpResponse) mHttpClient.execute(mHttpPost,mHttpContext);
			InputStream is = response.getEntity().getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while((linea = br.readLine())!=null) {
				datos += linea;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datos;
	}
	
	/* Verbo PUT HTTP para Inserción de datos */
	private static String PUTData(String url, ArrayList<NameValuePair> params) 
	{
		String datos="";
		String linea;
		HttpContext mHttpContext = new BasicHttpContext();
		DefaultHttpClient mHttpClient = new DefaultHttpClient();
		HttpPut mHttpPut = null;
		mHttpPut = new HttpPut(url);
		try {
			if (params!= null) {
				
				mHttpPut.setEntity(new UrlEncodedFormEntity(params));
			}
			BasicHttpResponse response = (BasicHttpResponse) mHttpClient.execute(mHttpPut,mHttpContext);
			InputStream is = response.getEntity().getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while((linea = br.readLine())!=null) {
				datos += linea;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datos;
	}
	
	/*  Verbo GET HTTP para solicitud de datos */
	private static String GETData(String url, ArrayList<NameValuePair> params) {
		String datos="";
		String linea;
		HttpContext mHttpContext = new BasicHttpContext();
		DefaultHttpClient mHttpClient = new DefaultHttpClient();
		HttpGet mHttpGet = null;
		if (params!= null) {
			
			mHttpGet = new HttpGet(url+"&"+ URLEncodedUtils.format(params, "utf-8"));
			
		}else{
			mHttpGet = new HttpGet(url);
		}
		try {
			BasicHttpResponse response = (BasicHttpResponse) mHttpClient.execute(mHttpGet,mHttpContext);
			InputStream is = response.getEntity().getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while((linea = br.readLine())!=null) {
				datos += linea;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datos;
	}

}
