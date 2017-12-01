package com.example.dell.quarter;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.igexin.sdk.PushManager;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;

import java.net.ConnectException;

import server.DemoIntentService;
import server.DemoPushService;

/**
 * Created by DELL on 2017/11/14.
 */

public class MyApp extends Application {
    public static Context context;
    @Override
    public void onCreate() {

        super.onCreate();
        context=getApplicationContext();
        //Bugly
        CrashReport.initCrashReport(getApplicationContext(), "115b86a800", false);


        //友盟统计
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL );
       // MobclickAgent.startWithConfigure(new MobclickAgent.UMAnalyticsConfig(this, "5a0a80dea40fa3748200016d", "Channel ID"));


        //l
        LeakCanary.install(this);


        //个推
        PushManager.getInstance().initialize(this.getApplicationContext(), DemoPushService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);

        Fresco.initialize(this);
    }





}
