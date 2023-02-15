package com.forcs.eformsign.webhook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "")
public class DocumentController {
    @RequestMapping(value = "/documentList")
    public String documentList(Model model){

        return "";
    }
}
