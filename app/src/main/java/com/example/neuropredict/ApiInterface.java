package com.example.neuropredict;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST("/signup")
    Call<Void> getUserInformation(@Body User user);
    @POST("/tapping")
    Call<Void> postTappingInformation (@Body tappingItem taps);

    @GET("/tapping/{patientId}")
    Call<List<scoreItem>> getTappingInformation (@Path("patientId") Integer patientID);

}
