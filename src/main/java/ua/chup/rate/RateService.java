package ua.chup.rate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

public class RateService {
    public static String performRequest(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        StringBuilder sb = new StringBuilder();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("bcproxy1.ua.net.intra", 3128));
        HttpURLConnection http = (HttpURLConnection) url.openConnection(proxy);
        BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
        char[] buf = new char[1000000];
        int r = 0;
        do{
            if ((r = br.read(buf))>0){
                sb.append(new String(buf,0,r));
            }
        }while (r > 0);

        return sb.toString();
    }
}
