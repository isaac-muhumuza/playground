package io.sample.playground.client;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TempClient {

    @GET("/executions/get/")
    Call<ResponseBody> getResponseObject(@Query("id") String id);
}
