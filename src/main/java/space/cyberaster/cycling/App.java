package space.cyberaster.cycling;

import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.SimpleFileServer;
import space.cyberaster.cycling.bean.QueryCyclingListVO;
import space.cyberaster.cycling.ginat.Apis;
import space.cyberaster.cycling.ginat.BicyclingRecord;
import space.cyberaster.cycling.ginat.BicyclingRecordLap;
import space.cyberaster.cycling.ginat.BicyclingRecordSecData;
import space.cyberaster.cycling.web.handler.GetCyclingBinHandler;
import space.cyberaster.cycling.web.handler.GetCyclingListHandler;
import space.cyberaster.cycling.web.handler.LoginHandler;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.InetAddress;
/**
 * Hello world!
 *
 */
public class App 
{




    public static void main( String[] args ) throws IOException {
//        new App().process();
        InetSocketAddress addr = new InetSocketAddress(Apis.Port);
        String absolutePath1 = new File("./").getAbsolutePath();
        Path path = Path.of(absolutePath1+"/src/main/resources/web");
        Path absolutePath = path.toAbsolutePath();
        HttpServer httpserver = SimpleFileServer.createFileServer(addr, absolutePath, SimpleFileServer.OutputLevel.VERBOSE);
        httpserver.createContext("/login", new LoginHandler());
        httpserver.createContext("/queryList", new GetCyclingListHandler(new TypeToken<QueryCyclingListVO>() {}.getType()));
        httpserver.createContext("/find", new GetCyclingBinHandler());
        httpserver.setExecutor(null);
        httpserver.start();
        System.out.println("server started");
    }
}
