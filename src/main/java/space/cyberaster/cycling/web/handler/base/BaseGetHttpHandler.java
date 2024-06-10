package space.cyberaster.cycling.web.handler.base;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import space.cyberaster.cycling.web.vo.AppResponse;

import java.io.IOException;

public abstract class BaseGetHttpHandler <T> extends BaseHttpHandler <T> implements HttpHandler {


    public abstract AppResponse<T> doGet(HttpExchange httpExchange) throws IOException;





}
