package com.personal.gb2260;

import java.io.File;

/**
 * 项目相关的常量
 */
public class ProjectConstant {
    public static final String USER_DIR = System.getProperty("user.dir");
    public static final String SRC_DIR = USER_DIR + File.separator + "src";
    public static final String MAIN_DIR = SRC_DIR + File.separator + "main";
    public static final String MAIN_JAVA_DIR = MAIN_DIR + File.separator + "java";
    public static final String MAIN_RESOURCES_DIR = MAIN_DIR + File.separator + "resources";
    public static final String TEST_DIR = SRC_DIR + File.separator + "test";
    public static final String TEST_JAVA_DIR = TEST_DIR + File.separator + "java";
    public static final String TEST_RESOURCES_DIR = TEST_DIR + File.separator + "resources";

    public static final String RAW_RESOURCES_DIR = MAIN_RESOURCES_DIR + File.separator + "raw";
    public static final String JSON_RESOURCES_DIR = MAIN_RESOURCES_DIR + File.separator + "json";
}

