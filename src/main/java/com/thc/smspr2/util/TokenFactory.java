package com.thc.smspr2.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;


// Security 사용으로 더이상 사용되지 않음.
@Component
public class TokenFactory {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public String generate(String tbuserId, long sec){
        long expiredMillSec = 0;

        Date nowDate = new Date();
        logger.info("now : " + nowDate.getTime());
        nowDate.setTime(nowDate.getTime() + sec * 1000);
        expiredMillSec = nowDate.getTime();
        logger.info("expiredMillSec : " + expiredMillSec);
        String token = "";
        try{
            String secretKey = "1234567890123456";
            token = AES256Cipher.AES_Encode(secretKey, tbuserId + "_" + expiredMillSec);
        } catch (Exception e){
            throw new RuntimeException("AES encrypt failed");
        }
        return token;
    }
    public String verify(String token){
        String decodeToken = "";
        long expiredMillSec = 0;
        long nowMillSec = 0;
        String tbuserId = "";
        try{
            String secretKey = "1234567890123456";
            decodeToken = AES256Cipher.AES_Decode(secretKey, token);

            Date nowDate = new Date();
            nowMillSec = nowDate.getTime();
            String[] arrayToken = decodeToken.split("_");
            expiredMillSec = Long.parseLong(arrayToken[1]);
            tbuserId = arrayToken[0];

        } catch (Exception e){
            logger.info("failed to verify token");
            throw new RuntimeException("AES encrypt failed");
        }
        if(nowMillSec < expiredMillSec){
            return tbuserId;
        } else {
            throw new RuntimeException("expired");
        }
    }
    public String refreshToken(String tbuserId){
        return generate(tbuserId, 60 * 60 * 24 * 7);
    }
    public String accessToken(String refreshToken){
        return generate(verify(refreshToken), 60 * 60 * 24 * 2);
        //return generate(verify(refreshToken), 5);
    }

    //public


}
