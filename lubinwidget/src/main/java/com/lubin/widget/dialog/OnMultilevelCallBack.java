package com.lubin.widget.dialog;

/**
 * @author lubin
 * @version 1.0 ·2018/10/31
 */
public interface OnMultilevelCallBack {
    void onSelectdCallBack(String cityName, int cityCode);

    void onFullSelectdCallBack(String provinceName, String cityName, String countyName, String subdistrictOffice, int cityCode);

}
