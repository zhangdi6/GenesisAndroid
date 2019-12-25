package com.iruiyou.pet.bean;

import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.pet.App;

import java.io.Serializable;
import java.util.Locale;

/**
 * 类描述:初始配置bean
 * 创建日期:2018/5/28 on 17:07
 * 作者:JiaoPeiRong
 */
public class ConfigBean implements Serializable {

    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : {"update":{"require":true,"option":false},"showMining":true,"invitationConfig":true,"discountAble":true,"rechargeAble":true,"rechargeUrl":"https://h5.youzan.com/v2/goods/3ez99dqvgmkik?reft=1529914019392&spm=f71254180","falseUserCount":0,"lang":{"zh":{"dbSelectInputStandards":{"blockchainIdentityLocations":[{"dbKey":0,"langValue":"区块链爱好者"},{"dbKey":1,"langValue":"区块链从业者"}],"educations":[{"dbKey":7,"langValue":"博士"},{"dbKey":6,"langValue":"硕士"},{"dbKey":5,"langValue":"本科"},{"dbKey":4,"langValue":"专科"},{"dbKey":3,"langValue":"高中"},{"dbKey":2,"langValue":"初中"},{"dbKey":1,"langValue":"小学"}],"companyPeopleCounts":[{"dbKey":8,"langValue":"1000人以上"},{"dbKey":7,"langValue":"500人以上"},{"dbKey":6,"langValue":"200人以上"},{"dbKey":5,"langValue":"99人以上"},{"dbKey":4,"langValue":"50人以上"},{"dbKey":3,"langValue":"20人以上"},{"dbKey":2,"langValue":"9人以上"},{"dbKey":1,"langValue":"9人及以下"}],"pbsRecords":[{"dbKey":2,"langValue":"PETS及云宠兑换"},{"dbKey":1,"langValue":"收获挖矿产量"},{"dbKey":0,"langValue":"未知"}],"combatRecords":[{"dbKey":8,"langValue":"添加教育经历"},{"dbKey":7,"langValue":"添加工作经验"},{"dbKey":6,"langValue":"添加区块链从业信息"},{"dbKey":5,"langValue":"添加基本资料"},{"dbKey":4,"langValue":"邀请好友数达20位"},{"dbKey":3,"langValue":"邀请好友数达10位"},{"dbKey":2,"langValue":"邀请好友数达5位"},{"dbKey":1,"langValue":"成功邀请1位好友"},{"dbKey":0,"langValue":"未知"}]}},"en":{"dbSelectInputStandards":{"blockchainIdentityLocations":[{"dbKey":0,"langValue":"Block-chain Amateur"},{"dbKey":1,"langValue":"Block-chain Practitioner"}],"educations":[{"dbKey":7,"langValue":"doctor"},{"dbKey":6,"langValue":"master"},{"dbKey":5,"langValue":"Undergraduate"},{"dbKey":4,"langValue":"Specialty"},{"dbKey":3,"langValue":"high school"},{"dbKey":2,"langValue":"Junior middle school"},{"dbKey":1,"langValue":"Primary school"}],"companyPeopleCounts":[{"dbKey":8,"langValue":"More than 1000 people"},{"dbKey":7,"langValue":"More than 500 people"},{"dbKey":6,"langValue":"More than 200 people"},{"dbKey":5,"langValue":"More than 99 people"},{"dbKey":4,"langValue":"More than 50 people"},{"dbKey":3,"langValue":"More than 20 people"},{"dbKey":2,"langValue":"More than 9 people"},{"dbKey":1,"langValue":"9 persons and below"}],"pbsRecords":[{"dbKey":2,"langValue":"PETS and cloud convertibility"},{"dbKey":1,"langValue":"Yield of harvested ore"},{"dbKey":0,"langValue":"Unknown"}],"combatRecords":[{"dbKey":8,"langValue":"Adding educational experience"},{"dbKey":7,"langValue":"Add work experience"},{"dbKey":6,"langValue":"Add block chain information"},{"dbKey":5,"langValue":"Add basic information"},{"dbKey":4,"langValue":"The number of friends invited is 20."},{"dbKey":3,"langValue":"The number of friends invited is 10."},{"dbKey":2,"langValue":"The number of friends invited is 5."},{"dbKey":1,"langValue":"Successfully invited 1 friends"},{"dbKey":0,"langValue":"Unknown"}]}}},"smsTemplate":"快来创世职场，注册时记得输入我的邀请码${inviteCode}"}
     * csrfToken : null
     * token : null
     */

    private int statusCode;
    private String message;
    private String error;
    private DataBean data;
    private String csrfToken;
    private String token;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(String csrfToken) {
        this.csrfToken = csrfToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class DataBean implements Serializable {
        /**
         * update : {"require":true,"option":false}
         * showMining : true
         * invitationConfig : true
         * discountAble : true
         * rechargeAble : true
         * rechargeUrl : https://h5.youzan.com/v2/goods/3ez99dqvgmkik?reft=1529914019392&spm=f71254180
         * falseUserCount : 0
         * lang : {"zh":{"dbSelectInputStandards":{"blockchainIdentityLocations":[{"dbKey":0,"langValue":"区块链爱好者"},{"dbKey":1,"langValue":"区块链从业者"}],"educations":[{"dbKey":7,"langValue":"博士"},{"dbKey":6,"langValue":"硕士"},{"dbKey":5,"langValue":"本科"},{"dbKey":4,"langValue":"专科"},{"dbKey":3,"langValue":"高中"},{"dbKey":2,"langValue":"初中"},{"dbKey":1,"langValue":"小学"}],"companyPeopleCounts":[{"dbKey":8,"langValue":"1000人以上"},{"dbKey":7,"langValue":"500人以上"},{"dbKey":6,"langValue":"200人以上"},{"dbKey":5,"langValue":"99人以上"},{"dbKey":4,"langValue":"50人以上"},{"dbKey":3,"langValue":"20人以上"},{"dbKey":2,"langValue":"9人以上"},{"dbKey":1,"langValue":"9人及以下"}],"pbsRecords":[{"dbKey":2,"langValue":"PETS及云宠兑换"},{"dbKey":1,"langValue":"收获挖矿产量"},{"dbKey":0,"langValue":"未知"}],"combatRecords":[{"dbKey":8,"langValue":"添加教育经历"},{"dbKey":7,"langValue":"添加工作经验"},{"dbKey":6,"langValue":"添加区块链从业信息"},{"dbKey":5,"langValue":"添加基本资料"},{"dbKey":4,"langValue":"邀请好友数达20位"},{"dbKey":3,"langValue":"邀请好友数达10位"},{"dbKey":2,"langValue":"邀请好友数达5位"},{"dbKey":1,"langValue":"成功邀请1位好友"},{"dbKey":0,"langValue":"未知"}]}},"en":{"dbSelectInputStandards":{"blockchainIdentityLocations":[{"dbKey":0,"langValue":"Block-chain Amateur"},{"dbKey":1,"langValue":"Block-chain Practitioner"}],"educations":[{"dbKey":7,"langValue":"doctor"},{"dbKey":6,"langValue":"master"},{"dbKey":5,"langValue":"Undergraduate"},{"dbKey":4,"langValue":"Specialty"},{"dbKey":3,"langValue":"high school"},{"dbKey":2,"langValue":"Junior middle school"},{"dbKey":1,"langValue":"Primary school"}],"companyPeopleCounts":[{"dbKey":8,"langValue":"More than 1000 people"},{"dbKey":7,"langValue":"More than 500 people"},{"dbKey":6,"langValue":"More than 200 people"},{"dbKey":5,"langValue":"More than 99 people"},{"dbKey":4,"langValue":"More than 50 people"},{"dbKey":3,"langValue":"More than 20 people"},{"dbKey":2,"langValue":"More than 9 people"},{"dbKey":1,"langValue":"9 persons and below"}],"pbsRecords":[{"dbKey":2,"langValue":"PETS and cloud convertibility"},{"dbKey":1,"langValue":"Yield of harvested ore"},{"dbKey":0,"langValue":"Unknown"}],"combatRecords":[{"dbKey":8,"langValue":"Adding educational experience"},{"dbKey":7,"langValue":"Add work experience"},{"dbKey":6,"langValue":"Add block chain information"},{"dbKey":5,"langValue":"Add basic information"},{"dbKey":4,"langValue":"The number of friends invited is 20."},{"dbKey":3,"langValue":"The number of friends invited is 10."},{"dbKey":2,"langValue":"The number of friends invited is 5."},{"dbKey":1,"langValue":"Successfully invited 1 friends"},{"dbKey":0,"langValue":"Unknown"}]}}}
         * smsTemplate : 快来创世职场，注册时记得输入我的邀请码${inviteCode}
         * "qrCodePath": "/downloads/profs/qrcode.png",
         "profsDownload": "https://bit-fun.com/downloads/profs/",
         "yzCrystal": "https://h5.youzan.com/wscshop/goods/2xlgb0ocimd30?step=2",
         "crystal2Pbs": 9,
         "pbs2Crystal": 10,
         "pbsPrice": 10
         */

        private String updatePathAndroid;
        private UpdateBean update;
        private boolean showMining;
        private boolean invitationConfig;
        private boolean discountAble;
        private boolean rechargeAble;
        private String rechargeUrl;
        private int falseUserCount;
        private int launchDelay;
        private int minShowEdit = 99;
        private LangBean lang;
        private String smsTemplate;
        private String qrCodePath;
        private String yzCrystal;
        private String crystal2Pbs;
        private String pbs2Crystal;
        private String pbsPrice;

        /**
         * crystal2Pbs: 9, // 一个水晶-》9个pbs
         pbs2Crystal: 10, // 10个pbs-》一个水晶
         pbsPrice: 0.1, // PBS标价
         */

        public String getQrCodePath() {
            return qrCodePath;
        }

        public void setQrCodePath(String qrCodePath) {
            this.qrCodePath = qrCodePath;
        }

        public String getYzCrystal() {
            return yzCrystal;
        }

        public void setYzCrystal(String yzCrystal) {
            this.yzCrystal = yzCrystal;
        }

        public String getCrystal2Pbs() {
            return crystal2Pbs;
        }

        public void setCrystal2Pbs(String crystal2Pbs) {
            this.crystal2Pbs = crystal2Pbs;
        }

        public String getPbs2Crystal() {
            return pbs2Crystal;
        }

        public void setPbs2Crystal(String pbs2Crystal) {
            this.pbs2Crystal = pbs2Crystal;
        }

        public String getPbsPrice() {
            return pbsPrice;
        }

        public void setPbsPrice(String pbsPrice) {
            this.pbsPrice = pbsPrice;
        }

        public int getLaunchDelay() {
            return launchDelay;
        }

        public void setLaunchDelay(int launchDelay) {
            this.launchDelay = launchDelay;
        }

        public int getMinShowEdit() {
            return minShowEdit;
        }

        public void setMinShowEdit(int minShowEdit) {
            this.minShowEdit = minShowEdit;
        }
        public UpdateBean getUpdate() {
            return update;
        }

        public void setUpdate(UpdateBean update) {
            this.update = update;
        }

        public boolean isShowMining() {
            return showMining;
        }

        public void setShowMining(boolean showMining) {
            this.showMining = showMining;
        }

        public boolean isInvitationConfig() {
            return invitationConfig;
        }

        public void setInvitationConfig(boolean invitationConfig) {
            this.invitationConfig = invitationConfig;
        }

        public boolean isDiscountAble() {
            return discountAble;
        }

        public void setDiscountAble(boolean discountAble) {
            this.discountAble = discountAble;
        }

        public boolean isRechargeAble() {
            return rechargeAble;
        }

        public void setRechargeAble(boolean rechargeAble) {
            this.rechargeAble = rechargeAble;
        }

        public String getRechargeUrl() {
            return rechargeUrl;
        }

        public void setRechargeUrl(String rechargeUrl) {
            this.rechargeUrl = rechargeUrl;
        }

        public int getFalseUserCount() {
            return falseUserCount;
        }

        public void setFalseUserCount(int falseUserCount) {
            this.falseUserCount = falseUserCount;
        }

        public LangBean getLang() {
            return lang;
        }

        public void setLang(LangBean lang) {
            this.lang = lang;
        }

        public String getSmsTemplate() {
            return smsTemplate;
        }

        public void setSmsTemplate(String smsTemplate) {
            this.smsTemplate = smsTemplate;
        }

        public String getUpdatePathAndroid() {
            return updatePathAndroid;
        }

        public void setUpdatePathAndroid(String updatePathAndroid) {
            this.updatePathAndroid = updatePathAndroid;
        }

        public static class UpdateBean implements Serializable {
            /**
             * require : true
             * option : false
             */

            private boolean require;
            private boolean option;
            private long fileSize;

            public long getFileSize() {
                return fileSize;
            }

            public void setFileSize(long fileSize) {
                this.fileSize = fileSize;
            }

            public boolean isRequire() {
                return require;
            }

            public void setRequire(boolean require) {
                this.require = require;
            }

            public boolean isOption() {
                return option;
            }

            public void setOption(boolean option) {
                this.option = option;
            }
        }

        public static class LangBean implements Serializable {
            /**
             * zh : {"dbSelectInputStandards":{"blockchainIdentityLocations":[{"dbKey":0,"langValue":"区块链爱好者"},{"dbKey":1,"langValue":"区块链从业者"}],"educations":[{"dbKey":7,"langValue":"博士"},{"dbKey":6,"langValue":"硕士"},{"dbKey":5,"langValue":"本科"},{"dbKey":4,"langValue":"专科"},{"dbKey":3,"langValue":"高中"},{"dbKey":2,"langValue":"初中"},{"dbKey":1,"langValue":"小学"}],"companyPeopleCounts":[{"dbKey":8,"langValue":"1000人以上"},{"dbKey":7,"langValue":"500人以上"},{"dbKey":6,"langValue":"200人以上"},{"dbKey":5,"langValue":"99人以上"},{"dbKey":4,"langValue":"50人以上"},{"dbKey":3,"langValue":"20人以上"},{"dbKey":2,"langValue":"9人以上"},{"dbKey":1,"langValue":"9人及以下"}],"pbsRecords":[{"dbKey":2,"langValue":"PETS及云宠兑换"},{"dbKey":1,"langValue":"收获挖矿产量"},{"dbKey":0,"langValue":"未知"}],"combatRecords":[{"dbKey":8,"langValue":"添加教育经历"},{"dbKey":7,"langValue":"添加工作经验"},{"dbKey":6,"langValue":"添加区块链从业信息"},{"dbKey":5,"langValue":"添加基本资料"},{"dbKey":4,"langValue":"邀请好友数达20位"},{"dbKey":3,"langValue":"邀请好友数达10位"},{"dbKey":2,"langValue":"邀请好友数达5位"},{"dbKey":1,"langValue":"成功邀请1位好友"},{"dbKey":0,"langValue":"未知"}]}}
             * en : {"dbSelectInputStandards":{"blockchainIdentityLocations":[{"dbKey":0,"langValue":"Block-chain Amateur"},{"dbKey":1,"langValue":"Block-chain Practitioner"}],"educations":[{"dbKey":7,"langValue":"doctor"},{"dbKey":6,"langValue":"master"},{"dbKey":5,"langValue":"Undergraduate"},{"dbKey":4,"langValue":"Specialty"},{"dbKey":3,"langValue":"high school"},{"dbKey":2,"langValue":"Junior middle school"},{"dbKey":1,"langValue":"Primary school"}],"companyPeopleCounts":[{"dbKey":8,"langValue":"More than 1000 people"},{"dbKey":7,"langValue":"More than 500 people"},{"dbKey":6,"langValue":"More than 200 people"},{"dbKey":5,"langValue":"More than 99 people"},{"dbKey":4,"langValue":"More than 50 people"},{"dbKey":3,"langValue":"More than 20 people"},{"dbKey":2,"langValue":"More than 9 people"},{"dbKey":1,"langValue":"9 persons and below"}],"pbsRecords":[{"dbKey":2,"langValue":"PETS and cloud convertibility"},{"dbKey":1,"langValue":"Yield of harvested ore"},{"dbKey":0,"langValue":"Unknown"}],"combatRecords":[{"dbKey":8,"langValue":"Adding educational experience"},{"dbKey":7,"langValue":"Add work experience"},{"dbKey":6,"langValue":"Add block chain information"},{"dbKey":5,"langValue":"Add basic information"},{"dbKey":4,"langValue":"The number of friends invited is 20."},{"dbKey":3,"langValue":"The number of friends invited is 10."},{"dbKey":2,"langValue":"The number of friends invited is 5."},{"dbKey":1,"langValue":"Successfully invited 1 friends"},{"dbKey":0,"langValue":"Unknown"}]}}
             */

            private LangChildBean zh;
            private LangChildBean en;
            private LangChildBean currentLang;
            private Locale locale = App.getInstance().getResources().getConfiguration().locale;
            private String language = locale.getLanguage();

            public LangChildBean getCurrentLang() {
                if ("zh".equals(language) || "zh_CN".equals(language) || "zh_TW".equals(language)) {
                    SharePreferenceUtils.getBaseSharePreference().saveLanguage(com.iruiyou.common.utils.TagConstants.ZH);
                    return getZh();
                } else {
                    return getEn();
                }
            }

            public void setLang(LangChildBean currentLang) {
                this.currentLang = currentLang;
            }

            public LangChildBean getZh() {
                return zh;
            }

            public void setZh(LangChildBean zh) {
                this.zh = zh;
            }

            public LangChildBean getEn() {
                return en;
            }

            public void setEn(LangChildBean en) {
                this.en = en;
            }

        }
    }
}
