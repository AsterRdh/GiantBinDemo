package space.cyberaster.cycling.web.handler;

import com.sun.net.httpserver.HttpExchange;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import space.cyberaster.cycling.bean.BicyclingSecData;
import space.cyberaster.cycling.ginat.Apis;
import space.cyberaster.cycling.ginat.BicyclingRecord;
import space.cyberaster.cycling.ginat.BicyclingRecordSecData;
import space.cyberaster.cycling.utils.DataProcess;
import space.cyberaster.cycling.utils.GiantHttpClient;
import space.cyberaster.cycling.web.handler.base.BaseGetHttpHandler;
import space.cyberaster.cycling.web.vo.AppResponse;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetCyclingBinHandler extends BaseGetHttpHandler<List<BicyclingSecData>> {

    @Override
    public AppResponse<List<BicyclingSecData>> doGet(HttpExchange httpExchange) throws IOException {

        String query = httpExchange.getRequestURI().getQuery();
        Map<String, String> paramMap = getParamMap(query);
        String cyclingID = paramMap.get("cyclingID");
        if (cyclingID == null) {
            return AppResponse.ERROR("参数异常");
        }
        String trackUrl = Apis.GetTrackUrl.replace("$$", cyclingID);
        Request request = new Request.Builder()
                .url(trackUrl)
                .build();
        Call call = GiantHttpClient.httpClient.newCall(request);
        Response response = null;
        ResponseBody body = null;
        try {
            response = call.execute();
            body = response.body();
            if (body == null) {
                return AppResponse.ERROR("获取异常");
            }
            DataProcess processor = DataProcess.processor;
            BicyclingRecord process = processor.process(body.bytes());
            List<BicyclingSecData> secData = processor.getSecData(process).stream()
                    .map(BicyclingSecData::new)
                    .sorted(Comparator.comparing(BicyclingSecData::getCurrentTime))
                    .toList();

            return AppResponse.OK(secData);
        }catch (Exception e) {
            return AppResponse.ERROR(e.getMessage(),e);
        }finally {
            if (body != null) {
                body.close();
            }
            if (response != null) {
                response.close();
            }

        }
    }
}
