package com.jstechnologies.dunzo_assignmentjerinsebastian;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.gesture.GestureLibraries;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jstechnologies.flickerapi.ApiBase.ApiCallback;
import com.jstechnologies.flickerapi.ApiBase.ApiManager;
import com.jstechnologies.flickerapi.CustomAPIs.Flicker.FlickerApi;
import com.jstechnologies.flickerapi.CustomAPIs.Flicker.FlickerResponse;
import com.jstechnologies.flickerapi.CustomAPIs.Flicker.Requests.FlickerFeedResponse;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    FlickerApi.FetchRequest fetchRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.image);

        ApiManager.initialize(this);

        fetchRequest= new FlickerApi.FetchRequest.Builder()
                .addOnCompleteListener(new ApiCallback<FlickerResponse.Photo>() {
                    @Override
                    public void onFetchSuccess(List<FlickerResponse.Photo> models) {
                        String link=FlickerApi.getThumbnail(models.get(0));
                        Glide.with(imageView).load(link).into(imageView);
                    }

                    @Override
                    public void onFetchError(String errorMessage) {

                    }
                })
                .setSearchTag("phone")
                .build();

        fetchRequest.execute();
    }

    private void startSlideShow(FlickerResponse.Photo photo){
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },3000);
    }
}