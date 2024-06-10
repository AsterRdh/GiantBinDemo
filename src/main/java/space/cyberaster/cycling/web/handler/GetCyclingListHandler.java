package space.cyberaster.cycling.web.handler;

import com.sun.net.httpserver.HttpExchange;
import okhttp3.*;
import space.cyberaster.cycling.bean.QueryCyclingListVO;
import space.cyberaster.cycling.ginat.Apis;
import space.cyberaster.cycling.utils.GiantHttpClient;
import space.cyberaster.cycling.web.handler.base.BasePostHttpHandler;
import space.cyberaster.cycling.web.vo.AppResponse;
import space.cyberaster.cycling.web.vo.cycling.list.CyclingData;
import space.cyberaster.cycling.web.vo.cycling.list.GetCyclingListRespVO;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class GetCyclingListHandler extends BasePostHttpHandler<List<CyclingData>, QueryCyclingListVO> {


    public GetCyclingListHandler(Type type) {
        super(type);
    }

    @Override
    public AppResponse<List<CyclingData>> doPost(HttpExchange httpExchange, QueryCyclingListVO body) throws IOException {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginTime = body.getBeginTime();
        String beginTimeStr = "";
        Date endTime = body.getEndTime();
        String endTimeStr = "";
        if ((beginTime != null) == (endTime != null)){
            if (beginTime != null) {
                long l = endTime.getTime() - beginTime.getTime();
                if (l > 518400000){
                    return AppResponse.ERROR("只能查询近一周的数据");
                }
                beginTimeStr = format.format(beginTime);
                endTimeStr = format.format(beginTime);
            }
        }else {
            return AppResponse.ERROR("参数不合法");
        }




        int pageCount = 1;

        Response response = null;
        ResponseBody responseBody = null;
        List<CyclingData> dataF = new ArrayList<>();
        try {
            boolean hasNext = true;
            while (hasNext) {
                FormBody formBody = new FormBody.Builder()
                        .add("page", pageCount + "")
                        .add("user_id", body.getUserID())
                        .add("begin_time", beginTimeStr)
                        .add("end_time", endTimeStr)
                        .build();
                Request request = new Request.Builder()
                        .url(Apis.ListUrl)
                        .post(formBody)
                        .build();
                Call call = GiantHttpClient.httpClient.newCall(request);
                response = call.execute();
                responseBody = response.body();
                if (responseBody != null) {
                    String string = responseBody.string();
                    GetCyclingListRespVO getCyclingListRespVO = gson.fromJson(string, GetCyclingListRespVO.class);
                    List<CyclingData> data = getCyclingListRespVO.getData();
                    if (data != null && !data.isEmpty()) {
                        dataF.addAll(data);
                    }else {
                        hasNext = false;
                    }
                }

                pageCount++;
                response.close();
                if (responseBody != null) {
                    responseBody.close();
                }
            }

        }catch (Exception e) {
            return AppResponse.ERROR("获取失败",e);
        }finally {
            if (response != null) {
                response.close();
            }
            if (responseBody != null) {
                responseBody.close();
            }
        }

        dataF.sort(Comparator.comparing(CyclingData::getStartTime));

        return AppResponse.OK(dataF);
    }
}
