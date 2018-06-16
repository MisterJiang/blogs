package com.modules.thread.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetJsonData {
    public static String str = File.separator;
    public static String url = "json/dataSource.json";
    public static String fileUrl;

    static {
        fileUrl = GetJsonData.class.getClassLoader().getResource(url).getFile();
    }

    //根据文件名获取json文件中的名称
    public static List<Map<String, Object>> getData() {
        List<Map<String, Object>> mapArrayList = new ArrayList<>();
        try {
            File file = new File(fileUrl);
            if(file.exists()){
                String content = FileUtils.readFileToString(file, "UTF-8");
                JSONObject parseObject = JSONObject.parseObject(content);
                JSONArray tables = parseObject.getJSONArray("tableNames");
                System.out.println("需要上传的数据库表"  + tables);
                for (Object t : tables) {
                    Map hashMap = new HashMap<String, String>();
                    JSONObject tableInfoJson = parseObject.getJSONObject(t.toString());
                    hashMap.put("tableName", t.toString());
                    hashMap.put("driverClassName", tableInfoJson.getString("driverClassName"));
                    hashMap.put("url", tableInfoJson.getString("url"));
                    hashMap.put("username", tableInfoJson.getString("username"));
                    hashMap.put("password", tableInfoJson.getString("password"));
                    mapArrayList.add(hashMap);
                }
                return mapArrayList;
            }else {
                return mapArrayList;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapArrayList;
    }

    public static void main(String[] args) {
        List<Map<String, Object>> data = getData();
        System.out.println(data);
    }

}
