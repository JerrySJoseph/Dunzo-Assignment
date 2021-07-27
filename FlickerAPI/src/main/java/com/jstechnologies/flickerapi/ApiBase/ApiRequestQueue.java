package com.jstechnologies.flickerapi.ApiBase;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class ApiRequestQueue {

    private static ApiRequestQueue mInstance;
    private Context context;
    private RequestQueue mRequestQueue;

    public static synchronized ApiRequestQueue getInstance(Context context){
        if(mInstance==null)
            mInstance= new ApiRequestQueue(context);
        return mInstance;
    }

    private ApiRequestQueue(Context context){
        this.context=context;
        this.mRequestQueue=getRequestQueue();
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue==null)
            mRequestQueue= Volley.newRequestQueue(context.getApplicationContext());
        return mRequestQueue;
    }
    public void add(ApiRequest request){
        mRequestQueue.add(request);
    }
}
