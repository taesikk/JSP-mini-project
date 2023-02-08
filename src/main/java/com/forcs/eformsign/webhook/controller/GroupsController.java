package com.forcs.eformsign.webhook.controller;

import com.forcs.eformsign.webhook.openAPI.method.group.GroupList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class GroupsController {
    @RequestMapping(value = "/groups_listsearch")
    public String groups_listsearch(String include_member, String include_field) {
        System.out.println("groups_listsearch");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        GroupList.check1 = include_member;
        GroupList.check2 = include_field;

        GroupList groupList = new GroupList();
        groupList.group_list();

        return "home";
    }
}
