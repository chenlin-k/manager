package com.chenlink.wordocr.utils;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;

public class imageUtils {

    //设置APPID/AK/SK
    public static final String APP_ID = "10962508";
    public static final String API_KEY = "dVbqbs12iYvjVKulZS8QfrNw";
    public static final String SECRET_KEY = "34PCnsmh3WuqVxHgOwzbqhTfPOIFsI1w";

//    public static void main(String[] args) throws IOException {
//
//        File file = new File("d:\\pdf");
//        if (file.exists()) {
//            File[] files = file.listFiles();
//            if (files.length == 0) {
//                System.out.println("文件夹是空的!");
//                return;
//
//            } else {
//                StringBuffer sb = new StringBuffer();
//                for (File singlefile : files) {
////                    String path = singlefile.getPath();
//                    imageUtils im = new imageUtils();
//                    byte[] b=im.getBytes(singlefile);
//                    sb.append(im.basicGeneral(b) + "\r\n\n");
//                }
//                FileOutputStream io = new FileOutputStream("d:\\result.txt");
//                BufferedWriter bw = null;
//                bw = new BufferedWriter(new OutputStreamWriter(io, "UTF-8"));
//                bw.write(sb.toString());
//                bw.close();
//            }
//        } else {
//            System.out.println("文件不存在!");
//        }
//
//
//    }

    private byte[] getBytes(File file){
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public StringBuffer basicGeneral(byte[] bytes) throws IOException {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        JSONObject res = client.basicAccurateGeneral(bytes, options);
        // 参数为本地图片二进制数组
        JSONArray ja = res.getJSONArray("words_result");
        Iterator<Object> it = ja.iterator();
        StringBuffer sb = new StringBuffer();
        while (it.hasNext()) {
            JSONObject ob = (JSONObject) it.next();
            sb.append(ob.getString("words") + "\r\n");
        }
//        System.out.println(sb.toString());
        return sb;
    }
}
