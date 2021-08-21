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
public class MessagesBean {

private String messageKey;
private String userKeyFrom;
private String userKeyTo;
private String message;
private String deleteFrom;
private String deleteTo;
private String messageOn;
private String status;

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeleteFrom() {
        return deleteFrom;
    }

    public void setDeleteFrom(String deleteFrom) {
        this.deleteFrom = deleteFrom;
    }

    public String getDeleteTo() {
        return deleteTo;
    }

    public void setDeleteTo(String deleteTo) {
        this.deleteTo = deleteTo;
    }

    public String getMessageOn() {
        return messageOn;
    }

    public void setMessageOn(String messageOn) {
        this.messageOn = messageOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }    
}
