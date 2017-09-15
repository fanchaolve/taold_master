/**  
   * Copyright (C) 2015 The LianLianYT PAY SDK Source Project
   * All right reserved.
   * @Title:  EntireFactorBean.java   
   * @Package com.yintong.pay.utils   
   * @author: Marco Jin     
   * @date:   2017年2月6日 下午6:11:05       
*/  
package com.bb.taold.lianlian.utils;

public class EntireFactorBean {
    private String id_type;
    private String id_no;
    private String acct_name;
    private String bind_mob;
    private String card_no;
    private String oid_partner;
    private String user_id;
    private String sign;
    private String sign_type;
    private String api_version;
    private String time_stamp;
    private String toke;
	private String no_order;
	private String flag_pay_product;
	private String flag_chnl;
	private String money_order;
	private String no_agree;
    
	public String getId_type() {
		return id_type;
	}
	public void setId_type(String id_type) {
		this.id_type = id_type;
	}
	public String getId_no() {
		return id_no;
	}
	public void setId_no(String id_no) {
		this.id_no = id_no;
	}
	public String getAcct_name() {
		return acct_name;
	}
	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
	}
	public String getBind_mob() {
		return bind_mob;
	}
	public void setBind_mob(String bind_mob) {
		this.bind_mob = bind_mob;
	}
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	public String getOid_partner() {
		return oid_partner;
	}
	public void setOid_partner(String oid_partner) {
		this.oid_partner = oid_partner;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getApi_version() {
		return api_version;
	}
	public void setApi_version(String api_version) {
		this.api_version = api_version;
	}
	public String getTime_stamp() {
		return time_stamp;
	}
	public void setTime_stamp(String time_stamp) {
		this.time_stamp = time_stamp;
	}
	
	public String getToke() {
		return toke;
	}
	public void setToke(String toke) {
		this.toke = toke;
	}

	public String getNo_order() {
		return no_order;
	}

	public void setNo_order(String no_order) {
		this.no_order = no_order;
	}

	public String getFlag_pay_product() {
		return flag_pay_product;
	}

	public void setFlag_pay_product(String flag_pay_product) {
		this.flag_pay_product = flag_pay_product;
	}

	public String getFlag_chnl() {
		return flag_chnl;
	}

	public void setFlag_chnl(String flag_chnl) {
		this.flag_chnl = flag_chnl;
	}

	public String getMoney_order() {
		return money_order;
	}

	public void setMoney_order(String money_order) {
		this.money_order = money_order;
	}

	public String getNo_agree() {
		return no_agree;
	}

	public void setNo_agree(String no_agree) {
		this.no_agree = no_agree;
	}
}
