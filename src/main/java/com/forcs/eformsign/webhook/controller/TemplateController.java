package com.forcs.eformsign.webhook.controller;

import com.forcs.eformsign.webhook.openAPI.method.document.DocumentTemplateList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "")
public class TemplateController {
    @RequestMapping(value = "templateList")
    public String templateList(){
        DocumentTemplateList documentTemplateList = new DocumentTemplateList();
        StringBuilder sb = documentTemplateList.document_template_list();

        return "";
    }
}
