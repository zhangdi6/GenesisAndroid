package com.iruiyou.common.http.task;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.iruiyou.common.http.HttpService;
import com.iruiyou.common.http.UrlContent;
import com.iruiyou.common.utils.EncodeUtils;
import com.iruiyou.common.utils.ParamUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.SystemUtil;
import com.iruiyou.http.retrofit_rx.Api.HttpManagerApi;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextSubListener;
import com.ta.utdid2.android.utils.StringUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 多api共存方案
 * Created by WZG on 2017/4/13.
 */

public class UserTask extends HttpManagerApi {

    public UserTask(HttpOnNextListener onNextListener, RxAppCompatActivity appCompatActivity) {
        super(onNextListener, appCompatActivity);
        /*统一设置*/
        setCache(false);
    }

    public UserTask(HttpOnNextSubListener onNextSubListener, RxAppCompatActivity appCompatActivity) {
        super(onNextSubListener, appCompatActivity);
        /*统一设置*/
        setCache(false);
    }


    /**
     * post请求演示
     * api-1
     *
     * @param all 参数
     */
    public void postApi(final boolean all) {
        /*也可单独设置请求，会覆盖统一设置*/
        setCache(false);
        setMethod("AppFiftyToneGraph/videoLink");
        HttpService httpService = getRetrofit().create(HttpService.class);
        doHttpDeal(httpService.getAllVedioBy(all));
    }

    /**
     * post请求演示
     * api-2
     *
     * @param all 参数
     */
    public void postApiOther(boolean all) {
        setCache(true);
        setMethod("AppFiftyToneGraph/videoLink");
        HttpService httpService = getRetrofit().create(HttpService.class);
        doHttpDeal(httpService.getAllVedioBy(all));
    }

    public void getL() {
        setCache(false);//是否需要缓存
        setBaseUrl("https://mu-api.hejf.com/");//更换前缀,不更换可以不设置;默认前缀在{@link com.iruiyou.http.retrofit_rx.Api.BaseApi}中
        HttpService httpService = getRetrofit().create(HttpService.class);
        doHttpDeal(httpService.getL());
    }

    /**
     * 注册
     *
     * @param name
     * @param password
     */
    public void register(String userId, String name, String password, String invitedCode) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("name", name);
        map.put("password", password);
        map.put("invitedCode", invitedCode);
        map.put("lang", SharePreferenceUtils.getBaseSharePreference().readLanguage());
        doHttpDeal(httpService.register(map));
    }


    public void register2( String phone,String countryCode) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("countryCode", countryCode);
        map.put("lang", SharePreferenceUtils.getBaseSharePreference().readLanguage());
        doHttpDeal(httpService.register(map));
    }


    /**
     * 获取斗米职位列表
     *
     */
    public void getDoumiPositionList(String domain,String job_type,int job_date_type,int page) {
        setCache(false);
        HttpService httpService = getPositionRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("domain",domain);
        map.put("job_type",job_type);
        map.put("job_date_type",job_date_type);
        map.put("page",page);
        map.put("data_type",0);
        doHttpDeal(httpService.getDoumiPositionList(map));
    }
    public void getDoumiPositionLists() {
        setCache(false);
        HttpService httpService = getPositionRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.getDoumiPositionList(map));
    }


    /**
     * 斗米职位筛选条件
     */
    public void getPositionOptions(){
        setCache(false);
        HttpService httpService = getPositionRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.getPositionOptions(map));
    }

    /**
     * 新注册2
     *
     */
    public void checkVrfAndSetPassword(String vrfCode, String countryCode, String phone,String password) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
//        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map = getRequestMap();
//        map.put("userId", userId);
        map.put("vrfCode", vrfCode);
        map.put("countryCode", countryCode);
        map.put("phone", phone);
        map.put("password", password);
//        map.put("lang", SharePreferenceUtils.getBaseSharePreference().readLanguage());
        doHttpDeal(httpService.checkVrfAndSetPassword(map));
    }

    /**
     * 新忘记密码
     *
     */
    public void resetPasswordWithVrf(String vrfCode, String countryCode, String phone,String password) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
//        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map = getRequestMap();
//        map.put("userId", userId);
        map.put("vrfCode", vrfCode);
        map.put("countryCode", countryCode);
        map.put("phone", phone);
        map.put("password", password);
//        map.put("lang", SharePreferenceUtils.getBaseSharePreference().readLanguage());
        doHttpDeal(httpService.resetPasswordWithVrf(map));
    }

//    /**
//     * 注册-资料编辑
//     */
//    public void mustBasic() {
//        setCache(false);
//        HttpService httpService = getRetrofit().create(HttpService.class);
//        Map<String, Object> requestMap = getRequestMap();
//        doHttpDeal(httpService.homeRefresh(requestMap));
//    }
    /**
     * 注册-资料编辑
     *
     */
    public void mustBasic(String realName, String headImg, int professionalIdentity,String company,String position) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
//        map.put("userId", userId);
        map.put("realName", realName);
        if(!StringUtils.isEmpty(headImg))
        {
            map.put("headImg", headImg);
        }
        map.put("professionalIdentity", professionalIdentity);
        if(professionalIdentity!=15)
        {
            map.put("company",company);
            map.put("position",position);
        }
//        map.put("lang", SharePreferenceUtils.getBaseSharePreference().readLanguage());
        doHttpDeal(httpService.mustBasic(map));
    }
    public void mustBasic(String realName, int professionalIdentity) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
//        map.put("userId", userId);
        map.put("realName", realName);
        map.put("professionalIdentity", professionalIdentity);
//        map.put("lang", SharePreferenceUtils.getBaseSharePreference().readLanguage());
        doHttpDeal(httpService.mustBasic(map));
    }

    /**
     * 注册-邀请码
     *
     * @param invitedCode
     */
    public void registerInvitedCode(String phone, String countryCode, String vrfCode, String invitedCode) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("countryCode", countryCode);
        map.put("vrfCode", vrfCode);
        map.put("invitedCode", invitedCode);
        map.put("lang", SharePreferenceUtils.getBaseSharePreference().readLanguage());
        doHttpDeal(httpService.register(map));
    }

    /**
     * 登录
     *
     * @param pwd
     */
    public void login(String phone, String pwd, String countryCode) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("password", pwd);
        map.put("countryCode", countryCode);
        map.put("lang", SharePreferenceUtils.getBaseSharePreference().readLanguage());
        doHttpDeal(httpService.login(map));
    }
    /**
     * 新登录
     *
     * @param pwd
     */
    public void loginWithPassword(String phone, String pwd, String countryCode) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = new HashMap<>();
//        Map<String, Object> map = getRequestMap();
        map.put("phone", phone);
        map.put("password", pwd);
        map.put("countryCode", countryCode);
//        map.put("lang", SharePreferenceUtils.getBaseSharePreference().readLanguage());
        doHttpDeal(httpService.loginWithPassword(map));
    }

    /**
     * 登陆测试接口
     * @param phone
     * @param pwd
     * @param countryCode
     */
    public void loginWithOutPassword(String phone, String pwd, String countryCode){
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("password", pwd);
        map.put("countryCode", countryCode);
        doHttpDeal(httpService.loginWithOutPassword(map));
    }

    /**
     * 新重置密码
     *
     * @param oldPassword
     * @param newPassword
     */
    public void resetPasswordWithOld(String oldPassword, String newPassword) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
//        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map = getRequestMap();
        map.put("oldPassword", oldPassword);
        map.put("newPassword", newPassword);
        doHttpDeal(httpService.resetPasswordWithOld(map));
    }

    /**
     * 添加工作经验
     *
     * @param position
     * @param company
     * @param number
     * @param duration
     * @param jobDesc
     */
    public void addWork(String position, String company, int number, String duration, String jobDesc) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map map = getRequestMap();
        map.put("position", position);
        map.put("company", company);
        map.put("number", number);
        map.put("duration", duration);
        map.put("jobDesc", jobDesc);
        doHttpDeal(httpService.addWork(map));
    }

//    public void addWork(String position, String company, int number, String duration, String jobDesc) {
//        setCache(false);
//        HttpService httpService = getRetrofit().create(HttpService.class);
//        Map map = getRequestMap();
//        map.put("position", position);
//        map.put("company", company);
//        map.put("number", number);
//        map.put("duration", duration);
//        map.put("jobDesc", jobDesc);
//
//        map.put("position", position);
//        map.put("company", company);
//        map.put("number", number);
//        map.put("duration", duration);
//        map.put("jobDesc", jobDesc);
//        map.put("userId", SharePreferenceUtils.getBaseSharePreference().readUserId());
//        map.put("token", SharePreferenceUtils.getBaseSharePreference().readToken());
//        doHttpDeal(httpService.addWork(position,company,number,duration,jobDesc,SharePreferenceUtils.getBaseSharePreference().readUserId()
//        ,SharePreferenceUtils.getBaseSharePreference().readToken()));
//    }

    /**
     * 更改工作经验
     *
     * @param position
     * @param company
     * @param number
     * @param duration
     * @param jobDesc
     */
    public void updateWork(String id, String position, String company, int number, String duration, String jobDesc) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("_id", id);
        map.put("position", position);
        map.put("company", company);
        map.put("number", number);
        map.put("duration", duration);
        map.put("jobDesc", jobDesc);
        doHttpDeal(httpService.updateWork(map));
    }

    /**
     * 排行榜
     */
    public void combatCharts() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.combatCharts(map));
    }

    /**
     * 获取推荐
     */
    public void getRecomends() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.getRecomends(map));
    }

    /**
     * 59、获取水晶商品列表
     */
    public void crystalGoods() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.crystalGoods(map));
    }


    /**
     * 获取会员列表
     */
    public void memberGoos()
    {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.memberGoods(map));
    }

    /**
     * 删除短文
     * @param essayId
     */
    public void deleteEssay(String essayId){
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("essayId",essayId);
        doHttpDeal(httpService.deleteEssay(map));
    }

    /**
     * 长文详情
     * @param essayId
     */
    public void getEssayByIdNew(String essayId){
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("essayId",essayId);
        doHttpDeal(httpService.getEssayByIdNew(map));
    }

    /**
     * 翻页长文评论
     * @param essayId
     */
    public void getCommentByPageNew(String essayId,long lastCommentTime){
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("essayId",essayId);
        map.put("lastCommentTime",lastCommentTime);
        map.put("limit",15);
        doHttpDeal(httpService.getCommentByPageNew(map));
    }


    /**
     * 长文点赞
     * @param essayId 微博ID
     * @param basicId 关联资料人ID
     * @param type 点赞类型 0-文章  1-评论  2-回复
     * @param commentId 评论ID
     * @param replyId 最新回复的ID
     * @param fabulousOrDelete 1-点赞 other-取消点赞
     */
    public void myfabulous(String essayId,String basicId,int type,String commentId,String replyId,int fabulousOrDelete){
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("essayId",essayId);
        map.put("basicId",basicId);
        map.put("type",type);
        map.put("commentId",commentId);
        map.put("replyId",replyId);
        map.put("fabulousOrDelete",fabulousOrDelete);
        doHttpDeal(httpService.myfabulous(map));
    }




    /**
     * 发布短文
     * @param images
     * @param content
     */
    public void publishEssaysForm(List<File> images,String content){
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        List<MultipartBody.Part> parts=null;
        if(images!=null){
            parts=new ArrayList<>();
            for(int i=0;i<images.size();i++){
                File file=images.get(i);
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
                MultipartBody.Part part = MultipartBody.Part.createFormData("IMAGE"+(i+1), file.getName(), requestBody);
                parts.add(part);
            }
        }
        doHttpDeal(httpService.publishEssayForm(parts, SharePreferenceUtils.getBaseSharePreference().readToken(), SharePreferenceUtils.getBaseSharePreference().readUserId(),"android", SharePreferenceUtils.getBaseSharePreference().readLanguage(),"userHead",content));
    }

//    /**
//     * 发布短文
//     * @param images
//     * @param content
//     */
//    public void publishEssaysForm(List<File> images,String content){
//        setCache(false);
//        HttpService httpService = getRetrofit().create(HttpService.class);
//        if(images!=null){
//            MultipartBody.Builder builder = new MultipartBody.Builder()
//                    .setType(MultipartBody.FORM); //表单类型
//            for(int i=0;i<images.size();i++){
//                File file=images.get(i);
//                RequestBody body=RequestBody.create(MediaType.parse("image/*"),file);//表单类型
//                //3.调用MultipartBody.Builder的addFormDataPart()方法添加表单数据
//                builder.addFormDataPart("IMAGE"+(i+1),file.getName(),body); //添加图片数据，body创建的请求体
//            }
//            Map<String, String> params = new HashMap<>();
//            params.put("sys","android");
//            params.put("userId", SharePreferenceUtils.getBaseSharePreference().readUserId());
//            params.put("token", SharePreferenceUtils.getBaseSharePreference().readToken());
//            params.put("lang", SharePreferenceUtils.getBaseSharePreference().readLanguage());
//            params.put("imgType","userHead");
//            params.put("content",content);
//            //参数以添加header方式将参数封装，否则上传参数为空
//            if (params != null && !params.isEmpty()) {
//                for (String key : params.keySet()) {
//                    builder.addPart(
//                            Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
//                            RequestBody.create(null, params.get(key)));
//                }
//            }
//
////            RequestBody requestBody=builder.build().parts();
////            doHttpDeal(httpService.publishEssayForm(builder.build().parts()));
//
////            builder.addFormDataPart("imgType", "essayImage");//传入服务器需要的key，和相应value值
////            builder.addFormDataPart("content", content);//传入服务器需要的key，和相应value值
////            builder.addFormDataPart("sys", "android");//传入服务器需要的key，和相应value值
////            builder.addFormDataPart("userId", SharePreferenceUtils.getBaseSharePreference().readUserId());//传入服务器需要的key，和相应value值
////            builder.addFormDataPart("token", SharePreferenceUtils.getBaseSharePreference().readToken());//传入服务器需要的key，和相应value值
//        }
//    }

//
//    public static RequestBody toRequestBodyOfText (String value) {
//        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
//        return body ;
//    }
//
//    public static RequestBody toRequestBodyOfImage(File pFile){
//        RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), pFile);
//        return fileBody;
//    }

    /**
     * 获取短文
     */
    public void getEssays(int type,int count,int lastEssayId,int lastEssayHot) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("type", type);
        map.put("count", count);
        map.put("lastEssayId", lastEssayId);
        map.put("lastEssayHot", lastEssayHot);
        doHttpDeal(httpService.getEssays(map));
    }

    /**
     * 获取评论
     */
    public void getComments(int essayId,int lastTime) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("type", essayId);
        map.put("count", lastTime);
        doHttpDeal(httpService.getComments(map));
    }

    /**
     * 发评论
     */
    public void comment(int essayId,String content) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("essayId",essayId);
        map.put("content", content);
        doHttpDeal(httpService.comment(map));
    }


    /**
     * 加入群聊
     */
    public void joinGroup(String basicId, int groupId) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("basicId", basicId);
        map.put("groupId", groupId);
        doHttpDeal(httpService.joinGroup(map));
    }

    /**
     * 退出群聊
     */
    public void quitGroup(int groupId) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("groupId", groupId);
        doHttpDeal(httpService.quitGroup(map));
    }

    /**
     * 获取我加过的群
     */
    public void getMyGroups() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.getMyGroups(map));
    }
    /**
     * 获取公司队员
     */
    public void getCompanyMembers(int companyId) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("companyId", companyId);
        doHttpDeal(httpService.getCompanyMembers(map));
    }

    /**
     * 获取公司信息
     */
    public void getCompanys(int companyId) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("companyId", companyId);
        doHttpDeal(httpService.getCompanys(map));
    }
    /**
     * 获取群成员
     */
    public void getGroupMembers(int groupId, int lastCount) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("groupId", groupId);
        map.put("lastCount", lastCount);
        doHttpDeal(httpService.getGroupMembers(map));
    }

    /**
     * 检查好友状态
     */
    public void checkRegister(List<String> friendArr) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.checkRegister(map,friendArr));
    }

    /**
     * 获取PDF
     */
    public void getContractPDF() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.getContractPDF(map));
    }

    /**
     * 基本信息
     *
     * @param realName
     * @param school
     * @param education
     * @param company
     * @param position
     * @param country
     * @param number
     * @param nature
     * @param headImg
     * @param selfDesc
     */
    public void updateBasic(String realName, String school, String education, String company, String position, String country, String number, String nature, String headImg, String selfDesc) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("realName", realName);
        map.put("school", school);
        map.put("education", education);
        map.put("company", company);
        map.put("position", position);
        map.put("country", country);
        map.put("number", number);
        map.put("nature", nature);
        map.put("headImg", headImg);
        map.put("selfDesc", selfDesc);
        doHttpDeal(httpService.updateBasic(map));
    }

    /**
     * 基本信息
     *
     * @param realName
     * @param school
     * @param company
     * @param position
     * @param headImg
     * @param professionalIdentity
     */
//    public void updateBasic(String realName, String school, String education, String company, String position, String country, String number, String nature, String headImg, String selfDesc, int professionalIdentity) {
    public void updateBasic(String id,String realName, String school, String company, String position,
                            String headImg, int professionalIdentity,int gender,int showEdit,int userId,
                            String createdAt,String updatedAt,int __v,String positionTitle,String selfDesc) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("_id", id);
        if(!TextUtils.isEmpty(realName)){
            map.put("realName", realName);
        }
        if(!TextUtils.isEmpty(school)){
            map.put("school", school);
        }

        if(!TextUtils.isEmpty(company)){
            map.put("company", company);
        }

        if(!TextUtils.isEmpty(position)){
            map.put("position", position);
        }

        if(!TextUtils.isEmpty(headImg)){
            map.put("headImg", headImg);
        }


        if(!TextUtils.isEmpty(positionTitle)){
            map.put("positionTitle", positionTitle);
        }

        if(!TextUtils.isEmpty(selfDesc)){
            map.put("selfDesc", selfDesc);
        }

        map.put("professionalIdentity", professionalIdentity);
        map.put("gender", gender);
        map.put("showEdit", showEdit);
        map.put("userId", userId);
        map.put("createdAt", createdAt);
        map.put("updatedAt", updatedAt);
        map.put("__v", __v);
        doHttpDeal(httpService.updateBasic(map));
        //Log.e("sgf",map.toString());
    }

    /**
     * 基本信息 无头像
     *
     * @param realName
     * @param school
     * @param company
     * @param position
     * @param professionalIdentity
     */
    public void updateBasic(String id,String realName, String school, String company, String position, int professionalIdentity,int gender,int showEdit,int userId,String createdAt,String updatedAt,int __v,String positionTitle) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("_id", id);
        map.put("realName", realName);
        map.put("school", school);
        map.put("company", company);
        map.put("position", position);
        map.put("professionalIdentity", professionalIdentity);
        map.put("gender", gender);
        map.put("showEdit", showEdit);
        map.put("userId", userId);
        map.put("createdAt", createdAt);
        map.put("updatedAt", updatedAt);
        map.put("__v", __v);
        map.put("positionTitle",positionTitle);
        doHttpDeal(httpService.updateBasic(map));
    }
    /**
     * 基本信息
     *
     * @param realName
     * @param school
     * @param education
     * @param company
     * @param position
     * @param country
     * @param number
     * @param nature
     * @param headImg
     * @param selfDesc
     * @param professionalIdentity
     */
    public void updateBasic(String id, String realName, String school, String education, String company, String position, String country, String number, String nature, String headImg, String selfDesc, int professionalIdentity,String positionTitle) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("_id", id);
        map.put("realName", realName);
        map.put("school", school);
        map.put("education", education);
        map.put("company", company);
        map.put("position", position);
        map.put("country", country);
        map.put("number", number);
        map.put("nature", nature);
        map.put("headImg", headImg);
        map.put("selfDesc", selfDesc);
        map.put("professionalIdentity", professionalIdentity);
        map.put("positionTitle",positionTitle);
        doHttpDeal(httpService.updateBasic(map));
    }

    /**
     * 添加区块链信息
     *
     * @param identityLocation
     * @param time
     * @param position
     * @param positionDesc
     */
    public void addBlockChain(String identityLocation, String time, String position, String positionDesc) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("identityLocation", identityLocation);
        map.put("time", time);
        map.put("position", position);
        map.put("positionDesc", positionDesc);
        doHttpDeal(httpService.addBlockChain(map));
    }

    /**
     * 添加教育经历
     *
     * @param school
     * @param education
     * @param major
     * @param duration
     * @param experience
     */
    public void addEducation(String school, String education, String major, String duration, String experience) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("school", school);
        map.put("education", education);
        map.put("major", major);
        map.put("duration", duration);
        map.put("experience", experience);
        doHttpDeal(httpService.addEducation(map));
    }

    /**
     * 更改教育经历
     *
     * @param id
     * @param school
     * @param education
     * @param major
     * @param duration
     * @param experience
     */
    public void updateEducation(String id, String school, String education, String major, String duration, String experience) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("_id", id);
        map.put("school", school);
        map.put("education", education);
        map.put("major", major);
        map.put("duration", duration);
        map.put("experience", experience);
        doHttpDeal(httpService.updateEducation(map));
    }


    /**
     * 我的页面
     */
    public void mineRefresh() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.mineRefresh(map));
    }

    /**
     * 简历
     */
    public void briefRefresh() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.briefRefresh(map));
    }
    /**
     * 简历
     */
    public void briefRefresh2(int targetUserId) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("targetUserId", targetUserId);
        doHttpDeal(httpService.briefRefresh(map));
    }

    /**
     * 首页
     */
    public void homeRefresh() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        doHttpDeal(httpService.homeRefresh(requestMap));
    }

    /**
     * 脉场页接口
     */
    public void maichang() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        doHttpDeal(httpService.maichang(requestMap));
    }

    /**
     * 脉场首页卡片资源
     */
    public void genesisCuv() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        doHttpDeal(httpService.genesisCuv(requestMap));
    }

    /**
     * 脉场首页咨询资源
     */
    public void getBreakingNews() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        doHttpDeal(httpService.getBreakingNews(requestMap));
    }



    /**
     * 获取申请者
     */
    public void getAppliers() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        doHttpDeal(httpService.getAppliers(requestMap));
    }
    /**
     * 获取好友
     */
    public void getFriends() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        doHttpDeal(httpService.getFriends(requestMap));
    }

    /**
     * 获取黑名单
     */
    public void getBlacks() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        doHttpDeal(httpService.getBlacks(requestMap));
    }

    /**
     * 删除好友
     */
    public void deleteFriend(int targetUserId) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        requestMap.put("targetUserId", targetUserId);
        doHttpDeal(httpService.deleteFriend(requestMap));
    }

    /**
     * 移除黑名单
     */
    public void unBlack(int targetUserId) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        requestMap.put("targetUserId", targetUserId);
        doHttpDeal(httpService.unBlack(requestMap));
    }

    /**
     * 拉黑
     */
    public void genesisBlack(int targetUserId) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        requestMap.put("targetUserId", targetUserId);
        doHttpDeal(httpService.genesisBlack(requestMap));
    }

    /**
     * 通过申请
     */
    public void adopt(int targetUserId) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        requestMap.put("targetUserId", targetUserId);
        doHttpDeal(httpService.adopt(requestMap));
    }
    /**
     * 忽略申请
     */
    public void ignore(int targetUserId) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        requestMap.put("targetUserId", targetUserId);
        doHttpDeal(httpService.ignore(requestMap));
    }

    /**
     * 好友申请
     */
    public void apply(int targetUserId) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        requestMap.put("targetUserId", targetUserId);
        doHttpDeal(httpService.apply(requestMap));
    }

    /**
     * 同步好友关系
     */
    public void synFriends() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        doHttpDeal(httpService.synFriends(requestMap));
    }

    /**
     * 付费查看用户资料
     * @param targetId
     */
    public void buyInfo(String targetId)
    {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        requestMap.put("targetId",targetId);
        doHttpDeal(httpService.buyInfo(requestMap));
    }

    /**
     * 取消好友申请
     * @param targetUserId
     */
    public void cancelApply(int targetUserId)
    {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        requestMap.put("targetUserId",targetUserId);
        doHttpDeal(httpService.cancelApply(requestMap));
    }


    /**
     * 关注
     */
    public void followFriends(int targetUserId) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        requestMap.put("targetUserId", targetUserId);
        doHttpDeal(httpService.followFriends(requestMap));
    }

    /**
     * 取消关注
     */
    public void unFollow(int targetUserId) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        requestMap.put("targetUserId", targetUserId);
        doHttpDeal(httpService.unFollow(requestMap));
    }

    /**
     * 获取粉丝列表
     */
    public void getFans() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        doHttpDeal(httpService.getFans(requestMap));
    }

    /**
     * 获取关注列表
     */
    public void getFollows() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        doHttpDeal(httpService.getFollows(requestMap));
    }

    /**
     * 获取用户粗略信息
     */
    public void rough(int targetUserId) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        requestMap.put("targetUserId", targetUserId);
        doHttpDeal(httpService.rough(requestMap));
    }

    /**
     * 设置密码
     *
     * @param phone
     * @param pwd
     * @param countryCode
     */
    public void resetPassword(String phone, String pwd, String countryCode) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("password", pwd);
        map.put("countryCode", countryCode);
        map.put("lang", SharePreferenceUtils.getBaseSharePreference().readLanguage());
        doHttpDeal(httpService.resetPassword(map));
    }

    /**
     * 发送验证码
     *
     * @param countryCode
     * @param phone
     */
    public void sendVrfCode(String countryCode, String phone) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = new HashMap<>();
        map.put("countryCode", countryCode);
        map.put("phone", phone);
        map.put("lang", SharePreferenceUtils.getBaseSharePreference().readLanguage());

        doHttpDeal(httpService.sendVrfCode(map));
    }

    /**
     * 获取验证码---忘记密码页面
     *
     * @param countryCode
     * @param phone
     */
    public void forgetCode(String countryCode, String phone) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("countryCode", countryCode);
        map.put("phone", phone);

        doHttpDeal(httpService.forgetCode(map));
    }

    /**
     * 注册获取验证码---忘记密码页面new
     *
     * @param countryCode
     * @param phone
     */
    public void sendForSignup(String countryCode, String phone) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("countryCode", countryCode);
        map.put("phone", phone);

        doHttpDeal(httpService.sendForSignup(map));
    }

    /**
     * 购买会员
     *
     * @param type
     * @param amount
     * type(会员类型1、2、3）、amount(购买年限）
     */
    public void buyVip(int type, String amount, int buyType) {
        //Log.e("test","-------------buy vip in 915");
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        if (type == 1) {
            map.put("price", 1000);
        } else if(type == 2) {
            map.put("price", 5000);
        } else if(type == 3) {
            map.put("price", 10000);
        } else if(type == 4) {
            map.put("price", 50000);
        } else if(type ==5){
            map.put("price", 100);
        }else if(type ==6){
            map.put("price", 10000);
        }else if(type ==7){
            map.put("price", 50000);
        }

        map.put("amount", amount);
        map.put("buyType",buyType);
        doHttpDeal(httpService.buyVip(map));
    }

    /**
     * 忘记密码获取验证码---忘记密码页面new
     *
     * @param countryCode
     * @param phone
     */
    public void sendForForget(String countryCode, String phone) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("countryCode", countryCode);
        map.put("phone", phone);

        doHttpDeal(httpService.sendForForget(map));
    }

    /**
     * 获取购买商品记录列表
     */
    public void goodsBuyRecords(){
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.goodsBuyRecords(map));
    }

    /**
     * 新制度下人脉收益测试
     */
    public void scheduleProduct20190717Test(){
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.scheduleProduct20190717Test(map));
    }

    /**
     * 新制度下人脉收益测试
     */
    public void buChang(){
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.buChang(map));
    }


    /**
     * 注册邀请码new
     *
     * @param invitedCode
     */
    public void setInvitedCodes(String invitedCode) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();//封装的所有必需的参数，如：token，userid等
        map.put("invitedCode", invitedCode);
        doHttpDeal(httpService.setInvitedCodes(map));
    }

    /**
     * 获取矿里排行榜
     */
    public void getCharts() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.getCharts(map));
    }

    /**
     * 收获pets币
     * @param adView 0-跳过广告 1-观看广告
     */
    public void harvest(int adView) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("adView",adView);
        doHttpDeal(httpService.harvest(map));
    }


    /**
     * 获取用户信息
     */
    public void getUser() {
        setCache(false);
        setShowProgress(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.getUser(map));
    }

    /**
     * 验证码校验
     *
     * @param countryCode
     * @param phone
     */
    public void checkVrfCode(String countryCode, String phone, String vrfCode) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = new HashMap<>();
        map.put("phone", phone);
        map.put("vrfCode", vrfCode);
        map.put("countryCode", countryCode);
        map.put("lang", SharePreferenceUtils.getBaseSharePreference().readLanguage());

        doHttpDeal(httpService.checkVrfCode(map));
    }

    /**
     * 删除宠物
     */
    public void deletePet(String petId) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("petId", petId);
        doHttpDeal(httpService.deletePet(map));
    }

    /**
     * 删除宠物
     */
    public void bindCloudPets(String email, String pwd) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("email", email);
        map.put("password", pwd);
        doHttpDeal(httpService.bindCloudPets(map));
    }

    /**
     * 配置
     */
    public void config() {
        setCache(false);
        setShowProgress(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("version", SystemUtil.getAppVersion());
        doHttpDeal(httpService.config(map));
    }

    /**
     * 宠物类型
     */
    public void petType() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        doHttpDeal(httpService.petType());
    }

    /**
     * 宠物列表
     */
    public void myPets() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
//        map.put("version", AppUtils.getAppVersion());
        doHttpDeal(httpService.myPets(map));
    }

    /**
     * pbs收支列表
     */
    public void harvestList() {
        harvestList(false);
    }

    /**
     * pbs收支列表
     * @param isNotNeed 是否需要返回支出列表
     */
    public void harvestList(boolean isNotNeed) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("isNotNeed",isNotNeed);
        doHttpDeal(httpService.harvestList(map));
    }

    /**
     * 水晶充值订单
     */
    public void getOrders() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.getOrders(map));
    }
    /**
     * 水晶PBS互换
     */
    public void crystalVsPbs(int type,int amount) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("type",type);//type：0为PBS->水晶；1为水晶->PBS
        map.put("amount",amount);
        doHttpDeal(httpService.crystalVsPbs(map));
    }

    /**
     * 获取群组推荐
     */
    public void recommendGroups() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.recommendGroups(map));
    }

    /**
     *获取pbs行情
     */
    public void getUsdtPbs() {
        setCache(false);
        setShowProgress(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.getUsdtPbs(map));
    }
    /**
     *获取定存产品
     */
    public void getDepositGoods() {
        setCache(false);
        setShowProgress(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.getDepositGoods(map));
    }
    /**
     * 获取某用户的资料
     */
    public void getBasicInfos(int targetUserId) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("targetUserId",targetUserId);
        doHttpDeal(httpService.getBasicInfos(map));
    }

    /**
     * 资产归集
     * @param phone
     */
    public void assetsImputation(String phone){
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("phone",phone);
        doHttpDeal(httpService.assetsImputation(map));
    }


    /**
     * 修复脉场空间购买数据
     */
    public void repaireCrowdFundData(){
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.repaireCrowdFundData(map));
    }

    /**
     * 脉场股东分红
     */
    public void scheduleEquityPartnersDeposit(){
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.scheduleEquityPartnersDeposit(map));
    }

    /**
     * 数据统计测试
     */
    public void statisticsTest(){
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.statisticsTest(map));
    }

    /**
     * 60、获取课程列表
     */
    public void getCourseIntro(int category,int lastCount) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("category",category);
        map.put("lastCount",lastCount);//默认0
        doHttpDeal(httpService.getCourseIntro(map));
    }

    /**
     * 61、pbs提现接口
     */
    public void drawPbs(String preAmount,String drawAddress) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("preAmount",preAmount);
        map.put("drawAddress",drawAddress);
        doHttpDeal(httpService.drawPbs(map));
    }
    /**
     * 62、（钱包转账方式）生成PBS充值金额（存入资产）
     */
    public void getCheckAmount(String preAmount) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("preAmount",preAmount);
        doHttpDeal(httpService.getCheckAmount(map));
    }
    /**
     * 63、购买定存宝接口(普通定存宝)
     */
    public void buyFromOfficial2(String type,String amount,String currency) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("type",type);
        map.put("amount",amount);
        map.put("currency",currency);
        doHttpDeal(httpService.buyFromOfficial2(map));
    }
    /**
     * 64、购买高端定存宝接口(高端定存宝)
     */
    public void futuresBuyFromOfficial2(String type,String amount,String currency) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("type",type);
        map.put("amount",amount);
        map.put("currency",currency);
        doHttpDeal(httpService.futuresBuyFromOfficial2(map));
    }
    /**
     * 65、上传有赞订单号接口
     */
    public void createOrder(String tid) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("tid",tid);
        doHttpDeal(httpService.createOrder(map));
    }

    /**
     * 66、检查联系人注册状态，并获取好友
     */
    public void checkFriends(ArrayList<String> phones, ArrayList<String> names) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Gson gson = new Gson();
//        HashMap<String, Object> stringObjectHashMap1 = new HashMap<>();
//        stringObjectHashMap1.put("phones",gson.toJson(phones));
//        HashMap<String, Object> stringObjectHashMap2 = new HashMap<>();
//        stringObjectHashMap2.put("names",gson.toJson(names));
        Map<String, Object> map = getRequestMap();
        map.put("phones",gson.toJson(phones));
        map.put("names",gson.toJson(names));
        doHttpDeal(httpService.checkFriends(map));
    }
    /**
     * 67、PBS转账接口
     * targetPhone（收款方手机号）, type（0余额转账、1定存转账）, amount（转账数量）
     */
    public void transfer(String targetPhone,int type,String amount) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("targetPhone",targetPhone);
        map.put("type",type);
        map.put("amount",amount);
        doHttpDeal(httpService.transfer(map));
    }
    /**
     * 68、提取时获取短信码接口
     */
    public void sendForDrawToBqex(String countryCode,String phone) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("countryCode",countryCode);
        map.put("phone",phone);
        doHttpDeal(httpService.sendForDrawToBqex(map));
    }


    /**
     * 支付宝转账到支付宝
     * @param phone 支付宝账号
     * @param name 支付宝账号名称
     * @param amount amount 提取水晶的数量
     */
    public void drawZfbToZfb(String phone,String name,String amount)
    {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("phone",phone);
        map.put("amount",amount);
        map.put("type",2);
        map.put("name",name);
        doHttpDeal(httpService.drawToZfb(map));
    }

    /**
     * 支付宝转账到银行卡
     * @param bankCode 银行卡号
     * @param bankName 银行名称
     * @param name 银行账户名称
     * @param amount amount 提取水晶的数量
     */
    public void drawZfbToBank(String bankCode,String bankName,String name,String amount)
    {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("amount",amount);
        map.put("type",1);
        map.put("name",name);
        map.put("bankCardsNumber",bankCode);
        map.put("bankName",bankName);
        doHttpDeal(httpService.drawToZfb(map));
    }

//    /**
//     * 69、提取到支付宝接口
//     * @param phone 支付宝账号
//     * @param name 账户名称
//     * @param amount 提取水晶的数量
//     */
//    public void drawToZfb(String phone,String name,String amount,int type,String banckCardsNumber,String bankName) {
//        setCache(false);
//        HttpService httpService = getRetrofit().create(HttpService.class);
//        Map<String, Object> map = getRequestMap();
//        map.put("phone",phone);
//        map.put("amount",amount);
//        map.put("type",type);
//        map.put("name",name);
//        map.put("banckCardsNumber",banckCardsNumber);
//        map.put("bankName",bankName);
//        doHttpDeal(httpService.drawToZfb(map));
//    }

    /**
     * 69、提取到币全接口
     */
    public void drawToBqex(String countryCode,String phone,String vrfCode,String amount,int type) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("countryCode",countryCode);
        map.put("phone",phone);
        map.put("vrfCode",vrfCode);
        map.put("amount",amount);
        map.put("type",type);
        doHttpDeal(httpService.drawToBqex(map));
    }

    /**
     * 创建币全收款订单
     * arrivalAmount, countryCode, phone, userId,
     */
    public void createReceiptOrder(String countryCode,String phone,String arrivalAmount) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("countryCode",countryCode);
        map.put("phone",phone);
        map.put("arrivalAmount",arrivalAmount);
        doHttpDeal(httpService.createReceiptOrder(map));
    }

    /**
     * 币全收款
     * orderId, vcode, userId, phone,
     */
    public void receiptFromBqex(String orderId,String vcode,String phone) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("orderId",orderId);
        map.put("vcode",vcode);
        map.put("phone",phone);
        doHttpDeal(httpService.receiptFromBqex(map));
    }


    /**
     */
    public void keruyunGetToken() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.keruyunGetToken(map));
    }

    /**
     * 创建购买客如云订单号
     */
    public void createKeruyunOrderID(String actionId,String shopId, String keruyunToken) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("actionId",actionId);
        map.put("shopId",shopId);
        map.put("keruyunToken",keruyunToken);
        doHttpDeal(httpService.createKeruyunOrderID(map));
    }


    /**
     * 微信购买统一下单
     */
    public void unifiedorderForApp(String buyerName,String totalFee, String outTradeNo) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("body",buyerName);
        map.put("total_fee",totalFee);
        map.put("notify_url","android");
        map.put("trade_type","APP");
        map.put("out_trade_no",outTradeNo);
        doHttpDeal(httpService.unifiedorderForApp(map));
    }

    /**
     * 微信订单轮询
     */
    public void startPollMember(String name, String phone, String amount, String tpOrderId) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("name",name);
        map.put("phone",phone);
        map.put("amount",amount);
        map.put("out_trade_no",tpOrderId);
        doHttpDeal(httpService.startPollMember(map));
    }

    /**
     * 我的算力
     */
    public void combatList() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.combatList(map));
    }

    public void teamMonthPage(){
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("userId","1620");
        map.put("year","2019");
        map.put("month","05");
        doHttpDeal(httpService.teamMonthPage(map));
    }

    /**
     * 添加宠物
     */
    public void addPet(String animal, String variety, String headImg, String petNick, String sex, String ownerNick) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("animal", animal);
        map.put("variety", variety);
        map.put("headImg", headImg);
        map.put("petNick", petNick);
        map.put("sex", sex);
        map.put("ownerNick", ownerNick);
        doHttpDeal(httpService.addPet(map));
    }

    /**
     * 上传img
     *
     * @param requestBody
     */
    public void upImg(RequestBody requestBody, MultipartBody.Part part, String imgType) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        requestMap.put("imgType", imgType);
        doHttpDeal(httpService.upImg(SharePreferenceUtils.getBaseSharePreference().readToken(), SharePreferenceUtils.getBaseSharePreference().readUserId(), imgType, part));
//        doHttpDeal(httpService.upImg(requestMap,part));
    }

    /**
     * 修改密码
     *
     * @param uname
     * @param passwd1 原始密码
     * @param passwd2 新密码
     */
    public void modifyPwd(String uname, String passwd1, String passwd2) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = new HashMap<>();
        map.put("uname", uname);
        map.put("passwd1", EncodeUtils.getMd5Value(passwd1 + UrlContent.PWD));
        map.put("passwd2", EncodeUtils.getMd5Value(passwd2 + UrlContent.PWD));

        String s = ParamUtils.enCode(map, true);
        doHttpDeal(httpService.modifyPwd(s));
    }

    /**
     * 获取用户信息
     */
    public void getUserInfoNew(String inviteCode) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("inviteCode",inviteCode);
        doHttpDeal(httpService.getUserInfoNew(map));
    }

    /**
     * 获取用户信息
     */
    public void getUserInfo() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        String s = ParamUtils.enCode(null, true);
        doHttpDeal(httpService.getUserInfo(s));
    }


    /**
     * 根据职业分类来获取用户列表
     * @param pid 职业id
     * @param lastUserId 拉取到的最后一个用户的ID--------分页的id
     */
    public void getUserInfoByPid(String pid,String lastUserId)
    {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("pid", pid);
        map.put("lastUserId", lastUserId);
//        String s = ParamUtils.enCode(map, true);
        doHttpDeal(httpService.getUserInfoByPid(map));
    }

    public void yzToken() {
        setCache(false);
        setShowProgress(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        doHttpDeal(httpService.yzToken(requestMap));
    }

    public void yzLogin() {
        setCache(false);
        setShowProgress(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        doHttpDeal(httpService.yzLogin(requestMap));
    }

    public void yzLoginNew() {
        setCache(false);
        setShowProgress(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        doHttpDeal(httpService.yzLoginNew(requestMap));
    }

    /**
     * 修改地址
     *
     * @param address
     */
    public void modifyAddress(String address) {
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = new HashMap<>();
        map.put("address", address);
        String s = ParamUtils.enCode(map, true);
        doHttpDeal(httpService.modifyAddress(s));
    }

    /**
     * 修改邮箱
     *
     * @param email
     */
    public void modifEmail(String email) {
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        String s = ParamUtils.enCode(map, true);
        doHttpDeal(httpService.modifEmail(s));
    }

    /**
     * 退出登录
     */
    public void loginout() {
        HttpService httpService = getRetrofit().create(HttpService.class);
        String s = ParamUtils.enCode(null, true);
        doHttpDeal(httpService.loginout(s));
    }

    /**
     * 检查课程购买状态
     * @param courseId 课程ID
     */
    public void checkCourseBuy(Integer courseId) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("courseId", courseId);
        doHttpDeal(httpService.checkCourseBuy(map));
    }

    /**
     * 购买课程
     * @param courseId 课程ID
     */
    public void buyCourse(Integer courseId) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("courseId", courseId);
        doHttpDeal(httpService.buyCourse(map));
    }

    /**
     * 获取课时列表
     * @param courseId 课程ID
     */
    public void getLessons(Integer courseId) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("courseId", courseId);
        doHttpDeal(httpService.getLessons(map));
    }

    /**
     * 删除教育经历或工作经验
     * @param type 0工作经验；1教育经历
     * @param infoId 记录的ID
     */
    public void deleteInfo(Integer type, String infoId) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("type", type);
        map.put("infoId", infoId);
        doHttpDeal(httpService.deleteInfo(map));
    }

    /**
     * 获取提现记录
     */
    public void getDrawalRecords() {
        setCache(false);//是否需要缓存
        HttpService httpService = getRetrofit().create(HttpService.class);
        doHttpDeal(httpService.drawalRecords(getRequestMap()));
    }

    /**
     * 获取双向好友申请信息
     */
    public void getApplyMeAndMyApply()
    {
        setCache(false);//是否需要缓存
        HttpService httpService = getRetrofit().create(HttpService.class);
        doHttpDeal(httpService.getApplyMeAndMyApply(getRequestMap()));
    }

    public void getProductById(String productId)
    {
        setCache(false);//是否需要缓存
        HttpService httpService = getRetrofit().create(HttpService.class);
        doHttpDeal(httpService.youzanSearch(getRequestMap()));
    }

    /**
     * 根据id获取有赞商品
     * @param goodId
     */
    public void getGoodById(String goodId){
        setCache(false);//是否需要缓存
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map map=getRequestMap();
        map.put("itemNumber",goodId);
        doHttpDeal(httpService.youzanGetGood(map));
    }

    /**
     * 获取有赞商在售品列表
     */
    public void getYZGoodsList(int pageNumber,int pageSize, boolean isInsert){
        setCache(false);//是否需要缓存
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("pageNumber", pageNumber);
        map.put("pageSize", pageSize);
        map.put("isInsert", isInsert);
        doHttpDeal(httpService.youzanOnsale(map));
    }

    /**
     * 获取有赞商在售品列表
     */
    public void getYZGoodsListV2(int pageNumber,String tagIds){
        setCache(false);//是否需要缓存
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("pageNo", pageNumber);
        map.put("tagIds", tagIds);
//        map.put("itemIds",item_ids);
        doHttpDeal(httpService.youzanOnsaleV2(map));
    }

    public void getYZGoodsListV3(){
        setCache(false);//是否需要缓存
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
//        map.put("itemIds",item_ids);
        doHttpDeal(httpService.youzanOnsaleV2(map));
    }



    /**
     * 获取商机列表
     */
    public void getOpportunities(){
        setCache(false);//是否需要缓存
        HttpService httpService = getRetrofit().create(HttpService.class);
        doHttpDeal(httpService.getOpportunities(getRequestMap()));
    }

    /**
     * 购买测试
     * @param unitPrice  购买单价
     * @param quantity 购买数量
     */
    public void buyProduct2(int unitPrice,int quantity){
        setCache(false);//是否需要缓存
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("unitPrice", unitPrice);
        map.put("quantity", quantity);
        doHttpDeal(httpService.buyTest(map));
    }

    /**
     * 财务转移测试
     */
    public void institutionMigration(String userId){
        setCache(false);//是否需要缓存
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("userId",userId);
        doHttpDeal(httpService.institutionMigration(map));
    }


    /**
     * 解冻PBS---慎重操作
     * @param phone
     */
    public void unfreezePBS(String phone){
        setCache(false);//是否需要缓存
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("phone",phone);
        doHttpDeal(httpService.unfreezePBS(map));
    }

    /**
     * 强制下架目前所有在售内盘订单
     */
    public void froceAction(){
        setCache(false);//是否需要缓存
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        doHttpDeal(httpService.froceAction(map));
    }


    /**
     * 根据标签获取推荐人脉列表
     * @param pid
     * @param page
     */
    public void getRecommendByTag(String pid,int page){
        setCache(false);//是否需要缓存
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("pid", pid);
        map.put("page",page);
        doHttpDeal(httpService.getRecommendByTag(map));
    }

    /**
     * 添加好友访问记录
     */
    public void addFriendLog(String fromInfo, String toInfo){
        setCache(false);//是否需要缓存
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("fromInfo", fromInfo);
        map.put("toInfo",toInfo);
        doHttpDeal(httpService.addFriendLog(map));
    }

    /**
     * 获取好友访问记录
     */
    public void findFriendLog(String basicId){
        setCache(false);//是否需要缓存
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> map = getRequestMap();
        map.put("infoId", basicId);
        map.put("page",1);
        map.put("limit",10);
        doHttpDeal(httpService.findFriendLog(map));
    }




    public Map<String, Object> getRequestMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("sys","android");
        String userId= SharePreferenceUtils.getBaseSharePreference().readUserId();
        Log.e("test","getRequestMap userId is "+userId);
        map.put("userId", userId);
        map.put("token", SharePreferenceUtils.getBaseSharePreference().readToken());
        map.put("lang", SharePreferenceUtils.getBaseSharePreference().readLanguage());
        return map;
    }



    public void getKongianHuoDong() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        doHttpDeal(httpService.getkongjian(requestMap));
    }


    public void getZhongChou() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        doHttpDeal(httpService.getzhongchou(requestMap));
    }


    public void getJiaMen() {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        doHttpDeal(httpService.getjiamen(requestMap));
    }


    public void getlogin(String phone,String countryCode,String  vrfCode) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        requestMap.put("phone",phone);
        requestMap.put("countryCode",countryCode);
        requestMap.put("vrfCode",vrfCode);
        doHttpDeal(httpService.getquickly(requestMap));
    }


    public void getdianzan(String essayId,int type,int fabulousOrDelete,String userId) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        requestMap.put("essayId",essayId);
        /*
        *   type     0 文章点赞，1是评论点赞
        * fabulousOrDelete  0是点赞 ，其他是取消点赞
        */

        requestMap.put("type",type);
        requestMap.put("fabulousOrDelete",fabulousOrDelete);
        requestMap.put("userId",userId);
        doHttpDeal(httpService.getdianzan(requestMap));
    }

    public void sendPinglun(String essayId,String content) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        requestMap.put("essayId",essayId);
        requestMap.put("content",content);

        doHttpDeal(httpService.getpinglun(requestMap));
    }
    /*
    * 微博长文
    * */
    public void getWeiBo(String essayId) {
         setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        requestMap.put("essayId",essayId);
        requestMap.put("sort",-1);
        doHttpDeal(httpService.getWeibo(requestMap));
    }

/*
* 评论页数
* */
    public void getpinglunpage(String essayId,String limit,String lastCommentTime) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();
        requestMap.put("essayId",essayId);
        requestMap.put("limit",limit);
        requestMap.put("lastCommentTime",lastCommentTime);

        doHttpDeal(httpService.getpinglunpage(requestMap));
    }


   /* *//*
    * 我的--动态
    * *//*
    public void getminedynamic(int pageNum,int essayNum,String userid) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);
        Map<String, Object> requestMap = getRequestMap();

        requestMap.put("pageNum",pageNum);
        requestMap.put("essayNum",essayNum);


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userid",userid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String string = jsonObject.toString();
      //  requestMap.put("userid",string);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), string);
        doHttpDeal(httpService.getdynamic(requestMap,requestBody));
    }*/



    public void getminedynamic(int pageNum,int essayNum,String userId) {
        setCache(false);
        HttpService httpService = getRetrofit().create(HttpService.class);


        JSONObject jsonObject = new JSONObject();
        int delete = 0;
        try {
            jsonObject.put("userId",userId);
            jsonObject.put("deleted",delete);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String string = jsonObject.toString();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), string);
        doHttpDeal(httpService.getdynamic(requestBody,1,15, SharePreferenceUtils.getBaseSharePreference().readToken()
                , SharePreferenceUtils.getBaseSharePreference().readUserId()));
    }

}
