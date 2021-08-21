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
public class FeedLikeBean {
private String feedLikeKey;
private String feedKey;
private String userKey;
private String likedOn;

    public String getFeedLikeKey() {
        return feedLikeKey;
    }

    public void setFeedLikeKey(String feedLikeKey) {
        this.feedLikeKey = feedLikeKey;
    }

    public String getFeedKey() {
        return feedKey;
    }

    public void setFeedKey(String feedKey) {
        this.feedKey = feedKey;
    }
    
    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getLikedOn() {
        return likedOn;
    }

    public void setLikedOn(String likedOn) {
        this.likedOn = likedOn;
    }


    
}
