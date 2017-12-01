package utils;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;



/**
 * 沉浸式模式
 */
public class ImmersionUtil {
    /**
     * 透明状态栏
     * @param appCompatActivity
     */
    public static void TransparentStatusbar(AppCompatActivity appCompatActivity) {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = appCompatActivity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            appCompatActivity.getWindow().setNavigationBarColor(Color.TRANSPARENT);
            appCompatActivity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = appCompatActivity.getSupportActionBar();
        if (actionBar!=null)
        actionBar.hide();

    }

    /**
     * 全屏模式(播放电影或者游戏模式)
     * 重写AppCompatActivity的onWindowFocusChanged方法
     * @param hasFocus
     * @param appCompatActivity
     */
    public static void FullScreenMode(Boolean hasFocus, AppCompatActivity appCompatActivity) {
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = appCompatActivity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }

    }


}
