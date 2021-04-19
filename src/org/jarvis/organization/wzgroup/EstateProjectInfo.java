package org.jarvis.organization.wzgroup;

import org.jarvis.operations.PhantomJs;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;

//房地产项目信息
public class EstateProjectInfo {

    private AddressInfo addressInfo = new AddressInfo();

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
     *
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

    /**
     * 房天下项目布局
     *
     * @throws Exception
     */
    public void getFangProjectInfo() throws Exception {
        String[] strAddress = {"/Users/zhangyibin/Downloads/城市版-企业-项目布局.html"};
        for (int i = 0; i < strAddress.length; i++) {
            File file = new File(strAddress[i]);
            Document document = Jsoup.parse(file, "UTF-8", "");
            Elements title = document.select("[class=cont_tit mt05 clearfix]");
            Elements title_fl = title.select("[class=fl]");
//            System.out.println(title_fl.text());

            Elements divContent = document.select("[class=cont_tblist cont_tblist-floating]");
            Elements tbody = divContent.select("tbody").select("tr");
            for (int j = 0; j < tbody.size(); j++) {
                System.out.println(title_fl.text() + " " + tbody.get(j).text());
            }
        }
    }

    /**
     * 恒大项目
     *
     * @throws Exception
     */
    public void getEvergrandeInfo() throws Exception {
        AddressInfo addressInfo = new AddressInfo();
//        String[] strAddressArray = {
//                "河南", "江苏", "山东", "北京",
//                "四川", "深圳", "华东", "广西",
//                "湖北", "江西", "贵州", "湖南", "辽宁", "福建",
//                "安徽", "珠三角", "山西", "内蒙古", "吉林",
//                "黑龙江", "甘肃", "云南", "陕西", "海南", "新疆"};
        String[] strAddressArray = {"重庆"};
        for (String str : strAddressArray) {
            File file = new File("/Users/zhangyibin/Downloads/HTML/" + str + ".html");
            Document document = Jsoup.parse(file, "UTF-8", "");
            Elements elements = document.select("[class=projs clearfix]");
            Elements elements1 = elements.select("[class=proj Blist]").select("p");
            for (int i = 0; i < elements1.size(); i++) {
                String info = elements1.get(i).text().toString();
                try {
                    String city = info.substring(0, info.indexOf("恒"));
                    System.out.println(addressInfo.getAddressInfo(city) + "," + info);

                } catch (Exception e) {
                    System.out.println("null" + "," + "null" + "," + info);
                }
            }
        }
    }

    /**
     * 龙湖项目
     *
     * @throws Exception
     */
    public void getLongForInfo() throws Exception {
        AddressInfo addressInfo = new AddressInfo();
        // 分类： 办公、待公布：车位、商铺1、2
        File file = new File("/Users/zhangyibin/Downloads/HTML/待公布.html");
        Document document = Jsoup.parse(file, "UTF-8", "");
        Elements projectList_box = document.select("[class=projectList_box]");
        Elements projectList_left = projectList_box.select("[class=projectList_left]");
        Elements project_item = projectList_left.select("[class=project-item]");
        for (int i = 0; i < project_item.size(); i++) {
            Elements elements = project_item.get(i).select("[class=item-name text-eli]");
            String data = elements.text();
            String city = data.substring(0, data.indexOf(" "));
            String project = data.substring(data.indexOf(" ") + 1, data.length());
            System.out.println(addressInfo.getAddressInfo(city) + "," + project);

        }
    }

    /**
     * 中国铁建
     *
     * @throws Exception
     */
    public void getCrccreInfo() throws Exception {
        for (int j = 1; j < 16; j++) {
            Connection connection = Jsoup.connect("http://crccre.crcc.cn/jrobotwww/search.do?webid=29&pg=12&p="
                    + j + "&tpl=&category=%E9%A1%B9%E7%9B%AE%E5%88%97%E8%A1%A8&q=&pos=&od=&date=&date=&criteria_field_1954=&criteria_field_2004=&criteria_field_2005=&criteria_field_2006=");
            connection.ignoreContentType(true);
            connection.timeout(5000);
            connection.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) " +
                    "AppleWebKit/537.36 (KHTML, like Gecko) " +
                    "Chrome/81.0.4044.122 Safari/537.36");
            Document document = connection.get();
            Elements box = document.select("[class=jsearch-result-box pic-box]");
            for (int i = 0; i < box.size(); i++) {
                Elements title = box.get(i).select("[class=pic-title]");
                String data = title.select("a").text();
                String address = title.select("[class=xmlbaddress]").text();
                String addressInfoc = address.substring(address.indexOf("地址：") + 3, address.length());
//            System.out.println(data+","+addressInfo);
                System.out.println(addressInfo.getAddressInfo(addressInfoc) + "," + data);

            }
        }
    }

    /**
     * 合景泰富集团
     *
     * @throws Exception
     */
    public void getKwgGroupHoldings() throws Exception {
        String[] strFile = {"住宅地产", "写字楼", "购物中心"};

        for (String str : strFile) {
            File file = new File("/Users/zhangyibin/Downloads/HTML/" + str + ".html");
            Document document = Jsoup.parse(file, "UTF-8", "");
            Elements div2 = document.select("[id=div2]");
            Elements eList = div2.select("[class=c_list c_list_m]");
            Elements fadeInUp = eList.select("[class=wow z_fadeInUp animated]");
            for (int i = 0; i < fadeInUp.size(); i++) {
                Elements contP = fadeInUp.get(i).select("[class=contP]");
                String address = contP.select("p").text();
                String data = contP.select("h4").text();
                try {
                    System.out.println(addressInfo.getAddressInfo(address) + "," + data + "," + str);

                } catch (Exception e) {
                    System.out.println(address + "," + data + "," + str);

                }
            }
        }
    }

    /**
     * 祥生地产集团
     *
     * @throws Exception
     */
    public void getXsjt() throws Exception {
        String[] strAddress = {"杭州", "宁波", "绍兴", "舟山", "台州", "湖州",
                "衢州", "嘉兴", "温州", "丽水", "滁州", "宣城", "芜湖", "宿州",
                "马鞍山", "安庆", "合肥", "泰州", "连云港", "南通", "宿迁", "苏州",
                "扬州", "盐城", "南京", "镇江", "武汉", "仙桃", "荆州", "荆门", "济宁",
                "济南", "聊城", "上饶", "九江", "抚州", "岳阳", "常德", "衡阳", "南平",
                "上海", "呼和浩特"};

        for (String str : strAddress) {
            try {
                System.out.println("--" + str);
                File file = new File("/Users/zhangyibin/Downloads/HTML/" + str + ".html");
                Document document = Jsoup.parse(file, "UTF-8", "");
                Elements elements = document.select("[class=det f-cb]");
                for (int i = 0; i < elements.size(); i++) {
                    String href = elements.get(i).select("a").text().toString();
                    String data = href.replaceAll(" ", "\r\n");
                    System.out.println(data);

                }
                System.out.println("");

            } catch (Exception e) {
                // 异常不处理

            }

        }
    }

    /**
     * 奥园集团有限公司
     * 地产项目
     *
     * @throws Exception
     */
    public void getAoyuanRealEstate() throws Exception {
        PhantomJs phantomJs = new PhantomJs();
        String Url = "https://www.aoyuan.com.cn/realEstate/realEstate.aspx?strm=115001001&page="; //地产项目-15页
//        String Url = "https://www.aoyuan.com.cn/realEstate/realEstate.aspx?strm=115007002&page="; //商业项目-4页

        for (int j = 1; j < 16; j++) {
            try {
                String html = phantomJs.getHtmlJs(Url + j);
                Document document = Jsoup.parse(html);
                Elements docList = document.select("[class=list]");
                Elements projectName = docList.select("ul").select("li").select("[class=name]");
                System.out.println(projectName.html());// 这里一定要打印以下才可以输出完整的数据
                Elements projectAddress = docList.select("ul").select("li").select("[class=box]");
//                for (int i = 2; i < projectName.size()+2; i++) {
//                    System.out.println(
//                            addressInfo.getAddressInfo(projectAddress.get(i).select("p").text())
//                                    + ","
//                                    + projectName.get(i).text());
//
//                }

            } catch (Exception e) {
                // 异常不处理
            }
        }
    }

    /**
     * 重庆华宇集团有限公司
     *
     * @throws Exception
     */
    public void getCqhyrc() throws Exception {
        File file = new File("/Users/zhangyibin/Downloads/HTML/安徽.html");
        Document document = Jsoup.parse(file, "UTF-8", "");
        Elements div_list = document.select("[class=in_list]");
        for (int i = 0; i < div_list.size(); i++) {
            String str = "[name=div_list_" + (i + 1) + "]";
            if (i == 0) {
                System.out.println("重庆");
            }
            if (i == 1) {
                System.out.println("四川");
            }
            if (i == 2) {
                System.out.println("江苏");
            }
            if (i == 3) {
                System.out.println("安徽");
            }
            if (i == 4) {
                System.out.println("浙江");
            }
            if (i == 5) {
                System.out.println("辽宁");
            }
            if (i == 6) {
                System.out.println("湖北");
            }
            if (i == 7) {
                System.out.println("河南");
            }
            if (i == 8) {
                System.out.println("陕西");
            }
            if (i == 9) {
                System.out.println("天津");
            }
            if (i == 10) {
                System.out.println("广东");
            }
            Elements elements = div_list.select(str);
            String href = elements.select("a").text().toString();
            String data = href.replaceAll(" ", "\r\n");
            System.out.println(data);
            System.out.println("");

        }
    }

    /**
     * 中国金茂控股集团有限公司
     *
     * @throws Exception
     */
    public void getChinaJinMao() throws Exception {
        Document document = null;
        Elements contentPane = null;
        /*
         * 城市运营{34766、34767、34768 }
         *
         * 酒店经营{23486}
         *
         * 零售商业{23487}
         *
         * 商务租赁{23483}
         *
         * 物业开发{23471、23510、23511}
         *
         */

        String[] type = {"城市运营", "酒店经营", "零售商业", "商务租赁", "物业开发"};
        for (String strType : type) {
            document = Jsoup
                    .parse(new File("/Users/zhangyibin/Downloads/HTML/" + strType + ".html"),
                            "UTF-8", "");
            contentPane = document.select("[id=dnn_ContentPane]");
            if (strType.equals("城市运营")) {
                String[] id = {"34766", "34767", "34768"};
                for (String str_id : id) {
                    //System.out.println("id>>>"+str_id);
                    Elements content_id = contentPane.select("[id=Content-" + str_id + "]");
                    Elements title = content_id.select("[class=xcsyy-news-item-title]");
                    String project = title.text().toString();
                    String data = project.replaceAll(" ", "\r\n");
                    System.out.println(data);

                }
            }

            if (strType.equals("酒店经营")) {
                String[] id = {"23486"};
                for (String str_id : id) {
                    //System.out.println("id>>>"+str_id);
                    Elements content_id = contentPane.select("[id=Content-" + str_id + "]");
                    Elements title = content_id.select("[class=xcsyy-news-item-title]");
                    String project = title.text().toString();
                    String data = project.replaceAll(" ", "\r\n");
                    System.out.println(data);

                }
            }

            if (strType.equals("零售商业")) {
                String[] id = {"23487"};
                for (String str_id : id) {
                    //System.out.println("id>>>"+str_id);
                    Elements content_id = contentPane.select("[id=Content-" + str_id + "]");
                    Elements title = content_id.select("[class=xcsyy-news-item-title]");
                    String project = title.text().toString();
                    String data = project.replaceAll(" ", "\r\n");
                    System.out.println(data);

                }
            }

            if (strType.equals("商务租赁")) {
                String[] id = {"23483"};
                for (String str_id : id) {
                    //System.out.println("id>>>"+str_id);
                    Elements content_id = contentPane.select("[id=Content-" + str_id + "]");
                    Elements title = content_id.select("[class=xcsyy-news-item-title]");
                    String project = title.text().toString();
                    String data = project.replaceAll(" ", "\r\n");
                    System.out.println(data);

                }
            }

            if (strType.equals("物业开发")) {
                String[] id = {"23471", "23510", "23511"};
                for (String str_id : id) {
                    //System.out.println("id>>>"+str_id);
                    Elements content_id = contentPane.select("[id=Content-" + str_id + "]");
                    Elements title = content_id.select("[class=wykf-news-item-title]");
                    String project = title.text().toString();
                    String data = project.replaceAll(" ", "\r\n");
                    System.out.println(data);

                }
            }

        }

    }

    /**
     * 优采项目
     *
     * @throws Exception
     */
    public void getYouCaiYunIndustryInfo() throws Exception {
        String[] strIndustryInfo = {"机电类", "精装修", "建筑部品", "设计类",
                "土建类", "市政园林类", "勘察检测类", "咨询服务类", "游乐设施", "招商运营"};
        for (String str : strIndustryInfo) {
            File file = new File("/Users/zhangyibin/Downloads/HTML/" + str + ".html");
            Document document = Jsoup.parse(file, "UTF-8", "");
            Elements elements = document.select("[class=checklogolist clfenleibnox layui-clear ]").select("li");
            for (int i = 0; i < elements.size(); i++) {
                System.out.println(str + "," + elements.get(i).text().toString());
            }
        }
    }

    /**
     * 东财财务表公司简称
     * @throws Exception
     */

    public void getNewFinanceAnalysis() throws Exception {
        File file=new File("/Users/zhangyibin/Downloads/财务数据/东财财务表公司简称0416.json");
        InputStream inputStream=new FileInputStream(file);
        InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        String strJson="";
        String strJsonLine="";
        while((strJson=bufferedReader.readLine())!=null){
            strJsonLine=strJsonLine+strJson;

        }
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();

        JSONObject jsonObject=new JSONObject(strJsonLine);
        System.out.println(jsonObject.toString());

    }

    public static void main(String[] args) throws Exception {
        EstateProjectInfo estateProjectInfo = new EstateProjectInfo();
        estateProjectInfo.getNewFinanceAnalysis();


//        estateProjectInfo.getYouCaiYunIndustryInfo();
//        estateProjectInfo.getAoyuanRealEstate();
//        estateProjectInfo.getChinaJinMao();
//        estateProjectInfo.getCqhyrc();
//        estateProjectInfo.getAoyuanRealEstate();
//        estateProjectInfo.getXsjt();
//        estateProjectInfo.getKwgGroupHoldings();
//        estateProjectInfo.getCrccreInfo();
//        estateProjectInfo.getLongForInfo();
//        estateProjectInfo.getEvergrandeInfo();
//        estateProjectInfo.getFangProjectInfo();
//        estateProjectInfo.getAssetLiabilityRatio();
//        estateProjectInfo.getFinanceTableInfo();
//        estateProjectInfo.getSuNingProjectInfo();

//        String[] word = new String[]{
//                "重庆", "贵州", "四川", "陕西", "北京",
//                "天津", "河北", "辽宁", "山西", "山东",
//                "河南", "江苏", "安徽", "江西", "广西",
//                "云南", "广东", "福建", "浙江", "上海", "湖北", "湖南", "新疆"
//        };
//        for (String str : word) {
//            estateProjectInfo.getJinKeProjectInfo(str, "");
//            System.out.println("");
//
//
//        }

    }
}
