package org.jarvis.organization.wzgroup;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.util.*;

public class AmsUserPermissionsList {

    private String UM_distinctid = "1768436374053e-0e995a91f92a0a-6d112d7c-13c680-1768436374192f";
    private String Hm_lvt_27d5944aabe587561b8deef6b586aef4 = "1608167687,1608535914,1608779577,1608880640";
    private String admin_auth_ticket = "e0e63287f1bd580203050939df0ccef1";
    private String JSESSIONID = "e4e03c59ae2eb777a75000dc28d7715ddbb7c6fc";
    private String SESSION = "MjQwY2NjNWQtYTFiMy00OTVmLTkwNDktZjM2YzNjOTQ3OTJh";
    private String user = "s%3ATzovqqGzPS-B5P9QCMIewrsaj3uRHzTw.maYtzX02AtU1LGDiq2VTAbBK4JAY1luMVIoDgyq25HI";

    /**
     * 获取权限结构列表
     *
     * @return
     * @throws Exception
     */
    public Map<String, String> getFindTreeRoles() throws Exception {
        Map<String, String> map = new HashMap<String, String>();

        Connection connection = Jsoup
                .connect("https://ams.qjdchina.com/forwardGet?url=/front/role/findTreeRoles");
        connection.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) " +
                "Chrome/81.0.4044.122 Safari/537.36");
        connection.ignoreContentType(true);
        connection.timeout(20000);
        connection.cookie("UM_distinctid", this.UM_distinctid);
        connection.cookie("Hm_lvt_27d5944aabe587561b8deef6b586aef4", this.Hm_lvt_27d5944aabe587561b8deef6b586aef4);
        connection.cookie("admin_auth_ticket", this.admin_auth_ticket);
        connection.cookie("JSESSIONID", this.JSESSIONID);
        connection.cookie("SESSION", this.SESSION);
        connection.cookie("user", this.user);

        Document document = connection.get();
        String strJson = document.text();
        JSONObject jsonObject = new JSONObject(strJson);
        String data = String.valueOf(jsonObject.get("data"));
        JSONObject jsonObject1 = new JSONObject(data);
        Set<String> set = jsonObject1.toMap().keySet();
        for (String str : set) {
            JSONObject jsonObject2 = new JSONObject(jsonObject1.get(str).toString());
            map.put(jsonObject2.get("roleId").toString(), jsonObject2.get("roleName").toString());

        }
        return map;
    }

    public String getUserPermissions(String roleId) throws Exception {
        /**
         * 权限列表
         */
        Connection connectionPermissions = Jsoup
                .connect("https://ams.qjdchina.com/forwardGet?url=/front/role/getAmsPermissions&roleId=" + roleId);
        connectionPermissions.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) " +
                "Chrome/81.0.4044.122 Safari/537.36");
        connectionPermissions.ignoreContentType(true);
        connectionPermissions.timeout(20000);
        connectionPermissions.cookie("UM_distinctid", this.UM_distinctid);
        connectionPermissions.cookie("Hm_lvt_27d5944aabe587561b8deef6b586aef4", this.Hm_lvt_27d5944aabe587561b8deef6b586aef4);
        connectionPermissions.cookie("admin_auth_ticket", this.admin_auth_ticket);
        connectionPermissions.cookie("JSESSIONID", this.JSESSIONID);
        connectionPermissions.cookie("SESSION", this.SESSION);
        connectionPermissions.cookie("user", this.user);

        Document documentPermissions = connectionPermissions.get();
        String strJsonPermissions = documentPermissions.text();
        JSONObject jsonObjectPermissions = new JSONObject(strJsonPermissions);
        JSONArray jsonArrayPermissions = jsonObjectPermissions.getJSONArray("data");
        String dataNamePermissions = "";
        for (int i = 0; i < jsonArrayPermissions.length(); i++) {
            JSONObject jsonObject1Permissions = jsonArrayPermissions.getJSONObject(i);
            dataNamePermissions = dataNamePermissions + String.valueOf(jsonObject1Permissions.get("name")) + ";";

        }
//        System.out.println(dataNamePermissions);

        /**
         * 用户列表
         */
        Connection connectionUser = Jsoup
                .connect("https://ams.qjdchina.com/forwardGet?url=/front/user/getUsersByRoleId&roleId=" + roleId);
        connectionUser.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) " +
                "Chrome/81.0.4044.122 Safari/537.36");
        connectionUser.ignoreContentType(true);
        connectionUser.timeout(20000);
        connectionUser.cookie("UM_distinctid", this.UM_distinctid);
        connectionUser.cookie("Hm_lvt_27d5944aabe587561b8deef6b586aef4", this.Hm_lvt_27d5944aabe587561b8deef6b586aef4);
        connectionUser.cookie("admin_auth_ticket", this.admin_auth_ticket);
        connectionUser.cookie("JSESSIONID", this.JSESSIONID);
        connectionUser.cookie("SESSION", this.SESSION);
        connectionUser.cookie("user", this.user);

        Document documentUser = connectionUser.get();
        String strJsonUser = documentUser.text();
        JSONObject jsonObjectUser = new JSONObject(strJsonUser);
        JSONArray jsonArrayUser = jsonObjectUser.getJSONArray("data");
        String dataNameUser = "";
        for (int i = 0; i < jsonArrayUser.length(); i++) {
            JSONObject jsonObject1User = jsonArrayUser.getJSONObject(i);
            dataNameUser = dataNameUser + String.valueOf(jsonObject1User.get("name")) + ";";

        }
//        System.out.println(dataNameUser);

        return dataNamePermissions + "|" + dataNameUser;

    }


    public static void main(String[] args) throws Exception {
        AmsUserPermissionsList amsUserPermissionsList = new AmsUserPermissionsList();
        Map<String,String> map=amsUserPermissionsList.getFindTreeRoles();
        Iterator<String> iterator=map.keySet().iterator();
        while(iterator.hasNext()){
            String strKey=iterator.next();
            System.out.println(strKey+"|"+map.get(strKey)+"|"+
                    amsUserPermissionsList.getUserPermissions(strKey));

        }

//        System.out.println(amsUserPermissionsList.getUserPermissions("4"));

    }
}
