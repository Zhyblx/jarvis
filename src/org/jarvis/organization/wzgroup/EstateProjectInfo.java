package org.jarvis.organization.wzgroup;

import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;

//房地产项目信息
public class EstateProjectInfo {

    /**
     * 金科项目信息
     *
     * @param shenghui
     * @param city
     * @throws Exception
     */
    public void getJinKeProjectInfo(String shenghui, String city) throws Exception {
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

    }

    /**
     * 苏宁项目信息
     *
     * @throws Exception
     */

    public void getSuNingProjectInfo() throws Exception {
        String[] strAddress = {
                "/Users/zhangyibin/Downloads/苏宁置业-苏宁置业官网.html",
                "/Users/zhangyibin/Downloads/苏宁置业-苏宁置业官网2.html",
                "/Users/zhangyibin/Downloads/苏宁置业-苏宁置业官网3.html"

        };

        for (int i = 0; i < strAddress.length; i++) {
            File file = new File(strAddress[i]);
            Document document = Jsoup.parse(file, "UTF-8", "");
            Elements elements = document.select("[class=list_content]");
            Elements elements1 = elements.select("h1");
            if (i == 0) {
                System.out.println("商业地产");

            } else if (i == 1) {
                System.out.println("住宅地产");

            } else if (i == 2) {
                System.out.println("产城小镇");

            }
            for (int j = 0; j < elements1.size(); j++) {
                String str1 = elements1.get(j).html().toString();
                System.out.println(str1.substring(0, 2) + "," + str1);

            }
            System.out.println("");

        }
    }

    /**
     * 财务数据：
     * 1.按报告期
     * 2.按年度
     * 3.按单季度
     * 4.数据来源：http://f10.eastmoney.com/f10_v2/FinanceAnalysis.aspx?code=sz000656#bfbbb-0
     * @throws Exception
     */
    public void getFinanceTableInfo() throws Exception {

        String[] strAddress1 = {
                "/Users/zhangyibin/Downloads/按报告期.html",
                "/Users/zhangyibin/Downloads/按年度.html",
                "/Users/zhangyibin/Downloads/按单季度.html"
        };

        for (int i = 0; i < strAddress1.length; i++) {
            File file_3 =
                    new File(strAddress1[i]);
            Document document_3 = Jsoup.parse(file_3, "UTF-8", "");
            Elements elements_3 = document_3.select("[id=F10MainTargetDiv]");
            Elements elements1_3 = elements_3.select("tbody").select("tr");
            if (i == 0) {
                System.out.println("按报告期");

            } else if (i == 1) {
                System.out.println("按年度");

            } else if (i == 2) {
                System.out.println("按单季度");

            }
            for (int j = 0; j < elements1_3.size(); j++) {
                System.out.println(elements1_3.get(j).text());

            }
            System.out.println("");

        }

    }

    /**
     * Asset liability ratio：资产负债表
     * <p>
     * 1.按报告期
     * 2.按年度
     * 3.报告期同比
     * 4.年度同比
     * 5.数据来源：http://f10.eastmoney.com/f10_v2/FinanceAnalysis.aspx?code=sz000656#bfbbb-0
     */

    public void getAssetLiabilityRatio() throws Exception {
        String[] strAddress = {
                "/Users/zhangyibin/Downloads/按报告期.html",
                "/Users/zhangyibin/Downloads/按年度.html",
                "/Users/zhangyibin/Downloads/报告期同比.html",
                "/Users/zhangyibin/Downloads/年度同比.html"

        };

        for (int j = 0; j < strAddress.length; j++) {
            File file = new File(strAddress[j]);

            Document document = Jsoup.parse(file, "UTF-8", "");
            Elements elements = document.select("[id=report_zcfzb]");
            Elements elements_table = elements.select("tbody").select("tr");
            if (j == 0) {
                System.out.println("按报告期");

            } else if (j == 1) {
                System.out.println("按年度");

            } else if (j == 2) {
                System.out.println("报告期同比");

            } else if (j == 3) {
                System.out.println("年度同比");

            }

            for (int i = 0; i < elements_table.size(); i++) {
                System.out.println(elements_table.get(i).text());

            }
            System.out.println("");


        }

    }

    public static void main(String[] args) throws Exception {
        EstateProjectInfo estateProjectInfo = new EstateProjectInfo();
//        estateProjectInfo.getAssetLiabilityRatio();

        estateProjectInfo.getFinanceTableInfo();

//        estateProjectInfo.getSuNingProjectInfo();

//        String[] word = new String[]{
//                "重庆", "贵州", "四川", "陕西", "北京",
//                "天津", "河北", "辽宁", "山西", "山东",
//                "河南", "江苏", "安徽", "江西", "广西",
//                "云南", "广东", "福建", "浙江", "上海", "湖北", "湖南", "新疆"
//        };
//
//        for (String str : word) {
//            estateProjectInfo.getJinKeProjectInfo(str, "");
//            System.out.println("");
//
//
//        }

    }
}
