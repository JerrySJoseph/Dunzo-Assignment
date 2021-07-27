package com.jstechnologies.flickerapi.ApiBase;

import java.util.List;

public interface ApiCallback<T> {
    void onFetchSuccess(List<T> models);
    void onFetchError(String errorMessage);
}
