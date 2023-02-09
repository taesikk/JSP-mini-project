package com.forcs.eformsign.webhook.controller;

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

    //get방식으로 요청이 들어왔을때
    @RequestMapping(method = RequestMethod.GET, value = "")
    public ResponseEntity<Object> get(@RequestBody(required = false) String params) { //String값으로 body를 받음
        System.out.println("MainController.get");

        if (params == null) {
            params = "{response_code : 200, result : completed}";
        }

        //요청받은 body를 파일에 쓰는 메소드
        fileWrite(params, "GET");

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body("{\"code\": 200, \n\"result\": \"success\" }"); // 클라이언트에 값 전송
    }

    //post방식으로 요청이 들어왔을때
    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<Object> post(@RequestBody(required = false) String params) { // String값으로 body를 받음
        System.out.println("MainController.post");

        try {
//            String privateKeyHex = "3041020100301306072a8648ce3d020106082a8648ce3d030107042730250201010420bd35442dcba89032946efe52dc0fc756817fc2cdf6c1fb90df3f88c19fc33aad";
////		String publicKeyHex = "3059301306072a8648ce3d020106082a8648ce3d030107034200045ac8a472cee38601e99b2a2d731c958e738eee1ee6aca28f6f5637f231e9a8444f3cb80d9ce6c5bace1d0e71167673ff81743e0ea811ebd999f2f314f1d0a676";
            String publicKeyHex = "3059301306072a8648ce3d020106082a8648ce3d03010703420004c04deed1347f2c7573ef2a1727cbc58bb4ee4ad6c3dcf5c89fd29559e982396e5c929e5434637fe98d483ad2ffcdd38f44bf2a8bdada8254b654e693510670a4";  // 비즈앤플랫폼
            String eformsign_signature = "3059301306072a8648ce3d020106082a8648ce3d030107034200043eb1d97531829336cdae92017dc26f587264b89432d8759f2fb53536f6bdac356f083cf37e44e318ef1a86d5598d95bce523e2c1d7ae5a43acbd83567c0676d1";  // 비즈앤플랫폼

            //private key
//            KeyFactory privateKeyFact = KeyFactory.getInstance("EC");
//            PKCS8EncodedKeySpec psks8KeySpec = new PKCS8EncodedKeySpec(new BigInteger(privateKeyHex,16).toByteArray());
//            PrivateKey privateKey = privateKeyFact.generatePrivate(psks8KeySpec);
//
//            //signature
//            String testData = "{\"test\":\"signature test\"}";
//            Signature ecdsa = Signature.getInstance("SHA256withECDSA");
//            ecdsa.initSign(privateKey);
//            ecdsa.update(testData.getBytes("UTF-8"));
//            String eformsign_signature = new BigInteger(ecdsa.sign()).toString(16);
//
//            System.out.println("data : "+testData);
//            System.out.println("eformsign_signature : "+eformsign_signature);

//		eformsign_signature = "30440220735abb18480080edac2e3b398e3b3c1df7b8b23280cd4ccd79b41536140993650220745cf13a450b87b63321cadb35189cbd305b52855794f298bb2e9034ad67cc57";

            //public key
            KeyFactory publicKeyFact = KeyFactory.getInstance("EC");
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(new BigInteger(publicKeyHex,16).toByteArray());
            PublicKey publicKey = publicKeyFact.generatePublic(x509KeySpec);

            System.out.println("publicKey : "+publicKey);

            //verify
            Signature signature = Signature.getInstance("SHA256withECDSA");
            signature.initVerify(publicKey);
            signature.update(params.getBytes("UTF-8"));
            if(signature.verify(new BigInteger(eformsign_signature,16).toByteArray())){
                //검증 성공
                System.out.println("검증 성공");
            }else{
                //검증 실패
                System.out.println("검증 실패");
            }

        }catch (Exception e){
            e.getMessage();
        }

        //요청받은 body를 파일에 쓰는 메소드
        fileWrite(params, "POST");

        System.out.println("result : " + params);
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
