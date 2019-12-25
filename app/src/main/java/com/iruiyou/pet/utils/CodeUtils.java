package com.iruiyou.pet.utils;

import com.iruiyou.pet.App;
import com.iruiyou.pet.bean.ConfigBean;
import com.iruiyou.pet.bean.LangChildBean;

import java.util.List;

/**
 * 类描述:码值转化工具类
 * 创建日期:2018/8/31 on 16:14
 * 作者:JiaoPeiRong
 */
public class CodeUtils {
    private volatile static CodeUtils codeUtils;
    private ConfigBean configBean;

    public static CodeUtils getInstance() {
        if (codeUtils == null) {
            synchronized (CodeUtils.class) {
                if (codeUtils == null) {
                    return new CodeUtils();
                }
            }
        }
        return codeUtils;
    }

    private CodeUtils() {
        configBean = App.getConfigBean();
    }

    /**
     * 返回学历
     *
     * @param code
     * @return
     */
    public String getEducationByCode(int code) {
        List<LangChildBean.DbSelectInputStandardsBean.EducationsBean> educations = configBean.getData().getLang().getCurrentLang().getDbSelectInputStandards().getEducations();
        if (educations != null) {
            for (int i = 0; i < educations.size(); i++) {
                if (educations.get(i).getDbKey() == code) {
                    return educations.get(i).getLangValue();
                }
            }
        }
        return "--";
    }


    public String getGenders(int code){
        List<LangChildBean.DbSelectInputStandardsBean.CrystalRecordsBean> genesisList = configBean.getData().getLang().getCurrentLang().getDbSelectInputStandards().getGenders();
        if(genesisList!=null&&genesisList.size()>0){
            for(LangChildBean.DbSelectInputStandardsBean.CrystalRecordsBean bean:genesisList){
                if(code== bean.getDbKey()){
                    return bean.getLangValue();
                }
            }
        }
        return "--";
    }

    /**
     * 返回职业
     *
     * @param code
     * @return
     */
    public String getProfessional(int code) {
        List<LangChildBean.DbSelectInputStandardsBean.ProfessionalIdentitiesBean> professional = configBean.getData().getLang().getCurrentLang().getDbSelectInputStandards().getProfessionalIdentities();
        if (professional != null) {
            for (int i = 0; i < professional.size(); i++) {
                LangChildBean.DbSelectInputStandardsBean.ProfessionalIdentitiesBean professionalIdentitiesBean = professional.get(i);
                if (professionalIdentitiesBean.getDbKey() == code) {
                    return professionalIdentitiesBean.getLangValue();
                }else if(professionalIdentitiesBean.getSecondary()!=null){
                    for(LangChildBean.DbSelectInputStandardsBean.ProfessionalIdentitiesBean professionalIdentitiesBean1:professionalIdentitiesBean.getSecondary()){
                        if (professionalIdentitiesBean1.getDbKey() == code){
                            return professionalIdentitiesBean1.getLangValue();
                        }
                    }
                }
            }
        }
        return "--";
    }

    /**
     * 算力
     *
     * @param code
     * @return
     */
    public String getCombatByCode(int code) {
        List<LangChildBean.DbSelectInputStandardsBean.CombatRecordsBean> combatRecords = configBean.getData().getLang().getCurrentLang().getDbSelectInputStandards().getCombatRecords();
        if (combatRecords != null) {
            for (int i = 0; i < combatRecords.size(); i++) {
                if (combatRecords.get(i).getDbKey() == code) {
                    if (combatRecords.get(i).getDbKey() == code) {
                        return combatRecords.get(i).getLangValue();
                    }
                }
            }
        }
        return "--";
    }

    /**
     * 工作信息
     *
     * @param code
     * @return
     */
    public String getWorkInfoByCode(int code) {
        List<LangChildBean.DbSelectInputStandardsBean.CompanyPeopleCountsBean> companyPeopleCounts = configBean.getData().getLang().getCurrentLang().getDbSelectInputStandards().getCompanyPeopleCounts();
        if (companyPeopleCounts != null) {
            for (int i = 0; i < companyPeopleCounts.size(); i++) {
                if (companyPeopleCounts.get(i).getDbKey() == code) {
                    return companyPeopleCounts.get(i).getLangValue();
                }
            }
        }
        return "--";
    }

    /**
     * pbs(我的钱包页)
     *
     * @param code
     * @return
     */
    public String getPBSByCode(int code) {
        List<LangChildBean.DbSelectInputStandardsBean.PbsRecordsBean> pbsRecords = configBean.getData().getLang().getCurrentLang().getDbSelectInputStandards().getPbsRecords();
        if (pbsRecords != null) {
            for (int i = 0; i < pbsRecords.size(); i++) {
                if (pbsRecords.get(i).getDbKey() == code) {
                    return pbsRecords.get(i).getLangValue();
                }
            }
        }
        return "--";
    }


    public String getCrashRecord(int code)
    {
        List<LangChildBean.DbSelectInputStandardsBean.DrawRecordsBean> pbsRecords = configBean.getData().getLang().getCurrentLang().getDbSelectInputStandards().getDrawRecords();
        if (pbsRecords != null) {
            for (int i = 0; i < pbsRecords.size(); i++) {
                if (pbsRecords.get(i).getDbKey() == code) {
                    return pbsRecords.get(i).getLangValue();
                }
            }
        }
        return "--";
    }

    /**
     * 定存(我的钱包页)
     *
     * @param code
     * @return
     */
    public String getHatches(int code) {
        List<LangChildBean.DbSelectInputStandardsBean.HatchRecordsBean> hatchRecordsBean = configBean.getData().getLang().getCurrentLang().getDbSelectInputStandards().getHatchRecords();
        if (hatchRecordsBean != null) {
            for (int i = 0; i < hatchRecordsBean.size(); i++) {
                if (hatchRecordsBean.get(i).getDbKey() == code) {
                    return hatchRecordsBean.get(i).getLangValue();
                }
            }
        }
        return "--";
    }
    /**
     * 水晶(我的钱包页)
     *
     * @param code
     * @return
     */
    public String getCrystalByCode(int code) {
        List<LangChildBean.DbSelectInputStandardsBean.CrystalRecordsBean> crystalRecords = configBean.getData().getLang().getCurrentLang().getDbSelectInputStandards().getCrystalRecords();
        if (crystalRecords != null) {
            for (int i = 0; i < crystalRecords.size(); i++) {
                if (crystalRecords.get(i).getDbKey() == code) {
                    return crystalRecords.get(i).getLangValue();
                }
            }
        }
        return "--";
    }

    /**
     * 水晶收益记录
     * @param code
     * @return
     */
    public String getCrystalRecordsByCode(int code)
    {
        List<LangChildBean.DbSelectInputStandardsBean.HatchCrystalRecordsBean> crystalRecords = configBean.getData().getLang().getCurrentLang().getDbSelectInputStandards().getHatchCrystalRecords();
        if (crystalRecords != null) {
            for (int i = 0; i < crystalRecords.size(); i++) {
                if (crystalRecords.get(i).getDbKey() == code) {
                    return crystalRecords.get(i).getLangValue();
                }
            }
        }
        return "--";
    }

}
