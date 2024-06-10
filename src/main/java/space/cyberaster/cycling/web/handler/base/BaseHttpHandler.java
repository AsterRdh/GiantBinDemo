package space.cyberaster.cycling.web.handler.base;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import space.cyberaster.cycling.web.vo.AppResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class BaseHttpHandler <T> implements HttpHandler {

    protected Gson gson = new Gson();

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestMethod = httpExchange.getRequestMethod().toUpperCase();

        Headers responseHeaders = httpExchange.getResponseHeaders();
        responseHeaders.set("Content-Type", "application/json; charset=utf-8");
        AppResponse<T> objectAppResponse = null;
        switch (requestMethod) {
            case "GET":
                objectAppResponse = doGet(httpExchange);
                break;
            case "POST":
                objectAppResponse =  doPost(httpExchange);
                break;
            default:
                objectAppResponse = AppResponse.ERROR("不支持的请求类型");
        }

        String json = gson.toJson(objectAppResponse);


        httpExchange.sendResponseHeaders(objectAppResponse.getCode(), json.getBytes(StandardCharsets.UTF_8).length);

        OutputStream responseBody = httpExchange.getResponseBody();
        OutputStreamWriter writer = new OutputStreamWriter(responseBody, StandardCharsets.UTF_8);
        writer.write(json);
        writer.close();
        responseBody.close();


    }

    public AppResponse<T> doGet(HttpExchange httpExchange) throws IOException{
        return null;
    };
    public AppResponse<T> doPost(HttpExchange httpExchange) throws IOException{
        return null;
    };


    public static Map<String, String> getParamMap(String query) {
        if (query == null || query.isEmpty()) return Collections.emptyMap();
        return Stream.of(query.split("&"))
                .filter(s -> !s.isEmpty())
                .map(kv -> kv.split("=", 2))
                .collect(Collectors.toMap(x -> x[0], x-> x[1]));

    }




}
