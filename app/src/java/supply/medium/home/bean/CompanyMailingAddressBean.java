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
public class CompanyMailingAddressBean {
private String mailingKey;
private String companyKey;
private String branch;
private String country;
private String address;
private String city;
private String state;
private String zipcode;
private String addressType;
private String addedByUserKey;
private String createdOn; 

    public String getMailingKey() {
        return mailingKey;
    }

    public void setMailingKey(String mailingKey) {
        this.mailingKey = mailingKey;
    }

    public String getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(String companyKey) {
        this.companyKey = companyKey;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddedByUserKey() {
        return addedByUserKey;
    }

    public void setAddedByUserKey(String addedByUserKey) {
        this.addedByUserKey = addedByUserKey;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

}
