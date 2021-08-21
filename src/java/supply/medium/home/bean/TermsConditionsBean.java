/*
 * 
 * 
 * 
 */

package supply.medium.home.bean;

/**
 *
 * @author Intel
 */
public class TermsConditionsBean {

private String tcKey;
private String companyKey;
private String transactionType;
private String textMessage;
private String createdOn;

    public String getTcKey() {
        return tcKey;
    }

    public void setTcKey(String tcKey) {
        this.tcKey = tcKey;
    }

    public String getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(String companyKey) {
        this.companyKey = companyKey;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

}
