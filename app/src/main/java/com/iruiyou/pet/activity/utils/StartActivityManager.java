package com.iruiyou.pet.activity.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.pet.activity.AboutUsActivity;
import com.iruiyou.pet.activity.AdvancedDepositTreasureActivity;
import com.iruiyou.pet.activity.AssetsDeposited1Activity;
import com.iruiyou.pet.activity.AssetsDepositedActivity;
import com.iruiyou.pet.activity.BQEXTurnInActivity;
import com.iruiyou.pet.activity.BasicInfoActivity;
import com.iruiyou.pet.activity.CalculationActivity;
import com.iruiyou.pet.activity.CashWithdrawalActivity;
import com.iruiyou.pet.activity.CompanyHomeActivity;
import com.iruiyou.pet.activity.CourseContentActivity;
import com.iruiyou.pet.activity.CourseContentActivity2;
import com.iruiyou.pet.activity.CrashSelectActivity;
import com.iruiyou.pet.activity.CrystalRechargeActivity;
import com.iruiyou.pet.activity.EssayDetailActivity;
import com.iruiyou.pet.activity.ForgetActivity;
import com.iruiyou.pet.activity.ForgetActivity3;
import com.iruiyou.pet.activity.FriendApplicationListActivity;
import com.iruiyou.pet.activity.GendersSelectActivity;
import com.iruiyou.pet.activity.GoodsBuyRecordsActivity;
import com.iruiyou.pet.activity.GoodsDetailActivity;
import com.iruiyou.pet.activity.GroupMembersActivity;
import com.iruiyou.pet.activity.HehuorenAgreementActivity;
import com.iruiyou.pet.activity.Home_KeChenActivity;
import com.iruiyou.pet.activity.InvitFriend2;
import com.iruiyou.pet.activity.InvitFriendActivity;
import com.iruiyou.pet.activity.InvitFriendV2Activity;
import com.iruiyou.pet.activity.LookForPeopleActivity;
import com.iruiyou.pet.activity.MCLoginActivity;
import com.iruiyou.pet.activity.MainActivity;
import com.iruiyou.pet.activity.MemberAgreementActivity;
import com.iruiyou.pet.activity.MyInviterActivity;
import com.iruiyou.pet.activity.MyWalletActivity;
import com.iruiyou.pet.activity.MyWalletV2Activity;
import com.iruiyou.pet.activity.NetWorkActivity;
import com.iruiyou.pet.activity.OpenMembershipActivity;
import com.iruiyou.pet.activity.OrdinaryDepositTreasureActivity;
import com.iruiyou.pet.activity.PDFShowActivity;
import com.iruiyou.pet.activity.PartnerRightsActivity;
import com.iruiyou.pet.activity.PbsIncubationActivity;
import com.iruiyou.pet.activity.PersonalMsgActivity;
import com.iruiyou.pet.activity.PositionActivity;
import com.iruiyou.pet.activity.PositionDetailActivity;
import com.iruiyou.pet.activity.RecommendGroupsActivity;
import com.iruiyou.pet.activity.RegisterActivity3;
import com.iruiyou.pet.activity.RegisterLastActivity2;
import com.iruiyou.pet.activity.ResultsOfWithdrawalsActivity;
import com.iruiyou.pet.activity.ResumeActivity2;
import com.iruiyou.pet.activity.SeniorMemberActivity;
import com.iruiyou.pet.activity.SetActivity;
import com.iruiyou.pet.activity.TransactionRecordActivity;
import com.iruiyou.pet.activity.TransferAccountsActivity;
import com.iruiyou.pet.activity.UserDetailsActivity;
import com.iruiyou.pet.activity.VIPSelectActivity;
import com.iruiyou.pet.activity.WebViewActivity;
import com.iruiyou.pet.activity.WebViewNewActivity;
import com.iruiyou.pet.activity.WithdrawalOfAssetsActivity;
import com.iruiyou.pet.activity.ZhiWeiActivity;
import com.iruiyou.pet.activity.registered.RegisterCodeActivity;
import com.iruiyou.pet.bean.DMOptionListBean;
import com.iruiyou.pet.bean.GetCourseIntroBean;
import com.iruiyou.pet.bean.GetEssaysBean;
import com.iruiyou.pet.bean.LoginNewBean;
import com.iruiyou.pet.utils.Constant;

/**
 * 封装一个activity跳转的类
 * Created by sgf on 2018/9/19.
 */

public class StartActivityManager {

//    /**
//     * 支付页面
//     * @param act 调起支付页面的activity
//     * @param cid 合约地址
//     */
//    public static void startPaymentActivity(Activity act,String cid,PaymentActivity.Nature nature){
//        Intent intent = new Intent(act, PaymentActivity.class);
//        intent.putExtra("cid",cid);
//        intent.putExtra("nature",nature);//订单性质（借入/借出）
//        act.startActivity(intent);
//    }
//    /**
//     * 支付页面 待补仓订单状态中还币时调用此方法 status传还币状态6
//     * @param act 调起支付页面的activity
//     * @param cid 合约地址
//     */
//    public static void startPaymentActivity(Activity act,String cid,PaymentActivity.Nature nature,int status){
//        Intent intent = new Intent(act, PaymentActivity.class);
//        intent.putExtra("cid",cid);
//        intent.putExtra("nature",nature);//订单性质（借入/借出）
//        intent.putExtra("status",status);//订单状态中所点击按钮的状态
//        act.startActivity(intent);
//    }
//
//    /**
//     * 注册Activity
//     *
//     * @param act
//     */
//    public static void startRegisterActivity(Activity act,String flag) {
//        Intent intent = new Intent(act, RegisterActivity.class);
//        intent.putExtra("flag", flag);
//        act.startActivity(intent);
//    }





//    /**
//     * 抄写助记词Activity
//     *
//     * @param act
//     */
//    public static void startWriteDownBrainSeedActivity(Activity act, Wallet wallet) {
//        Intent intent = new Intent(act, WriteDownBrainSeedActivity.class);
//        intent.putExtra(Const.KEY_WALLET_BEAN, wallet);
//        act.startActivity(intent);
//    }

//    /**
//     * webViewActivity
//     *
//     * @param act
//     */
//    public static void startWebViewActivity(Activity act, int title, int url,String contract_flag) {
//        Intent intent = new Intent(act, WebViewActivity.class);
//        intent.putExtra("title", act.getString(title));
//        intent.putExtra("url", act.getString(url));
//        intent.putExtra("contract_flag", contract_flag);
//        act.startActivity(intent);
//    }
//
//    /**
//     * webViewActivity
//     *
//     * @param act
//     */
//    public static void startWebViewActivity(Activity act, int title, int state) {
//        Intent intent = new Intent(act, WebViewActivity.class);
//        intent.putExtra("title", act.getString(title));
//        intent.putExtra("url", act.getString(state));//url
//        act.startActivity(intent);
//    }


    public static void startLookForPeople(Activity act,String clickPid){
        Intent intent= new Intent(act, LookForPeopleActivity.class);
        intent.putExtra("clickPid",clickPid);
        act.startActivity(intent);
    }

    public static void startWebViewActivity(Activity act, String title, String url){
        startWebViewActivity(act,title,url,true,false);
    }

    public static void startPositionDetailActivity(Activity act, DMOptionListBean.DataBean dataBean){
        Intent intent = new Intent(act, PositionDetailActivity.class);
        intent.putExtra("dataBean",dataBean);
        act.startActivity(intent);
    }

    /**
     * webViewActivity
     * @param act
     */
    public static void startWebViewActivity(Activity act, String title, String url,boolean isSetTitle,boolean showShare) {
        Intent intent = new Intent(act, WebViewActivity.class);
        intent.putExtra(TagConstants.TITLE, title);
        intent.putExtra(TagConstants.WebTag, url);
        intent.putExtra("isSetTitle",isSetTitle);
        intent.putExtra("showShare",showShare);
        act.startActivity(intent);
    }
    public static void startHome_KeChengActivity(Context act) {
        Intent intent = new Intent(act, Home_KeChenActivity.class);
        act.startActivity(intent);
    }

    public static void startHome_ZhiWeiActivity(Context act) {
        Intent intent = new Intent(act, ZhiWeiActivity.class);
        act.startActivity(intent);
    }


    public static void startBaseicInfoActivity(Activity activity, LoginNewBean.DataBean.BasicInfoBean basicInfo){
        Bundle bundle = new Bundle();
        bundle.putString("PositionTitle",basicInfo.getPosition());
        bundle.putString("_id",basicInfo.get_id());
        bundle.putString("Company",basicInfo.getCompany());
        bundle.putInt("ProfessionalIdentity",basicInfo.getProfessionalIdentity());
        bundle.putString("RealName",basicInfo.getRealName());
        bundle.putString("Position",basicInfo.getPosition());
        bundle.putString("School",basicInfo.getSchool());
        bundle.putString("Education",basicInfo.getEducation()+"");
        bundle.putString("Country",basicInfo.getCountry());
//                                    bundle.putString("Number",basicInfo.getNumber());
        bundle.putString("Nature",basicInfo.getNature());
        bundle.putString("HeadImg",basicInfo.getHeadImg());
        bundle.putInt("genders",basicInfo.getGender());
//                                    bundle.putString("city",basicInfo.getCity());
        bundle.putString("selfDesc",basicInfo.getSelfDesc());
//                                    if(StringUtil.isNotEmpty(basicInfo.getCityCode())){
//                                        bundle.putInt("cityCode",Integer.valueOf(basicInfo.getCityCode()).intValue());
//                                    }
        Intent intent = new Intent(activity, BasicInfoActivity.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }


    /**
     * webViewActivity  跳转到合约详情
     *
     * @param act
     */
//    public static void startWebViewActivity(Activity act, String cid,  String title,String contract_flag) {
//        Intent intent_address = new Intent(act, WebViewActivity.class);
//        intent_address.putExtra("cid",cid);
//        intent_address.putExtra("title",title);
//        intent_address.putExtra("contract_flag", contract_flag);
//        act.startActivity(intent_address);
//    }

    /**
     * 合伙人权益界面
     * @param act
     * @param realName
     * @param crowdFundLevel
     * @param genders
     */
    public static void startPartnerRightsActivity(Context act,String realName,int crowdFundLevel,int genders,String imageHeadUrl,String PositionTitle){
        Intent intent = new Intent(act, PartnerRightsActivity.class);
        intent.putExtra("realName",realName);
        intent.putExtra("crowdFundLevel",crowdFundLevel);
        intent.putExtra("genders",genders);
        intent.putExtra("imageHeadUrl",imageHeadUrl);
        intent.putExtra("PositionTitle",PositionTitle);
        act.startActivity(intent);
    }

    /**
     * 启动PDF展示界面
     * @param act
     */
    public static void startShowPDFActivity(Context act){
        Intent intent = new Intent(act, PDFShowActivity.class);
        act.startActivity(intent);
    }

    /**
     * 短文详情页面
     * @param act
     * @param dataBean
     */
    public static void startEssayDetail(Context act, GetEssaysBean.DataBean dataBean){
        Intent intent = new Intent(act, EssayDetailActivity.class);
        intent.putExtra("GetEssaysBean.DataBean",dataBean);
        act.startActivity(intent);
    }

    /**
     * 商品详情界面
     * @param act
     * @param itemId
     */
    public static void startGoodsDetail(Context act,String itemId){
        Intent intent = new Intent(act, GoodsDetailActivity.class);
        intent.putExtra("itemId",itemId);
        act.startActivity(intent);
    }

    /**
     * 我的钱包
     * @param act
     */
    public static void startMyWalletActivity(Context act) {
        Intent intent = new Intent(act, MyWalletActivity.class);
        act.startActivity(intent);
    }

    /**
     * 我的钱包
     * @param act
     */
    public static void startMyWalletActivityV2(Context act) {
        Intent intent = new Intent(act, MyWalletV2Activity.class);
        act.startActivity(intent);
    }
 /**
     * 脉点充值
     * @param act
     */
    public static void startCrystalRechargeActivity(Context act) {
        Intent intent = new Intent(act, CrystalRechargeActivity.class);
        act.startActivity(intent);
    }

    /**
     * 设置
     * @param act
     */
    public static void startSetActivity(Context act) {
        Intent intent = new Intent(act, SetActivity.class);
        act.startActivity(intent);
    }
    /**
     *我的算力
     * @param act
     */
    public static void startCalculationActivity(Context act) {
        Intent intent = new Intent(act, CalculationActivity.class);
        act.startActivity(intent);
    }
    /**
     * 简历全部资料
     * @param act
     */
    public static void startResumeActivity2(Context act) {
        Intent intent = new Intent(act, ResumeActivity2.class);
        act.startActivity(intent);
    }
    /**
     * 基本资料
     * @param act
     */
    public static void startBasicInfoActivity(Context act) {
        Intent intent = new Intent(act, BasicInfoActivity.class);
        act.startActivity(intent);
    }
    /**
     * 忘记密码与注册的第一个入口（获取验证码）
     * @param act
     */
    public static void startForgetActivity(Context act,String flag) {
        Intent intent = new Intent(act, ForgetActivity.class);
        intent.putExtra("FLAG", flag);
        act.startActivity(intent);
    }
    /**
     * 忘记密码与注册的第一个入口（获取验证码）
     * @param act
     */
    public static void startForgetActivity(Context act,String flag,String phone) {
        Intent intent = new Intent(act, ForgetActivity.class);
        intent.putExtra("FLAG", flag);
        intent.putExtra("phone", phone);
        act.startActivity(intent);
    }

    /**
     * 忘记密码与注册的第二个入口（设置忘记密码与注册密码）
     * @param act
     */
    public static void startForgetActivity3(Context act,String flag, String nationalCode, String phone) {
        Intent intent = new Intent(act, ForgetActivity3.class);
        intent.putExtra("FLAG", flag);
        intent.putExtra("nationalCode", nationalCode);
        intent.putExtra("phone", phone);
        act.startActivity(intent);
    }
    /**
     * 注册
     * @param act
     */
    public static void startRegisterActivity3(Context act,String phone, String nationalCode) {
        Intent intent = new Intent(act, RegisterActivity3.class);
        intent.putExtra("PHONE", phone);
        intent.putExtra("nationalCode", nationalCode);
        act.startActivity(intent);
    }
    /**
     * 注册-邀请码
     * @param act
     * @param nationalCode
     * @param phone
     * @param password
     */
    public static void startRegisterCodeActivity(Context act,String nationalCode,String phone,String smsCode, String password) {
        Intent intent = new Intent(act, RegisterCodeActivity.class);
        intent.putExtra("nationalCode", nationalCode);
        intent.putExtra("phone", phone);
        intent.putExtra("smsCode", smsCode);
        intent.putExtra("password", password);
        act.startActivity(intent);
    }

    /**
     * 注册-最后一步
     * @param act
     * @param nationalCode
     * @param phone
     * @param smsCode
     * @param password
     */
    public static void startRegisterLastActivity2(Context act,String nationalCode,String phone,String smsCode, String password) {
        Intent intent = new Intent(act, RegisterLastActivity2.class);
        intent.putExtra("nationalCode", nationalCode);
        intent.putExtra("phone", phone);
        intent.putExtra("smsCode", smsCode);
        intent.putExtra("password", password);
        act.startActivity(intent);
    }

    /**
     * 注册-最后一步
     * @param act
     * @param nationalCode
     * @param phone
     * @param smsCode
     * @param password
     * @param invitationCode
     */
    public static void startRegisterLastActivity2(Context act,String nationalCode,String phone,String smsCode, String password, String invitationCode) {
        Intent intent = new Intent(act, RegisterLastActivity2.class);
        intent.putExtra("nationalCode", nationalCode);
        intent.putExtra("phone", phone);
        intent.putExtra("smsCode", smsCode);
        intent.putExtra("password", password);
        intent.putExtra("invitationCode", invitationCode);
        act.startActivity(intent);
    }


    public static void startNetWorkActivity(Context act){
        Intent intent = new Intent(act, NetWorkActivity.class);
        act.startActivity(intent);
    }

    /**
     * 我的-求好评
     * @param act
     */
    public static void startWebViewNewActivity(Context act,String title, String url) {
        Intent intent = new Intent(act, WebViewNewActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        act.startActivity(intent);
    }

    /**
     * 我的-求好评
     * @param act
     */
    public static void startWebViewNewActivity(Context act,String title, String url,boolean isCurrentActivity) {
        Intent intent = new Intent(act, WebViewNewActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        intent.putExtra("isCurrentActivity", isCurrentActivity);
        Log.e("test","url is "+url);
        act.startActivity(intent);
    }


    /**
     * 启动性别选择
     * @param act
     * @param genders
     */
    public static void startGendersSelectActivity(Activity act,int genders,int code){
        Intent intent = new Intent(act, GendersSelectActivity.class);
        intent.putExtra("genders",genders);
        act.startActivityForResult(intent,code);
    }

    /**
     * 简历全部资料
     * @param act
     */
    public static void startResumeActivity2(Context act,String flag) {
        Intent intent = new Intent(act, ResumeActivity2.class);
        intent.putExtra("switch", flag);
        act.startActivity(intent);
    }

    /**
     * 邀请好友
     * @param act
     */
    public static void startInvitFriend2(Context act) {
        Intent intent = new Intent(act, InvitFriend2.class);
        act.startActivity(intent);
    }
    /**
     * 新邀请好友
     * @param act
     */
    public static void startInvitFriendActivity(Context act) {
        Intent intent = new Intent(act, InvitFriendActivity.class);
        act.startActivity(intent);
    }

    /**
     * 新邀请好友
     * @param act
     */
    public static void startInvitFriendV2Activity(Context act) {
        Intent intent = new Intent(act, InvitFriendV2Activity.class);
//        intent.putExtra("inviteCode",inviteCode);
        act.startActivity(intent);
    }


    /**
     * 我的邀请人信息界面
     * @param act
     */
    public static void startMyInviterActivity(Context act,String inviteCode) {
        Intent intent = new Intent(act, MyInviterActivity.class);
        intent.putExtra("inviteCode",inviteCode);
        act.startActivity(intent);
    }



    /**
     * 币全转入
     * @param act
     */
    public static void startBQEXTurnInActivity(Context act) {
        Intent intent = new Intent(act, BQEXTurnInActivity.class);
        act.startActivity(intent);
    }

    /**
     * 会员协议
     * @param act
     */
    public static void startMemberAgreementActivity(Context act) {
        Intent intent = new Intent(act, MemberAgreementActivity.class);
        act.startActivity(intent);
    }

    /**
     * 合伙人协议
     * @param act
     */
    public static void startHehuoAgreementActivity(Context act) {
        Intent intent = new Intent(act, HehuorenAgreementActivity.class);
        act.startActivity(intent);
    }


    /**
     * 职业身份
     * @param act
     */
    public static void startPositionActivity(Context act,String position) {
        Intent intent = new Intent(act, PositionActivity.class);
        intent.putExtra(Constant.WORKFLAG, position);
        act.startActivity(intent);
    }
    /**
     * 登陆
     * @param act
     */
    public static void startLoginActivity3(Context act) {
        Intent intent = new Intent(act, MCLoginActivity.class);
//        intent.putExtra(Constant.WORKFLAG, position);
        act.startActivity(intent);
    }

    /**
     * 首页
     * @param act
     */
    public static void startMainActivity(Context act) {
        Intent intent = new Intent(act, MainActivity.class);
        act.startActivity(intent);
    }
    /**
     * 关于我们
     * @param act
     */
    public static void startAboutUsActivity(Context act) {
        Intent intent = new Intent(act, AboutUsActivity.class);
        act.startActivity(intent);
    }

    /**
     * 商品购买记录列表
     * @param act
     */
    public static void startGoodsBuyList(Context act){
        Intent intent = new Intent(act, GoodsBuyRecordsActivity.class);
        act.startActivity(intent);
    }

    /**
     * 高级会员
     * @param act
     */
    public static void startSeniorMemberActivity(Context act,String name,String headIm,int vipLevel,boolean isPartener) {
//        Intent intent = new Intent(act, SeniorMemberActivity.class);
//        intent.putExtra("name", name);
//        intent.putExtra("headIm", headIm);
//        intent.putExtra("vipLevel", vipLevel);
//        act.startActivity(intent);
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("headIm", headIm);
        bundle.putInt("vipLevel", vipLevel);
        bundle.putBoolean("isPartner", isPartener);
        Intent intent1 = new Intent(act, SeniorMemberActivity.class);
        intent1.putExtras(bundle);
        act.startActivity(intent1);
    }

    /**
     * 高级会员
     * @param act
     */
    public static void startSeniorMemberActivity(Context act,String name,String headIm,int vipLevel) {
        startSeniorMemberActivity(act,name,headIm,vipLevel,false);
    }

    /**
     * 好友申请列表
     * @param act
     */
    public static void startFriendApplication(Context act)
    {
        Intent intent = new Intent(act, FriendApplicationListActivity.class);
        act.startActivity(intent);
    }

    /**
     * 用户的资料
     * @param act
     */
    public static void startUserDetailsActivity(Context act,int userid,String realName) {
        Intent intent = new Intent(act, UserDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("userid", userid);
        bundle.putString("realName", realName);
        intent.putExtras(bundle);
        act.startActivity(intent);
    }




    /**
     * 每一个用户的详细资料
     * @param act
     */
    public static void startPersonalMsgActivity(Context act,int userid,String realName) {
        Intent intent = new Intent(act, PersonalMsgActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("userid", userid);
        bundle.putString("realName", realName);
        intent.putExtras(bundle);
        act.startActivity(intent);
    }


    /**
     * 公司社区
     * @param act
     */
    public static void startCompanyHomeActivity(Context act) {
        Intent intent = new Intent(act, CompanyHomeActivity.class);
        act.startActivity(intent);
    }
    /**
     * 群聊推荐
     * @param act
     */
    public static void startRecommendGroupsActivity(Context act) {
        Intent intent = new Intent(act, RecommendGroupsActivity.class);
        act.startActivity(intent);
    }
    /**
     * 水晶充值
     * @param act
     */
    public static void startCrystalRechargeActivity(Context act,String crystal) {
        Intent intent = new Intent(act, CrystalRechargeActivity.class);
        intent.putExtra("crystal", crystal);
        act.startActivity(intent);
    }
    /**
     * 职场-课程内容
     * @param act
     */
    public static void startCourseContentActivity(Context act, GetCourseIntroBean.DataBean bean, int position) {
        Intent intent = new Intent(act, CourseContentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("getCourseIntroBean", bean);
        bundle.putInt("CourseIntroPosition", position);
        intent.putExtras(bundle);
        act.startActivity(intent);
    }
    /**
     * 职场-课程内容新版
     * @param act
     */
    public static void startCourseContent2Activity(Context act, GetCourseIntroBean.DataBean bean, int position) {
        Intent intent = new Intent(act, CourseContentActivity2.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("getCourseIntroBean", bean);
        bundle.putInt("CourseIntroPosition", position);
        intent.putExtras(bundle);
        act.startActivity(intent);
    }
    /**
     * 交易记录
     * @param act
     */
    public static void startTransactionRecordActivity(Context act) {
        Intent intent = new Intent(act, TransactionRecordActivity.class);
        act.startActivity(intent);
    }
    /**
     * 提现结果
     * @param act
     */
    public static void startResultsOfWithdrawalsActivity(Context act) {
        Intent intent = new Intent(act, ResultsOfWithdrawalsActivity.class);
        act.startActivity(intent);
    }
    /**
     * PBS孵化
     * @param act
     */
    public static void startPbsIncubationActivity(Context act) {
        Intent intent = new Intent(act, PbsIncubationActivity.class);
        act.startActivity(intent);
    }
    /**
     * 群成员信息
     * @param act
     */
    public static void startGroupMembersActivity(Context act,int member) {
        Bundle bundle = new Bundle();
        bundle.putInt("member", member);
        Intent intent = new Intent(act, GroupMembersActivity.class);
        intent.putExtras(bundle);
        act.startActivity(intent);
    }


    /**
     * 提取资产类型选择
     * @param act
     */
    public static void startCrashSelectActivity(Context act,String pbsAmount,String fixedAssetsAmount,
                                                String usdtPbs,String rebateCrystal,String UsdCNY) {
        Intent intent = new Intent(act, CrashSelectActivity.class);
        intent.putExtra("pbsAmount", pbsAmount);
        intent.putExtra("fixedAssetsAmount", fixedAssetsAmount);
        intent.putExtra("usdtPbs", usdtPbs);
        intent.putExtra("rebateCrystal", rebateCrystal);//水晶收益
        intent.putExtra("UsdCNY", UsdCNY);//usdt兑换人民币汇率
        act.startActivity(intent);
    }

    /**
     * 购买会员的选择页面
     * @param vipType 购买的会员类型
     * @param url 会员的h5链接
     */
    public static void startVIPSelectActivity(Context act,int vipType,String url){
        Intent intent = new Intent(act, VIPSelectActivity.class);
        intent.putExtra("vipType", vipType);
        intent.putExtra("url", url);
        act.startActivity(intent);
    }

    /**
     * 提取资产
     * @param act
     */
    public static void startWithdrawalOfAssetsActivity(Context act,String pbsAmount,String fixedAssetsAmount,
                                                       String usdtPbs,String rebateCrystal,String UsdCNY,int accountType) {
        Intent intent = new Intent(act, WithdrawalOfAssetsActivity.class);
        intent.putExtra("pbsAmount", pbsAmount);
        intent.putExtra("fixedAssetsAmount", fixedAssetsAmount);
        intent.putExtra("usdtPbs", usdtPbs);
        intent.putExtra("rebateCrystal", rebateCrystal);//水晶收益
        intent.putExtra("UsdCNY", UsdCNY);//usdt兑换人民币汇率
        intent.putExtra("accountType",accountType);//提取的类型
        act.startActivity(intent);
    }
    /**
     * 提现
     * @param act
     */
    public static void startCashWithdrawalActivity(Context act,String pbsAmount) {
        Intent intent = new Intent(act, CashWithdrawalActivity.class);
        intent.putExtra("pbsAmount", pbsAmount);
        act.startActivity(intent);
    }
    /**
     * PBS转账
     * @param act
     */
    public static void startTransferAccountsActivity(Context act,String pbsAmount,String fixedAssetsAmount) {
        Intent intent = new Intent(act, TransferAccountsActivity.class);
        intent.putExtra("pbsAmount", pbsAmount);
        intent.putExtra("fixedAssetsAmount", fixedAssetsAmount);
        act.startActivity(intent);
    }
    /**
     * 存入资产1
     * @param act
     */
    public static void startAssetsDeposited1Activity(Context act,String pbsAmount) {
        Intent intent = new Intent(act, AssetsDeposited1Activity.class);
        intent.putExtra("pbsAmount", pbsAmount);
        act.startActivity(intent);
    }
    /**
     * 存入资产2
     * @param act
     */
    public static void startAssetsDepositedActivity(Context act,String pbsAmount,Double pbsAmount2) {
        Bundle bundle = new Bundle();
        bundle.putString("pbsAmount", pbsAmount);
        bundle.putDouble("pbsAmount2", pbsAmount2);
        Intent intent1 = new Intent(act, AssetsDepositedActivity.class);
        intent1.putExtras(bundle);
        act.startActivity(intent1);
    }
    /**
     * 开通会员
     * @param act
     */
    public static void startOpenMembershipActivity(Context act,int membershipType) {
        Bundle bundle = new Bundle();
        bundle.putInt("membershipType", membershipType);
        Intent intent1 = new Intent(act, OpenMembershipActivity.class);
        intent1.putExtras(bundle);
        act.startActivity(intent1);
    }

    /**
     * 我的钱包-普通定存宝
     * @param act
     */
    public static void startOrdinaryDepositTreasureActivity(Context act,String title,String type,String dayRate,String time) {
        Intent intent = new Intent(act, OrdinaryDepositTreasureActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("type", type);
        intent.putExtra("dayRate", dayRate);
        intent.putExtra("time", time);
        act.startActivity(intent);
    }
    /**
     * 我的钱包-高端定存宝
     * @param act
     */
    public static void startAdvancedDepositTreasureActivity(Context act,String title) {
//        Bundle bundle = new Bundle();
//        bundle.putString("pbsAmount", pbsAmount);
//        bundle.putDouble("pbsAmount2", pbsAmount2);
//        Intent intent1 = new Intent(act, AdvancedDepositTreasureActivity.class);
//        intent1.putExtras(bundle);
//        act.startActivity(intent1);
        Intent intent = new Intent(act, AdvancedDepositTreasureActivity.class);
        intent.putExtra("title", title);
        act.startActivity(intent);
    }
}