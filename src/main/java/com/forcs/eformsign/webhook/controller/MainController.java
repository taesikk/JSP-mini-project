package com.forcs.eformsign.webhook.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.forcs.eformsign.webhook.data.Result;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/main")
public class MainController {
    private final Logger logger = LoggerFactory.getLogger("log");
    //get방식으로 요청이 들어왔을때
    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<Object> get(@RequestBody(required = false) String params) { //String값으로 body를 받음
        System.out.println("MainController.get");

        if (params == null) {
            params = "{response_code : 200, result : completed}";
        }

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("{\"code\": 200, \n\"result\": \"success\" }"); // 클라이언트에 값 전송
    }

    //post방식으로 요청이 들어왔을때
    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<Object> post(@RequestBody(required = false) String params) { // String값으로 body를 받음
        System.out.println("MainController.post");
        String ip = "";
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        ip = req.getHeader("X-FORWARDED-FOR");

        logger.info("(" + ip + ") " + params);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public void fileWrite(String param, String type) {
        LocalDateTime now = LocalDateTime.now(); // 현재시간
        File file = null;
        FileWriter fileWriter = null;
        BufferedWriter writer = null;
        String ip = "";

        try {
            file = new File(".\\src\\main\\java\\com\\forcs\\eformsign\\webhook\\webhookLog.txt");
            fileWriter = new FileWriter(file, true);
            writer = new BufferedWriter(fileWriter);

            //클라이언트의 ip를 가져옴
            HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            ip = req.getHeader("X-FORWARDED-FOR");
            //ip = req.getRemoteAddr();

            String formatNow = now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd.HH:mm")); // 날짜 포맷 세팅

            writer.write(formatNow + " (" + type + ") (" + ip + ") : " + param + "\n");
            writer.flush();

        } catch (Exception e) {
            e.getMessage();
        }finally {
            try {
                writer.close();
            }catch (Exception exception){
                exception.printStackTrace();
            }

        }
        System.out.println("ip : " + ip);
        /*
        서버 요청 : 15.165.98.188   15.165.115.166
        */

    }












    public String dataParse(String param) {
        JSONParser parser = new JSONParser();
        String temp = param;
        temp = temp.replace("=", "\":\"");
        temp = temp.replace(", ", "\", \"");
        temp = temp.replace("{", "{\"");
        temp = temp.replace("}", "\"}");
        /*try {
            json = (JSONObject) parser.parse(temp);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }*/
        return temp;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/json")
    public ResponseEntity<Object> post2(@RequestBody(required = false) Result param) { // String값으로 body를 받음
        System.out.println("MainController.post");

        //요청받은 body를 파일에 쓰는 메소드
        //fileWrite(params, "POST");

        System.out.println("name : " + param.getDocument().workflow_seq);

        /*String temp = param.getDocument().toString();
        temp = dataParse(temp);
        System.out.println("{\"webhook_id\":\"" + param.getWebhook_id() + "\",\"webhook_name\":\"" + param.getWebhook_name() + "\",\"company_id\":\"" + param.getCompany_id() + "\",\"event_type\":\"" + param.getEvent_type() + "\",\"document\":" + temp + "}");

        System.out.println("JSONObject : " + json.get("id") + ", " + json.get("document_title") + ", " + json.get("status"));*/
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
