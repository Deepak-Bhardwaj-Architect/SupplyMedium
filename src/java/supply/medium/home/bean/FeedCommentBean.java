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
public class FeedCommentBean {
private String feedCommentKey;
private String feedKey;
private String userKey;
private String comment;
private String commentedOn;

    public String getFeedCommentKey() {
        return feedCommentKey;
    }

    public void setFeedCommentKey(String feedCommentKey) {
        this.feedCommentKey = feedCommentKey;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentedOn() {
        return commentedOn;
    }

    public void setCommentedOn(String commentedOn) {
        this.commentedOn = commentedOn;
    }


}
