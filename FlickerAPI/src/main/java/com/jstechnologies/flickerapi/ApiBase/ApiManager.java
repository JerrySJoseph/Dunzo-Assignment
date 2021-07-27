package com.jstechnologies.flickerapi.ApiBase;

import android.content.Context;

import androidx.annotation.NonNull;

public class ApiManager {

    private static ApiManager mInstance;
    private static Context mContext;
    private ApiRequestQueue apiRequestQueue;

    public static void initialize(Context context){
        mContext=context;
    }

    public static synchronized ApiManager getInstance() {
        if(mContext==null)
            throw new ApiNotInitilizedException();
        if(mInstance==null)
            mInstance=new ApiManager();
        return mInstance;
    }

    public ApiManager() {
        this.apiRequestQueue = ApiRequestQueue.getInstance(mContext);
    }

    public  static <T extends ApiRequest> T run(@NonNull T request){
        if(request!=null)
            ApiManager.getInstance().apiRequestQueue.add(request);
        return request;
    }


}
