package com.chenlink.wordocr.controller;

import com.chenlink.wordocr.utils.imageUtils;
import com.google.gson.JsonObject;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.*;

@RestController
@CrossOrigin
@RequestMapping("WordOcr")
public class WordOcrController {

        /**
         * 测试专用接口
         * @return
         */
        @ApiOperation(value="测试专用接口", notes="测试专用接口")
        @RequestMapping(value="/test", method= RequestMethod.GET)
        public String test() {
            return "/index" ;

        }

    /**
     * 上传图片进行识别
     * @param file
     * @return
     */
    @ApiOperation(value="上传文件", notes="上传文件")
    @CrossOrigin
    @PostMapping(value="/upload",consumes = "multipart/*",headers = "content-type=multipart/form-data")
//    @ApiImplicitParam(name = "", value = "身份证号码", required = true, dataType = "String",paramType = "form")
    public String baiduocr(@ApiParam(name = "file",value = "图片文件") @RequestParam(value = "upload") MultipartFile file) throws Exception {
        String msg = "成功";
        int success = 1;
        String data=" ";
        JsonObject json =new JsonObject();
        imageUtils im=new imageUtils();
        StringBuffer sb=new StringBuffer();
        try {
            sb.append(im.basicGeneral(file.getBytes()));
            data=sb.toString();
        } catch (Exception e) {
            success=0;
            msg="失败";
            data="图片信息过于复杂转换失败，请发邮件联系734135938@qq.com进行升级！";
        }
        json.addProperty("msg",msg);
        json.addProperty("success",success);
        json.addProperty("data",data);
        return json.toString();
    }


}
