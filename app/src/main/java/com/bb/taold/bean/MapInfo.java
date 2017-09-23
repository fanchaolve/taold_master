package com.bb.taold.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhouwan on 2017/9/23.
 */

public class MapInfo implements Serializable {


    /**
     * memberContactInfo : []
     * education : 40
     * address : 哈哈哈
     * companyAddress : 哈哈
     * company : 是
     */

    private int education;
    private String address;
    private String companyAddress;
    private String company;
    private List<MemberContactInfo> memberContactInfo;

    public List<MemberContactInfo> getMemberContactInfo() {
        return memberContactInfo;
    }

    public void setMemberContactInfo(List<MemberContactInfo> memberContactInfo) {
        this.memberContactInfo = memberContactInfo;
    }

    public int getEducation() {
        return education;
    }

    public void setEducation(int education) {
        this.education = education;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
