package com.personal.gb2260;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GB2260Test {

    @Test
    public void getDivisionByCode() {
        Division division = GB2260.getInstance().getDivisionByCode("510128");
        assertTrue(division.getProvince().equals("四川省"));
        assertTrue(division.getCity().equals("成都市"));
        assertTrue(division.getCounty().equals("崇庆县"));
        assertTrue(division.getAddress().equals("四川省成都市崇庆县"));


        division = GB2260.getInstance().getDivisionByCode("132822");
        assertTrue(division.getProvince().equals("河北省"));
        assertTrue(division.getCity().equals("廊坊地区"));
        assertTrue(division.getCounty().equals("大厂回族自治县"));
        assertTrue(division.getAddress().equals("河北省廊坊地区大厂回族自治县"));

        division = GB2260.getInstance().getDivisionByCode("110101");
        assertTrue(division.getProvince().equals("北京市"));
        assertTrue(division.getCity().equals("东城区"));
        assertTrue(division.getCounty() == null);
        assertTrue(division.getAddress().equals("北京市东城区"));

        division = GB2260.getInstance().getDivisionByCode(2018, "361021");
        assertTrue(division.getProvince().equals("江西省"));
        assertTrue(division.getCity().equals("抚州市"));
        assertTrue(division.getCounty().equals("南城县"));
        assertTrue(division.getAddress().equals("江西省抚州市南城县"));
    }
}