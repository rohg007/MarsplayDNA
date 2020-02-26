package com.rohg007.android.marsplaydna.network;

import com.rohg007.android.marsplaydna.models.ApiResponse;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocRepository {

    private static DocRepository docRepository;
    private DocAPI docAPI;

    public static DocRepository getInstance(){
        if(docRepository==null)
            docRepository = new DocRepository();

        return docRepository;
    }

    private DocRepository(){
        docAPI = RetrofitService.createService(DocAPI.class);
    }

    public MutableLiveData<ApiResponse> getDocs(){
        final MutableLiveData<ApiResponse> responseData = new MutableLiveData<>();
        docAPI.getDocList().enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful())
                    responseData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                responseData.setValue(null);
            }
        });
        return responseData;
    }

}
