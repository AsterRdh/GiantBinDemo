package space.cyberaster.cycling.web.vo.cycling.list;

import com.google.gson.annotations.SerializedName;

public class CyclingData {
    private String cover;

    @SerializedName("cycling_id")
    private String cyclingID;

    private String device;

    private double meter;

    private String source;

    @SerializedName("finish_time")
    private String finishTime;

    @SerializedName("start_time")
    private String startTime;

    private String title;

    @SerializedName("total_second")
    private long totalSecond;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCyclingID() {
        return cyclingID;
    }

    public void setCyclingID(String cyclingID) {
        this.cyclingID = cyclingID;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public double getMeter() {
        return meter;
    }

    public void setMeter(double meter) {
        this.meter = meter;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTotalSecond() {
        return totalSecond;
    }

    public void setTotalSecond(long totalSecond) {
        this.totalSecond = totalSecond;
    }
}
