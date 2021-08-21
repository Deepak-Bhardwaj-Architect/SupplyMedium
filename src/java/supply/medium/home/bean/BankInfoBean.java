/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package supply.medium.home.bean;

/**
 *
 * @author Intel
 */
public class BankInfoBean {

    private String companyKey;
    private String acName;
    private String acNo;
    private String swiffCode;
    private String ifscCode;
    private String branchAddress;
    private String paypalAcName;
    private String paypalEmail;

    public String getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(String companyKey) {
        this.companyKey = companyKey;
    }

    public String getAcName() {
        return acName;
    }

    public void setAcName(String acName) {
        this.acName = acName;
    }

    public String getAcNo() {
        return acNo;
    }

    public void setAcNo(String acNo) {
        this.acNo = acNo;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getPaypalAcName() {
        return paypalAcName;
    }

    public void setPaypalAcName(String paypalAcName) {
        this.paypalAcName = paypalAcName;
    }

    public String getPaypalEmail() {
        return paypalEmail;
    }

    public void setPaypalEmail(String paypalEmail) {
        this.paypalEmail = paypalEmail;
    }

    public String getSwiffCode() {
        return swiffCode;
    }

    public void setSwiffCode(String swiffCode) {
        this.swiffCode = swiffCode;
    }

}
