package com.forcs.eformsign.webhook.openAPI.method.document;

import com.forcs.eformsign.webhook.openAPI.common.Constants;

import java.net.HttpURLConnection;
import java.net.URL;

public class DocumentTemplateList {

    public void document_template_list(){
        String url = "";

        try {
            url = Constants.DOCUMENT_TEMPLATE_LIST;

            httpConnectionDocumentTemplateList(url);
        }catch (Exception e){
            e.getMessage();
        }
    }

    public void httpConnectionDocumentTemplateList(String urlData){
        String totalUrl = "";
        String accessToken = "";

        try {
            totalUrl = urlData;
            accessToken = Constants.ACCESS_TOKEN;

            URL url = new URL(totalUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn = Constants.headerSet(conn, accessToken, "GET");

            Constants.print(conn, "작성 가능한 템플릿 목록 조회");
        }catch (Exception e){
            e.getMessage();
        }
    }
}
