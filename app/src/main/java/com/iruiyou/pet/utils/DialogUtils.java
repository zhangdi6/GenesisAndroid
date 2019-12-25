package com.iruiyou.pet.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baijiayun.playback.signalanalysisengine.signal.NoticeSignalSelector;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.CountryBean;
import com.iruiyou.pet.view.wheel.OnWheelChangedListener;
import com.iruiyou.pet.view.wheel.StrericWheelAdapter;
import com.iruiyou.pet.view.wheel.WheelView;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * 类描述:
 * 创建日期:2018/5/28 on 15:19
 * 作者:JiaoPeiRong
 */
public class DialogUtils {
    private static String contryCode = "86";
    private static List<String> yearContent = null;
    private static List<String> monthContent = null;
    private static List<String> dayContent = null;
    private static int curYear;
    private static int curMonth;
    private static int curDay;
    private static String selectYear;


    public static void showAdSkipDialog(Context context,SkipAdDialog.AdDialogOnClick adDialogOnClick){
        new SkipAdDialog(context,adDialogOnClick).show();
    }

    public static void showKnowDialog(Activity context,ProfileInfoDialog.KnowClick knowClick){
        new ProfileInfoDialog(context,knowClick).show();
    }

    public static void showRuleDialog(Activity context,RuleNoticeDialog.KnowClick knowClick){
        new RuleNoticeDialog(context,knowClick).show();
    }

    public static void showShareDialog(Activity context, String title, String content, UMShareListener umShareListener,String shareUrl,String imageUrl){
        View view = ((LayoutInflater) Objects.requireNonNull(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)))
                .inflate(R.layout.layout_pop_share, null);
//        LinearLayout linear_share_weixin=view.findViewById(R.id.linear_share_weixin);
//        LinearLayout linear_share_weixin_circle=view.findViewById(R.id.linear_share_weixin_circle);
        final Dialog pickDialog = DialogUtils.showDialog(view, context);
        view.findViewById(R.id.linear_share_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareHeler.sharePlatform(context,
                        title,
                        false,
                        content,
                        "脉场",
                        false,
                        ShareHeler.KEY_FROM_QUESTION,
                        imageUrl,
                        shareUrl,
                        false, SHARE_MEDIA.WEIXIN,
                        umShareListener);
                pickDialog.dismiss();
            }
        });
        view.findViewById(R.id.linear_share_weixin_circle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareHeler.sharePlatform(context,
                        title,
                        false,
                        content,
                        "脉场",
                        false,
                        ShareHeler.KEY_FROM_QUESTION,
                        imageUrl,
                        shareUrl,
                        false, SHARE_MEDIA.WEIXIN_CIRCLE,
                        umShareListener);
                pickDialog.dismiss();
            }
        });
        view.findViewById(R.id.text_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDialog.dismiss();
            }
        });
        pickDialog.show();
    }

    public static void showPickTimeDialog(final TextView et,Activity context) {

        WheelView yearWheel, monthWheel,
                dayWheel;

        View view = ((LayoutInflater) Objects.requireNonNull(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)))
                .inflate(R.layout.time_picker, null);

        TextView textSure = view.findViewById(R.id.text_sure);
        TextView textCancel = view.findViewById(R.id.text_cancel);

        yearWheel = view
                .findViewById(R.id.yearwheel);
        monthWheel = view
                .findViewById(R.id.monthwheel);
        dayWheel = view
                .findViewById(R.id.daywheel);

        final Dialog pickDialog = DialogUtils.showDialog(view, context);

        initContent();

        yearWheel.setAdapter(new StrericWheelAdapter(yearContent));
        yearWheel.setCurrentItem(yearContent.size() - 1);
//        yearWheel.setCyclic(true);
        yearWheel.setInterpolator(new AnticipateOvershootInterpolator());

        monthWheel.setAdapter(new StrericWheelAdapter(monthContent));
        monthWheel.setCurrentItem(monthContent.size() - 1);
//        monthWheel.setCyclic(true);
        monthWheel.setInterpolator(new AnticipateOvershootInterpolator());

        dayWheel.setAdapter(new StrericWheelAdapter(dayContent));
        dayWheel.setCurrentItem(dayContent.size() - 1);
//        dayWheel.setCyclic(true);
        dayWheel.setInterpolator(new AnticipateOvershootInterpolator());

        yearWheel.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String year = yearContent.get(newValue);
                selectYear = year;
                monthContent.clear();
                dayContent.clear();
                Calendar calendar = Calendar.getInstance();
                int curMonth = calendar.get(Calendar.MONTH) + 1;
                if (String.valueOf(curYear).equals(year)) {
                    for (int i = 0; i < curMonth; i++) {
                        String month = String.valueOf(i + 1);
                        if (month.length() < 2) {
                            month = "0" + month;
                        }
                        monthContent.add(month);
                    }
                    if (curMonth == monthWheel.getCurrentItem() + 1) {
                        for (int i = 0; i < curDay; i++) {
                            String day = String.valueOf(i + 1);
                            if (day.length() < 2) {
                                day = "0" + day;
                            }
                            dayContent.add(day);
                        }
                    } else {
                        for (int i = 0; i < getDaysByYearMonth(Integer.parseInt(selectYear), curMonth); i++) {
                            String day = String.valueOf(i + 1);
                            if (day.length() < 2) {
                                day = "0" + day;
                            }
                            dayContent.add(day);
                        }
                    }

                } else {
                    for (int i = 0; i < 12; i++) {
                        String month = String.valueOf(i + 1);
                        if (month.length() < 2) {
                            month = "0" + month;
                        }
                        monthContent.add(month);
                    }

                    for (int i = 0; i < getDaysByYearMonth(Integer.parseInt(selectYear), curMonth); i++) {
                        String day = String.valueOf(i + 1);
                        if (day.length() < 2) {
                            day = "0" + day;
                        }
                        dayContent.add(day);
                    }
                }

                monthWheel.setAdapter(new StrericWheelAdapter(monthContent));
                if (String.valueOf(curYear).equals(year)) {
                    monthWheel.setCurrentItem(monthContent.size() - 1);
                } else {
                    monthWheel.setCurrentItem(curMonth - 1);
                }

                monthWheel.setInterpolator(new AnticipateOvershootInterpolator());
                dayWheel.setAdapter(new StrericWheelAdapter(dayContent));
                dayWheel.setCurrentItem(0);
                dayWheel.setInterpolator(new AnticipateOvershootInterpolator());
            }
        });

        monthWheel.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String month = monthContent.get(newValue);
                dayContent.clear();
                Calendar calendar = Calendar.getInstance();
                int curMonth = calendar.get(Calendar.MONTH) + 1;
                if (curMonth == Integer.parseInt(month)) {
                    for (int i = 0; i < curDay; i++) {
                        String day = String.valueOf(i + 1);
                        if (day.length() < 2) {
                            day = "0" + day;
                        }
                        dayContent.add(day);
                    }
                } else {
                    for (int i = 0; i < getDaysByYearMonth(Integer.parseInt(selectYear), Integer.parseInt(month)); i++) {
                        String day = String.valueOf(i + 1);
                        if (day.length() < 2) {
                            day = "0" + day;
                        }
                        dayContent.add(day);
                    }
                }
                dayWheel.setAdapter(new StrericWheelAdapter(dayContent));
                dayWheel.setCurrentItem(0);
                dayWheel.setInterpolator(new AnticipateOvershootInterpolator());
            }
        });

        textSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer sb = new StringBuffer();
                sb.append(yearWheel.getCurrentItemValue()).append("-")
                        .append(monthWheel.getCurrentItemValue()).append("-")
                        .append(dayWheel.getCurrentItemValue());

                long timeStemp_Value = TimeUtil.getTimeStemp_Value(sb
                        .toString());
//                bir = sb.toString();
//                selfInfo.birthday = timeStemp_Value;
                et.setText(sb);
                pickDialog.cancel();
                if (timeStemp_Value >= System.currentTimeMillis()) {
                    T.showShort("请选择正确的时间！");
                }
            }
        });
        textCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDialog.cancel();
            }
        });

        pickDialog.show();

    }



    private static void initContent() {

        Calendar calendar = Calendar.getInstance();
        curYear = calendar.get(Calendar.YEAR);
        curMonth = calendar.get(Calendar.MONTH) + 1;
        curDay = calendar.get(Calendar.DAY_OF_MONTH);

        selectYear = curYear + "";

        yearContent = new ArrayList<>();
        for (int i = 1885; i <= curYear; i++) {
            yearContent.add(String.valueOf(i));
        }

        monthContent = new ArrayList<>();
        for (int i = 0; i < curMonth; i++) {
            String month = String.valueOf(i + 1);
            if (month.length() < 2) {
                month = "0" + month;
            }
            monthContent.add(month);
        }

        dayContent = new ArrayList<>();
        for (int i = 0; i < curDay; i++) {
            String day = String.valueOf(i + 1);
            if (day.length() < 2) {
                day = "0" + day;
            }
            dayContent.add(day);
        }
    }

    /**
     * 根据年 月 获取对应的月份 天数
     */
    private static int getDaysByYearMonth(int year, int month) {

        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }




    public static Dialog showDialog(View view,Activity act) {

        final Dialog dialog = new Dialog(act,
                R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        Objects.requireNonNull(window).setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = act.getWindowManager()
                .getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        return dialog;
    }


    /**
     * 国家筛选
     *
     * @param context
     * @param country
     */
    public static void countrySelect(Context context, final TextView country, final TextView countryName, final String[] codeY) {
        final int[] sele = {0};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getResources().getString(R.string.selectConutry));
        String json = AssetsUtils.getJson("country.json", context);
        final CountryBean countryBean = GsonUtils.parseJson(json, CountryBean.class);
        String[] s = new String[countryBean.getList().size()];
        for (int i = 0; i < s.length; i++) {
            String code = "";
            if (com.iruiyou.common.utils.TagConstants.ZH.equals( SharePreferenceUtils.getBaseSharePreference().readLanguage())) {
                code = countryBean.getList().get(i).getZh();
            } else {
                code = countryBean.getList().get(i).getEn();
            }
            s[i] = code;
        }
        int co = 0;
        for (int i = 0; i < countryBean.getList().size(); i++) {
            if (countryBean.getList().get(i).getCode().equals(codeY[0])) {
                co = i;
            }
        }
        builder.setSingleChoiceItems(s, co, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sele[0] = which;
            }
        });
        //设置正面按钮
        builder.setPositiveButton(context.getResources().getString(R.string.sure), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                contryCode = countryBean.getList().get(sele[0]).getCode();
                country.setText("+" + countryBean.getList().get(sele[0]).getCode());
                if (TagConstants.ZH.equals(SharePreferenceUtils.getBaseSharePreference().readLanguage())) {
                    countryName.setText(countryBean.getList().get(sele[0]).getZh());
                } else {
                    countryName.setText(countryBean.getList().get(sele[0]).getEn());
                }
                codeY[0] = countryBean.getList().get(sele[0]).getCode();
                dialog.dismiss();
            }
        });
        //设置反面按钮
        builder.setNegativeButton(context.getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
