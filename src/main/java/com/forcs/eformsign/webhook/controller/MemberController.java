package com.forcs.eformsign.webhook.controller;

import com.forcs.eformsign.webhook.openAPI.common.Constants;
import com.forcs.eformsign.webhook.openAPI.method.member.MemberFix;
import com.forcs.eformsign.webhook.openAPI.method.member.MemberList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping(value = "")
public class MemberController {
    @RequestMapping(value = "/")
    public String mainHome(){
        return "index";
    }
    @RequestMapping(value = "/memberList")
    public String memberList(String accessToken, String memberAll, String includeField, String includeDelete, String name, Model model){
        System.out.println("memberAll : " + memberAll);

        Constants.ACCESS_TOKEN = accessToken;
        MemberList.member_all = memberAll;
        MemberList.include_field = includeField;
        MemberList.include_delete = includeDelete;
        MemberList.eb_search_name = name;

        MemberList memberList = new MemberList();
        StringBuilder sb = memberList.member_list();

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        JSONArray jsonArray = new JSONArray();
        String[] id = new String[100];
        String[] username = new String[100];
        HashMap<String, String> map = new HashMap<>();
        try {
            jsonObject = (JSONObject) parser.parse(sb.toString());
            jsonArray = (JSONArray) jsonObject.get("members");
            jsonObject = (JSONObject) jsonArray.get(0);
            for (int i=0;i<jsonArray.size();i++){
                jsonObject = (JSONObject) jsonArray.get(i);
                id[i] = jsonObject.get("id").toString();
                username[i] = jsonObject.get("name").toString();
                map.put(jsonObject.get("id").toString(), jsonObject.get("name").toString());
            }
            System.out.println("array id : " + id);

            //jsonObject.get("id")
        }catch (Exception e){
            e.getMessage();
        }

        model.addAttribute("result", id);
        model.addAttribute("username", username);
        model.addAttribute("json", sb.toString());
        return "MemberListResult";
    }
    @RequestMapping(value = "/memberFix")
    public String memberFix(String accessToken, String memberId, Model model){

        Constants.ACCESS_TOKEN = accessToken;
        MemberFix.member_id = memberId;

        MemberList memberList = new MemberList();
        StringBuilder sb = memberList.member_list();

        model.addAttribute("result", sb.toString());
        return "MemberFixResult";
    }
}
