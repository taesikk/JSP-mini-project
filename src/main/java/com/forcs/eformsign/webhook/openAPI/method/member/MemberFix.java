package com.forcs.eformsign.webhook.openAPI.method.member;

import com.forcs.eformsign.webhook.openAPI.common.Constants;
import com.forcs.eformsign.webhook.openAPI.common.JsonData;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class MemberFix {
    public static String member_id ="";
    public void member_fix(){
        String url = "";

        try {
            url = Constants.MEMBER_FIX_DELETE_URL;

            httpConnectionMemberFix(url);
        }catch (Exception e){
            e.getMessage();
        }
    }

    public void httpConnectionMemberFix(String urlData){
        String totalUrl = "";
        String accessToken = "";
        String jsondata = "";

        try {
            jsondata = JsonData.MEMBER_FIX_JSON;
            member_id = jsondata.substring(jsondata.indexOf("id")+6, jsondata.indexOf("name")-7);
            totalUrl = urlData+ member_id;
            accessToken = Constants.ACCESS_TOKEN;


            //conn객체에 PATCH 추가
            allowMethods("PATCH");

            URL url = new URL(totalUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //PATCH로 헤더 셋팅
            conn = Constants.headerSet(conn, accessToken, "PATCH");

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsondata.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            Constants.print(conn, "구성원 수정");
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
