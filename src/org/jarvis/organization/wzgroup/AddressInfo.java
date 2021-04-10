package org.jarvis.organization.wzgroup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.Connection;
import org.json.JSONObject;

public class AddressInfo {

    public String getAddressInfo(String name) {
        String data = "";
        try {
            Connection connection = Jsoup.connect("http://118.31.54.4:5001/ent_credit/parse_address/?name=" + name);
            connection.ignoreContentType(true);
            connection.timeout(2000);
            connection.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) " +
                    "AppleWebKit/537.36 (KHTML, like Gecko) " +
                    "Chrome/81.0.4044.122 Safari/537.36");
            Document document = connection.get();
            JSONObject jsonObject = new JSONObject(document.text());
            JSONObject datas = jsonObject.getJSONObject("datas");
            String province = datas.get("province").toString();
            String city = datas.get("city").toString();
            data = data + province + "," + city;

        } catch (Exception e) {
            data = data + "null" + "," + "null";

        }
        return data;

    }

    public static void main(String[] args) throws Exception {
        AddressInfo addressInfo = new AddressInfo();
        String[] city = {"杭州", "杭州", "杭州", "杭州", "杭州", "杭州", "杭州", "杭州", "杭州", "杭州", "杭州", "杭州", "宁波", "宁波", "宁波", "宁波", "宁波", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "绍兴", "舟山", "舟山", "舟山", "台州", "台州", "台州", "台州", "台州", "台州", "台州", "台州", "湖州", "湖州", "湖州", "湖州", "湖州", "湖州", "衢州", "衢州", "衢州", "衢州", "衢州", "衢州", "嘉兴", "嘉兴", "嘉兴", "嘉兴", "嘉兴", "温州", "温州", "温州", "丽水", "滁州", "滁州", "滁州", "滁州", "滁州", "滁州", "滁州", "滁州", "滁州", "滁州", "宣城", "宣城", "宣城", "宣城", "宣城", "宣城", "宣城", "宣城", "宣城", "芜湖", "芜湖", "芜湖", "芜湖", "芜湖", "宿州", "宿州", "马鞍山", "马鞍山", "安庆", "合肥", "合肥", "泰州", "泰州", "泰州", "泰州", "连云港", "连云港", "南通", "南通", "宿迁", "宿迁", "苏州", "苏州", "扬州", "盐城", "南京", "镇江", "仙桃", "仙桃", "荆门", "济宁", "济宁", "济南", "济南", "济南", "聊城", "上饶", "九江", "抚州", "抚州", "岳阳", "岳阳", "常德", "衡阳", "南平", "上海", "呼和浩特", "呼和浩特", "呼和浩特", "呼和浩特", "呼和浩特"};

        for (String str : city) {
            System.out.println(addressInfo.getAddressInfo(str));

        }
    }
}
