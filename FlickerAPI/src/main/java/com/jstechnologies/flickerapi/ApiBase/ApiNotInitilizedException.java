package com.jstechnologies.flickerapi.ApiBase;

public class ApiNotInitilizedException extends NullPointerException {
    public ApiNotInitilizedException() {
        super("You have not initialized the API instance with API Config. Make sure you have initialized the FlickerAPI in Application class with API Config Object");
    }

}
