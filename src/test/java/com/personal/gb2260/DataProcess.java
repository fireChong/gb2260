package com.personal.gb2260;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 进行原生数据处理，变成可读的json数据
 */
public class DataProcess {
    public static void main(String[] args) throws Exception {
        File rawFile = new File(ProjectConstant.RAW_RESOURCES_DIR);
        File jsonFile = new File(ProjectConstant.JSON_RESOURCES_DIR);

        for (File file : rawFile.listFiles()) {
            Map<String, String> map = new LinkedHashMap<>();
            String name = file.getName();
            File out = new File(jsonFile.getAbsolutePath() + File.separator + name + ".json");
            if (out.exists()) {
                continue;
            }

            List<String> lines = IOUtils.readLines(new FileInputStream(file), "UTF-8");
            for (String line : lines) {
                if (line.length() <= 6) {
                    continue;
                }

                String code = line.substring(0, 6);
                try {
                    Integer.parseInt(code);
                } catch (Exception e) {
                    continue;
                }

                String position = line.substring(6).trim();
                map.put(code, position);
            }


            IOUtils.write(getJson(map), new FileOutputStream(out), "UTF-8");
        }
    }

    private static String getJson(Map<String, String> map) {
        StringBuilder builder = new StringBuilder("{");
        map.forEach((k, v) -> {
            builder.append("\r\n")
                    .append("\t")
                    .append("\"")
                    .append(k)
                    .append("\"")
                    .append(":")
                    .append("\"")
                    .append(v)
                    .append("\"")
                    .append(",");
        });
        builder.deleteCharAt(builder.length() - 1);
        builder.append("\r\n");
        builder.append("}");
        return builder.toString();
    }
}
