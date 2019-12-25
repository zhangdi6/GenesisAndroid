package com.iruiyou.pet.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 类描述：
 * 作者：jiaopeirong on 2018/8/31 01:08
 * 邮箱：chinajpr@163.com
 */
public class LangChildBean implements Serializable{
    /**
     * dbSelectInputStandards : {"blockchainIdentityLocations":[{"dbKey":0,"langValue":"Block-chain Amateur"},{"dbKey":1,"langValue":"Block-chain Practitioner"}],"educations":[{"dbKey":7,"langValue":"doctor"},{"dbKey":6,"langValue":"master"},{"dbKey":5,"langValue":"Undergraduate"},{"dbKey":4,"langValue":"Specialty"},{"dbKey":3,"langValue":"high school"},{"dbKey":2,"langValue":"Junior middle school"},{"dbKey":1,"langValue":"Primary school"}],"companyPeopleCounts":[{"dbKey":8,"langValue":"More than 1000 people"},{"dbKey":7,"langValue":"More than 500 people"},{"dbKey":6,"langValue":"More than 200 people"},{"dbKey":5,"langValue":"More than 99 people"},{"dbKey":4,"langValue":"More than 50 people"},{"dbKey":3,"langValue":"More than 20 people"},{"dbKey":2,"langValue":"More than 9 people"},{"dbKey":1,"langValue":"9 persons and below"}],"pbsRecords":[{"dbKey":2,"langValue":"PETS and cloud convertibility"},{"dbKey":1,"langValue":"Yield of harvested ore"},{"dbKey":0,"langValue":"Unknown"}],"combatRecords":[{"dbKey":8,"langValue":"Adding educational experience"},{"dbKey":7,"langValue":"Add work experience"},{"dbKey":6,"langValue":"Add block chain information"},{"dbKey":5,"langValue":"Add basic information"},{"dbKey":4,"langValue":"The number of friends invited is 20."},{"dbKey":3,"langValue":"The number of friends invited is 10."},{"dbKey":2,"langValue":"The number of friends invited is 5."},{"dbKey":1,"langValue":"Successfully invited 1 friends"},{"dbKey":0,"langValue":"Unknown"}]}
     */

    private DbSelectInputStandardsBean dbSelectInputStandards;

    public DbSelectInputStandardsBean getDbSelectInputStandards() {
        return dbSelectInputStandards;
    }

    public void setDbSelectInputStandards(DbSelectInputStandardsBean dbSelectInputStandards) {
        this.dbSelectInputStandards = dbSelectInputStandards;
    }

    public static class DbSelectInputStandardsBean implements Serializable{
        private List<DrawRecordsBean> drawRecords;//提现记录
        private List<BlockchainIdentityLocationsBean> blockchainIdentityLocations;
        private List<EducationsBean> educations;
        private List<CompanyPeopleCountsBean> companyPeopleCounts;
        private List<PbsRecordsBean> pbsRecords;
        private List<CombatRecordsBean> combatRecords;
        private List<ProfessionalIdentitiesBean> newProfessionalIdentities;//add
        private List<CrystalRecordsBean> crystalRecords;//add
        private List<HatchRecordsBean> hatchRecords;//孵化资产收支类型
        private List<HatchCrystalRecordsBean> hatchCrystalRecords;//水晶收益列表
        private List<CrystalRecordsBean> genders;

        public List<CrystalRecordsBean> getGenders() {
            return genders;
        }

        public void setGenders(List<CrystalRecordsBean> genders) {
            this.genders = genders;
        }

        public List<DrawRecordsBean> getDrawRecords() {
            return drawRecords;
        }

        public void setDrawRecords(List<DrawRecordsBean> drawRecords) {
            this.drawRecords = drawRecords;
        }

        public List<HatchCrystalRecordsBean> getHatchCrystalRecords() {
            return hatchCrystalRecords;
        }

        public void setHatchCrystalRecords(List<HatchCrystalRecordsBean> hatchCrystalRecords) {
            this.hatchCrystalRecords = hatchCrystalRecords;
        }

        public List<HatchRecordsBean> getHatchRecords() {
            return hatchRecords;
        }

        public void setHatchRecords(List<HatchRecordsBean> hatchRecords) {
            this.hatchRecords = hatchRecords;
        }

        public List<CrystalRecordsBean> getCrystalRecords() {
            return crystalRecords;
        }

        public void setCrystalRecords(List<CrystalRecordsBean> crystalRecords) {
            this.crystalRecords = crystalRecords;
        }

        public List<ProfessionalIdentitiesBean> getProfessionalIdentities() {
            return newProfessionalIdentities;
        }

        public void setProfessionalIdentities(List<ProfessionalIdentitiesBean> professionalIdentities) {
            this.newProfessionalIdentities = professionalIdentities;
        }

        public List<BlockchainIdentityLocationsBean> getBlockchainIdentityLocations() {
            return blockchainIdentityLocations;
        }

        public void setBlockchainIdentityLocations(List<BlockchainIdentityLocationsBean> blockchainIdentityLocations) {
            this.blockchainIdentityLocations = blockchainIdentityLocations;
        }

        public List<EducationsBean> getEducations() {
            return educations;
        }

        public void setEducations(List<EducationsBean> educations) {
            this.educations = educations;
        }

        public List<CompanyPeopleCountsBean> getCompanyPeopleCounts() {
            return companyPeopleCounts;
        }

        public void setCompanyPeopleCounts(List<CompanyPeopleCountsBean> companyPeopleCounts) {
            this.companyPeopleCounts = companyPeopleCounts;
        }

        public List<PbsRecordsBean> getPbsRecords() {
            return pbsRecords;
        }

        public void setPbsRecords(List<PbsRecordsBean> pbsRecords) {
            this.pbsRecords = pbsRecords;
        }

        public List<CombatRecordsBean> getCombatRecords() {
            return combatRecords;
        }

        public void setCombatRecords(List<CombatRecordsBean> combatRecords) {
            this.combatRecords = combatRecords;
        }

        public static class BlockchainIdentityLocationsBean implements Serializable{
            /**
             * dbKey : 0
             * langValue : Block-chain Amateur
             */

            private int dbKey;
            private String langValue;

            public int getDbKey() {
                return dbKey;
            }

            public void setDbKey(int dbKey) {
                this.dbKey = dbKey;
            }

            public String getLangValue() {
                return langValue;
            }

            public void setLangValue(String langValue) {
                this.langValue = langValue;
            }
        }

        public static class EducationsBean implements Serializable{
            /**
             * dbKey : 7
             * langValue : doctor
             */

            private int dbKey;
            private String langValue;

            public int getDbKey() {
                return dbKey;
            }

            public void setDbKey(int dbKey) {
                this.dbKey = dbKey;
            }

            public String getLangValue() {
                return langValue;
            }

            public void setLangValue(String langValue) {
                this.langValue = langValue;
            }
        }

        public static class CompanyPeopleCountsBean implements Serializable{
            /**
             * dbKey : 8
             * langValue : More than 1000 people
             */

            private int dbKey;
            private String langValue;

            public int getDbKey() {
                return dbKey;
            }

            public void setDbKey(int dbKey) {
                this.dbKey = dbKey;
            }

            public String getLangValue() {
                return langValue;
            }

            public void setLangValue(String langValue) {
                this.langValue = langValue;
            }
        }

        public static class PbsRecordsBean implements Serializable{
            /**
             * dbKey : 2
             * langValue : PETS and cloud convertibility
             */

            private int dbKey;
            private String langValue;

            public int getDbKey() {
                return dbKey;
            }

            public void setDbKey(int dbKey) {
                this.dbKey = dbKey;
            }

            public String getLangValue() {
                return langValue;
            }

            public void setLangValue(String langValue) {
                this.langValue = langValue;
            }
        }

        public static class CombatRecordsBean implements Serializable{
            /**
             * dbKey : 8
             * langValue : Adding educational experience
             */

            private int dbKey;
            private String langValue;

            public int getDbKey() {
                return dbKey;
            }

            public void setDbKey(int dbKey) {
                this.dbKey = dbKey;
            }

            public String getLangValue() {
                return langValue;
            }

            public void setLangValue(String langValue) {
                this.langValue = langValue;
            }
        }

        public static class ProfessionalIdentitiesBean implements Serializable{//add
            /**
             * dbKey : 8
             * langValue : Adding educational experience
             */

            private int dbKey;
            private String langValue;

            public int getDbKey() {
                return dbKey;
            }

            public void setDbKey(int dbKey) {
                this.dbKey = dbKey;
            }

            public String getLangValue() {
                return langValue;
            }

            public void setLangValue(String langValue) {
                this.langValue = langValue;
            }

            private List<ProfessionalIdentitiesBean> secondary;

            public List<ProfessionalIdentitiesBean> getSecondary() {
                return secondary;
            }

            public void setSecondary(List<ProfessionalIdentitiesBean> secondary) {
                this.secondary = secondary;
            }
        }

        public static class DrawRecordsBean implements Serializable
        {
            /**
             * dbKey : 8
             * langValue : Adding educational experience
             */

            private int dbKey;
            private String langValue;

            public int getDbKey() {
                return dbKey;
            }

            public void setDbKey(int dbKey) {
                this.dbKey = dbKey;
            }

            public String getLangValue() {
                return langValue;
            }

            public void setLangValue(String langValue) {
                this.langValue = langValue;
            }
        }

        public static class HatchCrystalRecordsBean implements Serializable
        {
            /**
             * dbKey : 8
             * langValue : Adding educational experience
             */

            private int dbKey;
            private String langValue;

            public int getDbKey() {
                return dbKey;
            }

            public void setDbKey(int dbKey) {
                this.dbKey = dbKey;
            }

            public String getLangValue() {
                return langValue;
            }

            public void setLangValue(String langValue) {
                this.langValue = langValue;
            }
        }

        public static class CrystalRecordsBean implements Serializable{//add
            /**
             * dbKey : 8
             * langValue : Adding educational experience
             */

            private int dbKey;
            private String langValue;

            public int getDbKey() {
                return dbKey;
            }

            public void setDbKey(int dbKey) {
                this.dbKey = dbKey;
            }

            public String getLangValue() {
                return langValue;
            }

            public void setLangValue(String langValue) {
                this.langValue = langValue;
            }
        }
        public static class HatchRecordsBean implements Serializable{
            /**
             * dbKey : 8
             * langValue : Adding educational experience
             */

            private int dbKey;
            private String langValue;

            public int getDbKey() {
                return dbKey;
            }

            public void setDbKey(int dbKey) {
                this.dbKey = dbKey;
            }

            public String getLangValue() {
                return langValue;
            }

            public void setLangValue(String langValue) {
                this.langValue = langValue;
            }
        }
    }


}
