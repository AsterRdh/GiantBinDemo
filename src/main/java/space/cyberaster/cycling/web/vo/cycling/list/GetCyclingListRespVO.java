package space.cyberaster.cycling.web.vo.cycling.list;

import java.util.Date;
import java.util.List;

public class GetCyclingListRespVO {
    private int status;
    private String msg;
    private List<CyclingData> data; //yyyy-MM-dd

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<CyclingData> getData() {
        return data;
    }

    public void setData(List<CyclingData> data) {
        this.data = data;
    }
}
