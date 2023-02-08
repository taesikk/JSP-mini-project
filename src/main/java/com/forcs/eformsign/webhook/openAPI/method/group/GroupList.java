package com.forcs.eformsign.webhook.openAPI.method.group;

import com.forcs.eformsign.webhook.openAPI.common.Constants;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GroupList {
    public static String check1="";
    public static String check2="";


    public void group_list(){
        String url = "";


        try {
            checkValue(check1,check2);
            url = Constants.GROUP_LIST_URL;

            httpConnectionGroupList(url);
        }catch (Exception e){
            e.getMessage();
        }
    }

    public void httpConnectionGroupList(String urlData){
        String totalUrl = "";
        String accessToken = "";

        try {
            totalUrl = urlData;
            accessToken = Constants.ACCESS_TOKEN;

            URL url = new URL(totalUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn = Constants.headerSet(conn, accessToken, "GET");

            Constants.print(conn, "그룹 목록 조회");
        }catch (Exception e){
            e.getMessage();
        }
    }

    public void checkValue(String include_member, String include_field){
        Constants.setGroupListUrl(include_member, include_field);
    }
}