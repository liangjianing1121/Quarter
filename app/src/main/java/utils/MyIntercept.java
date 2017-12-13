package utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.dell.quarter.MyApp;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;
import static com.example.dell.quarter.MyApp.context;

/**
 * Created by DELL on 2017/11/27.
 */

public class MyIntercept implements Interceptor {

    private int versionCode;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();



        if(request.method().equals("POST"))
        {
            SharedPreferences token = context.getSharedPreferences("token", Context.MODE_PRIVATE);
            String token1 = token.getString("token", "");

            if(request.body() instanceof FormBody)
            {
                System.out.println("开始添加公共参数");

                PackageManager pm = context.getPackageManager();
                PackageInfo pi = null;
                try {
                    pi = pm.getPackageInfo(context.getPackageName(), 0);

                    versionCode = pi.versionCode;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }


                FormBody.Builder builder=new FormBody.Builder();
                //获取原先的body
                FormBody body= (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    builder.add(body.encodedName(i),body.encodedValue(i));

                }
                //添加制定的公共参数到新的boby里,把原先的boby替换掉
                builder.add("source","android")
                .add("appVersion",versionCode+"")
                .add("token",token1);

                /*builder.addEncoded("appVersion",versionCode+"");
                builder.addEncoded("token",token1);*/

                request = request.newBuilder().post(builder.build()).build();
                System.out.println(token+"++++++++++++++");
            }
            else if(request.body() instanceof MultipartBody)
            {
                MultipartBody body = (MultipartBody) request.body();
                MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM);
                builder.addFormDataPart("source","android")
                        .addFormDataPart("appVersion",versionCode+"")
                        .addFormDataPart("token",token1);
                List<MultipartBody.Part> parts = body.parts();
                for (MultipartBody.Part part : parts) {
                    builder.addPart(part);
                }
                request=request.newBuilder().post(builder.build()).build();
            }
        }
        else if(request.method().equals("GET"))
        {

            SharedPreferences token = context.getSharedPreferences("token", Context.MODE_PRIVATE);
            String token1 = token.getString("token", "");

            HttpUrl httpUrl = request.url()
                    .newBuilder()
                    .addQueryParameter("source","android")
                    .addQueryParameter("appVersion",versionCode+"")
                    .addQueryParameter("token",token1)
                    .build();
           request= request.newBuilder().url(httpUrl).build();


        }

        return chain.proceed(request);
    }
}
