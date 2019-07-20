package com.internship.gpforum.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.internship.gpforum.tool.GsonUtils;
import com.internship.gpforum.tool.HttpUtil;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FaceService {
    public static String detect(String image) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
        String msg="";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image",image);
            map.put("max_face_num",5);
            map.put("image_type", "BASE64");
            map.put("face_field", "face_shape,face_type,glasses");
            String param = GsonUtils.toJson(map);
            String accessToken = "24.2600c600f215f38fff158fa163aa1aa7.2592000.1565574904.282335-16778468";
            String result = HttpUtil.post(url, accessToken, "application/json", param);

            System.out.println("检测");
            JSONObject obj= JSON.parseObject(result);
            if(obj.getString("error_msg").equals("SUCCESS"))
            {
                msg = obj.getJSONObject("result").getString("face_num");
                if(!msg.equals("1"))
                    msg="检测到多张脸，请重试";
            }else {
                msg = obj.getString("error_msg");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    public static String add(String image,String user_id,String user_info) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        String msg="";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", image);
            map.put("image_type", "BASE64");
            map.put("group_id", "group_repeat");
            map.put("user_id", user_id);
            map.put("user_info", user_info);
            map.put("quality_control", "LOW");

            String param = GsonUtils.toJson(map);
            String accessToken = "24.2600c600f215f38fff158fa163aa1aa7.2592000.1565574904.282335-16778468";
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            JSONObject obj= JSON.parseObject(result);

            msg=obj.getString("error_msg");
            System.out.println(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    public static String search(String image) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        String msg="";
        try {

            Map<String, Object> map = new HashMap<>();
            map.put("image", image);
            map.put("image_type", "BASE64");
            map.put("group_id_list", "group_repeat");
            map.put("quality_control", "LOW");
            String param = GsonUtils.toJson(map);
            String accessToken = "24.2600c600f215f38fff158fa163aa1aa7.2592000.1565574904.282335-16778468";
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            JSONObject obj= JSON.parseObject(result);
            System.out.println("搜索");
            msg="Login Failure";
            if(obj.getString("error_msg").equals("SUCCESS")) {
                String tem = JSON.parseObject(obj.getJSONObject("result").getJSONArray("user_list").getString(0)).getString("score");
                String user_info=JSON.parseObject(obj.getJSONObject("result").getJSONArray("user_list").getString(0)).getString("user_info");
                if (Double.parseDouble(tem) >= 80) {
                    msg =user_info;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }
    public static String faceVerify(String image) {

        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceverify";
        String msg="";
        try {

            Map map = new HashMap<>();
            List list = new ArrayList();
            map.put("image", image);//总数据大小应小于10M
            map.put("face_field", "face_liveness,thresholds");
            map.put("image_type", "BASE64");
            list.add(map);
            String param = GsonUtils.toJson(list);
            String accessToken = "24.2600c600f215f38fff158fa163aa1aa7.2592000.1565574904.282335-16778468";
            String result = HttpUtil.post(url, accessToken, "application/json", param);

            System.out.println("验证");
            JSONObject obj= JSON.parseObject(result);
            if(obj.getString("error_msg").equals("SUCCESS")) {
                if (Double.parseDouble(obj.getJSONObject("result").getString("face_liveness")) >= 0.393241) {
                    msg = "pass";
                } else {
                    msg = "未通过活体检测！";
                }
            }else {
                msg=obj.getString("error_msg");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }
}
