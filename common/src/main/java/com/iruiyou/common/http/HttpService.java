package com.iruiyou.common.http;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * 测试接口service-post相关
 * Created by WZG on 2016/12/19.
 */

public interface HttpService {

    @GET("AppFiftyToneGraph/videoLink/{once_no}")
    Observable<String> getAllVedioBy(@Query("once_no") boolean once_no);

    // weatherInfo?city=110101&key=f817f60cd82605e21986bc1623fc6379&extensions=all
    @GET("weatherInfo?key=f817f60cd82605e21986bc1623fc6379&extensions=all")
    Observable<String> getWeatherInfo(@Query("city") String city);

    @GET("api/v3/insurance/product/list")
    Observable<String> getL();

    @FormUrlEncoded
    @POST(UrlContent.getOpportunities)
    Observable<String> getOpportunities(@FieldMap Map<String, Object> map);


    ////    @FormUrlEncoded
//    @POST("User/login")
//    Observable<String> login(@Query("uname") String uname,
//                             @Query("passwd") String passwd,
//                             @Query("client") String client);
    //登录
    @FormUrlEncoded
    @POST(UrlContent.login)
    Observable<String> login(@FieldMap Map<String, Object> map);

    //新登录
    @FormUrlEncoded
    @POST(UrlContent.loginWithPassword)
    Observable<String> loginWithPassword(@FieldMap Map<String, Object> map);

    //新登录
    @FormUrlEncoded
    @POST(UrlContent.loginWithOutPassword)
    Observable<String> loginWithOutPassword(@FieldMap Map<String, Object> map);

    //获取我加过的群
    @FormUrlEncoded
    @POST(UrlContent.getMyGroups)
    Observable<String> getMyGroups(@FieldMap Map<String, Object> map);

    //新重置密码
    @FormUrlEncoded
    @POST(UrlContent.resetPasswordWithOld)
    Observable<String> resetPasswordWithOld(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.addWork)
    Observable<String> addWork(@FieldMap Map<String, Object> map);


//    @FormUrlEncoded
//    @POST(UrlContent.addWork)
//    Observable<String> addWork(@Field("position") String position,@Field("company") String company,@Field("number") Integer number,
//                               @Field("duration") String duration,@Field("jobDesc") String jobDesc,@Field("userId") String userId,@Field("token") String token);


    @FormUrlEncoded
    @POST(UrlContent.updateWork)
    Observable<String> updateWork(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.checkRegister)
    Observable<String> checkRegister(@FieldMap Map<String, Object> map, @Field("phones") List<String> list);

    @FormUrlEncoded
    @POST(UrlContent.combatCharts)
    Observable<String> combatCharts(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.getRecomends)
    Observable<String> getRecomends(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.crystalGoods)
    Observable<String> crystalGoods(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.memberGoods)
    Observable<String> memberGoods(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.deleteEssay)
    Observable<String> deleteEssay(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.getEssayByIdNew)
    Observable<String> getEssayByIdNew(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.getCommentByPageNew)
    Observable<String> getCommentByPageNew(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.myfabulous)
    Observable<String> myfabulous(@FieldMap Map<String, Object> map);

    @Multipart
    @POST(UrlContent.publishEssay)
    Observable<ResponseBody> publishEssay(@PartMap Map<String, RequestBody> params, @Part MultipartBody.Part image);

    @Multipart
    @POST(UrlContent.publishEssay)
    Observable<String> publishEssayForm(@Part List<MultipartBody.Part> partLis, @Part("token") String token, @Part("userId") String userId, @Part("sys") String sys, @Part("lang") String lang, @Part("imgType") String imgType, @Part("content") String content);

    @FormUrlEncoded
    @POST(UrlContent.getEssays)
    Observable<String> getEssays(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.getComments)
    Observable<String> getComments(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.comment)
    Observable<String> comment(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.joinGroup)
    Observable<String> joinGroup(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.quitGroup)
    Observable<String> quitGroup(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.getCompanyMembers)
    Observable<String> getCompanyMembers(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.getGroupMembers)
    Observable<String> getGroupMembers(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.getCompanys)
    Observable<String> getCompanys(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.updateBasic)
    Observable<String> updateBasic(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.addBlockChain)
    Observable<String> addBlockChain(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.addEducation)
    Observable<String> addEducation(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.updateEducation)
    Observable<String> updateEducation(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.mineRefresh)
    Observable<String> mineRefresh(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.briefRefresh)
    Observable<String> briefRefresh(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.homeRefresh)
    Observable<String> homeRefresh(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.maichang)
    Observable<String> maichang(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.genesisCuv)
    Observable<String> genesisCuv(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.getBreakingNews)
    Observable<String> getBreakingNews(@FieldMap Map<String, Object> map);

    @FormUrlEncoded//获取申请者
    @POST(UrlContent.getAppliers)
    Observable<String> getAppliers(@FieldMap Map<String, Object> map);

    @FormUrlEncoded//获取好友
    @POST(UrlContent.getFriends)
    Observable<String> getFriends(@FieldMap Map<String, Object> map);

    @FormUrlEncoded//获取黑名单
    @POST(UrlContent.getBlacks)
    Observable<String> getBlacks(@FieldMap Map<String, Object> map);

    @FormUrlEncoded//删除好友
    @POST(UrlContent.deleteFriend)
    Observable<String> deleteFriend(@FieldMap Map<String, Object> map);

    @FormUrlEncoded//移除黑名单
    @POST(UrlContent.unBlack)
    Observable<String> unBlack(@FieldMap Map<String, Object> map);

    @FormUrlEncoded//拉黑
    @POST(UrlContent.genesisBlack)
    Observable<String> genesisBlack(@FieldMap Map<String, Object> map);

    @FormUrlEncoded//通过申请
    @POST(UrlContent.adopt)
    Observable<String> adopt(@FieldMap Map<String, Object> map);

    @FormUrlEncoded//忽略申请
    @POST(UrlContent.ignore)
    Observable<String> ignore(@FieldMap Map<String, Object> map);

    @FormUrlEncoded//好友申请
    @POST(UrlContent.apply)
    Observable<String> apply(@FieldMap Map<String, Object> map);

    @FormUrlEncoded//同步好友关系
    @POST(UrlContent.synFriends)
    Observable<String> synFriends(@FieldMap Map<String, Object> map);

    @FormUrlEncoded//关注
    @POST(UrlContent.followFriends)
    Observable<String> followFriends(@FieldMap Map<String, Object> map);

    @FormUrlEncoded//取消关注
    @POST(UrlContent.unFollow)
    Observable<String> unFollow(@FieldMap Map<String, Object> map);

    @FormUrlEncoded//获取粉丝列表
    @POST(UrlContent.getFans)
    Observable<String> getFans(@FieldMap Map<String, Object> map);

    @FormUrlEncoded//获取关注列表
    @POST(UrlContent.getFollows)
    Observable<String> getFollows(@FieldMap Map<String, Object> map);

    @FormUrlEncoded//获取用户粗略信息
    @POST(UrlContent.rough)
    Observable<String> rough(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.resetPassword)
    Observable<String> resetPassword(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.checkVrfCode)
    Observable<String> checkVrfCode(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.deletePet)
    Observable<String> deletePet(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.bindCloudPets)
    Observable<String> bindCloudPets(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.forgetCode)
    Observable<String> forgetCode(@FieldMap Map<String, Object> map);

    @FormUrlEncoded//add 注册-发送验证码
    @POST(UrlContent.sendForSignup)
    Observable<String> sendForSignup(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.buyVip)
    Observable<String> buyVip(@FieldMap Map<String, Object> map);

    //资产归集
    @FormUrlEncoded
    @POST(UrlContent.assetsImputation)
    Observable<String> assetsImputation(@FieldMap Map<String, Object> map);

    //修复脉场空间购买数据
    @FormUrlEncoded
    @POST(UrlContent.repaireCrowdFundData)
    Observable<String> repaireCrowdFundData(@FieldMap Map<String, Object> map);

    //脉场股东分红
    @FormUrlEncoded
    @POST(UrlContent.scheduleEquityPartnersDeposit)
    Observable<String> scheduleEquityPartnersDeposit(@FieldMap Map<String, Object> map);


    //统计数据测试接口
    @FormUrlEncoded
    @POST(UrlContent.statisticsTest)
    Observable<String> statisticsTest(@FieldMap Map<String, Object> map);


    @FormUrlEncoded//add 忘记密码-发送验证码
    @POST(UrlContent.sendForForget)
    Observable<String> sendForForget(@FieldMap Map<String, Object> map);

    @FormUrlEncoded//add 注册邀请setInvitedCodes码
    @POST(UrlContent.setInvitedCodes)
    Observable<String> setInvitedCodes(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.charts)
    Observable<String> getCharts(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.harvest)
    Observable<String> harvest(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.getUser)
    Observable<String> getUser(@FieldMap Map<String, Object> map);

    @POST(UrlContent.sendVrfCode)
    Observable<String> sendVrfCode(@QueryMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.goodsBuyRecords)
    Observable<String> goodsBuyRecords(@FieldMap Map<String, Object> map);

    //config
    @FormUrlEncoded
    @POST(UrlContent.config)
    Observable<String> config(@FieldMap Map<String, Object> map);

    @POST(UrlContent.petType)
    Observable<String> petType();

    @FormUrlEncoded
    @POST(UrlContent.myPets)
    Observable<String> myPets(@FieldMap Map<String, Object> map);

    @Multipart
    @POST(UrlContent.upImg)
    Observable<String> upImg(@Part("token") String token, @Part("userId") String userId, @Part("imgType") String imgType, @Part MultipartBody.Part file);

//    @Multipart
//    @POST(UrlContent.upImg)
//    Observable<String> upImg(@PartMap Map<String , Object> map, @Part MultipartBody.Part file);

    @FormUrlEncoded
    @POST(UrlContent.register)
    Observable<String> register(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.checkVrfAndSetPassword)//新注册校验短信验证码并设置密码接口
    Observable<String> checkVrfAndSetPassword(@FieldMap Map<String, Object> map);
    @FormUrlEncoded
    @POST(UrlContent.resetPasswordWithVrf)//新忘记密码校验短信验证码并设置密码接口
    Observable<String> resetPasswordWithVrf(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.mustBasic)//新注册-资料编辑
    Observable<String> mustBasic(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.addPet)
    Observable<String> addPet(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.harvestList)
    Observable<String> harvestList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.getOrders)
    Observable<String> getOrders(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.crystalVsPbs)
    Observable<String> crystalVsPbs(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.recommendGroups)
    Observable<String> recommendGroups(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.getUsdtPbs)
    Observable<String> getUsdtPbs(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.getDepositGoods)
    Observable<String> getDepositGoods(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.checkFriends)
    Observable<String> checkFriends(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.getCourseIntro)
    Observable<String> getCourseIntro(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.getBasicInfos)
    Observable<String> getBasicInfos(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.combatList)
    Observable<String> combatList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.drawPbs)
    Observable<String> drawPbs(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.transfer)
    Observable<String> transfer(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.getCheckAmount)
    Observable<String> getCheckAmount(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.buyFromOfficial2)
    Observable<String> buyFromOfficial2(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.futuresBuyFromOfficial2)
    Observable<String> futuresBuyFromOfficial2(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.sendForDrawToBqex)
    Observable<String> sendForDrawToBqex(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.drawToBqex)
    Observable<String> drawToBqex(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.drawToZfb)
    Observable<String> drawToZfb(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.createReceiptOrder)
    Observable<String> createReceiptOrder(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.receiptFromBqex)
    Observable<String> receiptFromBqex(@FieldMap Map<String, Object> map);


    @FormUrlEncoded
    @POST(UrlContent.keruyunGetToken)
    Observable<String> keruyunGetToken(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.createKeruyunOrderID)
    Observable<String> createKeruyunOrderID(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.unifiedorderForApp)
    Observable<String> unifiedorderForApp(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.startPollMember)
    Observable<String> startPollMember(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST(UrlContent.createOrder)
    Observable<String> createOrder(@FieldMap Map<String, Object> map);

    //修改密码
    @FormUrlEncoded
    @POST(UrlContent.modifyPwd)
    Observable<String> modifyPwd(@Field("param") String fields);

    //获取用户信息
    @FormUrlEncoded
    @POST(UrlContent.getUserInfo)
    Observable<String> getUserInfo(@Field("param") String fields);

    //获取用户信息
    @FormUrlEncoded
    @POST(UrlContent.getUserInfoNew)
    Observable<String> getUserInfoNew(@FieldMap Map<String, Object> map);

    //根据职业获取用户列表
    @FormUrlEncoded
    @POST(UrlContent.getUserInfoByPid)
    Observable<String> getUserInfoByPid(@FieldMap Map<String, Object> map);


    //获取有赞微商城无登录态token接口
    @FormUrlEncoded
    @POST(UrlContent.yzToken)
    Observable<String> yzToken(@FieldMap Map<String, Object> map);

    //有赞微商城登录接口
    @FormUrlEncoded
    @POST(UrlContent.yzLogin)
    Observable<String> yzLogin(@FieldMap Map<String, Object> map);

    //有赞微商城登录接口
    @FormUrlEncoded
    @POST(UrlContent.yzLoginNew)
    Observable<String> yzLoginNew(@FieldMap Map<String, Object> map);

    //修改地址
    @FormUrlEncoded
    @POST(UrlContent.modifyAddress)
    Observable<String> modifyAddress(@Field("param") String fields);

    //修改邮箱
    @FormUrlEncoded
    @POST(UrlContent.modifEmail)
    Observable<String> modifEmail(@Field("param") String fields);

    //退出登录
    @FormUrlEncoded
    @POST(UrlContent.loginout)
    Observable<String> loginout(@Field("param") String fields);

    //检查课程购买状态
    @FormUrlEncoded
    @POST(UrlContent.checkCourseBuy)
    Observable<String> checkCourseBuy(@FieldMap Map<String, Object> map);

    //检查课程购买状态
    @FormUrlEncoded
    @POST(UrlContent.buyCourse)
    Observable<String> buyCourse(@FieldMap Map<String, Object> map);

    //获取课时列表
    @FormUrlEncoded
    @POST(UrlContent.getLessons)
    Observable<String> getLessons(@FieldMap Map<String, Object> map);

    //删除教育经历或工作经验
    @FormUrlEncoded
    @POST(UrlContent.deleteInfo)
    Observable<String> deleteInfo(@FieldMap Map<String, Object> map);

    //付费查看对方资料
    @FormUrlEncoded
    @POST(UrlContent.buyInfo)
    Observable<String> buyInfo(@FieldMap Map<String, Object> map);

    //付费查看对方资料
    @FormUrlEncoded
    @POST(UrlContent.cancelApply)
    Observable<String> cancelApply(@FieldMap Map<String, Object> map);

    //提现记录
    @FormUrlEncoded
    @POST(UrlContent.withdrawalRecords)
    Observable<String> drawalRecords(@FieldMap Map<String, Object> map);

    //获取双向好友申请信息
    @FormUrlEncoded
    @POST(UrlContent.getApplyMeAndMyApply)
    Observable<String> getApplyMeAndMyApply(@FieldMap Map<String, Object> map);

    //搜索有赞商品
    @FormUrlEncoded
    @POST(UrlContent.youzanSearch)
    Observable<String> youzanSearch(@FieldMap Map<String, Object> map);


    //获取有赞商品
    @FormUrlEncoded
    @POST(UrlContent.youzanGetGood)
    Observable<String> youzanGetGood(@FieldMap Map<String, Object> map);

    //获取有赞在售商品列表
    @FormUrlEncoded
    @POST(UrlContent.youzanOnsale)
    Observable<String> youzanOnsale(@FieldMap Map<String, Object> map);


    //获取有赞在售商品列表
    @FormUrlEncoded
    @POST(UrlContent.youzanOnsaleV2)
    Observable<String> youzanOnsaleV2(@FieldMap Map<String, Object> map);


    //获取有赞在售商品列表
    @FormUrlEncoded
    @POST(UrlContent.buyTest)
    Observable<String> buyTest(@FieldMap Map<String, Object> map);

    //个人数据迁移
    @FormUrlEncoded
    @POST(UrlContent.institutionMigration)
    Observable<String> institutionMigration(@FieldMap Map<String, Object> map);

    //新制度下人脉收益测试
    @FormUrlEncoded
    @POST(UrlContent.scheduleProduct20190717Test)
    Observable<String> scheduleProduct20190717Test(@FieldMap Map<String, Object> map);


    //新制度下人脉收益测试
    @FormUrlEncoded
    @POST(UrlContent.buChang)
    Observable<String> buChang(@FieldMap Map<String, Object> map);


    //按月查询数据
    @FormUrlEncoded
    @POST(UrlContent.teamMonthPage)
    Observable<String> teamMonthPage(@FieldMap Map<String, Object> map);


    //解冻PBS
    @FormUrlEncoded
    @POST(UrlContent.unfreezePBS)
    Observable<String> unfreezePBS(@FieldMap Map<String, Object> map);

    //卖家操作
    @FormUrlEncoded
    @POST(UrlContent.froceAction)
    Observable<String> froceAction(@FieldMap Map<String, Object> map);


    //获取推荐人
    @FormUrlEncoded
    @POST(UrlContent.getRecommendByTag)
    Observable<String> getRecommendByTag(@FieldMap Map<String, Object> map);

    //添加好友访问记录
    @FormUrlEncoded
    @POST(UrlContent.addFriendLog)
    Observable<String> addFriendLog(@FieldMap Map<String, Object> map);

    //添加好友访问记录
    @FormUrlEncoded
    @POST(UrlContent.findFriendLog)
    Observable<String> findFriendLog(@FieldMap Map<String, Object> map);


    //斗米职位列表
    @FormUrlEncoded
    @POST(UrlContent.positionList)
    Observable<String> getDoumiPositionList(@FieldMap Map<String, Object> map);


    //斗米职位筛选条件
    @FormUrlEncoded
    @POST(UrlContent.positionOptions)
    Observable<String> getPositionOptions(@FieldMap Map<String, Object> map);

    //获取PDF
    @FormUrlEncoded
    @POST(UrlContent.getContractPDF)
    Observable<String> getContractPDF(@FieldMap Map<String, Object> map);


    //空间-活动列表
    @POST(UrlContent.kongjianhuodong)
    @FormUrlEncoded
    Observable<String> getkongjian(@FieldMap Map<String, Object> map);


    //空间-活动列表
    @POST(UrlContent.zhiyingzhongchou)
    @FormUrlEncoded
    Observable<String> getzhongchou(@FieldMap Map<String, Object> map);


    //空间-加盟列表
    @POST(UrlContent.jiamen)
    @FormUrlEncoded
    Observable<String> getjiamen(@FieldMap Map<String, Object> map);



    //快速登录
    @POST(UrlContent.quicklylogin)
    @FormUrlEncoded
    Observable<String> getquickly(@FieldMap Map<String, Object> map);

    //点赞

    @POST(UrlContent.dianzan)
    @FormUrlEncoded
    Observable<String> getdianzan(@FieldMap Map<String, Object> map);

    //评论
    @POST(UrlContent.pinglun)
    @FormUrlEncoded
    Observable<String> getpinglun(@FieldMap Map<String, Object> map);

    //评论翻页
    @POST(UrlContent.pinglunpage)
    @FormUrlEncoded
    Observable<String> getpinglunpage(@FieldMap Map<String, Object> map);


    //微博长文
    @POST(UrlContent.weibo)
    @FormUrlEncoded
    Observable<String> getWeibo(@FieldMap Map<String, Object> requestMap);

    /*//我的--动态
    @POST(UrlContent.dynamic)
    @FormUrlEncoded
    Observable<String> getdynamic(@FieldMap  Map<String, Object> requestMap,@Part RequestBody body);
*/
    //我的--动态
    @POST(UrlContent.dynamic)
    @FormUrlEncoded
    Observable<String> getdynamic(@Field("dic") RequestBody requestBody, @Field("pageNum") int pageNum, @Field("essayNum") int essayNum
           );

}
