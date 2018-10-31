package com.lubin.widget.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
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
import android.widget.TextView;

import com.lubin.widget.R;
import com.lubin.widget.dialog.data.CityData;

import java.util.List;

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
    private TextView tvProvince, tvCity, tvCounties, tvSubdistrictOffice;
    private ListView listView;

    private static final int TYPE_PROVINCE = 1;
    private static final int TYPE_CITY = 2;
    private static final int TYPE_COUNTY = 3;
    private static final int TYPE_SUBDISTRICTOFFICE = 4;
    /**
     * 选中数据
     */
    private String nameProvince;
    private int codeProvince;
    private String nameCity;
    private int codeCity;
    private String nameCounty;
    private int codeCounty;
    private String nameSubdistrictOffice;
    private int codeSubdistrictOffice;

    /**
     * 默认
     */
    private int isType = TYPE_PROVINCE;


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
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
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
        imClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        tvProvince.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.im_multilevel_close) {
            dismiss();
        } else if (id == R.id.txt_multilevel_province) {

        } else if (id == R.id.txt_multilevel_city) {

        } else if (id == R.id.txt_multilevel_counties) {

        } else if (id == R.id.txt_multilevel_subdistrict_office) {

        }
    }


    public void loadData(List<CityData.DateBean> beanList, int cityType) {
        switch (cityType) {
            case TYPE_PROVINCE:

                break;
            case TYPE_CITY:

                break;
            case TYPE_COUNTY:

                break;
            case TYPE_SUBDISTRICTOFFICE:

                break;
            default:
                break;
        }
    }

    class MultilevelListAdapter extends BaseAdapter {

        private List<CityData.DateBean> cityBean;
        private OnListItemClick onListItemClick;
        private int selseCode;

        public MultilevelListAdapter(List<CityData.DateBean> cityBean, OnListItemClick onListItemClick) {
            this.cityBean = cityBean;
            this.onListItemClick = onListItemClick;
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
                convertView.setTag(hodler);
            } else {
                hodler = (ViewListHodler) convertView.getTag();
            }
            if (selseCode != 0) {
                if (cityBean.get(position).getCode().equals(selseCode + "")) {

                }
            }
            hodler.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onListItemClick != null) {
                        onListItemClick.onCitySelected(cityBean.get(position));

                    }
                }
            });

            return convertView;
        }

        class ViewListHodler {
            private TextView tvCityName;
            private ImageView imIsChecked;
            private LinearLayout layout;

            public void setViewId(View root) {
                tvCity = root.findViewById(R.id.txt_multilevel_name);
                imIsChecked = root.findViewById(R.id.im_multilevel_checked);
                layout = root.findViewById(R.id.multilevel_city_name_layout);
            }
        }

    }

    public interface OnListItemClick {
        /**
         * 选中的city
         *
         * @param cityBean cityBean
         */
        void onCitySelected(CityData.DateBean cityBean);
    }

}
