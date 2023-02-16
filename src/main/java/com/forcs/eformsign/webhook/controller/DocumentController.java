package com.forcs.eformsign.webhook.controller;

import com.forcs.eformsign.webhook.openAPI.method.document.DocumentList;
import com.forcs.eformsign.webhook.openAPI.method.document.DocumentRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "")
public class DocumentController {
    @RequestMapping(value = "/documentList")
    public String documentList(Model model){
        DocumentList documentList = new DocumentList();
        StringBuilder sb = documentList.document_list();
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        JSONArray jsonArray = new JSONArray();
        String[] doc_id = new String[100];
        String[] doc_name = new String[100];
        String[] create_name = new String[100];
        String[] create_id = new String[100];
        String[] recipient_id = new String[100];
        String[] recipient_name = new String[100];

        try {
            jsonObject = (JSONObject) parser.parse(sb.toString());
            jsonArray = (JSONArray) jsonObject.get("documents");
            for (int i =0;i<jsonArray.size();i++){
                jsonObject = (JSONObject) jsonArray.get(i);
                doc_id[i] = jsonObject.get("id").toString();
                doc_name[i] = jsonObject.get("document_name").toString();
                JSONObject sample = (JSONObject) jsonObject.get("creator");
                create_name[i] = sample.get("name").toString();
                create_id[i] = sample.get("id").toString();
                sample = (JSONObject) jsonObject.get("current_status");
                JSONArray arraySample = (JSONArray) sample.get("step_recipients");
                sample = (JSONObject) arraySample.get(0);
                if (sample.get("id") != null){
                    recipient_id[i] = sample.get("id").toString();
                }else {
                    recipient_id[i] = sample.get("email").toString();
                }
                recipient_name[i] = sample.get("name").toString();

            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        model.addAttribute("doc_id", doc_id);
        model.addAttribute("doc_name", doc_name);
        model.addAttribute("create_id", create_id);
        model.addAttribute("create_name", create_name);
        model.addAttribute("recipient_id", recipient_id);
        model.addAttribute("recipient_name", recipient_name);

        return "DocumentListResult";
    }

    @RequestMapping(value = "/documentRequest")
    public String documentRequest(String docId, String jsonData, Model model){
        System.out.println("docId : " +docId + ", jsonData : " + jsonData);
        /*DocumentRequest.doc_id = docId;
        DocumentRequest documentRequest = new DocumentRequest();
        documentRequest.document_request();*/
        return "DocumentListResult";
    }
}
