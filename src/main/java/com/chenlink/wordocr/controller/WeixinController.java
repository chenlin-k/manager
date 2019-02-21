package com.chenlink.wordocr.controller;

import com.alibaba.fastjson.JSONObject;
import com.chenlink.wordocr.utils.imageUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


/**
 * 微信授权获取用户信息测试类
 */
@RestController
@CrossOrigin
public class WeixinController {

    /**
     * request
     */
    @Autowired
    private HttpServletRequest request;

    /**
     * response
     */
    @Autowired
    private HttpServletResponse response;

    private String  appid="wxb63e06ab64c1796e";

    private String  appSecret="cd4edc3494822cbe3d41c43a1fe26942";

    private String  redirect_uri="http://njhz7u.natappfree.cc/verifyBack";

    /**
     * 测试专用接口
     *
     * @return
     */
    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public void verify() throws Exception {


        String path = request.getContextPath();
        int port = request.getServerPort();
        String basePath = (port == 80 || port == 443) ? request.getScheme() + "://" + request.getServerName() + path + "/" : request.getScheme() + "://" + request.getServerName() + ":" + port + path + "/";
        String state = "STATE";//state会原样返回
        redirect_uri = URLEncoder.encode(redirect_uri, "utf-8");
        String wxurl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=" + redirect_uri + "&response_type=code&scope=snsapi_userinfo&state=" + state + "#wechat_clientstate";
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML>");
        out.println("<html>");
        out.println("  <head><title>微信授权登录中...</title><meta charset=\"UTF-8\"></head>");
        out.println("  <body>");
        out.print("<script>location.href='" + wxurl + "';</script>");
        out.println("  </body>");
        out.println("</html>");
        out.flush();
        out.close();
    }


    /**
     * 测试专用接口
     *
     * @return
     */
    @RequestMapping(value = "/verify2", method = RequestMethod.GET)
    public void verify2() throws Exception {

        int port = request.getServerPort();
        String state = "STATE";//state会原样返回
        redirect_uri = URLEncoder.encode(redirect_uri, "utf-8");
        String wxurl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=" + redirect_uri + "&response_type=code&scope=snsapi_base&state=" + state + "#wechat_clientstate";
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML>");
        out.println("<html>");
        out.println("  <head><title>微信授权登录中...</title><meta charset=\"UTF-8\"></head>");
        out.println("  <body>");
        out.print("<script>location.href='" + wxurl + "';</script>");
        out.println("  </body>");
        out.println("</html>");
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/verifyBack", method = RequestMethod.GET)
    public void code() throws Exception {

        // TODO Auto-generated method stub
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //接收code和state参数
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        //通过code换取access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + appSecret + "&code=" + code + "&grant_type=authorization_code";
        String message = getUrlReturnMessage(url);
        JSONObject jsontk = JSONObject.parseObject(message);
        //判断是否获取access_token成功
        if (jsontk.containsKey("access_token")) {
            String access_token = jsontk.getString("access_token");
            String openid = jsontk.getString("access_token");
            //通过access_token和openid拉取用户信息(需scope为 snsapi_userinfo)
            String getuserurl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid + "&lang=zh_CN";
            String rsUserInfo = getUrlReturnMessage(getuserurl);
            JSONObject jsonui = JSONObject.parseObject(rsUserInfo);
            //判断是否获取用户信息成功
            if (jsonui.containsKey("openid")) {
                //获取用户信息成功
                System.out.println("openid:" + jsonui.getString("openid"));
                System.out.println("nickname:" + jsonui.getString("nickname"));
                System.out.println("sex:" + jsonui.getString("sex"));
                System.out.println("province:" + jsonui.getString("province"));
                System.out.println("city:" + jsonui.getString("city"));
                System.out.println("country:" + jsonui.getString("country"));
                System.out.println("headimgurl:" + jsonui.getString("headimgurl"));
                String sex = jsonui.getString("sex");
                if (sex.equals("1")) {
                    sex = "男";
                } else {
                    sex = "女";
                }
                PrintWriter out = response.getWriter();
                out.println("<!DOCTYPE HTML>");
                out.println("<html>");
                out.println("  <head><title>微信授权登录中...</title><meta charset=\"UTF-8\"></head>");
                out.println("  <body>");
                out.println("  <div> 头像：<img src= "+ jsonui.getString("headimgurl")  +"> </div>");
                out.println("  <div> 微信名：" + jsonui.getString("nickname") + "</div>");
                out.println("  <div> 性别：" + sex + "</div>");
                out.println("  <div> 国家：" + jsonui.getString("country") + "</div>");
                out.println("  <div> 省份：" + jsonui.getString("province") + "</div>");
                out.println("  <div> 城市：" + jsonui.getString("city") + "</div>");
                out.println("  </body>");
                out.println("</html>");
                out.flush();
                out.close();
            } else {
                //获取用户信息失败
            }
        } else {
            //获取access_token失败
        }

    }






    public static String getUrlReturnMessage(String url) throws Exception {
        URL getUrl = new URL(url);
        HttpURLConnection http = (HttpURLConnection) getUrl.openConnection();
        http.setRequestMethod("GET");
        http.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        http.setDoOutput(true);
        http.setDoInput(true);
        http.connect();
        InputStream is = http.getInputStream();
        int size = is.available();
        byte[] b = new byte[size];
        is.read(b);
        String message = new String(b, "UTF-8");
        return message;
    }


}
