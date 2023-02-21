package com.forcs.eformsign.webhook.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;

public class File {

    public static JSONObject fileRead(String addr){
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        try {
            jsonObject = (JSONObject) parser.parse(new FileReader(".\\src\\main\\java\\com\\forcs\\eformsign\\webhook\\openAPI\\data\\"+addr));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return jsonObject;
    }

    public static void fileWrite(String addr, JSONObject jsonObject){
        try {
            FileWriter fileWriter = new FileWriter(".\\src\\main\\java\\com\\forcs\\eformsign\\webhook\\openAPI\\data\\" + addr);
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
