package com.forcs.eformsign.webhook.controller;

import com.forcs.eformsign.webhook.openAPI.common.Constants;
import com.forcs.eformsign.webhook.openAPI.method.token.EformsignSignatureMake;
import com.forcs.eformsign.webhook.openAPI.method.token.TokenAccess;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "")
public class PageController {

    @RequestMapping(value = "/home")
    public String homePage(){
        System.out.println("homehome");
        return "home.html";
    }

    @RequestMapping(value = "/send")
    public String send(String username, int userage, Model model){
        model.addAttribute("name", username);
        model.addAttribute("age", userage);
        String a = model.getAttribute("name").toString();
        System.out.println(a);
        return "Document_hint.html";

    }

    @RequestMapping(value = "/token")
    public String token(String userId, String apikey, String secret){
        System.out.println("tokentoken");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String a = userId;
        String b = apikey;
        String c = secret;

        Constants.API_KEY = b;
        EformsignSignatureMake.secret = c;
        TokenAccess.member_id = a;
        System.out.println("userId : " + TokenAccess.member_id + ", apikey : " + Constants.API_KEY + ", secret : " + EformsignSignatureMake.secret);
        TokenAccess tokenAccess = new TokenAccess();
        tokenAccess.token_access();
        System.out.println("Constants AccessToken : " + Constants.ACCESS_TOKEN);
        System.out.println("Constants RefreshToken : " + Constants.REFRESH_TOKEN);


        return "home";

    }
}
