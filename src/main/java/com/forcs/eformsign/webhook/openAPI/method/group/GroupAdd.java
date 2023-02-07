package com.forcs.eformsign.webhook.openAPI.method.group;

import com.forcs.eformsign.webhook.openAPI.common.Constants;
import com.forcs.eformsign.webhook.openAPI.common.JsonData;

import java.io.FileWriter;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GroupAdd {

    public void group_add() {
        String url = "";

        try {
            url = Constants.GROUP_URL;

            httpConnectionGroupAdd(url);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void httpConnectionGroupAdd(String urlData) {
        String totalUrl = "";
        String accessToken = "";
        String jsondata = "";
        Scanner scanner = new Scanner(System.in);

        try {
            totalUrl = urlData;
            accessToken = Constants.ACCESS_TOKEN;

            jsondata = JsonData.GROUP_ADD_JSON;

            URL url = new URL(totalUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn = Constants.headerSet(conn, accessToken, "POST");

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsondata.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            StringBuilder response = Constants.print(conn, "그룹 추가");
            String id = response.substring(response.indexOf("id") + 5, response.indexOf("name") - 3);


            String deleteJson = "{\"group_ids\": [\""+ id +"\"]}";
            FileWriter file = new FileWriter(".\\APITest_Auto\\src\\main\\java\\data\\GroupDelete.json");
            file.write(deleteJson);
            file.flush();
            file.close();

            /*GroupDelete groupDelete = new GroupDelete();
            groupDelete.group_id = id;*/
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
