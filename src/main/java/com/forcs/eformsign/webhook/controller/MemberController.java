package com.forcs.eformsign.webhook.controller;

import com.forcs.eformsign.webhook.openAPI.common.Constants;
import com.forcs.eformsign.webhook.openAPI.method.member.MemberList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

        model.addAttribute("result", sb.toString());
        return "MemberListResult";
    }
}
