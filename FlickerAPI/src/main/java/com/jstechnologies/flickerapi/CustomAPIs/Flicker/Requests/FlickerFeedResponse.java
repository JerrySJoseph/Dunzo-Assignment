package com.jstechnologies.flickerapi.CustomAPIs.Flicker.Requests;

import java.util.Date;
import java.util.List;

public class FlickerFeedResponse {

    public String title;
    public String link;
    public String description;
    public Date modified;
    public String generator;
    public List<Item> items;

    public class Media{
        public String m;
    }

    public class Item{
        public String title;
        public String link;
        public Media media;
        public Date date_taken;
        public String description;
        public Date published;
        public String author;
        public String author_id;
        public String tags;
    }
}
