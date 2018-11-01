package com.lubin.lubin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lubin.lubin.data.IMultilevel;
import com.lubin.lubin.view.InputBoxActivity;
import com.lubin.lubin.view.TabbarActivity;
import com.lubin.widget.dialog.MultiLevelBottomDialog;
import com.lubin.widget.dialog.OnMultilevelCallBack;
import com.lubin.widget.dialog.data.CityData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private IMultilevel iMultilevel;
    private MultiLevelBottomDialog multiLevelBottomDialog;

    private List<CityData.DateBean> beans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initNet();
        beans = new ArrayList<>();
        findViewById(R.id.btn_box).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InputBoxActivity.class));
            }
        });
        findViewById(R.id.btn_tabbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TabbarActivity.class));
            }
        });
        findViewById(R.id.btn_multiLevel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multiLevelBottomDialog.show(getSupportFragmentManager(), "AAG");
                setNet("",MultiLevelBottomDialog.TYPE_PROVINCE);
            }
        });


    }

    private void setNet(String cityCodes, final int nextCity) {
        if (nextCity == MultiLevelBottomDialog.TYPE_PROVINCE) {
            iMultilevel.cityData().enqueue(new Callback<CityData>() {
                @Override
                public void onResponse(Call<CityData> call, Response<CityData> response) {
                    assert response.body() != null;
                    beans.clear();
                    beans.addAll(response.body().getDate());
                    multiLevelBottomDialog.loadData(beans, MultiLevelBottomDialog.TYPE_PROVINCE);
                    Log.e("TAG",response.body().toString());

                }

                @Override
                public void onFailure(Call<CityData> call, Throwable t) {
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("TAG",t.getMessage());
                }
            });
        } else {
            iMultilevel.cityDataByCode(cityCodes).enqueue(new Callback<CityData>() {
                @Override
                public void onResponse(Call<CityData> call, Response<CityData> response) {
                    assert response.body() != null;
                    beans.clear();
                    beans.addAll(response.body().getDate());
                    multiLevelBottomDialog.loadData(beans, nextCity);
                    Log.e("TAG",response.body().toString());
                }

                @Override
                public void onFailure(Call<CityData> call, Throwable t) {
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("TAG",t.getMessage());
                }
            });
        }

    }

    private void initNet() {
        Retrofit retrofit = new Retrofit.Builder()
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                //设置网络请求的Url地址
                .baseUrl("http://gaosq.top:8080/")
                .build();
        iMultilevel = retrofit.create(IMultilevel.class);
        multiLevelBottomDialog = MultiLevelBottomDialog.newInstance().initDialogHeight(1200).initTopTitle("Add address");
        multiLevelBottomDialog.setListener(new OnMultilevelCallBack() {
            @Override
            public void onSelectedCallBack(String cityName, String cityCode, int nextCityType) {
                setNet(cityCode,nextCityType);
            }

            @Override
            public void onFullSelectedCallBack(String provinceName, String cityName, String countyName, String subdistrictOffice, String cityCode) {
                Toast.makeText(MainActivity.this, provinceName + cityName + countyName + subdistrictOffice + " * " + cityCode, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
