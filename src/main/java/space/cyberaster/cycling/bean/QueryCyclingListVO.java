package space.cyberaster.cycling.bean;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class QueryCyclingListVO {
    private String userID;
    private Date beginTime;
    private Date endTime;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
