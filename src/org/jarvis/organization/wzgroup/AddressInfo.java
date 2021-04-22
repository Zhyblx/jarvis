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
        String[] city = {"北京通州富力中心","秦皇岛富力金禧项目","广州富力天海湾","秦皇岛富力和园","凯里富力东南府","湘江富力城","邹平富力城","太原富力华庭","富力·博士山","珠海富力优派广场","太原富力湾","太原富力天禧公馆","太原富力天禧城","太原富力金禧城","太原富力城","太原富力尚悦居","太原富力城八号园","西安富力环球商品贸易港","包头富力华庭","乌鲁木齐富力城","南京尚悦居"};

        for (String str : city) {
            System.out.println(addressInfo.getAddressInfo(str));

        }
    }
}
