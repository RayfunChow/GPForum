package com.internship.gpforum.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.internship.gpforum.tool.GsonUtils;
import com.internship.gpforum.tool.HttpUtil;
import com.sun.mail.util.BASE64EncoderStream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class BaiduAPI {
    public static String content_adult(String content){
        String url = "https://aip.baidubce.com/rest/2.0/antispam/v2/spam";
        String feedback="1";
        try {
            if(content.equals(""))
                return "0";
            String param = "content="+content;
            String accessToken = "24.0daf3d58eaf44ca81623bd21d923e8ae.2592000.1565922995.282335-16823011";
            String result = HttpUtil.post(url, accessToken, "application/x-www-form-urlencoded", param);
            JSONObject obj= JSON.parseObject(result);
            feedback=obj.getJSONObject("result").getString("spam").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feedback;
    }

    public static String image_audit(String content) {
        String url = "https://aip.baidubce.com/rest/2.0/solution/v1/img_censor/v2/user_defined";
        try {
            Document document= Jsoup.parse(content);
            Elements element=document.select("img[src]");
            for (Element e : element) {
                System.out.println(e.attr("src"));
                String imgUrl= URLEncoder.encode(e.attr("src"), "UTF-8");
                String param = "imgUrl=" +imgUrl;
                String accessToken = "24.0daf3d58eaf44ca81623bd21d923e8ae.2592000.1565922995.282335-16823011";
                String result = HttpUtil.post(url, accessToken,"application/json;charset=utf-8", param);
                JSONObject obj= JSON.parseObject(result);
                if(obj.getString("conclusion").equals("不合规"))
                    return "不合规";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "合规";
    }
    public static String image(String imageUrl){
        String url = "https://aip.baidubce.com/rest/2.0/solution/v1/img_censor/v2/user_defined";
        try{
            String image=imageUrl.replace("data:image/jpeg;base64,","");
            System.out.printf(image);
            String image1=URLEncoder.encode(image,"UTF-8");
            String param = "image=" +image1;
            String accessToken = "24.0daf3d58eaf44ca81623bd21d923e8ae.2592000.1565922995.282335-16823011";
            String result = HttpUtil.post(url, accessToken,"application/json;charset=utf-8", param);
            JSONObject obj= JSON.parseObject(result);
            if(obj.getString("conclusion").equals("不合规"))
                return "不合规";
        }catch (Exception e){e.printStackTrace();}
        return "合规";
    }

}
