package com.lubin.widget.dialog;

/**
 * @author lubin
 * @version 1.0 ·2018/10/31
 */
public interface OnMultilevelCallBack {
    /**
     * 选中的项
     *
     * @param cityName     选中的name
     * @param cityCode     选中的code
     * @param nextCityType 所需的下一个级别
     */
    void onSelectedCallBack(String cityName, String cityCode, int nextCityType);

    /**
     * 全部选中返回
     *
     * @param provinceName      省
     * @param cityName          市
     * @param countyName        县/区
     * @param subdistrictOffice 街道
     * @param cityCode          选中的code
     */
    void onFullSelectedCallBack(String provinceName, String cityName, String countyName, String subdistrictOffice, String cityCode);

}
