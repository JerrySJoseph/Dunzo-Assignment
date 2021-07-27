package com.jstechnologies.flickerapi.CustomAPIs.Flicker;

import java.util.List;

public class FlickerResponse {

    public Photos photos;
    public String stat;


    public class Photo {
        public String id;
        public String owner;
        public String secret;
        public String server;
        public String title;
        public String ispublic;
        public String isfriend;
        public String isfamily;
    }

    protected class Photos {
        public List<Photo> photo;
        public String page;
        public String pages;
        public String perpage;
        public String total;
    }
}
