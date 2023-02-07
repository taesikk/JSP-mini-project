package com.forcs.eformsign.webhook.openAPI.method.document;

import com.forcs.eformsign.webhook.openAPI.common.Constants;
import com.forcs.eformsign.webhook.openAPI.common.JsonData;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DocumentList {

    public void document_list() {
        String url = "";

        try {
            url = Constants.DOCUMENT_LIST_URL;

            httpConnectionDocumentList(url);
        } catch (NullPointerException e) {
            e.getMessage();
        }
    }

    public void httpConnectionDocumentList(String urlData) {
        String accessToken = "";
        String jsondata = "";
        String totalUrl = urlData;
        try {
            accessToken = Constants.ACCESS_TOKEN;

            URL url = new URL(totalUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();


            jsondata = JsonData.DOCUMENT_LIST_JSON;

            conn = Constants.headerSet(conn, accessToken, "POST");

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsondata.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            Constants.print(conn, "문서 목록 조회");
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
