package com.lubin.widget.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lubin.widget.R;
import com.lubin.widget.dialog.data.CityData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author lubin 2018/10/25
 * <p>
 * 多级别选择，类似京东app地址选择器
 * <p>
 * Multi domain selection, similar to Jingdong APP address selector
 */
public class MultiLevelBottomDialog extends DialogFragment implements View.OnClickListener {
    private ImageView imClose;
    /**
     * 省，市，县区/乡镇，街道
     */
    private TextView tvProvince, tvCity, tvCounties, tvSubdistrictOffice, tvTitleTop;
    private ListView listView;
    private ProgressBar progressBar;
    private int multilevelHeight = 0;
    private String titleMultiLevel;

    public static final int TYPE_PROVINCE = 1;
    public static final int TYPE_CITY = 2;
    public static final int TYPE_COUNTY = 3;
    public static final int TYPE_SUBDISTRICTOFFICE = 4;
    /**
     * 选中数据
     */
    private String nameProvince;
    private String codeProvince;
    private String nameCity;
    private String codeCity;
    private String nameCounty;
    private String codeCounty;
    private String nameSubdistrictOffice;
    private String codeSubdistrictOffice;

    private String codeSelected;

    private List<CityData.DateBean> provinceList;
    private List<CityData.DateBean> cityList;
    private List<CityData.DateBean> countyList;
    private List<CityData.DateBean> subdistrictOfficeList;

    private MultilevelListAdapter provinceAdapter;
    private MultilevelListAdapter cityAdapter;
    private MultilevelListAdapter countyAdapter;
    private MultilevelListAdapter subdistrictOfficeAdapter;


    private OnMultilevelCallBack callBack;


    public static MultiLevelBottomDialog newInstance() {
        return new MultiLevelBottomDialog();
    }

    public MultiLevelBottomDialog setListener(OnMultilevelCallBack callBack) {
        this.callBack = callBack;
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_AppCompat_Multilevel);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.multilevel_bottom_dialog_layout, container, false);
        Window window = getDialog().getWindow();
        assert window != null;
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        if (multilevelHeight == 0) {
            layoutParams.height = 800;
        } else {
            layoutParams.height = multilevelHeight;
        }
        window.setAttributes(layoutParams);
        initView(root);
        return root;
    }

    private void initView(View root) {
        imClose = root.findViewById(R.id.im_multilevel_close);
        listView = root.findViewById(R.id.multilevel_list_view);
        tvProvince = root.findViewById(R.id.txt_multilevel_province);
        tvCity = root.findViewById(R.id.txt_multilevel_city);
        tvCounties = root.findViewById(R.id.txt_multilevel_counties);
        tvSubdistrictOffice = root.findViewById(R.id.txt_multilevel_subdistrict_office);
        tvTitleTop = root.findViewById(R.id.txt_dialog_title);
        progressBar = root.findViewById(R.id.multilevel_progress_bar);
        imClose.setOnClickListener(this);
        tvProvince.setOnClickListener(this);
        tvCity.setOnClickListener(this);
        tvCounties.setOnClickListener(this);
        tvSubdistrictOffice.setOnClickListener(this);
        if (!TextUtils.isEmpty(titleMultiLevel)) {
            tvTitleTop.setText(titleMultiLevel);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.im_multilevel_close) {
            dismissDialog();
        } else if (id == R.id.txt_multilevel_province) {
            if (provinceAdapter != null) {
                listView.setAdapter(provinceAdapter);
                listView.setSelection(provinceAdapter.selectPosition);
            }
            tvProvince.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.multilevel_city_color_red));
            tvCity.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.multilevel_title_color));
            tvCounties.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.multilevel_title_color));
            tvSubdistrictOffice.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.multilevel_title_color));
        } else if (id == R.id.txt_multilevel_city) {
            if (cityAdapter != null) {
                listView.setAdapter(cityAdapter);
                listView.setSelection(cityAdapter.selectPosition);
            }
            tvCity.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.multilevel_city_color_red));
            tvCounties.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.multilevel_title_color));
            tvSubdistrictOffice.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.multilevel_title_color));
            tvProvince.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.multilevel_title_color));
        } else if (id == R.id.txt_multilevel_counties) {
            if (countyAdapter != null) {
                listView.setAdapter(countyAdapter);
                listView.setSelection(countyAdapter.selectPosition);
            }
            tvCounties.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.multilevel_city_color_red));
            tvSubdistrictOffice.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.multilevel_title_color));
            tvProvince.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.multilevel_title_color));
            tvCity.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.multilevel_title_color));
        } else if (id == R.id.txt_multilevel_subdistrict_office) {
            if (subdistrictOfficeAdapter != null) {
                listView.setAdapter(subdistrictOfficeAdapter);
                listView.setSelection(subdistrictOfficeAdapter.selectPosition);
            }
            tvSubdistrictOffice.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.multilevel_city_color_red));
            tvProvince.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.multilevel_title_color));
            tvCity.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.multilevel_title_color));
            tvCounties.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.multilevel_title_color));
        }
    }

    /**
     * 关闭dialog
     */
    private void dismissDialog() {
        dismiss();
    }

    /**
     * 添加数据
     *
     * @param beanList beanList
     * @param cityType cityType
     */
    public MultiLevelBottomDialog loadData(List<CityData.DateBean> beanList, int cityType) {
        if (beanList == null || beanList.size() == 0) {
            if (callBack != null) {
                callBack.onFullSelectedCallBack(nameProvince, nameCity, nameCounty, nameSubdistrictOffice, codeSelected);
                dismissDialog();
            }
            return this;
        }
        switch (cityType) {
            case TYPE_PROVINCE:
                initProvinec(beanList);
                tvProvince.setText(R.string.multilevel_title_please_choose);
                tvProvince.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.multilevel_city_color_red));
                showLoading(false);
                break;
            case TYPE_CITY:
                initCity(beanList);
                tvCity.setText(R.string.multilevel_title_please_choose);
                tvCity.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.multilevel_city_color_red));
                tvProvince.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.multilevel_title_color));
                showLoading(false);
                break;
            case TYPE_COUNTY:
                initCounty(beanList);
                tvCounties.setText(R.string.multilevel_title_please_choose);
                tvCounties.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.multilevel_city_color_red));
                tvCity.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.multilevel_title_color));
                showLoading(false);
                break;
            case TYPE_SUBDISTRICTOFFICE:
                initsubdistrictOffice(beanList);
                tvSubdistrictOffice.setText(R.string.multilevel_title_please_choose);
                tvSubdistrictOffice.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.multilevel_city_color_red));
                tvCounties.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.multilevel_title_color));
                showLoading(false);
                break;
            default:
                break;
        }
        return this;
    }

    /**
     * 设置高度
     *
     * @param dialogHeight int 高
     * @return MultiLevelBottomDialog
     */
    public MultiLevelBottomDialog initDialogHeight(int dialogHeight) {
        this.multilevelHeight = dialogHeight;
        return this;
    }

    public MultiLevelBottomDialog initTopTitle(String titleMultiLevel) {
        this.titleMultiLevel = titleMultiLevel;
        return this;
    }

    /**
     * 各个级别的数据
     *
     * @param beanList beanList
     */
    private void initCity(List<CityData.DateBean> beanList) {
        if (cityList != null) {
            cityList.clear();
        } else {
            cityList = new ArrayList<>();
        }
        cityList.addAll(beanList);
        if (cityAdapter == null) {
            cityAdapter = new MultilevelListAdapter(cityList, new OnListItemClick() {
                @Override
                public void onCitySelected(CityData.DateBean cityBean, int position) {
                    nameCity = cityBean.getName();
                    codeCity = cityBean.getCode();
                    tvCity.setText(nameCity);
                    nameCounty = null;
                    nameSubdistrictOffice = null;
                    codeCounty = null;
                    codeSubdistrictOffice = null;
                    countyAdapter = null;
                    cityAdapter.notifyData();
                    if (callBack != null) {
                        callBack.onSelectedCallBack(nameCity, codeCity, TYPE_COUNTY);
                        codeSelected = codeCity;
                    }
                    showLoading(true);
                    tvCounties.setText("");
                    tvSubdistrictOffice.setText("");
                    countyAdapter = null;
                    subdistrictOfficeAdapter = null;
                }

            });
            listView.setAdapter(cityAdapter);
        } else {
            listView.setAdapter(cityAdapter);
        }
    }

    private void initCounty(List<CityData.DateBean> beanList) {
        if (countyList != null) {
            countyList.clear();
        } else {
            countyList = new ArrayList<>();
        }
        countyList.addAll(beanList);
        if (countyAdapter == null) {
            countyAdapter = new MultilevelListAdapter(countyList, new OnListItemClick() {
                @Override
                public void onCitySelected(CityData.DateBean cityBean, int position) {
                    nameCounty = cityBean.getName();
                    codeCounty = cityBean.getCode();
                    tvCounties.setText(nameCounty);
                    nameSubdistrictOffice = null;
                    codeSubdistrictOffice = null;
                    subdistrictOfficeAdapter = null;
                    countyAdapter.notifyData();
                    if (callBack != null) {
                        callBack.onSelectedCallBack(nameCounty, codeCounty, TYPE_SUBDISTRICTOFFICE);
                        codeSelected = codeCounty;
                    }
                    showLoading(true);
                    tvSubdistrictOffice.setText("");
                    subdistrictOfficeAdapter = null;
                }
            });
            listView.setAdapter(countyAdapter);
        } else {
            listView.setAdapter(countyAdapter);
        }
    }

    private void initsubdistrictOffice(List<CityData.DateBean> beanList) {
        if (subdistrictOfficeList != null) {
            subdistrictOfficeList.clear();
        } else {
            subdistrictOfficeList = new ArrayList<>();
        }
        subdistrictOfficeList.addAll(beanList);
        if (subdistrictOfficeAdapter == null) {
            subdistrictOfficeAdapter = new MultilevelListAdapter(subdistrictOfficeList, new OnListItemClick() {
                @Override
                public void onCitySelected(CityData.DateBean cityBean, int position) {
                    nameSubdistrictOffice = cityBean.getName();
                    codeSubdistrictOffice = cityBean.getCode();
                    tvSubdistrictOffice.setText(nameSubdistrictOffice);
                    subdistrictOfficeAdapter.notifyData();
                    if (callBack != null) {
                        callBack.onSelectedCallBack(nameSubdistrictOffice, codeSubdistrictOffice, 0);
                        codeSelected = codeSubdistrictOffice;
                    }
                    showLoading(true);
                }
            });
            listView.setAdapter(subdistrictOfficeAdapter);
        } else {
            listView.setAdapter(subdistrictOfficeAdapter);
        }

    }

    private void initProvinec(List<CityData.DateBean> beanList) {
        if (provinceList != null) {
            provinceList.clear();
        } else {
            provinceList = new ArrayList<>();
        }
        provinceList.addAll(beanList);
        if (provinceAdapter == null) {
            provinceAdapter = new MultilevelListAdapter(provinceList, new OnListItemClick() {
                @Override
                public void onCitySelected(CityData.DateBean cityBean, int position) {
                    nameProvince = cityBean.getName();
                    codeProvince = cityBean.getCode();
                    tvProvince.setText(nameProvince);
                    nameCity = null;
                    nameCounty = null;
                    nameSubdistrictOffice = null;
                    codeCity = null;
                    codeCounty = null;
                    codeSubdistrictOffice = null;
                    cityAdapter = null;
                    provinceAdapter.notifyData();
                    if (callBack != null) {
                        callBack.onSelectedCallBack(nameProvince, codeProvince, TYPE_CITY);
                        codeSelected = codeProvince;
                    }
                    tvCity.setText("");
                    tvCounties.setText("");
                    tvSubdistrictOffice.setText("");
                    cityAdapter = null;
                    countyAdapter = null;
                    subdistrictOfficeAdapter = null;
                    showLoading(true);

                }
            });
            listView.setAdapter(provinceAdapter);
        } else {
            listView.setAdapter(provinceAdapter);
        }
    }

    /**
     * listView适配器
     */
    class MultilevelListAdapter extends BaseAdapter {

        private List<CityData.DateBean> cityBean;
        private OnListItemClick onListItemClick;
        private String selseCode;
        private int selectPosition;

        public MultilevelListAdapter() {
        }

        MultilevelListAdapter(List<CityData.DateBean> cityBean, OnListItemClick onListItemClick) {
            this.cityBean = cityBean;
            this.onListItemClick = onListItemClick;
        }

        public void setDefaultData(String cityName, String cityCode, int cityType) {
            this.selseCode = cityCode;
        }

        @Override
        public int getCount() {
            return cityBean == null ? 0 : cityBean.size();
        }

        @Override
        public Object getItem(int position) {
            return cityBean.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewListHodler hodler;
            if (convertView == null) {
                convertView = View.inflate(getContext(), R.layout.multilevel_item_layout, null);
                hodler = new ViewListHodler();
                hodler.setViewId(convertView);
                convertView.setTag(hodler);
            } else {
                hodler = (ViewListHodler) convertView.getTag();
            }
            if (!TextUtils.isEmpty(selseCode) && cityBean.get(position).getCode().equals(selseCode)) {
                hodler.tvCityName.setText(cityBean.get(position).getName());
                hodler.tvCityName.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.multilevel_city_color_red));
                hodler.imIsChecked.setSelected(true);
            } else {
                hodler.tvCityName.setText(cityBean.get(position).getName());
                hodler.tvCityName.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.multilevel_city_color));
                hodler.imIsChecked.setSelected(false);
            }
            hodler.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onListItemClick != null) {
                        onListItemClick.onCitySelected(cityBean.get(position), position);
                        selseCode = cityBean.get(position).getCode();
                        selectPosition = position;
                        notifyData();
                    }
                }
            });

            return convertView;
        }

        class ViewListHodler {
            private TextView tvCityName;
            private ImageView imIsChecked;
            private LinearLayout layout;

            void setViewId(View root) {
                tvCityName = root.findViewById(R.id.txt_multilevel_name);
                imIsChecked = root.findViewById(R.id.im_multilevel_checked);
                layout = root.findViewById(R.id.multilevel_city_name_layout);
            }
        }

        /**
         * 刷新
         */
        private void notifyData() {
            notifyDataSetChanged();
        }
    }

    public interface OnListItemClick {
        /**
         * 选中的city
         *
         * @param cityBean cityBean
         * @param position 位置
         */
        void onCitySelected(CityData.DateBean cityBean, int position);
    }

    private void showLoading(boolean isShow) {
        if (isShow) {
            listView.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            listView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        provinceAdapter = null;
        cityAdapter = null;
        countyAdapter = null;
    }
}
