package space.cyberaster.cycling.web.handler;

import com.sun.net.httpserver.HttpExchange;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import space.cyberaster.cycling.ginat.Apis;
import space.cyberaster.cycling.ginat.LoginRespVO;
import space.cyberaster.cycling.utils.GiantHttpClient;
import space.cyberaster.cycling.web.handler.base.BaseGetHttpHandler;
import space.cyberaster.cycling.web.vo.AppResponse;

import java.io.IOException;
import java.util.Map;

public class LoginHandler extends BaseGetHttpHandler<String> {

    @Override
    public AppResponse<String> doGet(HttpExchange httpExchange) throws IOException {
        String query = httpExchange.getRequestURI().getQuery();
        Map<String, String> paramMap = getParamMap(query);
        String username = paramMap.get("username");
        String password = paramMap.get("password");


        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return AppResponse.ERROR("登录失败,缺少登录参数");
        }
        FormBody formBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .add("re_url", Apis.AddResUrl)
                .add("re_code", "14")
                .build();
        Request build = new Request.Builder()
                .url(Apis.LoginUrl)
                .post(formBody)
                .build();


        Call call = GiantHttpClient.httpClient.newCall(build);
        Response execute = null;
        Response executePage = null;
        ResponseBody body = null;
        ResponseBody bodyPage = null;

        try {
            execute = call.execute();
            if (execute.isSuccessful()) {
                body = execute.body();
                if (body != null) {
                    String string = body.string();
                    LoginRespVO loginRespVO = gson.fromJson(string, LoginRespVO.class);

                    Request buildHtml = new Request.Builder()
                            .url(loginRespVO.getMsg())
                            .get().build();
                    executePage =  GiantHttpClient.httpClient.newCall(buildHtml).execute();
                    if (executePage.isSuccessful()) {
                        bodyPage = executePage.body();
                        if (bodyPage!=null){
                            String pageStr = bodyPage.string();
                            String str = "  \"user_id\":";
                            int i = pageStr.indexOf(str);
                            if (i < 0){
                                return AppResponse.ERROR("登录失败，找不到用户主键，可能目标网站结构发生变更，请等待更新或提一个Issues");
                            }
                            i += str.length();
                            int i1 = pageStr.indexOf(",", i);
                            if (i1 < 0){
                                return AppResponse.ERROR("登录失败，找不到用户主键，可能目标网站结构发生变更，请等待更新或提一个Issues");
                            }
                            String userID = pageStr.substring(i, i1).trim();
                            System.out.println(userID);
                            return AppResponse.OK("成功",userID);
                        }
                    }
                }
            }
        }catch (Exception e){
            return AppResponse.ERROR("登录失败",e);
        }finally {

            if (bodyPage!=null){
                bodyPage.close();
            }
            if (executePage!=null){
                executePage.close();
            }


            if (body!=null){
                body.close();
            }
            if (execute!=null){
                execute.close();
            }
        }




        return AppResponse.ERROR("登录失败");
    }
}
