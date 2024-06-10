package space.cyberaster.cycling.web.handler.base;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.io.IOUtils;
import space.cyberaster.cycling.web.vo.AppResponse;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

public abstract class BasePostHttpHandler <T,E> extends BaseHttpHandler <T> implements HttpHandler {
    Class<E> clazz;
    Type type;
    public BasePostHttpHandler(Class<E> clazz) {
        this.clazz = clazz;
    }

    public BasePostHttpHandler(Type type) {
        this.type = type;
    }

    @Override
    public AppResponse<T> doPost(HttpExchange httpExchange) throws IOException {
        InputStream requestBody = httpExchange.getRequestBody();
        String string = IOUtils.toString(requestBody, StandardCharsets.UTF_8);
        //String string = responseBody.toString();
        E e;
        if (clazz!=null){
            e = gson.fromJson(string, clazz);
        }else {
            e = gson.fromJson(string, type);
        }


        return doPost(httpExchange, e);
    }

    public abstract AppResponse<T> doPost(HttpExchange httpExchange, E body) throws IOException;



}
