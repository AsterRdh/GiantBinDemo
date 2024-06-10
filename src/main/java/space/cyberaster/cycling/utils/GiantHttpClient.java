package space.cyberaster.cycling.utils;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import space.cyberaster.cycling.ginat.Apis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GiantHttpClient {
    public static final OkHttpClient httpClient = new OkHttpClient.Builder()
            .cookieJar(new CookieJar() {
        private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();
        @Override
        public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> cookies) {
            cookieStore.put(httpUrl, cookies);
            cookieStore.put(HttpUrl.parse("http://localhost:" + Apis.Port), cookies);
            for(Cookie cookie:cookies){
                System.out.println("put cookie Name:"+cookie.name()+"; Path:"+cookie.path());

            }
        }

        @NotNull
        @Override
        public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
            List<Cookie> cookies = cookieStore.get(HttpUrl.parse("http://localhost:" + Apis.Port));
            if(cookies==null){
                System.out.println(httpUrl + " 没加载到cookie");
            }
            return cookies != null ? cookies : new ArrayList<Cookie>();
        }
    })
            .build();


}
