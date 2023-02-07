package com.forcs.eformsign.webhook.openAPI.method.group;

import com.forcs.eformsign.webhook.openAPI.common.Constants;
import com.forcs.eformsign.webhook.openAPI.common.JsonData;
import com.forcs.eformsign.webhook.openAPI.method.member.MemberFix;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class GroupFix {
    public String group_id= "";
    public void group_fix(){
        String url = "";

        try {
            Object ob = new JSONParser().parse(new FileReader(".\\APITest_Auto\\src\\main\\java\\data\\DocumentId.json"));
            JSONObject jsonObject = (JSONObject) ob;
            group_id = jsonObject.get("group_fix").toString();
            url = Constants.GROUP_URL + "/" + group_id;

            httpConnectionGroupFix(url);
        }catch (Exception e){
            e.getMessage();
        }
    }

    public void httpConnectionGroupFix(String urlData){
        String totalUrl = "";
        String accessToken = "";
        String jsondata = "";

        try {
            totalUrl = urlData;
            accessToken = Constants.ACCESS_TOKEN;
            jsondata = JsonData.GROUP_FIX_JSON;

            allowMethods("PATCH");

            URL url = new URL(totalUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn = Constants.headerSet(conn, accessToken, "PATCH");

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsondata.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            Constants.print(conn, "그룹 수정");
        }catch (Exception e){
            e.getMessage();
        }
    }

    private static void allowMethods(String methods) {
        try {
            Field methodsField = HttpURLConnection.class.getDeclaredField("methods");

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

            methodsField.setAccessible(true);

            String[] oldMethods = (String[]) methodsField.get(null);
            Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
            methodsSet.addAll(Arrays.asList(methods));
            String[] newMethods = methodsSet.toArray(new String[0]);

            methodsField.set(null, newMethods);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
}
