package com.bb.taold.bean;

import java.util.List;

/**
 * ==============================================
 * <p>
 * 包名：com.bb.taold.bean
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/14
 * <p>
 * ==============================================
 */

public class UserParam {

    private String education="";

    private String address="";

    private String company="";

    private String companyAddress="";

    private String memberContactInfo="";

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public String getMemberContactInfo() {
        return memberContactInfo;
    }

    public void setMemberContactInfo(String memberContactInfo) {
        this.memberContactInfo = memberContactInfo;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
}
