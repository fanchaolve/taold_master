package com.bb.taold.bean;

/**
 * ==============================================
 * <p>
 * 包名：com.bb.taold.bean
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/12
 * <p>
 * ==============================================
 */

public class AuthResult {


    /**
     * url_photoget : https://idsafe-auth.udcredit.com/front/4.0/api/file_download/platform/web?name=i_20170802175451558959473.jpg&param=8664FD3F90B382E1676EA4FB541222F364E46C14FFA4404CFD1E26F56095D7
     * flag_sex : 男
     * risk_tag : {"living_attack":"0"}
     * ret_msg : 交易成功
     * id_name : 朱成安
     * be_idcard : 0.9548
     * id_no : 330727199412300057
     * date_birthday : 1994.12.30
     * url_frontcard : https://idsafe-auth.udcredit.com/front/4.0/api/file_download/platform/web?name=i_20170802175451152829112.jpg&param=8664FD3F90B382E1676EA4FB541222F364E46C14FFA4404CFD1E26F56095D7
     * addr_card : 浙江省磐安县安文镇石头村227号
     * branch_issued : 磐安县公安局
     * state_id : 汉
     * url_backcard : https://idsafe-auth.udcredit.com/front/4.0/api/file_download/platform/web?name=i_20170802175500362411697.jpg&param=8664FD3F90B382E1676EA4FB541222F364E46C14FFA4404CFD1E26F56095D7
     * ret_code : 000000
     * result_auth : T
     * start_card : 2012.03.04-2022.03.04
     * url_photoliving : https://idsafe-auth.udcredit.com/front/4.0/api/file_download/platform/web?name=i_20170802175524699870618.jpg&param=8664FD3F90B382E1676EA4FB541222F364E46C14FFA4404CFD1E26F56095D7
     */

    private String url_photoget;
    private String flag_sex;
    private RiskTagBean risk_tag;
    private String ret_msg;
    private String id_name;
    private String be_idcard;
    private String id_no;
    private String date_birthday;
    private String url_frontcard;
    private String addr_card;
    private String branch_issued;
    private String state_id;
    private String url_backcard;
    private String ret_code;
    private String result_auth;
    private String start_card;
    private String url_photoliving;

    public String getUrl_photoget() {
        return url_photoget;
    }

    public void setUrl_photoget(String url_photoget) {
        this.url_photoget = url_photoget;
    }

    public String getFlag_sex() {
        return flag_sex;
    }

    public void setFlag_sex(String flag_sex) {
        this.flag_sex = flag_sex;
    }

    public RiskTagBean getRisk_tag() {
        return risk_tag;
    }

    public void setRisk_tag(RiskTagBean risk_tag) {
        this.risk_tag = risk_tag;
    }

    public String getRet_msg() {
        return ret_msg;
    }

    public void setRet_msg(String ret_msg) {
        this.ret_msg = ret_msg;
    }

    public String getId_name() {
        return id_name;
    }

    public void setId_name(String id_name) {
        this.id_name = id_name;
    }

    public String getBe_idcard() {
        return be_idcard;
    }

    public void setBe_idcard(String be_idcard) {
        this.be_idcard = be_idcard;
    }

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }

    public String getDate_birthday() {
        return date_birthday;
    }

    public void setDate_birthday(String date_birthday) {
        this.date_birthday = date_birthday;
    }

    public String getUrl_frontcard() {
        return url_frontcard;
    }

    public void setUrl_frontcard(String url_frontcard) {
        this.url_frontcard = url_frontcard;
    }

    public String getAddr_card() {
        return addr_card;
    }

    public void setAddr_card(String addr_card) {
        this.addr_card = addr_card;
    }

    public String getBranch_issued() {
        return branch_issued;
    }

    public void setBranch_issued(String branch_issued) {
        this.branch_issued = branch_issued;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getUrl_backcard() {
        return url_backcard;
    }

    public void setUrl_backcard(String url_backcard) {
        this.url_backcard = url_backcard;
    }

    public String getRet_code() {
        return ret_code;
    }

    public void setRet_code(String ret_code) {
        this.ret_code = ret_code;
    }

    public String getResult_auth() {
        return result_auth;
    }

    public void setResult_auth(String result_auth) {
        this.result_auth = result_auth;
    }

    public String getStart_card() {
        return start_card;
    }

    public void setStart_card(String start_card) {
        this.start_card = start_card;
    }

    public String getUrl_photoliving() {
        return url_photoliving;
    }

    public void setUrl_photoliving(String url_photoliving) {
        this.url_photoliving = url_photoliving;
    }

    public static class RiskTagBean {
        /**
         * living_attack : 0
         */

        private String living_attack;

        public String getLiving_attack() {
            return living_attack;
        }

        public void setLiving_attack(String living_attack) {
            this.living_attack = living_attack;
        }
    }
}
