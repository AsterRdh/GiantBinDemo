package space.cyberaster.cycling.web.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import space.cyberaster.cycling.web.vo.Response;

import java.io.IOException;

public abstract class BaseGetHttpHandler <T> extends BaseHttpHandler <T> implements HttpHandler {


    public abstract Response<T> doGet(HttpExchange httpExchange) throws IOException;





}
