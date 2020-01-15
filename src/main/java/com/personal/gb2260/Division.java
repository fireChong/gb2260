package com.personal.gb2260;

public class Division {
    /**
     * 年份
     */
    private int year;

    /**
     * 行政区号
     */
    private String code;

    /**
     * 行政区名称
     */
    private String name;

    /**
     * 省(自治区、直辖市、特别行政区)
     */
    private String province;

    /**
     * 市(地级市、自治州、盟及国家直辖市所属市辖区和县的汇总码)
     */
    private String city;

    /**
     * 县(市辖区、县级市、旗)
     */
    private String county;

    /**
     * 总称
     */
    private String address;


    public Division(int year, String code, String name, Division parent) {
        this.year = year;
        this.code = code;
        this.name = name;

        if (parent == null) {
            this.province = name;
            this.address = name;
        } else if (parent.getCity() == null) {
            this.province = parent.getProvince();
            this.city = name;
            this.address = parent.getAddress() + name;
        } else {
            this.province = parent.getProvince();
            this.city = parent.getCity();
            this.county = name;
            this.address = parent.getAddress() + name;
        }
    }


    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getAddress() {
        return address;
    }

    public int getYear() {
        return year;
    }
}
