package com.jstechnologies.flickerapi.ApiBase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public abstract class ApiRequest extends StringRequest {

    Builder builder;

    protected ApiRequest(Builder builder){
        super(builder.getMethod(), builder.getUrl(), builder.getListener(), builder.getErrorListener());
        this.builder=builder;
    }

    public  void execute(){
        ApiManager.getInstance().run(this);
    }


    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        if(builder!=null)
            return builder.getParams();
        Map<String,String> map= new HashMap<>();
        map.put("Some","some");
        return map;
    }

    public static abstract class Builder{

        Response.Listener<String> listener;
        Response.ErrorListener errorListener;

        public Builder(){
             listener=new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    onSuccess(response);
                }
            };
            errorListener=new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    onFailure(error.getMessage());
                }
            };
        }

        @NonNull
        protected abstract int getMethod();

        @Nullable
        protected abstract Map<String,String> getParams();

        @NonNull
        protected abstract String getUrl();

        public abstract <T extends ApiRequest> T build();

        public abstract void onSuccess(String response);
        public abstract void onFailure(String reason);

        public Response.Listener<String> getListener() {
            return listener;
        }

        public Response.ErrorListener getErrorListener() {
            return errorListener;
        }
    }
}
