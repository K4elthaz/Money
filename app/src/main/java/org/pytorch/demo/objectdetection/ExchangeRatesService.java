package org.pytorch.demo.objectdetection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ExchangeRatesService {
    @GET("latest")
    Call<ExchangeRatesResponse> getLatestRates(@Query("apikey") String apiKey);
}
