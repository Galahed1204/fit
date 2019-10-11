package com.galinc.hardtraining2.net;

import com.galinc.hardtraining2.db.itility.Document;
import com.galinc.hardtraining2.db.itility.Exercise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface JSONPlaceHolderApi {
//    @POST("/hardtraining/hs/ht/main/call/")
//    public Call<List<Exercise>> postData(@Body Post data);

    @POST("/hardtraining/hs/ht/main/call/")
    public Call<List<Exercise>> postData(@Body String data);

    @POST("/hardtraining/hs/ht/main/call/")
    public Call<List<Document>> postDocuments(@Body String data);

//    @POST("/hardtraining/hs/ht/main/call/")
//    public Call<List<TemplateTraining>> postTemplate(@Body String data);

}
