package com.forcs.eformsign.webhook.controller;

import com.forcs.eformsign.webhook.openAPI.common.Constants;
import com.forcs.eformsign.webhook.openAPI.method.group.GroupAdd;
import com.forcs.eformsign.webhook.openAPI.method.group.GroupDelete;
import com.forcs.eformsign.webhook.openAPI.method.group.GroupFix;
import com.forcs.eformsign.webhook.openAPI.method.group.GroupList;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping(value = "/group")
public class GroupsController {
    @RequestMapping(value = "/groupsListSearch")
    public String groups_listsearch(String accessToken, String includeMember, String includeField, Model model) {
        System.out.println("groupsListSearch");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        Constants.ACCESS_TOKEN=accessToken;
        GroupList.check1 = includeMember;
        GroupList.check2 = includeField;

        GroupList groupList = new GroupList();
        groupList.group_list();

        String resultSB = groupList.mainSB.toString();
        model.addAttribute("result",resultSB);
        return "group/GroupsListSearchResult";
    }

    @RequestMapping(value = "/groupsCreate")
    public String groups_create(String accessToken,String name,String description,String members,Model model) {
        System.out.println("groupsCreate");

        Constants.ACCESS_TOKEN=accessToken;
        GroupAdd.name=name;
        GroupAdd.description=description;
        GroupAdd.members=members;

        GroupAdd groupAdd = new GroupAdd();
        groupAdd.group_add();

        String resultSB=groupAdd.mainSB.toString();
        model.addAttribute("result",resultSB);

        return "group/GroupsCreateResult";
    }

    @RequestMapping(value = "/groupsDelete")
    public String groups_delete(String accessToken, String group_id,Model model) {
        System.out.println("groupsDelete");

        Constants.ACCESS_TOKEN=accessToken;
        GroupDelete.group_id=group_id;

        GroupDelete groupDelete = new GroupDelete();
        groupDelete.group_delete();

        String resultSB=groupDelete.mainSB.toString();
        model.addAttribute("result",resultSB);

        return "group/GroupsDeleteResult";
//        return ResponseEntity.status(302).header("Location", "/home").build();
    }

    @RequestMapping(value = "/groupsUpdate")
    public String groups_update(String accessToken,String group_id,String name,String description,String member, Model model) {
        System.out.println("groupsUpdate");

        Constants.ACCESS_TOKEN=accessToken;
        GroupFix.group_id=group_id;
        GroupFix.name=name;
        GroupFix.description=description;
        GroupFix.member=member;

        GroupFix groupFix = new GroupFix();
        groupFix.group_fix();

        String resultSB=groupFix.mainSB.toString();
        model.addAttribute("result",resultSB);

        return "group/GroupsUpdateResult";
    }
}
