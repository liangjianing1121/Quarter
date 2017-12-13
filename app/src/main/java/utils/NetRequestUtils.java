package utils;

import com.example.dell.quarter.MyApp;
import com.facebook.drawee.debug.DebugControllerOverlayDrawable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import common.BaseUrl;
import okhttp3.Cache;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import service.ApiService;

/**
 * Created by DELL on 2017/11/27.
 */

public class NetRequestUtils {
    private ApiService apiService;
    private static NetRequestUtils mInstance;




    public NetRequestUtils(ApiService apiService) {
        this.apiService = apiService;
    }

    public ApiService getApiService() {
        return apiService;
    }


    public static  class Builder{
        private List<Converter.Factory> converterFactories=new ArrayList<>();
        private List<CallAdapter.Factory> calladapterFactories=new ArrayList<>();

        public Builder addConverterFactories(Converter.Factory factory){
            converterFactories.add(factory);
            return this;
        }

        public Builder addCallAdapterFactories(CallAdapter.Factory factory){
            calladapterFactories.add(factory);
            return this;
        }

        public NetRequestUtils build(){
            long SIZE_OF_CACHE = 10 * 1024 * 1024; // 10 MiB

            String cacheFile = MyApp.context.getCacheDir()+"/http";
            Cache cache = new Cache(new File(cacheFile), SIZE_OF_CACHE);
            OkHttpClient.Builder okBuilder=new OkHttpClient.Builder();
            okBuilder.addInterceptor(new MyIntercept())
            .addNetworkInterceptor(MyGetIntercept.REWRITE_RESPONSE_INTERCEPTOR)
            .addInterceptor(MyGetIntercept.REWRITE_RESPONSE_INTERCEPTOR_OFFLINE)
            .cache(cache)
            .build();
            Retrofit.Builder builder=new Retrofit.Builder().baseUrl(BaseUrl.BASE_URL)
                    .client(okBuilder.build());

            if(converterFactories.size()==0)
            {
                converterFactories.add(GsonConverterFactory.create());
            }
            else
            {
                for (Converter.Factory converterFactory : converterFactories) {

                    builder.addConverterFactory(converterFactory);
                }
            }

            if(calladapterFactories.size()==0)
            {
                calladapterFactories.add(RxJava2CallAdapterFactory.create());
            }
            else
            {
                for (CallAdapter.Factory calladapterFactory : calladapterFactories) {
                    builder.addCallAdapterFactory(calladapterFactory);
                }

            }
            ApiService apiService = builder.build().create(ApiService.class);

            mInstance=new NetRequestUtils(apiService);

            return mInstance;

        }

    }


}
