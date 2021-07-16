package org.jarvis.organization.wzgroup;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.*;

public class AmsUserPermissionsList {

    private String UM_distinctid = "1768436374053e-0e995a91f92a0a-6d112d7c-13c680-1768436374192f";
    private String Hm_lvt_27d5944aabe587561b8deef6b586aef4 = "1608167687,1608535914,1608779577,1608880640";
    private String admin_auth_ticket = "e0e63287f1bd580203050939df0ccef1";
    private String JSESSIONID = "e4e03c59ae2eb777a75000dc28d7715ddbb7c6fc";
    private String SESSION = "MjQwY2NjNWQtYTFiMy00OTVmLTkwNDktZjM2YzNjOTQ3OTJh";
    private String user = "s%3ATzovqqGzPS-B5P9QCMIewrsaj3uRHzTw.maYtzX02AtU1LGDiq2VTAbBK4JAY1luMVIoDgyq25HI";

    /**
     * 获取页面文档
     *
     * @param url
     * @return document(Html对象文件)
     * @author 明霄
     */
    private Document getDocument(String url) {
        Connection connection = Jsoup
                .connect(url);
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
        Document document = null;
        try {
            document = connection.get();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return document;

    }

    /**
     * 获取权限结构列表
     *
     * @return map(角色id - > 角色名称)
     * @atuhor 明霄
     */
    public Map<String, String> getFindTreeRoles() {
        Map<String, String> map = new HashMap<String, String>();
        Document document = this.getDocument("https://ams.qjdchina.com/forwardGet?url=/front/role/findTreeRoles");
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

    /**
     * 用户权限列表
     *
     * @param roleId(角色ID)
     * @return 权限与用户对应关系
     * @author 明霄
     */
    public String getUserPermissions(String roleId) {
        /*
         * 权限列表
         */
        Document documentPermissions = this.getDocument("https://ams.qjdchina.com/forwardGet?url=/front/role/getAmsPermissions&roleId=" + roleId);
        String strJsonPermissions = documentPermissions.text();
        JSONObject jsonObjectPermissions = new JSONObject(strJsonPermissions);
        JSONArray jsonArrayPermissions = jsonObjectPermissions.getJSONArray("data");
        String dataNamePermissions = "";
        for (int i = 0; i < jsonArrayPermissions.length(); i++) {
            JSONObject jsonObject1Permissions = jsonArrayPermissions.getJSONObject(i);
            dataNamePermissions = dataNamePermissions + String.valueOf(jsonObject1Permissions.get("name")) + ";";

        }

        /*
         * 用户列表
         */
        Document documentUser = this.getDocument("https://ams.qjdchina.com/forwardGet?url=/front/user/getUsersByRoleId&roleId=" + roleId);
        String strJsonUser = documentUser.text();
        JSONObject jsonObjectUser = new JSONObject(strJsonUser);
        JSONArray jsonArrayUser = jsonObjectUser.getJSONArray("data");
        String dataNameUser = "";
        for (int i = 0; i < jsonArrayUser.length(); i++) {
            JSONObject jsonObject1User = jsonArrayUser.getJSONObject(i);
            dataNameUser = dataNameUser + String.valueOf(jsonObject1User.get("name")) + ";";

        }
        return dataNamePermissions + "|" + dataNameUser;

    }

    private AmsUserPermissionsList() {
        //私有化构造

    }

    public static AmsUserPermissionsList getAmsUserPermissionsList() {
        return new AmsUserPermissionsList();

    }

    // 启动数据下载
    public static void main(String[] args) throws Exception {
        AmsUserPermissionsList amsUserPermissionsList = AmsUserPermissionsList.getAmsUserPermissionsList();
        Map<String, String> map = amsUserPermissionsList.getFindTreeRoles();
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String strKey = iterator.next();
            System.out.println(strKey + "|" + map.get(strKey) + "|" +
                    amsUserPermissionsList.getUserPermissions(strKey));

        }
    }
}