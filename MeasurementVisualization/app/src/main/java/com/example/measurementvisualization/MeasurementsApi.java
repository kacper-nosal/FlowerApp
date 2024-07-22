package com.example.measurementvisualization;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface MeasurementsApi {
    @GET("api/Measurements")
    Call<MeasurementsResponse> getMeasurements(
            @Query("$filter") String filter,
            @Query("$orderby") String orderBy
    );

    @GET("api/Measurements")
    Call<MeasurementsResponse> getMeasurements(
            @Header("$after") String after,
            @Query("$filter") String filter,
            @Query("$orderby") String orderBy
    );
}

