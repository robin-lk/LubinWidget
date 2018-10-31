package com.lubin.widget.dialog.data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lubin
 * @version 1.0 ·2018/10/25
 */
public class CityData {

    /**
     * msg : success
     * code : 0
     * date : [{"code":"110000","parent_code":"100000","name":"北京"},{"code":"120000","parent_code":"100000","name":"天津"},{"code":"130000","parent_code":"100000","name":"河北省"},{"code":"140000","parent_code":"100000","name":"山西省"},{"code":"150000","parent_code":"100000","name":"内蒙古自治区"},{"code":"210000","parent_code":"100000","name":"辽宁省"},{"code":"220000","parent_code":"100000","name":"吉林省"},{"code":"230000","parent_code":"100000","name":"黑龙江省"},{"code":"310000","parent_code":"100000","name":"上海"},{"code":"320000","parent_code":"100000","name":"江苏省"},{"code":"330000","parent_code":"100000","name":"浙江省"},{"code":"340000","parent_code":"100000","name":"安徽省"},{"code":"350000","parent_code":"100000","name":"福建省"},{"code":"360000","parent_code":"100000","name":"江西省"},{"code":"370000","parent_code":"100000","name":"山东省"},{"code":"410000","parent_code":"100000","name":"河南省"},{"code":"420000","parent_code":"100000","name":"湖北省"},{"code":"430000","parent_code":"100000","name":"湖南省"},{"code":"440000","parent_code":"100000","name":"广东省"},{"code":"450000","parent_code":"100000","name":"广西壮族自治区"},{"code":"460000","parent_code":"100000","name":"海南省"},{"code":"500000","parent_code":"100000","name":"重庆"},{"code":"510000","parent_code":"100000","name":"四川省"},{"code":"520000","parent_code":"100000","name":"贵州省"},{"code":"530000","parent_code":"100000","name":"云南省"},{"code":"540000","parent_code":"100000","name":"西藏自治区"},{"code":"610000","parent_code":"100000","name":"陕西省"},{"code":"620000","parent_code":"100000","name":"甘肃省"},{"code":"630000","parent_code":"100000","name":"青海省"},{"code":"640000","parent_code":"100000","name":"宁夏回族自治区"},{"code":"650000","parent_code":"100000","name":"新疆维吾尔自治区"},{"code":"710000","parent_code":"100000","name":"台湾"},{"code":"810000","parent_code":"100000","name":"香港特别行政区"},{"code":"820000","parent_code":"100000","name":"澳门特别行政区"},{"code":"900000","parent_code":"100000","name":"钓鱼岛"}]
     */

    private String msg;
    private int code;
    private List<DateBean> date;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DateBean> getDate() {
        return date;
    }

    public void setDate(List<DateBean> date) {
        this.date = date;
    }

    public static class DateBean implements Serializable {
        /**
         * code : 110000
         * parent_code : 100000
         * name : 北京
         */

        private String code;
        private String parent_code;
        private String name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getParent_code() {
            return parent_code;
        }

        public void setParent_code(String parent_code) {
            this.parent_code = parent_code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "DateBean{" +
                    "code='" + code + '\'' +
                    ", parent_code='" + parent_code + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CityData{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", date=" + date +
                '}';
    }
}
