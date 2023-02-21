package com.forcs.eformsign.webhook.controller;

import com.forcs.eformsign.webhook.openAPI.common.Constants;
import com.forcs.eformsign.webhook.openAPI.method.group.GroupList;
import com.forcs.eformsign.webhook.openAPI.method.token.EformsignSignatureMake;
import com.forcs.eformsign.webhook.openAPI.method.token.TokenAccess;
import com.forcs.eformsign.webhook.openAPI.method.token.TokenRefresh;

import org.apache.tomcat.util.bcel.Const;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.json.simple.parser.JSONParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.*;
import java.text.AttributedString;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import static java.awt.SystemColor.window;

@Controller
@RequestMapping(value = "")
public class PageController {

    @RequestMapping(value = "/home")
    public String main(Model model) throws FileNotFoundException {
        System.out.println("homehome");
        JSONObject js = null;
        String api=null;
        String secret=null;

        try {
            js = File.fileRead("token.json");
            Constants.ACCESS_TOKEN = js.get("access-token").toString();
            Constants.REFRESH_TOKEN = js.get("refresh-token").toString();

            System.out.println(Constants.ACCESS_TOKEN);
            System.out.println(Constants.REFRESH_TOKEN);

            TokenRefresh tokenRefresh = new TokenRefresh();
            tokenRefresh.token_refresh();

            System.out.println(Constants.ACCESS_TOKEN);
            System.out.println(Constants.REFRESH_TOKEN);

            js = File.fileRead("tokenInfo.json");
            TokenAccess.member_id = js.get("id").toString();
            api = js.get("api-key").toString();
            secret = js.get("secret").toString();

            model.addAttribute("accessToken", Constants.ACCESS_TOKEN);
            model.addAttribute("refreshToken", Constants.REFRESH_TOKEN);
            model.addAttribute("userId", TokenAccess.member_id);
            model.addAttribute("APIKey", api);
            model.addAttribute("secret", secret);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "home";
    }

    @RequestMapping(value = "/refresh")
    public String tokenrefresh(String accessToken, String refreshToken, Model model) {
        System.out.println("tokenRefresh");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        Constants.ACCESS_TOKEN = accessToken;
        Constants.REFRESH_TOKEN = refreshToken;

        TokenRefresh tokenRefresh = new TokenRefresh();
        tokenRefresh.token_refresh();
        System.out.println("Constants AccessToken :  " + Constants.ACCESS_TOKEN);
        System.out.println("Constants RefreshToken : " + Constants.REFRESH_TOKEN);

        model.addAttribute("accessToken", Constants.ACCESS_TOKEN);
        model.addAttribute("refreshToken", Constants.REFRESH_TOKEN);

        return "TokenRefreshResult";
    }

    @RequestMapping(value = "/login")
    public String login(String userId, String apikey, String secret, Model model) {
        JSONObject jsonObject = new JSONObject();
        String api = "";
        String id="";
        String sKey="";

        System.out.println("login입니다.");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();


        TokenAccess.member_id = userId;
        Constants.API_KEY = apikey;
        EformsignSignatureMake.secret = secret;

        jsonObject.put("id", TokenAccess.member_id);
        jsonObject.put("api-key", Constants.API_KEY);
        jsonObject.put("secret", EformsignSignatureMake.secret);


        File.fileWrite("tokenInfo.json", jsonObject);
        /*try {
            FileWriter file = new FileWriter(".\\src\\main\\java\\com\\forcs\\eformsign\\webhook\\openAPI\\data\\tokenInfo.json");
            file.write(jsonObject.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        TokenAccess tokenAccess = new TokenAccess();
        tokenAccess.token_access();

        System.out.println("Constants AccessToken :  " + Constants.ACCESS_TOKEN);
        System.out.println("Constants RefreshToken : " + Constants.REFRESH_TOKEN);

        try {
            Object ob = new JSONParser().parse(new FileReader(".\\src\\main\\java\\com\\forcs\\eformsign\\webhook\\openAPI\\data\\tokenInfo.json"));
            JSONObject js = (JSONObject) ob;
            api =  js.get("api-key").toString();
            id =  js.get("id").toString();
            sKey = js.get("secret").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("accessToken", Constants.ACCESS_TOKEN);
        model.addAttribute("refreshToken", Constants.REFRESH_TOKEN);
        model.addAttribute("APIKey", api);
        model.addAttribute("userId", id);
        model.addAttribute("secret", sKey);


        //return ResponseEntity.status(302).header("Location", "/home?atoken=" + Constants.ACCESS_TOKEN + "&rtoken=" + Constants.REFRESH_TOKEN).build();
        return "TokenAccessResult";
    }
}
