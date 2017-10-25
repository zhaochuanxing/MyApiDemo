package com.xing.apidemo.webview;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.logging.MemoryHandler;

/**
 * Created by zhao on 17-10-25.
 */

public class JsBridge implements IBridge {

    private static HashMap<String,HashMap<String,Method>> exposedMethodMap = new HashMap<>();

    public static void register(String exposedName,Class<? extends IBridge> clazz){
        if(!exposedMethodMap.containsKey(exposedName)){
            exposedMethodMap.put(exposedName,getAllMethodName(clazz));
        }

    }

    private static HashMap<String, Method> getAllMethodName(Class<? extends IBridge> clazz) {
        HashMap<String,Method> methodMap = new HashMap<>();
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method :methods){
            String name = method.getName();
            if(method.getModifiers()!=(Modifier.PUBLIC|Modifier.STATIC)){
                continue;
            }
            Class[] parameters = method.getParameterTypes();
            if(parameters!=null && parameters.length==3){
                if(parameters[0]== WebView.class && parameters[1]== JSONObject.class&& parameters[2]== WebViewCallBack.class){
                    methodMap.put(name,method );
                }
            }
        }
        return methodMap;
    }

    /**
     *
     * @param webView
     * @param uriString
     */
    public static void callJava(WebView webView,String uriString){
        if(!TextUtils.isEmpty(uriString) && uriString.startsWith("JSBridge")){
            Uri uri = Uri.parse(uriString);
            String className = uri.getHost();
            String param = uri.getQuery();
            String port = String.valueOf(uri.getPort());
            String path = uri.getPath();
            String methodName = "";
            if(!TextUtils.isEmpty(path)){
                methodName = path.replace("/","");
            }

            if(exposedMethodMap.containsKey(className)){
                HashMap<String, Method> methodHashMap = exposedMethodMap.get(className);
                if(methodHashMap!=null && methodHashMap.size()>0 && methodHashMap.containsKey(methodName)){
                    Method method = methodHashMap.get(methodName);
                    if(method!=null){
                        method.setAccessible(true);
                        try {
                            method.invoke(null,webView,new JSONObject(param),new WebViewCallBack(webView,port));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
