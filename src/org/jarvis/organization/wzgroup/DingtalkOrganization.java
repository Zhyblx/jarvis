package org.jarvis.organization.wzgroup;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class DingtalkOrganization {

    /**
     * 获取Token
     *
     * @return null
     * @throws Exception
     * @author 明霄
     */
    public String getToken() throws Exception {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey("dingpvmbzvg61hgzfesn");
        request.setAppsecret("Jdj2RSelFgiFgll7cbi3TsbcaEOM9O3pzHx8j_hAzFaxk-56pyXIKNlJT9U1QUQ2");
        request.setHttpMethod("GET");
        OapiGettokenResponse response = client.execute(request);
//        System.out.println(response.getBody());
        JSONObject jsonObject = new JSONObject(response.getBody());
        String token = jsonObject.get("access_token").toString();
//        System.out.println("token:" + token);
        return token;

    }

    /**
     * 返回Token
     *
     * @return token
     * @author 明霄
     */
    private String tokenID() throws Exception {
        return this.getToken();
    }

    /**
     * 获取子部门ID列表
     * URL:https://developers.dingtalk.com/document/app/obtain-a-sub-department-id-list-v2?spm=ding_open_doc.document.0.0.2321777dulrOdO#topic-1961082
     *
     * @param deptId(部门ID);如果不传，默认部门为根部门，根部门ID为1;此接口只支持查询下一级子部门，不支持查询多级子部门。
     * @return 返回下一级部门ID
     * @throws Exception
     * @author 明霄
     */
    public long[] getListSubDepartmentID(long deptId) throws Exception {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/department/listsubid");
        OapiV2DepartmentListsubidRequest req = new OapiV2DepartmentListsubidRequest();
        req.setDeptId(deptId);//一级部门：499426523 ;二级部门：499415431
        OapiV2DepartmentListsubidResponse rsp = client.execute(req, this.tokenID());
        JSONObject jsonObject = new JSONObject(rsp.getBody());
        System.out.println("获取子部门ID列表:" + jsonObject);
        JSONObject result = jsonObject.getJSONObject("result");
        List<Object> dept_id_list = result.getJSONArray("dept_id_list").toList();
        long[] deptIdArrays = new long[dept_id_list.size()];
        for (int i = 0; i < dept_id_list.size(); i++) {
            deptIdArrays[i] = Long.parseLong(dept_id_list.get(i).toString());
        }
        return deptIdArrays;

    }

    /**
     * 获取下一级部门列表
     * URL:https://developers.dingtalk.com/document/app/obtain-the-department-list-v2
     *
     * @param deptId(部门ID)
     * @return 返回Map(部门id - > 部门名称)
     * @author 明霄
     */
    public HashMap<String, String> getListDepartments(long deptId) throws Exception {
        HashMap<String, String> mapDeptId = new HashMap<String, String>();
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/department/listsub");
        OapiV2DepartmentListsubRequest req = new OapiV2DepartmentListsubRequest();
        req.setDeptId(deptId);
        req.setLanguage("zh_CN");
        OapiV2DepartmentListsubResponse rsp = client.execute(req, this.tokenID());
        JSONObject jsonObject = new JSONObject(rsp.getBody());
        System.out.println("获取下一级部门列表:" + jsonObject);
        JSONArray result = jsonObject.getJSONArray("result");
        for (int i = 0; i < result.length(); i++) {
            JSONObject jsonObject1 = result.getJSONObject(i);
            String dept_id = jsonObject1.get("dept_id").toString();
            String name = jsonObject1.get("name").toString();
            mapDeptId.put(dept_id, name);
        }
        return mapDeptId;

    }

    /**
     * 获取用户部门列表
     * URL:https://developers.dingtalk.com/document/app/query-the-list-of-department-userids?spm=ding_open_doc.document.0.0.2321777dulrOdO#topic-1960043
     *
     * @param deptId(部门ID)
     * @return 返回部门列表下所有的用户ID
     * @throws Exception
     * @author 明霄
     */
    public String[] getListUserDepartments(long deptId) throws Exception {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/user/listid");
        OapiUserListidRequest req = new OapiUserListidRequest();
        req.setDeptId(deptId);
        OapiUserListidResponse rsp = client.execute(req, this.tokenID());
        JSONObject jsonObject = new JSONObject(rsp.getBody());
        System.out.println("获取用户部门列表:" + jsonObject);
        JSONObject result = jsonObject.getJSONObject("result");
        List<Object> userid_list = result.getJSONArray("userid_list").toList();
        String[] userIDArray = new String[userid_list.size()];
        for (int i = 0; i < userid_list.size(); i++) {
            userIDArray[i] = userid_list.get(i).toString();
        }
        return userIDArray;

    }

    /**
     * 根据userid获取用户详情
     * URL:https://developers.dingtalk.com/document/app/query-user-details
     *
     * @param userid(用户ID)
     * @return 返回用户信息(含角色信息)
     * @throws Exception
     * @author 明霄
     */
    public String getUserDetails(String userid) throws Exception {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/get");
        OapiV2UserGetRequest req = new OapiV2UserGetRequest();
        req.setUserid(userid); //manager1437
        req.setLanguage("zh_CN");
        OapiV2UserGetResponse rsp = client.execute(req, this.tokenID());
        JSONObject jsonObject = new JSONObject(rsp.getBody());
        System.out.println("根据userid获取用户详情:" + jsonObject);
        JSONObject result = jsonObject.getJSONObject("result");
//        System.out.println(result.get("name"));
        return result.get("name").toString();


    }

    /**
     * 发送普通消息
     *
     * @return null(返回空)
     * @throws Exception 获取：setCid() 需要与下方的JSAPI 地址配置使用
     *                   JSAPI：https://open-dev.dingtalk.com/apiExplorer?spm=ding_open_doc.document.0.0.748134928O6bS7#/jsapi?api=biz.chat.pickConversation
     *                   Corpid(企业ID)：dingc3322e8a45fc0cc2f2c783f7214b6d69
     *                   isConfirm：true
     * @author 明霄
     */
    public String setPushMessage() throws Exception {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/message/send_to_conversation");
        OapiMessageSendToConversationRequest req = new OapiMessageSendToConversationRequest();
        req.setSender("manager1437");
        req.setCid("b846736ed9063cb29cac4d1012fe3743");
        OapiMessageSendToConversationRequest.Msg msg = new OapiMessageSendToConversationRequest.Msg();

        // 文本消息
        OapiMessageSendToConversationRequest.Text text = new OapiMessageSendToConversationRequest.Text();
        text.setContent(
                "【审批处理提醒】\n" +
                        "任务名称：授信申报流程\n" +
                        "任务编号：dd92eeff-cd6f-11eb-9550-6241e29e87fa\n" +
                        "客户名称：重庆天投实业有限公司\n" +
                        "发起人：明霄\n" +
                        "提交时间：2021-06-15 08:23:10\n" +
                        "请前往仟金顶-厂家服务平台系统-我的待办事项中进行处理。");
        msg.setText(text);
        msg.setMsgtype("text");
        req.setMsg(msg);

        OapiMessageSendToConversationResponse response = client.execute(req, this.getToken());
        System.out.println(response.getBody());
        return null;

    }


    /**
     * 测试入口
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        //System.out.println(Arrays.toString(new DingtalkOrganization().getListSubDepartmentID(499426523)));// 获取一级部门ID列表
        //HashMap<String, String> hashMap = new DingtalkOrganization().getListDepartments(499415431);//获取具体一级部门下的下一级部门列表；并输出部门名称
        //System.out.println(hashMap.get("499415431"));
        //System.out.println(Arrays.toString(new DingtalkOrganization().getListUserDepartments(499426523)));//获取具体部门下的用户ID

        System.out.println(new DingtalkOrganization().getUserDetails("manager1437"));//获取用户的详情-13616506464
        System.out.println(new DingtalkOrganization().getUserDetails("304402833383"));//获取用户的详情- 13067760265
        //new DingtalkOrganization().setPushMessage();//发送会话消息

    }
}
