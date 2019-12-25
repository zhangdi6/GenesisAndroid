package com.iruiyou.pet.bean;

import java.io.Serializable;
import java.util.List;

public class DMOptionListBean {
    private int code;
    private String msg;
    private Data data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public static class Data{
        List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }
    }

    public static class DataBean implements Serializable {
        private String id;
        private String city_name;
        private String city_domain;
        private String job_type;
        private String job_type_title;
        private int hire_number;
        private String company_name;
        private String title;
        private String treatment;
        private String salary;// "":"3001-5000元",
        private List<String> address;//       "":Array[1],
        private String description;//
        private String sex_demand ;//"":"不限",
        private String work_years;//        "":"不限",
        private String education_demand;//"":"不限",
        private String identity_demand;//"":"不限",
        private String age_demand;//"":"不限"

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getCity_domain() {
            return city_domain;
        }

        public void setCity_domain(String city_domain) {
            this.city_domain = city_domain;
        }

        public String getJob_type() {
            return job_type;
        }

        public void setJob_type(String job_type) {
            this.job_type = job_type;
        }

        public String getJob_type_title() {
            return job_type_title;
        }

        public void setJob_type_title(String job_type_title) {
            this.job_type_title = job_type_title;
        }

        public int getHire_number() {
            return hire_number;
        }

        public void setHire_number(int hire_number) {
            this.hire_number = hire_number;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTreatment() {
            return treatment;
        }

        public void setTreatment(String treatment) {
            this.treatment = treatment;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public List<String> getAddress() {
            return address;
        }

        public void setAddress(List<String> address) {
            this.address = address;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getSex_demand() {
            return sex_demand;
        }

        public void setSex_demand(String sex_demand) {
            this.sex_demand = sex_demand;
        }

        public String getWork_years() {
            return work_years;
        }

        public void setWork_years(String work_years) {
            this.work_years = work_years;
        }

        public String getEducation_demand() {
            return education_demand;
        }

        public void setEducation_demand(String education_demand) {
            this.education_demand = education_demand;
        }

        public String getIdentity_demand() {
            return identity_demand;
        }

        public void setIdentity_demand(String identity_demand) {
            this.identity_demand = identity_demand;
        }

        public String getAge_demand() {
            return age_demand;
        }

        public void setAge_demand(String age_demand) {
            this.age_demand = age_demand;
        }
    }

}
