package com.example.neuropredict;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("/signup")
    Call<Void> getUserInformation(@Body User user);
    @POST("/tapping")
    Call<Void> postTappingInformation (@Body tappingItem taps);

    @GET("/tapping")
    Call<tapping> getTappingInformation (@Body User user);

}
