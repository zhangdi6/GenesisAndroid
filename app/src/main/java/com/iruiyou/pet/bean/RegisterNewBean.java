package com.iruiyou.pet.bean;

import java.util.List;

/**
 * 作者：jiaopeirong on 2018/5/24 22:54
 * 邮箱：chinajpr@163.com
 */
public class RegisterNewBean {


    /**
     * "rcToken": "Ro+XeU7bl2o3MIwMDLMfCKO9TsO7\/LpP7ndT6Yid7NTFi4nAme08cnvgnsLx\/TMrRHwEBFgQ\/\/98o8LI+CEkmw==",
     *      * csrfToken : null
     *      * data : null
     *      * message : Invalid verification code
     *      * statusCode : -1
     *      * error : null
     *      * token : null
     */

    private String csrfToken;
    private String rcToken;
    private List<BeanData> data;

    private String message;
    private int statusCode;
    private String error;
    private String token;

    public String getRcToken() {
        return rcToken;
    }

    public void setRcToken(String rcToken) {
        this.rcToken = rcToken;
    }

    public String getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(String csrfToken) {
        this.csrfToken = csrfToken;
    }

    public List<BeanData> getData() {
        return data;
    }

    public void setData(List<BeanData> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
  public   class  BeanData{

        private  String phone;



       public String getPhone() {
           return phone;
       }

       public void setPhone(String phone) {
           this.phone = phone;
       }
   }
}
