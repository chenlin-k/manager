package com.chenlink.wordocr.controller;


import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.faceid.v20180301.FaceidClient;
import com.tencentcloudapi.faceid.v20180301.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLEncoder;


/**
 * 活体检测测试类
 */
@RestController
@CrossOrigin
@RequestMapping("faceid")
public class FaceidController {


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

    private String  appid = "1255853614";

    private String  secretId = "AKIDPfAYmp5OuNgolWvVq35wd9TdcQ2THurd";

    private String  secretKey = "kM1Vo6KSVSJ3iGk9RcfXFoDYiiXB7uVu";


    /**
     * 实名核身鉴权
     *
     * @return
     */
    @RequestMapping(value = "/DetectAuth", method = RequestMethod.GET)
    public void DetectAuth() throws Exception {

        try{

            Credential cred = new Credential(secretId, secretKey);

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("faceid.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            FaceidClient client = new FaceidClient(cred, "ap-shanghai", clientProfile);

            String params = "{}";
            DetectAuthRequest req = DetectAuthRequest.fromJsonString(params, DetectAuthRequest.class);

            DetectAuthResponse resp = client.DetectAuth(req);

            System.out.println(DetectAuthRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

    /**
     * 获取动作顺序
     *
     * @return
     */
    @RequestMapping(value = "/GetActionSequence", method = RequestMethod.GET)
    public void GetActionSequence() throws Exception {

        try{

            Credential cred = new Credential("AKIDPfAYmp5OuNgolWvVq35wd9TdcQ2THurd", "kM1Vo6KSVSJ3iGk9RcfXFoDYiiXB7uVu");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("faceid.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            FaceidClient client = new FaceidClient(cred, "ap-shanghai", clientProfile);

            String params = "{}";
            GetActionSequenceRequest req = GetActionSequenceRequest.fromJsonString(params, GetActionSequenceRequest.class);

            GetActionSequenceResponse resp = client.GetActionSequence(req);

            System.out.println(GetActionSequenceRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }


    }

    /**
     * 获取数字验证码
     *
     * @return
     */
    @RequestMapping(value = "/GetLiveCode", method = RequestMethod.GET)
    public void GetLiveCode() throws Exception {
        try{

            Credential cred = new Credential("", "");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("faceid.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            FaceidClient client = new FaceidClient(cred, "ap-shanghai", clientProfile);

            String params = "{}";
            GetLiveCodeRequest req = GetLiveCodeRequest.fromJsonString(params, GetLiveCodeRequest.class);

            GetLiveCodeResponse resp = client.GetLiveCode(req);

            System.out.println(GetLiveCodeRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

    /**
     * 照片人脸核身
     *
     * @return
     */
    @RequestMapping(value = "/ImageRecognition", method = RequestMethod.GET)
    public void ImageRecognition() throws Exception {

        try{

            Credential cred = new Credential("", "");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("faceid.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            FaceidClient client = new FaceidClient(cred, "ap-shanghai", clientProfile);

            //idcard、name、身份证照片
            String params = "{\"IdCard\":\"2121\",\"Name\":\"陈琳\",\"ImageBase64\":\"1212\"}";
            ImageRecognitionRequest req = ImageRecognitionRequest.fromJsonString(params, ImageRecognitionRequest.class);

            ImageRecognitionResponse resp = client.ImageRecognition(req);

            System.out.println(ImageRecognitionRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }


    /**
     * 活体人脸比对
     *
     * @return
     */
    @RequestMapping(value = "/LivenessCompare", method = RequestMethod.GET)
    public void LivenessCompare() throws Exception {

        try{

            Credential cred = new Credential("", "");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("faceid.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            FaceidClient client = new FaceidClient(cred, "ap-shanghai", clientProfile);

            String params = "{\"VideoBase64\":\"活体检测的视频，视频的BASE64值； BASE64编码后的大小不超过5M，支持mp4、avi、flv格式\",\"LivenessType\":\"活体检测类型，取值：LIP/ACTION/SILENT。 LIP为数字模式，ACTION为动作模式，SILENT为静默模式，三种模式选择一种传入。\"}";
            LivenessCompareRequest req = LivenessCompareRequest.fromJsonString(params, LivenessCompareRequest.class);

            LivenessCompareResponse resp = client.LivenessCompare(req);

            System.out.println(LivenessCompareRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }


    /**
     * 活体人脸核身
     *
     * @return
     */
    @RequestMapping(value = "/LivenessRecognition", method = RequestMethod.GET)
    public void LivenessRecognition() throws Exception {

        try{

            Credential cred = new Credential("", "");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("faceid.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            FaceidClient client = new FaceidClient(cred, "ap-shanghai", clientProfile);

            String params = "{\"VideoBase64\":\"用于活体检测的视频，视频的BASE64值； BASE64编码后的大小不超过5M，支持mp4、avi、flv格式。\",\"LivenessType\":\"活体检测类型，取值：LIP/ACTION/SILENT。 LIP为数字模式，ACTION为动作模式，SILENT为静默模式，三种模式选择一种传入。\"}";
            LivenessRecognitionRequest req = LivenessRecognitionRequest.fromJsonString(params, LivenessRecognitionRequest.class);

            LivenessRecognitionResponse resp = client.LivenessRecognition(req);

            System.out.println(LivenessRecognitionRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }

    /**
     * 获取实名核身结果信息
     *
     * @return
     */
    @RequestMapping(value = "/GetDetectInfo", method = RequestMethod.GET)
    public void GetDetectInfo() throws Exception {

        try{

            Credential cred = new Credential("", "");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("faceid.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            FaceidClient client = new FaceidClient(cred, "ap-shanghai", clientProfile);

            String params = "{\"BizToken\":\"人脸核身流程的标识，调用DetectAuth接口时生成。\",\"InfoType\":\"指定拉取的结果信息，取值（0：全部；1：文本类；2：身份证正反面；3：视频最佳截图照片；4：视频）。 如 134表示拉取文本类、视频最佳截图照片、视频。\",\"RuleId\":\"用于细分客户使用场景，由腾讯侧在线下对接时分配。\"}";
            GetDetectInfoRequest req = GetDetectInfoRequest.fromJsonString(params, GetDetectInfoRequest.class);

            GetDetectInfoResponse resp = client.GetDetectInfo(req);

            System.out.println(GetDetectInfoRequest.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }

    }


}
