package com.iruiyou.pet.bean;

import java.util.List;

/**
 * 作者：jiaopeirong on 2018/5/26 23:43 11
 * 邮箱：chinajpr@163.com
 */
public class HarvestBean {

    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : {"userInfo":{"vcSendTime":0,"registered":true,"inviteCode":"444555","invitedCode":"我被邀请码","inviteCount":0,"pbsAmount":0,"lastHarvestAmount":0,"pbsFrozen":1,"combat":0,"headImg":"/img/upload/userHeadImages/1534504992258_8.jpg","_id":26,"countryCode":"86","phone":"17601011006","name":"uu","password":"$2a$10$pMjGGVZZEXRfiK0vm2otVOKj928nsyPDvTN8mpX0JPqxMswes2UDK","vrfCode":"用了就过期","createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-08-17T19:23:12+08:00","__v":0},"harvestList":[{"time":1534039171575,"_id":"5b6f94874f5fb36df9eff683","userId":26,"amount":1,"income":0,"type":"PETS及云宠兑换","createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-08-12T09:59:35+08:00","__v":0}]}
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

    public static class DataBean {
        /**
         * userInfo : {"vcSendTime":0,"registered":true,"inviteCode":"444555","invitedCode":"我被邀请码","inviteCount":0,"pbsAmount":0,"lastHarvestAmount":0,"pbsFrozen":1,"combat":0,"headImg":"/img/upload/userHeadImages/1534504992258_8.jpg","_id":26,"countryCode":"86","phone":"17601011006","name":"uu","password":"$2a$10$pMjGGVZZEXRfiK0vm2otVOKj928nsyPDvTN8mpX0JPqxMswes2UDK","vrfCode":"用了就过期","createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-08-17T19:23:12+08:00","__v":0}
         * harvestList : [{"time":1534039171575,"_id":"5b6f94874f5fb36df9eff683","userId":26,"amount":1,"income":0,"type":"PETS及云宠兑换","createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-08-12T09:59:35+08:00","__v":0}]
         * "sellTrade": 0,
         * "pbsTran": 0,
         * "pbsDeposit": 0,
         * "pbsFutures": 0,
         * "yuanBack": 0,
         * "yuanRebate": 0,
         * "yuanTeam": 0,
         * "kavenBack": 0,
         * "kavenRebate": 0,
         * "kavenTeam": 0
         */
        /**
         * sellTrade, // 冻结PBS在内盘的挂单数量（pets渠道）
         * pbsTran, // 定存PBS数量（niyang渠道）
         * pbsDeposit, // 定存PBS数量(kaven渠道)
         * pbsFutures, // 期货兑换的PBS数量（yuanyuan渠道）
         * yuanBack, // 定存的返回，单位：元（yuanyuan渠道）
         * yuanRebate, // 推广收益，单位：元（yuanyuan渠道）
         * yuanTeam, // 团队收益（yuanyuan渠道）
         * kavenBack, // 定存的自增，单位：PBS（kaven渠道
         * kavenRebate, // 推广收益，单位：PBS（kaven渠道）
         * kavenTeam, //团队收益（kaven渠道）
         */

        private UserInfoBean userInfo;
        private List<HarvestListBean> harvestList;
        private List<CrystalRevenueRecordsBean> hatchCrystals;
        private List<CrystalRecordsBean> crystalRecords;
        private List<HatchesBean> hatches;
        private String sellTrade;
        private String pbsTran;
        private String pbsDeposit;
        private String pbsFutures;
        private String yuanBack;
        private String yuanRebate;
        private String yuanTeam;
        private String kavenBack;
        private String kavenRebate;
        private String kavenTeam;

        public List<CrystalRevenueRecordsBean> getHatchCrystals() {
            return hatchCrystals;
        }

        public void setHatchCrystals(List<CrystalRevenueRecordsBean> hatchCrystals) {
            this.hatchCrystals = hatchCrystals;
        }

        public List<HatchesBean> getHatches() {
            return hatches;
        }

        public void setHatches(List<HatchesBean> hatches) {
            this.hatches = hatches;
        }

        public String getYuanBack() {
            return yuanBack;
        }

        public void setYuanBack(String yuanBack) {
            this.yuanBack = yuanBack;
        }

        public String getYuanRebate() {
            return yuanRebate;
        }

        public void setYuanRebate(String yuanRebate) {
            this.yuanRebate = yuanRebate;
        }

        public String getYuanTeam() {
            return yuanTeam;
        }

        public void setYuanTeam(String yuanTeam) {
            this.yuanTeam = yuanTeam;
        }

        public String getKavenBack() {
            return kavenBack;
        }

        public void setKavenBack(String kavenBack) {
            this.kavenBack = kavenBack;
        }

        public String getKavenRebate() {
            return kavenRebate;
        }

        public void setKavenRebate(String kavenRebate) {
            this.kavenRebate = kavenRebate;
        }

        public String getKavenTeam() {
            return kavenTeam;
        }

        public void setKavenTeam(String kavenTeam) {
            this.kavenTeam = kavenTeam;
        }

        public String getSellTrade() {
            return sellTrade;
        }

        public void setSellTrade(String sellTrade) {
            this.sellTrade = sellTrade;
        }

        public String getPbsTran() {
            return pbsTran;
        }

        public void setPbsTran(String pbsTran) {
            this.pbsTran = pbsTran;
        }

        public String getPbsDeposit() {
            return pbsDeposit;
        }

        public void setPbsDeposit(String pbsDeposit) {
            this.pbsDeposit = pbsDeposit;
        }

        public String getPbsFutures() {
            return pbsFutures;
        }

        public void setPbsFutures(String pbsFutures) {
            this.pbsFutures = pbsFutures;
        }

        public List<CrystalRecordsBean> getCrystalRecords() {
            return crystalRecords;
        }

        public void setCrystalRecords(List<CrystalRecordsBean> crystalRecords) {
            this.crystalRecords = crystalRecords;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public List<HarvestListBean> getHarvestList() {
            return harvestList;
        }

        public void setHarvestList(List<HarvestListBean> harvestList) {
            this.harvestList = harvestList;
        }

        public static class UserInfoBean {
            /**
             * vcSendTime : 0
             * registered : true
             * inviteCode : 444555
             * invitedCode : 我被邀请码
             * inviteCount : 0
             * pbsAmount : 0
             * lastHarvestAmount : 0
             * pbsFrozen : 1
             * combat : 0
             * headImg : /img/upload/userHeadImages/1534504992258_8.jpg
             * _id : 26
             * countryCode : 86
             * phone : 17601011006
             * name : uu
             * crystalAmount 水晶数量
             * password : $2a$10$pMjGGVZZEXRfiK0vm2otVOKj928nsyPDvTN8mpX0JPqxMswes2UDK
             * vrfCode : 用了就过期
             * cityNode
             * hatchAmount : // 孵化PBS资产(定存、高级定存、定存收益、人脉收益)
             * createdAt : 2018-08-12T09:59:35+08:00
             * updatedAt : 2018-08-17T19:23:12+08:00
             * __v : 0
             */

            private double crystalDrawLockedAmount;//水晶提现锁定数量
            private String vcSendTime;
            private boolean registered;
            private String inviteCode;
            private String invitedCode;
            private String inviteCount;
            private String hatchAmount;
            private String pbsAmount;
            private String crystalAmount;
            private String lastHarvestAmount;
            private String pbsFrozen;
            private String combat;
            private String headImg;
            private String cityNode;
            private int _id;
            private int vipLevel;//会员等级，0非会员、1高级、2VIP、3企业
            private String countryCode;
            private String phone;
            private String pbsDrawLockedAmount;//PBS提现锁定数量
            private String name;
            private String password;
            private String rebateCrystal; // 收益水晶（团队收益、高级定存返现）
            private String vrfCode;
            private String createdAt;
            private String updatedAt;
            private int __v;
            private String realName;

            public double getCrystalDrawLockedAmount() {
                return crystalDrawLockedAmount;
            }

            public void setCrystalDrawLockedAmount(double crystalDrawLockedAmount) {
                this.crystalDrawLockedAmount = crystalDrawLockedAmount;
            }

            public int getVipLevel() {
                return vipLevel;
            }

            public void setVipLevel(int vipLevel) {
                this.vipLevel = vipLevel;
            }

            public String getRebateCrystal() {
                return rebateCrystal;
            }

            public void setRebateCrystal(String rebateCrystal) {
                this.rebateCrystal = rebateCrystal;
            }

            public String getCityNode() {
                return cityNode;
            }

            public void setCityNode(String cityNode) {
                this.cityNode = cityNode;
            }

            public String getHatchAmount() {
                return hatchAmount;
            }

            public void setHatchAmount(String hatchAmount) {
                this.hatchAmount = hatchAmount;
            }

            public String getPbsDrawLockedAmount() {
                return pbsDrawLockedAmount;
            }

            public void setPbsDrawLockedAmount(String pbsDrawLockedAmount) {
                this.pbsDrawLockedAmount = pbsDrawLockedAmount;
            }

            public String getCrystalAmount() {
                return crystalAmount;
            }

            public void setCrystalAmount(String crystalAmount) {
                this.crystalAmount = crystalAmount;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getVcSendTime() {
                return vcSendTime;
            }

            public void setVcSendTime(String vcSendTime) {
                this.vcSendTime = vcSendTime;
            }

            public String getInviteCount() {
                return inviteCount;
            }

            public boolean isRegistered() {
                return registered;
            }

            public void setRegistered(boolean registered) {
                this.registered = registered;
            }

            public String getInviteCode() {
                return inviteCode;
            }

            public void setInviteCode(String inviteCode) {
                this.inviteCode = inviteCode;
            }

            public String getInvitedCode() {
                return invitedCode;
            }

            public void setInvitedCode(String invitedCode) {
                this.invitedCode = invitedCode;
            }



            public String getPbsAmount() {
                return pbsAmount;
            }

            public void setInviteCount(String inviteCount) {
                this.inviteCount = inviteCount;
            }

            public String getLastHarvestAmount() {
                return lastHarvestAmount;
            }

            public void setLastHarvestAmount(String lastHarvestAmount) {
                this.lastHarvestAmount = lastHarvestAmount;
            }

            public String getPbsFrozen() {
                return pbsFrozen;
            }

            public void setPbsFrozen(String pbsFrozen) {
                this.pbsFrozen = pbsFrozen;
            }

            public void setPbsAmount(String pbsAmount) {
                this.pbsAmount = pbsAmount;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public int get_id() {
                return _id;
            }

            public void set_id(int _id) {
                this._id = _id;
            }

            public String getCountryCode() {
                return countryCode;
            }

            public void setCountryCode(String countryCode) {
                this.countryCode = countryCode;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getVrfCode() {
                return vrfCode;
            }

            public void setVrfCode(String vrfCode) {
                this.vrfCode = vrfCode;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public int get__v() {
                return __v;
            }

            public void set__v(int __v) {
                this.__v = __v;
            }

            public String getCombat() {
                return combat;
            }

            public void setCombat(String combat) {
                this.combat = combat;
            }
        }

        public static class HarvestListBean {
            /**
             * time : 1534039171575
             * _id : 5b6f94874f5fb36df9eff683
             * userId : 26
             * amount : 1
             * income : 0
             * type : PETS及云宠兑换
             * createdAt : 2018-08-12T09:59:35+08:00
             * updatedAt : 2018-08-12T09:59:35+08:00
             * __v : 0
             */

            private long time;
            private String _id;
            private int userId;
            private double amount;
            private String income;
            private int type;
            private String createdAt;
            private String updatedAt;
            private int __v;

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getIncome() {
                return income;
            }

            public void setIncome(String income) {
                this.income = income;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public int get__v() {
                return __v;
            }

            public void set__v(int __v) {
                this.__v = __v;
            }
        }


        public static class CrystalRevenueRecordsBean
        {


            private String _id;//":"5c4df8bc7b8bd942c9e90564",
            private String createdAt;//":"2019-01-28T02:30:20+08:00",
            private String updatedAt;//":"2019-01-28T02:30:20+08:00",
            private int __v;//":0

            private long userId;//: { type: Number, required: true }, // 收益者ID
            private float amount;//: { type: Number, required: true }, // 本次收支PBS数量
            private long income;//: { type: Number, required: true }, // 收支（0收，1支）
            private long type;//: { type: Number, required: true }, // 收支类型(0:未知，1:收获挖矿产量，2:PETS及云宠兑换)
            private long time;//: { type: Number, required: true }, // 本次收支时间
            private long buyerId;//: { type: Number }, // 购买者

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public int get__v() {
                return __v;
            }

            public void set__v(int __v) {
                this.__v = __v;
            }

            public long getUserId() {
                return userId;
            }

            public void setUserId(long userId) {
                this.userId = userId;
            }

            public float getAmount() {
                return amount;
            }

            public void setAmount(float amount) {
                this.amount = amount;
            }

            public long getIncome() {
                return income;
            }

            public void setIncome(long income) {
                this.income = income;
            }

            public long getType() {
                return type;
            }

            public void setType(long type) {
                this.type = type;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public long getBuyerId() {
                return buyerId;
            }

            public void setBuyerId(long buyerId) {
                this.buyerId = buyerId;
            }
        }

        public static class CrystalRecordsBean {
            /**
             * time : 1534039171575
             * _id : 5b6f94874f5fb36df9eff683
             * userId : 26
             * amount : 1
             * income : 0
             * type : PETS及云宠兑换
             * createdAt : 2018-08-12T09:59:35+08:00
             * updatedAt : 2018-08-12T09:59:35+08:00
             * __v : 0
             */

            private long time;
            private String _id;
            private int userId;
            private String amount;
            private String income;
            private int type;
            private String createdAt;
            private String updatedAt;
            private int __v;

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getIncome() {
                return income;
            }

            public void setIncome(String income) {
                this.income = income;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public int get__v() {
                return __v;
            }

            public void set__v(int __v) {
                this.__v = __v;
            }
        }
        public static class HatchesBean {
            /**
             * time : 1534039171575
             * _id : 5b6f94874f5fb36df9eff683
             * userId : 26
             * amount : 1
             * income : 0
             * type : PETS及云宠兑换
             * createdAt : 2018-08-12T09:59:35+08:00
             * updatedAt : 2018-08-12T09:59:35+08:00
             * __v : 0
             */

            private long time;
            private String _id;
            private int userId;
            private String amount;
            private String income;
            private int type;
            private String createdAt;
            private String updatedAt;
            private int __v;

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getIncome() {
                return income;
            }

            public void setIncome(String income) {
                this.income = income;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public int get__v() {
                return __v;
            }

            public void set__v(int __v) {
                this.__v = __v;
            }
        }
    }
}
