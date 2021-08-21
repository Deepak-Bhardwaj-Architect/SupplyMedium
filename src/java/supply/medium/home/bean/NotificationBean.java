/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supply.medium.home.bean;

/**
 *
 * @author Lenovo
 */
public class NotificationBean {
   
private String notificationKey;
private String userKeyFrom;
private String userKeyTo;
private String companyKeyFrom;
private String companyKeyTo;
private String notificationType;
private String notificationTypeId;
private String notificationMessage;
private String status;
private String notificationOn;

    public String getNotificationKey() {
        return notificationKey;
    }

    public void setNotificationKey(String notificationKey) {
        this.notificationKey = notificationKey;
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

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getNotificationTypeId() {
        return notificationTypeId;
    }

    public void setNotificationTypeId(String notificationTypeId) {
        this.notificationTypeId = notificationTypeId;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotificationOn() {
        return notificationOn;
    }

    public void setNotificationOn(String notificationOn) {
        this.notificationOn = notificationOn;
    }


    
}
