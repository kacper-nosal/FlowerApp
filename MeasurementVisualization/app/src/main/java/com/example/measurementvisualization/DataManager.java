package com.example.measurementvisualization;

import static android.content.ContentValues.TAG;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataManager {
    public List<Measurement> measurements;
    private Retrofit retrofit;
    private String nextLink = null;
    private MeasurementsApi measurementsApi;
    private String filter = "TIMESTAMP gt 2024-07-20T16:15:25.103Z";
    private String orderby = "TIMESTAMP";

    DataManager(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://purple-moss-0945ff003.5.azurestaticapps.net/data-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        measurementsApi = retrofit.create(MeasurementsApi.class);
    }

    public void getData() {


        Call<MeasurementsResponse> call = measurementsApi.getMeasurements(filter,orderby);
        call.enqueue(new Callback<MeasurementsResponse>() {
            @Override
            public void onResponse(Call<MeasurementsResponse> call, Response<MeasurementsResponse> response) {
                if (!response.isSuccessful()) {
                    Log.e(TAG, "Code: " + response.code());
                    return;
                }

                MeasurementsResponse measurementsResponse = response.body();
                if (response.isSuccessful()) {
                    nextLink = measurementsResponse.getNextLink();
                    measurements = measurementsResponse.getValue();
                    if(nextLink != null){
                        getDeeperData();
                    }
                    Log.d(TAG, "Measurements fetched!!!!!!!!!!!!!!!!!!!");
                }
            }

            @Override
            public void onFailure(Call<MeasurementsResponse> call, Throwable t) {
                Log.e(TAG, "Failed: " + t.getMessage());
            }
        });
    }

    private void getDeeperData(){

            Call<MeasurementsResponse> call = measurementsApi.getMeasurements(nextLink,filter,orderby);
            call.enqueue(new Callback<MeasurementsResponse>() {
                @Override
                public void onResponse(Call<MeasurementsResponse> call, Response<MeasurementsResponse> response) {
                    MeasurementsResponse measurementsResponse = response.body();
                    if (response.isSuccessful()) {
                        nextLink = null;
                        nextLink = measurementsResponse.getNextLink();
                        nextLink = getStringAfterPhrase(nextLink);
                        measurements.addAll(measurementsResponse.getValue());
                        if(nextLink != null){
                            getDeeperData();
                        }else{
                            MainActivity.setText(measurements.size());
                        }
                        Log.d(TAG, "Measurements fetched!!!!!!!!!!!!!!!!!!!!!!!");
                    }else{
                        Log.d(TAG, nextLink);
                    }
                }

                @Override
                public void onFailure(Call<MeasurementsResponse> call, Throwable t) {
                    Log.e(TAG, "Failed: " + t.getMessage());
                }
            });

    }

    public static String getStringAfterPhrase(String input) {
        String phrase = "$after=";
        int index = input.indexOf(phrase);

        if (index != -1) {
            // Return the substring starting right after the phrase
            return input.substring(index + phrase.length());
        } else {
            // Phrase not found
            return null;
        }
    }
}
