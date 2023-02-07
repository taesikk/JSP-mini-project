package com.forcs.eformsign.webhook.openAPI.method.member;

import com.forcs.eformsign.webhook.openAPI.common.Constants;
import com.forcs.eformsign.webhook.openAPI.method.document.DocumentRequest;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Struct;
import java.util.Scanner;

public class MemberDelete {
    public static String doc_id="";
    public void member_delete(){
        String url = "";

        try {
            DocumentRequest.fileRead(Constants.DOC_ID_FILE_URL);
            url = Constants.MEMBER_FIX_DELETE_URL + doc_id;

            httpConnectionMemberDelete(url);
        }catch (Exception e){
            e.getMessage();
        }
    }

    public void httpConnectionMemberDelete(String urlData){
        String totalUrl = "";
        String accessToken = "";

        try {
            totalUrl = urlData;
            accessToken = Constants.ACCESS_TOKEN;

            URL url = new URL(totalUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn = Constants.headerSet(conn, accessToken, "DELETE");

            Constants.print(conn, "구성원 삭제");
        }catch (Exception e){
            e.getMessage();
        }
    }
}
