package com.iruiyou.pet.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONObject;

public class AppRegister extends BroadcastReceiver {

    private static IWXAPI api;

    @Override
    public void onReceive(Context context, Intent intent) {
        api = WXAPIFactory.createWXAPI(context, Constant.WEIXIN_APP_ID,false);
        // 将该app注册到微信
        api.registerApp(Constant.WEIXIN_APP_ID);
    }

    public static void sendPay(JSONObject dataJsonObject,Context context){
        PayReq request = new PayReq();
        request.appId = dataJsonObject.optString("appid");
        api = WXAPIFactory.createWXAPI(context, request.appId,false);
        request.partnerId =  dataJsonObject.optString("partnerid");
        request.prepayId= dataJsonObject.optString("prepayid");
        request.packageValue = dataJsonObject.optString("package");
        request.nonceStr= dataJsonObject.optString("noncestr");
        request.timeStamp= dataJsonObject.optString("timestamp");
        request.sign= dataJsonObject.optString("sign");
        // 将该app注册到微信
        api.registerApp(request.appId);
        api.sendReq(request);
    }
}