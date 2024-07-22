package com.example.measurementvisualization;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    static TextView text;

    public static void setText(int num){
        text.setText("Size: " + num);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://purple-moss-0945ff003.5.azurestaticapps.net/data-api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        MeasurementsApi measurementsApi = retrofit.create(MeasurementsApi.class);
//        Call<MeasurementsResponse> call = measurementsApi.getMeasurements("TIMESTAMP gt 2024-07-20T13:46:25.103Z","TIMESTAMP");
//        call.enqueue(new Callback<MeasurementsResponse>() {
//            @Override
//            public void onResponse(Call<MeasurementsResponse> call, Response<MeasurementsResponse> response) {
//                if (!response.isSuccessful()) {
//                    Log.e(TAG, "Code: " + response.code());
//                    return;
//                }
//
//                MeasurementsResponse measurementsResponse = response.body();
//                if (measurementsResponse != null) {
//                    List<Measurement> measurements = measurementsResponse.getValue();
//                    for (Measurement measurement : measurements) {
//                        Log.d(TAG, "Timestamp: " + measurement.getTimestamp() + ", Value: " + measurement.getValue());
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MeasurementsResponse> call, Throwable t) {
//                Log.e(TAG, "Failed: " + t.getMessage());
//            }
//        });

        DataManager dataManager = new DataManager();
        dataManager.getData();
        text = findViewById(R.id.tekst);

    }
}