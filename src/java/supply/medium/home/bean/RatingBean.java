/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supply.medium.home.bean;

/**
 *
 * @author LenovoB560
 */
public class RatingBean {
    
private String ratingKey;
private String userKeyFrom;
private String userKeyTo;
private String companyKeyFrom;
private String companyKeyTo;
private String ratingTitle;
private String ratingComment;
private String rating;
private String ratingOn;

    public String getRatingKey() {
        return ratingKey;
    }

    public void setRatingKey(String ratingKey) {
        this.ratingKey = ratingKey;
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

    public String getRatingTitle() {
        return ratingTitle;
    }

    public void setRatingTitle(String ratingTitle) {
        this.ratingTitle = ratingTitle;
    }

    public String getRatingComment() {
        return ratingComment;
    }

    public void setRatingComment(String ratingComment) {
        this.ratingComment = ratingComment;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRatingOn() {
        return ratingOn;
    }

    public void setRatingOn(String ratingOn) {
        this.ratingOn = ratingOn;
    }


    
}
