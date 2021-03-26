package org.jarvis.organization.wzgroup;

import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;

//金科地产在各省的项目信息

public class ProjectTest {

    public String getJinKeProjectInfo(String shenghui, String city) throws Exception {
        Connection connection = Jsoup
                .connect("https://www.jinke.com/skin/default/project/index.php?shenghui="
                        + shenghui
                        + "&city=" + city);
        connection.ignoreContentType(true);
        connection.timeout(5000);
        connection.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) " +
                "Chrome/81.0.4044.122 Safari/537.36");

        Document document = connection.get();
        Elements body = document.select("body");
        Elements div_class = body.select("[class=b_xm]");
        Elements div_p = div_class.select("p");

        System.out.println(shenghui + "," + city);
        System.out.println(div_p.html().toString());

        return null;

    }

    public void getSuNingProjectInfo() throws Exception {

        //商业地产
        File file = new File("/Users/zhangyibin/Downloads/苏宁置业-苏宁置业官网.html");
        Document document = Jsoup.parse(file, "UTF-8", "");
        Elements elements = document.select("[class=list_content]");
        Elements elements1 = elements.select("h1");
        System.out.println("商业地产");
        for (int i = 0; i < elements1.size(); i++) {
            String str1 = elements1.get(i).html().toString();
            System.out.println(str1.substring(0, 2) + "," + str1);


        }
        System.out.println("");

        //住宅地产
        File file_1 = new File("/Users/zhangyibin/Downloads/苏宁置业-苏宁置业官网2.html");
        Document document_1 = Jsoup.parse(file_1, "UTF-8", "");
        Elements elements_1 = document_1.select("[class=list_content]");
        Elements elements1_1 = elements_1.select("h1");
        System.out.println("住宅地产");
        for (int i = 0; i < elements1_1.size(); i++) {
            String str2 = elements1_1.get(i).html().toString();
            System.out.println(str2.substring(0, 2) + "," + str2);


        }
        System.out.println("");

        //产城小镇
        File file_2 = new File("/Users/zhangyibin/Downloads/苏宁置业-苏宁置业官网3.html");
        Document document_2 = Jsoup.parse(file_2, "UTF-8", "");
        Elements elements_2 = document_2.select("[class=list_content]");
        Elements elements1_2 = elements_2.select("h1");
        System.out.println("产城小镇");
        for (int i = 0; i < elements1_2.size(); i++) {
            String str3 = elements1_2.get(i).html().toString();
            System.out.println(str3.substring(0, 2) + "," + str3);


        }
    }

    // 财务数据
    public void getFinanceTableInfo() throws Exception {
        /*
         * 年度报告
         * 按单季度
         * 单季报告
         *
         */

        File file_3=
                new File("/Users/zhangyibin/Downloads/金科股份(000656.SZ)新财务分析-PC_HSF10资料.html");
        Document document_3=Jsoup.parse(file_3,"UTF-8", "");
        Elements elements_3=document_3.select("[id=F10MainTargetDiv]");
        Elements elements1_3=elements_3.select("tbody").select("tr");
        for(int i=0;i<elements1_3.size();i++){
            System.out.println(elements1_3.get(i).text());


        }

    }

    public static void main(String[] args) throws Exception {
        new ProjectTest().getFinanceTableInfo();

//        new ProjectTest().getSuNingProjectInfo();
//
//
//        String[] word = new String[]{
//                "重庆", "贵州", "四川", "陕西", "北京",
//                "天津", "河北", "辽宁", "山西", "山东",
//                "河南", "江苏", "安徽", "江西", "广西",
//                "云南", "广东", "福建", "浙江", "上海", "湖北", "湖南", "新疆"
//        };
//
//        for (String str : word) {
//            new ProjectTest().getJinKeProjectInfo(str, "");
//            System.out.println("");
//
//
//        }

    }
}
