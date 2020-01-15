# 中华人民共和国国家标准 GB/T 2260 行政区划代码

数据来源: http://www.mca.gov.cn/article/sj/xzqh//1980/

## 获取数据
 1. 从数据网站复制当前网页的数据放置在src/resources/raw目录下
 2. 运行src/test/java/com/personal/gb2260/DataProcess, 将在src/resources/json生成对应的json文件

## 如何使用
```text
// 通过行政区码进行查询，获取到最新的表示地区
Division division = GB2260.getInstance().getDivisionByCode("510128");

// 通过行政区码和年份进行精确查询
Division division = GB2260.getInstance().getDivisionByCode(2018, "361021");

// 获取省(自治区、直辖市、特别行政区)
division.getProvince();

// 获取市(地级市、自治州、盟及国家直辖市所属市辖区和县的汇总码)
division.getCity();

// 获取县(市辖区、县级市、旗)
division.getCounty();

// 获取总的名称 
division.getAddress();
```

