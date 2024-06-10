package space.cyberaster.cycling.web.vo.cycling.list;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class GetCyclingListResVO {
    private int page;
    @SerializedName("user_idq")
    private String userID;
    @SerializedName("begin_time")
    private Date beginTime;
    @SerializedName("end_time")//yyyy-MM-dd
    private Date endTime;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

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
