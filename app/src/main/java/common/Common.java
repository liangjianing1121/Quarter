package common;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
/**
 * Created by DELL on 2017/12/14.
 */

public class Common {

    /**
     * 获取软件版本号
     * @param context
     * @return
     */
    public static int getVerCode(Context context) {
        int verCode = -1;
        try {
            //注意："com.example.try_downloadfile_progress"对应AndroidManifest.xml里的package="……"部分
            verCode = context.getPackageManager().getPackageInfo(
                    "com.example.dell.quarter", 0).versionCode;
        } catch (NameNotFoundException e) {
            Log.e("msg",e.getMessage());
        }
        return verCode;
    }
    /**
     * 获取版本名称
     * @param context
     * @return
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(
                    "com.example.dell.quarter", 0).versionName;
        } catch (NameNotFoundException e) {
            Log.e("msg",e.getMessage());
        }
        return verName;
    }

}
