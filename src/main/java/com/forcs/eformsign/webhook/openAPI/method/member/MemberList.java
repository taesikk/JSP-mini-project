package com.forcs.eformsign.webhook.openAPI.method.member;

import com.forcs.eformsign.webhook.openAPI.common.Constants;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MemberList {

    public void member_list() {
        String url = "";

        try {
            checkValue();

            url = Constants.MEMBER_LIST_URL;

            httpConnectionMemberList(url);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void httpConnectionMemberList(String urlData){
        String totalUrl = "";
        String accessToken = "";

        try {
            totalUrl = urlData;
            accessToken = Constants.ACCESS_TOKEN;

            URL url = new URL(totalUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn = Constants.headerSet(conn, accessToken, "GET");

            Constants.print(conn, "구성원 목록 조회");
        }catch (Exception e){
            e.getMessage();
        }
    }
    public void checkValue() {
        String member_all = "false";
        String include_field = "false";
        String include_delete = "false";
        String eb_name_search = "";

        Constants.setMemberListUrl(member_all, include_field, include_delete, eb_name_search);
    }
}
