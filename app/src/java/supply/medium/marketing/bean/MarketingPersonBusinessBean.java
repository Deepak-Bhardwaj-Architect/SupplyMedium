/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supply.medium.marketing.bean;

/**
 *
 * @author Intel8GB
 */
public class MarketingPersonBusinessBean {

    private String marketing_person_business_key;
    private String date_of_registration;
    private String marketing_person_key;
    private String company_key;

    public String getDate_of_registration() {
        return date_of_registration;
    }

    public void setDate_of_registration(String date_of_registration) {
        this.date_of_registration = date_of_registration;
    }

    public String getMarketing_person_business_key() {
        return marketing_person_business_key;
    }

    public void setMarketing_person_business_key(String marketing_person_business_key) {
        this.marketing_person_business_key = marketing_person_business_key;
    }

    public String getMarketing_person_key() {
        return marketing_person_key;
    }

    public void setMarketing_person_key(String marketing_person_key) {
        this.marketing_person_key = marketing_person_key;
    }

    public String getCompany_key() {
        return company_key;
    }

    public void setCompany_key(String company_key) {
        this.company_key = company_key;
    }
}
