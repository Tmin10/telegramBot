package ru.tmin10.telegram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Type;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import ru.tmin10.telegram.telegramTypes.*;


public class Query 
{
	private String base_url = "";
	
	public <T> Result<T> run (String method, Type type)
	{
		return this.run(method, type, null);
	}
	
	public <T> Result<T> run (String method, Type type, Map<String, String> params)
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try
        {
            HttpPost httpPost = new HttpPost(this.base_url + method);
            if (params != null)
            {
            	List <NameValuePair> nvps = new ArrayList <NameValuePair>();
            	for (Map.Entry<String, String> entry : params.entrySet())
            	{
            		nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            	}
            	httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            }
//            List <NameValuePair> nvps = new ArrayList <NameValuePair>();
//            nvps.add(new BasicNameValuePair("username", "vip"));
//            nvps.add(new BasicNameValuePair("password", "secret"));
//            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response = httpclient.execute(httpPost);

            try 
            {
            	String text = EntityUtils.toString(response.getEntity());
            	System.out.println(text);
            	Gson json = new Gson();
            	Result<T> result = json.fromJson(text, type);
            	return result;
            }
            finally 
            {
                response.close();
            }
        } 
		catch (ClientProtocolException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        finally 
        {
            try 
            {
				httpclient.close();
			} 
            catch (IOException e) 
            {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return null;
	}
}
