package com.forcs.eformsign.webhook.openAPI.method.group;

import com.forcs.eformsign.webhook.openAPI.common.Constants;
import com.forcs.eformsign.webhook.openAPI.common.JsonData;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GroupDelete {

    public static String group_id = "";
    public void group_delete() {
        String url = "";

        try {
            JsonData.GROUP_DELETE_JSON = JsonData.fileRead("GroupDelete.json");
            url = Constants.GROUP_URL;

            httpConnectionGroupDelete(url);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void httpConnectionGroupDelete(String urlData) {
        String totalUrl = "";
        String accessToken = "";
        String jsondata = "";

        try {
            totalUrl = urlData;
            accessToken = Constants.ACCESS_TOKEN;
            jsondata = JsonData.GROUP_DELETE_JSON;

            URL url = new URL(totalUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn = Constants.headerSet(conn, accessToken, "DELETE");

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsondata.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            Constants.print(conn, "그룹 삭제");
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
