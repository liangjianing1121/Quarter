package utils;

import android.media.audiofx.Equalizer;

import com.example.dell.quarter.MyApp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by DELL on 2017/12/10.
 */

public class MyGetIntercept {
    private static final int TIMEOUT_CONNECT = 5; //5秒
    private static final int TIMEOUT_DISCONNECT = 60 * 60 * 24 * 7; //7天

    public static final Interceptor REWRITE_RESPONSE_INTERCEPTOR = new Interceptor(){


        @Override
        public Response intercept(Chain chain) throws IOException {


            String cache = chain.request().header("cache");
            Response originalResponse  = chain.proceed(chain.request());
            String cacheControl = originalResponse.header("Cache-Control");
            if(cacheControl==null)
            {
                if(cache ==null || "".equals(cache))
                {
                    cache=TIMEOUT_CONNECT + "";
                }
                originalResponse = originalResponse.newBuilder()
                        .header("Cache-Control","public, max-age=" + cache)
                        .build();
                return originalResponse;
            }
            else
            {
                return originalResponse;
            }

        }
    };



    public  static  final  Interceptor REWRITE_RESPONSE_INTERCEPTOR_OFFLINE = new Interceptor() {
       private  Request request;
        @Override
        public Response intercept(Chain chain) throws IOException {

           request = chain.request();

           /* NetUtils.viefil(this, new NetUtils.NetWork() {
                @Override
                public void youNetwork() {

                }

                @Override
                public void noNetwork() {
                    request = request.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale="+TIMEOUT_DISCONNECT)
                            .build();
                }
            });*/


            if (!NetUtils.isConnected(MyApp.context)) {
                request = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale="+TIMEOUT_DISCONNECT)
                        .build();
            }

            return chain.proceed(request);
        }
    };

}
