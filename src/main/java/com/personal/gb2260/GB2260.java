package com.personal.gb2260;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GB2260 {
    private static GB2260 instance = new GB2260();
    private Map<String, Division>[] data;
    private int minYear;
    private Division defaultDivision = new Division(1980, "", "", null);
    private GB2260() {
        load();
    }

    public static GB2260 getInstance() {
        return instance;
    }

    private void load() {
        String path = Object.class.getResource("/json").getFile();
        File jsonFile = new File(path);
        File[] files = jsonFile.listFiles();
        Map<String, Division>[] data = new Map[files.length];

        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            int year = Integer.parseInt(file.getName().substring(0, 4));
            if (i == 0) {
                minYear = year;
            }
            Map<String, Division> result = getDivisionFromFile(year, file);
            data[i] = result;
        }

        this.data = data;
    }

    private Map<String, Division> getDivisionFromFile(int year, File file) {
        Map<String, Division> result = new HashMap<>();
        try (FileInputStream in = new FileInputStream(file)) {
            String json = IOUtils.toString(in, "UTF-8");
            LinkedHashMap<String, String> map = JSON.parseObject(json, new TypeReference<LinkedHashMap<String, String>>() {
            });
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String code = entry.getKey();
                String name = entry.getValue();

                Division division;
                String provinceCode = code.substring(0, 2) + "0000";
                String cityCode = code.substring(0, 4) + "00";
                if (provinceCode.equals(code)) {
                    // 省级
                    division = new Division(year, code, name, null);
                } else if (cityCode.equals(code)) {
                    // 市级
                    Division province = result.get(provinceCode);
                    division = new Division(year, code, name, province);
                } else {
                    // 县级
                    Division city = result.get(cityCode);
                    if (city == null) {
                        city = result.get(provinceCode);
                    }

                    division = new Division(year, code, name, city);
                }
                result.put(code, division);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Division getDivisionByCode(int year, String code) {

        if (year < minYear) {
            return defaultDivision;
        }

        int index = year - minYear;
        if (index >= data.length) {
            return defaultDivision;
        }

        Map<String, Division> map = data[index];
        return map.getOrDefault(code, defaultDivision);
    }


    public Division getDivisionByCode(String code) {
        for (int i = data.length - 1; i >= 0; i--) {
            Map<String, Division> divisionMap = data[i];
            Division result = divisionMap.get(code);
            if (result != null) {
                return result;
            }
        }

        return defaultDivision;
    }
}
