package com.rohg007.android.marsplaydna.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiResponse {
    @SerializedName("response")
    @Expose
    private DNAResponse response;

    public DNAResponse getResponse() {
        return response;
    }

    public void setResponse(DNAResponse response) {
        this.response = response;
    }
}
