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
public class InquiryBean {
    
    private String inquiry_key;
    private String inquire_type;
    private String vr_key;
    private String rfq_key;
    private String transaction_key;
    private String inquire_from;
    private String inquire_to;
    private String inquire_details;
    private String sent_on;

    public String getInquiry_key() {
        return inquiry_key;
    }

    public void setInquiry_key(String inquiry_key) {
        this.inquiry_key = inquiry_key;
    }

    public String getInquire_type() {
        return inquire_type;
    }

    public void setInquire_type(String inquire_type) {
        this.inquire_type = inquire_type;
    }

    public String getVr_key() {
        return vr_key;
    }

    public void setVr_key(String vr_key) {
        this.vr_key = vr_key;
    }

    public String getRfq_key() {
        return rfq_key;
    }

    public void setRfq_key(String rfq_key) {
        this.rfq_key = rfq_key;
    }

    public String getTransaction_key() {
        return transaction_key;
    }

    public void setTransaction_key(String transaction_key) {
        this.transaction_key = transaction_key;
    }

    public String getInquire_from() {
        return inquire_from;
    }

    public void setInquire_from(String inquire_from) {
        this.inquire_from = inquire_from;
    }

    public String getInquire_to() {
        return inquire_to;
    }

    public void setInquire_to(String inquire_to) {
        this.inquire_to = inquire_to;
    }

    public String getInquire_details() {
        return inquire_details;
    }

    public void setInquire_details(String inquire_details) {
        this.inquire_details = inquire_details;
    }

    public String getSent_on() {
        return sent_on;
    }

    public void setSent_on(String sent_on) {
        this.sent_on = sent_on;
    }
    
}
