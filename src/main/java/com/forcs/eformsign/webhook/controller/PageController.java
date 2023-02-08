package com.forcs.eformsign.webhook.controller;

import com.forcs.eformsign.webhook.openAPI.common.Constants;
import com.forcs.eformsign.webhook.openAPI.method.group.GroupList;
import com.forcs.eformsign.webhook.openAPI.method.token.EformsignSignatureMake;
import com.forcs.eformsign.webhook.openAPI.method.token.TokenAccess;
import com.forcs.eformsign.webhook.openAPI.method.token.TokenRefresh;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.text.AttributedString;

@Controller
@RequestMapping(value = "")
public class PageController {

    @RequestMapping(value = "/home")
        public String main(Model model) {
            System.out.println("homehome");

        return "home.html";
    }

    @RequestMapping(value = "/send")
    public String send(String username, int userage, Model model) {
        model.addAttribute("name", username);
        model.addAttribute("age", userage);
        String a = model.getAttribute("name").toString();
        System.out.println(a);
        return "Document_hint.html";

    }


    @RequestMapping(value = "/token")
    public ResponseEntity<Void> token(String userId, String apikey, String secret, Model model) {
        System.out.println("tokentoken");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        TokenAccess.member_id = userId;
        Constants.API_KEY = apikey;
        EformsignSignatureMake.secret = secret;
//        System.out.println("userId : " + TokenAccess.member_id + ", apikey : " + Constants.API_KEY + ", secret : " + EformsignSignatureMake.secret);
        TokenAccess tokenAccess = new TokenAccess();
        tokenAccess.token_access();

        System.out.println("Constants AccessToken :  " + Constants.ACCESS_TOKEN);
        System.out.println("Constants RefreshToken : " + Constants.REFRESH_TOKEN);

        model.addAttribute("atoken", Constants.ACCESS_TOKEN);
        model.addAttribute("rtoken", Constants.REFRESH_TOKEN);
//        return "home.jsp?test=test";
        return ResponseEntity.status(302).header("Location", "/home?atoken=" + Constants.ACCESS_TOKEN + "&rtoken=" + Constants.REFRESH_TOKEN).build();
    }

    @RequestMapping(value = "/refresh")
    public String tokenrefresh(String accessToken, String refreshToken) {
        System.out.println("tokenRefresh");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        Constants.ACCESS_TOKEN = accessToken;
        Constants.REFRESH_TOKEN = refreshToken;

        TokenRefresh tokenRefresh = new TokenRefresh();
        tokenRefresh.token_refresh();
        System.out.println("Constants AccessToken :  " + Constants.ACCESS_TOKEN);
        System.out.println("Constants RefreshToken : " + Constants.REFRESH_TOKEN);

        return "resfresh.jsp";
    }
}
