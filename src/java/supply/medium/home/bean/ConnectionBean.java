/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supply.medium.home.bean;

/**
 *
 * @author Intel
 */
public class ConnectionBean {
    
private String connectionKey;
private String userKeyFrom;
private String userKeyTo;
private String companyKeyFrom;
private String companyKeyTo;
private String status;
private String sentOn;

    public String getConnectionKey() {
        return connectionKey;
    }

    public void setConnectionKey(String connectionKey) {
        this.connectionKey = connectionKey;
    }

    public String getUserKeyFrom() {
        return userKeyFrom;
    }

    public void setUserKeyFrom(String userKeyFrom) {
        this.userKeyFrom = userKeyFrom;
    }

    public String getUserKeyTo() {
        return userKeyTo;
    }

    public void setUserKeyTo(String userKeyTo) {
        this.userKeyTo = userKeyTo;
    }

    public String getCompanyKeyFrom() {
        return companyKeyFrom;
    }

    public void setCompanyKeyFrom(String companyKeyFrom) {
        this.companyKeyFrom = companyKeyFrom;
    }

    public String getCompanyKeyTo() {
        return companyKeyTo;
    }

    public void setCompanyKeyTo(String companyKeyTo) {
        this.companyKeyTo = companyKeyTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSentOn() {
        return sentOn;
    }

    public void setSentOn(String sentOn) {
        this.sentOn = sentOn;
    }


    
}
