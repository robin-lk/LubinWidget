package com.lubin.lubin.data;

import com.lubin.widget.dialog.data.CityData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author lubin
 * @version 1.0 ·2018/11/1
 */
public interface IMultilevel {
    /**
     * 城市名
     * @return
     */
    @GET("lubin/api/city")
    Call<CityData> cityData();

    @GET("lubin/api/city/{id}")
    Call<CityData> cityDataByCode(@Path("id") String id);
}
