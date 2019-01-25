package com.example.congduy.landmonitoring.network;

import com.example.congduy.landmonitoring.models.DataNode;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface AppService {
    @GET("getdata.php")
    Observable<List<DataNode>> getAllData();
}
