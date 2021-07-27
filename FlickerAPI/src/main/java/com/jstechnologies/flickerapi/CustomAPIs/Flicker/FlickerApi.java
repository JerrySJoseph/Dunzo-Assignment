package com.jstechnologies.flickerapi.CustomAPIs.Flicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jstechnologies.flickerapi.ApiBase.ApiCallback;
import com.jstechnologies.flickerapi.ApiBase.ApiRequest;
import com.jstechnologies.flickerapi.CustomAPIs.Flicker.Requests.FlickerFeedResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FlickerApi {

    //Request Parameters and constants
    public static String FLICKER_API_KEY="062a6c0c49e4de1d78497d13a7dbb360";
    public static String FLICKER_API_METHOD="flickr.photos.search";
    public static String FLICKER_API_FORMAT="json";
    public static String FLICKER_API_NO_CALLBACK="1";
    public static String FLICKER_API_URL="https://api.flickr.com/services/rest/";
    public static String FLICKER_API_TAG="phone";


    //Fetch Request
    public static class FetchRequest extends ApiRequest {

        int currPage=0;

        protected FetchRequest(FetchRequest.Builder builder) {
            super(builder);
        }

        public void setPage(int page){
            this.currPage=page;
            execute();
        }

        public void next(){
            setPage(++currPage);
        }

        @Nullable
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> params=new HashMap<>();
            params.put("method",FLICKER_API_METHOD);
            params.put("api_key",FLICKER_API_KEY);
            params.put("format",FLICKER_API_FORMAT);
            params.put("nojsoncallback",FLICKER_API_NO_CALLBACK);

            return params;
        }

        public static class Builder extends ApiRequest.Builder{

            ApiCallback<FlickerResponse.Photo> callback;
            String searchTag="flickr.photos.getRecent";

            public Builder(){
            }

            @Override
            protected int getMethod() {
                return Method.GET;
            }

            @Override
            protected String getUrl() {
                String rootURL=FLICKER_API_URL;
                rootURL+="?method="+FLICKER_API_METHOD
                        +"&api_key="+FLICKER_API_KEY
                        +"&format="+FLICKER_API_FORMAT
                        +"&nojsoncallback="+FLICKER_API_NO_CALLBACK
                        +"&tags="+this.searchTag;
                return rootURL;
            }

            public Builder setSearchTag(String searchTag) {
                this.searchTag = searchTag;
                return this;
            }

            @Override
            public void onSuccess(String response) {

                if(callback!=null){
                    try {
                        FlickerResponse flickerResponse= new Gson().fromJson(response,FlickerResponse.class);
                        callback.onFetchSuccess(flickerResponse.photos.photo);
                    }catch (Exception e){
                        callback.onFetchError(e.getMessage());
                    }

                }
            }

            @Override
            public void onFailure(String reason) {
                if(callback!=null)
                    callback.onFetchError(reason);
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params=new HashMap<>();
                params.put("method",FLICKER_API_METHOD);
                params.put("api_key",FLICKER_API_KEY);
                params.put("format",FLICKER_API_FORMAT);
                params.put("nojsoncallback",FLICKER_API_NO_CALLBACK);

                return params;
            }

            @Override
            public FetchRequest build() {
                return new FetchRequest(this);
            }

            public FetchRequest.Builder addOnCompleteListener(ApiCallback<FlickerResponse.Photo> callback){
                this.callback=callback;
                return this;
            }

        }
    }

    //Feed Request
    public static class FeedRequest extends ApiRequest{

        public FeedRequest(Builder builder) {
            super(builder);
        }

        public static class Builder extends ApiRequest.Builder{

            ApiCallback<FlickerFeedResponse.Item> callback;
            String format="json";

            @NonNull
            @Override
            protected int getMethod() {
                return Method.GET;
            }

            @Nullable
            @Override
            protected Map<String, String> getParams() {
                return null;
            }

            @NonNull
            @Override
            protected String getUrl() {
                return "https://www.flickr.com/services/feeds/photos_public.gne?format="+this.format+"&nojsoncallback=1";
            }

            @Override
            public FeedRequest build() {
                return new FeedRequest(this);
            }

            @Override
            public void onSuccess(String response) {
                if(callback!=null){
                    try {
                        FlickerFeedResponse flickerResponse= new Gson().fromJson(response,FlickerFeedResponse.class);
                        callback.onFetchSuccess(flickerResponse.items);
                    }catch (Exception e){
                        callback.onFetchError(e.getMessage());
                    }

                }
            }

            @Override
            public void onFailure(String reason) {
                if(callback!=null)
                    callback.onFetchError(reason);
            }

            public FeedRequest.Builder addOnCompleteListener(ApiCallback<FlickerFeedResponse.Item> callback){
                this.callback=callback;
                return this;
            }
        }
    }

    public static String getFullImage(FlickerResponse.Photo photo){
        return String.format("https://live.staticflickr.com/%s/%s_%s.jpg",photo.server,photo.id,photo.secret);
    }
    public static String getThumbnail(FlickerResponse.Photo photo){
        return String.format("https://live.staticflickr.com/%s/%s_%s_%s.jpg",photo.server,photo.id,photo.secret,"q");
    }
    public static String getFullImage(FlickerFeedResponse.Item photo){
        return photo.media.m;
    }
    public static String getThumbnail(FlickerFeedResponse.Item photo){
        return getFullImage(photo);
    }

}
