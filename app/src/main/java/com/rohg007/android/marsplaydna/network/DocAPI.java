package com.rohg007.android.marsplaydna.network;

import com.rohg007.android.marsplaydna.models.ApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DocAPI {
    @GET("search?q=title:DNA")
    Call<ApiResponse> getDocList();
}
