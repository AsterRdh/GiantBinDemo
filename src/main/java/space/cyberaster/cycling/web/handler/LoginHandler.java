package space.cyberaster.cycling.web.handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import space.cyberaster.cycling.ginat.LoginRespVO;
import space.cyberaster.cycling.web.vo.Response;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginHandler extends BaseGetHttpHandler<String> {

    @Override
    public Response<String> doGet(HttpExchange httpExchange) throws IOException {
        String query = httpExchange.getRequestURI().getQuery();
        Map<String, String> paramMap = getParamMap(query);
        String username = paramMap.get("username");
        String password = paramMap.get("password");


        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return Response.ERROR("登录失败,缺少登录参数");
        }
        FormBody formBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .add("re_url", "http://map.giant.com.cn/index.php/user_admin/route/add.html")
                .add("re_code", "14")
                .build();
        Request build = new Request.Builder()
                .url("https://u-api.giant.com.cn/index.php/Login/login.html")
                .post(formBody)
                .build();
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .cookieJar(new CookieJar() {
                    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();
                    @Override
                    public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> cookies) {
                        cookieStore.put(httpUrl, cookies);
                        cookieStore.put(HttpUrl.parse("http://localhost:9000"), cookies);
                        for(Cookie cookie:cookies){
                            System.out.println("put cookie Name:"+cookie.name()+"; Path:"+cookie.path());

                        }
                    }

                    @NotNull
                    @Override
                    public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
                        List<Cookie> cookies = cookieStore.get(HttpUrl.parse("http://localhost:9000"));
                        if(cookies==null){
                            System.out.println(httpUrl + " 没加载到cookie");
                        }
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .build();

        Call call = httpClient.newCall(build);
        okhttp3.Response execute = null;
        okhttp3.Response executePage = null;
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
                    executePage = httpClient.newCall(buildHtml).execute();
                    if (executePage.isSuccessful()) {
                        bodyPage = executePage.body();
                        if (bodyPage!=null){
                            String pageStr = bodyPage.string();
                            String str = "  \"user_id\":";
                            int i = pageStr.indexOf(str);
                            if (i < 0){
                                return Response.ERROR("登录失败，找不到用户主键，可能目标网站结构发生变更，请等待更新或提一个Issues");
                            }
                            i += str.length();
                            int i1 = pageStr.indexOf(",", i);
                            if (i1 < 0){
                                return Response.ERROR("登录失败，找不到用户主键，可能目标网站结构发生变更，请等待更新或提一个Issues");
                            }
                            String userID = pageStr.substring(i, i1).trim();
                            System.out.println(userID);
                            return Response.OK("成功",userID);
                        }
                    }
                }
            }
        }catch (Exception e){
            return Response.ERROR("登录失败",e);
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




        return Response.ERROR("登录失败");
    }
}
