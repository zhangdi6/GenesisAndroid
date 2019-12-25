package com.iruiyou.common.http;

import com.iruiyou.http.retrofit_rx.Api.BaseApi;

/**
 * 类描述:Url
 * 作者：Created by JiaoPeiRong on 2017/4/29 23:07
 * 邮箱：chinajpr@163.com
 */

public class UrlContent {
    public static final String PWD = "JuNm8Rdbaw@209";
    //根Url(请在com.iruiyou.http.retrofit_rx.Api.BaseApi中配置)
    public static final String BaseUrl = "http://q1726242p4.iask.in:8001/server/index.php/";
    //登录
//    public static final String login = "api/auth/login";
    public static final String login = "auth4/login";
    //添加工作经验
    public static final String addWork = "genesis/addWork";
    //修改工作经验
    public static final String updateWork = "genesis/updateWork";
    //请求好友
    public static final String checkRegister = "genesis/checkRegister";//checkRegister换成checkFriends参数不变
    //排行榜
    public static final String combatCharts = "genesis/combatCharts";
    //基本资料
    public static final String updateBasic = "genesis/updateBasicNew";
    //获取信息
    public static final String getContractPDF = "genesis/getContractByUserId";
    //添加区块链信息
    public static final String addBlockChain = "genesis/updateBlockchain";
    //添加教育经历
    public static final String addEducation = "genesis/addEducation";
    //更改教育经历
    public static final String updateEducation = "genesis/updateEducation";
    //我的页面
    public static final String mineRefresh = "genesis/mineRefresh";
    //我的钱包
//    public static final String harvestList = "genesis/harvestList";
    //简历
    public static final String briefRefresh = "genesis/briefRefresh";
    //首页接口
    public static final String homeRefresh = "genesis/homeRefresh";
    //设置密码
    public static final String resetPassword = "auth4/resetPassword";
    //获取验证码
    public static final String sendVrfCode = "auth4/sendVrfCode";
    //忘记密码获取验证码
    public static final String forgetCode = "auth4/forget";
    //校验验证码
    public static final String checkVrfCode = "auth4/checkVrfCode";
    //删除宠物
    public static final String deletePet = "api/genesis/deletePet";
    //绑定云宠
    public static final String bindCloudPets = "api/auth2/bindCloudPets";
    //配置
    public static final String config = "genesis/config";
    //宠物框里排行榜
    public static final String charts = "api/genesis/charts";
    //收取pete币
    public static final String harvest = "genesis/harvest";
    //获取用户信息
    public static final String getUser = "api/auth2/selfInfo";
    //宠物类型
    public static final String petType = "api/genesis/variety";
    //宠物列表
    public static final String myPets = "api/genesis/myPets";
    //上传头像
    public static final String upImg = "upload/imageNoSet";
    //添加宠物
    public static final String addPet = "api/genesis/addPet";
    //获取有赞微商城无登录态token接口
    public static final String yzToken = "api/auth2/yzToken";
    //有赞微商城登录接口
    public static final String yzLogin = "api/auth2/yzLogin";
    //注册
    //public static final String register = "api/auth/signup";
    public static final String register = "auth4/sendForLoginAndRegister";
    //收支列表
    public static final String harvestList = "genesis/harvestList";

    //我的算力
    public static final String combatList = "genesis/combatList";
    //修改密码
    public static final String modifyPwd = "User/modifyPwd";
    //获取用户信息
    public static final String getUserInfo = "User/getUserInfo";
    //根据职业分类获取对应用户列表
    public static final String getUserInfoByPid="genesis/getUserByPid";
    //修改地址
    public static final String modifyAddress = "User/modifyAddress";
    //修改邮箱
    public static final String modifEmail = "User/modifEmail";
    //退出登录
    public static final String loginout = "User/logout";
    //星球秘籍
    public static final String guide = "genesis/guide";
    //邀请好友
    public static final String invite = "genesis/invite";
    //兑换优惠码
    public static final String discount = "genesis/discount";

    //获取用户信息
    public static final String getUserInfoNew = "genesis/getUserInfo";



    //新加
    //注册-校验短信验证码并设置密码
    public static final String checkVrfAndSetPassword = "auth4/checkVrfAndSetPassword";
    //忘记密码-校验短信验证码并设置密码
    public static final String resetPasswordWithVrf = "auth4/resetPasswordWithVrf";
    //注册-发送验证码
    public static final String sendForSignup = "auth4/sendForSignup";
    //忘记密码-发送验证码
    public static final String sendForForget = "auth4/sendForForget";
    //注册邀请码
    public static final String setInvitedCodes = "auth4/setInvitedCode";
    //账号密码登陆
    public static final String loginWithPassword = "auth4/loginWithPassword";
    //账号登陆
    public static final String loginWithOutPassword = "auth4/loginWithoutPassword";
    //重置密码-老密码
    public static final String resetPasswordWithOld = "auth4/resetPasswordWithOld";

    //注册-资料编辑
    public static final String mustBasic = "genesis/mustBasic";

    //好友申请
    public static final String apply = "genesis/apply";
    //通过申请
    public static final String adopt = "genesis/adopt";
    //忽略申请
    public static final String ignore = "genesis/ignore";
    //拉黑
    public static final String genesisBlack = "genesis/black";
    //移出黑名单
    public static final String unBlack = "genesis/unBlack";
    //删除好友
    public static final String deleteFriend = "genesis/deleteFriend";
    //检查联系人注册状态，并获取好友
    public static final String checkFriends = "genesis/checkFriends";
    //获取好友
    public static final String getFriends = "genesis/getFriends";
    //获取申请者
    public static final String getAppliers = "genesis/getAppliers";
    //获取黑名单
    public static final String getBlacks = "genesis/getBlacks";
    //同步好友关系
    public static final String synFriends = "genesis/synFriends";
    //关注
    public static final String followFriends = "genesis/follow";
    //取消关注
    public static final String unFollow = "genesis/unFollow";
    //获取粉丝列表
    public static final String getFans = "genesis/getFans";
    //获取关注列表
    public static final String getFollows = "genesis/getFollows";
    //获取用户粗略信息
    public static final String rough = "genesis/rough";

    //获取推荐
    public static final String getRecomends = "genesis/getRecomends";
    //发布短文
    public static final String publishEssay = "genesis/publishEssay";
    //删除短文
    public static final String deleteEssay = "genesis/deleteEssay";
    //获取短文
    public static final String getEssays = "genesis/getEssaysNew";
    //获取长文详情
    public static final String getEssayByIdNew = "genesis/getEssayByIdNew";
    //翻页获取长文评论
    public static final String getCommentByPageNew = "genesis/getCommentByPageNew";
    //发评论
    public static final String comment = "genesis/comment";
    //获取评论
    public static final String getComments = "genesis/getComments";
    //回复
    public static final String reply = "genesis/reply";
    //获取回复
    public static final String getReplys = "genesis/getReplys";
    //赞
    public static final String myfabulous = "genesis/fabulous";
    //打赏
    public static final String reward = "genesis/reward";
    //同步点赞记录
    public static final String syncFabulous = "genesis/syncFabulous";
    //加入社群
    public static final String joinGroup = "genesis/joinGroup";
    //退出社群
    public static final String quitGroup = "genesis/quitGroup";
    //获取公司信息
    public static final String getCompanys = "genesis/getCompany";
    //获取公司队员
    public static final String getCompanyMembers = "genesis/getCompanyMembers";
    //获取群成员的信息
    public static final String getGroupMembers = "genesis/getGroupMembers";
    //水晶PBS互换
    public static final String crystalVsPbs = "genesis/crystalVsPbs";
    //有赞微商城登录接口
    public static final String yzLoginNew = "auth2/yzLogin";
    //获取某用户的资料
    public static final String getBasicInfos = "genesis/getBasicInfo";
    //获取水晶充值订单
    public static final String getOrders = "youzan/getOrders";
    //获取群组推荐
    public static final String recommendGroups = "genesis/recommendGroups";
    //获取我加过的群
    public static final String getMyGroups = "genesis/getMyGroups";
    //钱包页的帮助地址
    public static final String walletHelp = "genesis/walletHelp";
    //公告
    public static final String depositNotice = "deposit/notice";
    //59、获取水晶商品列表
    public static final String crystalGoods = "genesis/crystalGoods";
    //会员列表
    public static final String memberGoods ="/api/genesis/memberGoods";
    //60、获取课程列表
    public static final String getCourseIntro = "genesis/getCourseIntro";
    //好友访问日志
    public static final String addFriendLog = "genesis/addFriendLog";
    //获取好友访问日志
    public static final String findFriendLog = "genesis/findFriendLog";
    //获取pbs行情
    public static final String getUsdtPbs = "genesis/getUsdtPbs";
    //获取pbs行情
    public static final String getDepositGoods = "deposit/goods";

    public static final String pbsLoginInApp = "pbs/loginInApp";
    public static final String depositLoginInApp = "deposit/loginInApp";
    //pbs提现接口
    public static final String drawPbs = "omni/drawPbs";
    //（钱包转账方式）生成PBS充值金额
    public static final String getCheckAmount = "omni/getCheckAmount";
    //63、购买定存宝接口(普通定存宝)
    public static final String buyFromOfficial2 = "deposit/buyFromOfficial2";
    //64、购买高端定存宝接口(高端定存宝)
    public static final String futuresBuyFromOfficial2 = "futures/buyFromOfficial2";
    //65、上传有赞订单号接口
    public static final String createOrder = "youzan/createOrder";
    //66、脉场页接口
    public static final String maichang = "genesis/maichang";
    //66、脉场页接口
    public static final String genesisCuv = "genesis/cuv";
    //67、PBS转账接口
    public static final String transfer = "genesis/transfer";
    //68、脉场首页咨询
    public static final String getBreakingNews = "genesis/getBreakingNews";
    //提取时获取短信码接口
    public static final String sendForDrawToBqex = "omni/sendForDrawToBqex";
    //币全提取接口
    public static final String drawToBqex = "omni/drawToBqex";
    //支付宝提取接口
    public static final String drawToZfb = "omni/drawToZfb";
    //创建币全收款订单
    public static final String createReceiptOrder = "omni/createReceiptOrder";
    //币全收款
    public static final String receiptFromBqex = "omni/receiptFromBqex";
    //客如云测试
    public static final String keruyunGetToken = "keruyun/getToken";
    //创建购买客如云订单号
    public static final String createKeruyunOrderID = "keruyun/createKeruyunOrderID";
    //微信购买统一下单接口
    public static final String unifiedorderForApp = "wx/unifiedorderForApp";
    //微信订单轮询接口
    public static final String startPollMember = "wx/startPollMember";
    //购买会员接口
    public static final String buyVip = "product/buyProduct";
    //资产提前归集接口
    public static final String assetsImputation = "product/assetsImputation";
    //修复脉场空间购买数据
    public static final String repaireCrowdFundData = "product/repaireCrowdFundData";
    //脉场股东分红
    public static final String scheduleEquityPartnersDeposit = "product/scheduleEquityPartnersDeposit";
    //统计数据测试接口
    public static final String statisticsTest = "product/statisticsTest";
    //检查课程购买状态
    public static final String checkCourseBuy = "genesis/checkCourseBuy";
    //购买课程
    public static final String buyCourse = "genesis/buyCourse";
    //获取课时列表
    public static final String getLessons = "genesis/getLessons";
    //删除教育经历或工作经验
    public static final String deleteInfo = "genesis/deleteInfo";
    //付费查看对方资料
    public static final String buyInfo="genesis/buyInfo";
    //取消好友申请
    public static final String cancelApply="genesis/cancelApply";
    //提现记录
    public static final String withdrawalRecords="genesis/drawRecords";
    //分销商品购买记录
    public static final String goodsBuyRecords="youzan/ordersByPhone";
    //获取双向好友申请信息
    public static final String getApplyMeAndMyApply="genesis/getApplyMeAndMyApply";
    //获取有赞在售商品列表
    public static final String youzanOnsale="youzan/onsale";
    //获取有赞在售商品列表
    public static final String youzanOnsaleV2="youzan/onsaleV2";
    //有赞搜索商品
    public static final String youzanSearch="youzan/search";
    //根据商品id获取有赞商品
    public static final String youzanGetGood="youzan/getItemById";
    //BQEX新网址
    public static final String bqex_link2 = "https://market.bqopen.com/";//"http://www.bqex.tw";
    //BQEX旧网址
    public static final String bqex_link1 = "https://app.bqopen.com/bq/index.html";
    //提现教程链接
    public static final String pdf_total="https://pbase.io/downloads/pbs_bqex.pdf";
    //邀请链接
    public static final String invited_url= BaseApi.baseUrlNoApi+"r/";
    //获取商机列表
    public static final String getOpportunities = "genesis/getOpportunities";


    //购买测试
    public static final String buyTest="/api/product/buyProduct2";

    //按月查询数据
    public static final String teamMonthPage = "genesis/teamMonthPage";

    //财务转移测试
    public static final String institutionMigration="/api/product/systemMigration";

    //财务转移测试
    public static final String scheduleProduct20190717Test="/api/product/scheduleProduct20190717Test";

    //财务转移测试
    public static final String buChang="/api/product/buChang";

    public static final String unfreezePBS = "/api/pbs/unfreezePBS";

    //卖家操作
    public static final String froceAction = "/api/pbs/froceAction";

    // 通过标签获取推荐列表
    public static final String getRecommendByTag = "/api/genesis/getUserByPidNew";

    // 斗米职位列表
    public static final String positionList = "/list";

    // 斗米职位筛选条件
    public static final String positionOptions = "/options";

    //空间-活动列表
    public static final String kongjianhuodong = "/api/genesis/getActivityList";



    //空间-众筹列表
    public static final String zhiyingzhongchou = "/api/genesis/getCrowdfundingShopList";



    //空间-加盟列表

    public static final String jiamen = "/api/genesis/getJoinSpaceList";


    //快速登录
    public static final String quicklylogin = "/api/auth4/vrfCodeLogin";

    //点赞
    public static final String dianzan = "/api/genesis/fabulous";

    //评论
    public static final String pinglun = "/api/genesis/comment";

    //评论翻页
    public static final String pinglunpage = "/api/genesis/getCommentByPageNew";

    //微博长文
    public static final String weibo = "/api/genesis/getEssayByIdNew";

    //动态
    public static final String dynamic = "/api/genesis/getEssayByPageNew";


}
