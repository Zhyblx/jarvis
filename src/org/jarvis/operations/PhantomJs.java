package org.jarvis.operations;

import java.lang.Runtime;
import java.lang.Process;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PhantomJs {

    private Runtime runtime = null;
    private Process process = null;
    private InputStream inputStream = null;
    private BufferedReader bufferedReader = null;
    private StringBuffer stringBuffer = null;

    /**
     * PhantomJS是一个可编程的无头浏览器
     * 无头浏览器是指一个完整的浏览器内核,包括js解析引擎,渲染引擎,请求处理等,但是不包括显示和用户交互页面的浏览器。
     * 使用场景：
     * 1.页面自动化测试：希望自动的登陆网站并做一些操作然后检查结果是否正常。
     * 2.网页监控：希望定期打开页面，检查网站是否能正常加载，加载结果是否符合预期。加载速度如何等。
     * 3.网络爬虫：获取页面中使用js来下载和渲染信息，或者是获取链接处使用js来跳转后的真实地址。
     *
     * @param url
     * @return
     */

    public String getHtmlJs(String url) {
        try {
            runtime = Runtime.getRuntime();
            //phantomjs parser.js的路径之间有个空格 本代码只是测试用的绝对路径
            process = runtime.exec("./phantomjs/phantomjs-2.1.1-macosx/bin/phantomjs ./phantomjs/code.js " + url);
            inputStream = process.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            stringBuffer = new StringBuffer();
            String tmp = "";
            while ((tmp = bufferedReader.readLine()) != null) {
                stringBuffer.append(tmp);

            }
            //System.out.println(sbf.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();

    }

    public static void main(String[] args) throws Exception {
        System.out.println(
                new PhantomJs()
                        .getHtmlJs("http://jzsc.mohurd.gov.cn/data/project?complexname=万科")
        );

    }

}

