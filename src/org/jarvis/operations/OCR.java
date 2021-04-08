package org.jarvis.operations;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class OCR {
    //设置APPID/AK/SK
    private final String APP_ID = "9741399";
    private final String API_KEY = "GOpgz8YVDYGfrC3rQAYvK1mg";
    private final String SECRET_KEY = "zwz7i2WywKT1yfIjGiQFjGDDyl1EWFm8";

    /**
     * 调用OCR高精度解析方法
     * @param picturePath
     * @return
     */
    public List<String> getHighPrecisionParsingData(String picturePath) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        List<String> list=new ArrayList<String>();

        // 可选：设置网络连接参数
        //client.setConnectionTimeoutInMillis(2000);
        //client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", 22);  // 设置http代理
        //client.setSocketProxy("proxy_host", 22);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        //System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 传入可选参数调用接口(高精度版本)
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");

        // 调用接口
        JSONObject res = client.basicAccurateGeneral(picturePath, options);
        JSONArray result=res.getJSONArray("words_result");
        for(int i=0;i<result.length();i++){
            JSONObject data=result.getJSONObject(i);
            list.add(data.get("words").toString());

        }
        return list;

    }

    /**
     * 调用OCR普通解析方法
     * @param picturePath
     * @return
     */
    public List<String> getOrdinaryParsingData(String picturePath) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        List<String> list=new ArrayList<String>();

        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");

        // 调用接口
        JSONObject res = client.basicGeneral(picturePath, options);
        JSONArray result=res.getJSONArray("words_result");
        for(int i=0;i<result.length();i++){
            JSONObject data=result.getJSONObject(i);
            list.add(data.get("words").toString());

        }
        return list;

    }


    public static void main(String[] args) throws  Exception{
        List<String> list=new ArrayList<String>() ;
        OCR ocr=new OCR();
        String[] imgAddress = {"1.png", "2.png", "3.png", "4.png", "5.png"};
        for(String str:imgAddress){
            list.addAll(ocr.getHighPrecisionParsingData("/Users/zhangyibin/Downloads/"+str));
            Iterator<String> iterator=list.iterator();
            while(iterator.hasNext()){
                String imgStr=iterator.next();
                System.out.println(imgStr);

            }

            Thread.sleep(1000);
            list.clear();

        }
    }
}