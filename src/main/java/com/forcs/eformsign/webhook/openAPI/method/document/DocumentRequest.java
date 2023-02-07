package com.forcs.eformsign.webhook.openAPI.method.document;

import com.forcs.eformsign.webhook.openAPI.common.Constants;
import com.forcs.eformsign.webhook.openAPI.common.JsonData;
import com.forcs.eformsign.webhook.openAPI.method.member.MemberDelete;
import com.forcs.eformsign.webhook.openAPI.method.member.MemberFix;
import com.forcs.eformsign.webhook.openAPI.method.token.EformsignSignatureMake;
import com.forcs.eformsign.webhook.openAPI.method.token.TokenAccess;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.FileReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class DocumentRequest {
    public static String doc_id = "";

    public void document_request() {
        String url = "";

        try {
            fileRead(Constants.DOC_ID_FILE_URL);
            url = Constants.DOCUMENT_REQUEST_URL + doc_id + "/re_request_outsider";

            httpConnectionDocumentRequest(url);
        } catch (Exception e) {
            e.getMessage();
        }


    }

    public void httpConnectionDocumentRequest(String urlData) {
        String totalUrl = "";
        String accessToken = "";
        String jsondata = "";

        try {
            totalUrl = urlData;
            accessToken = Constants.ACCESS_TOKEN;
            jsondata = JsonData.DOCUMENT_REQUEST_JSON;

            URL url = new URL(totalUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn = Constants.headerSet(conn, accessToken, "POST");

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsondata.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            Constants.print(conn, "문서 재요청");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public static void fileRead(String name) {
        //json-simple 사용하여 json파일 읽기
        try {
            @SuppressWarnings("unchecked")
            Object ob = new JSONParser().parse(new FileReader(name));
            JSONObject jsonObject = (JSONObject) ob;

            DocumentRequest.doc_id = jsonObject.get("document_request").toString();
            DocumentMass.doc_id = jsonObject.get("document_mass").toString();
            DocumentInfo.info_id = jsonObject.get("document_info").toString();
            MemberDelete.doc_id = jsonObject.get("member_delete").toString();

        } catch (Exception e) {
            e.getMessage();
        }
    }
}
