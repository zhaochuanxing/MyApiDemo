package com.xing.apidemo.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.xing.apidemo.MyApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by zhao on 18-3-6.
 */

public class HttpHandler {

    private static RequestQueue sRequestQueue = MyRequestQueue.getInstance(MyApplication.getApplication()).getRequestQueue();

    public static String httpGetString(String url, boolean verifyFlag, int dataType, boolean jsonVerifyType) {

        String result = null;
        RequestFuture<String> requestFuture = RequestFuture.newFuture();
        StringRequest stringRequest = new StringRequest(url,requestFuture,requestFuture){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //增加header头
                HashMap<String,String> headerMap = new HashMap<>();
                headerMap.put("Accept-Encoding","gzip");
                return headerMap;
            }
        };
        sRequestQueue.add(stringRequest);
        try {
            result = requestFuture.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }catch (Exception e){

        }
        return result;
    }

    public static String httpPostString(String url,Map<String,String> paramMap){
        return null;
    }
}
