package application;

import android.app.Application;

import com.zsp.amap.BuildConfig;
import com.zsp.amap.library.configure.LocationInitConfigure;
import com.zsp.utilone.timber.configure.TimberInitConfigure;

/**
 * Created on 2019/7/1.
 *
 * @author 郑少鹏
 * @desc 应用
 */
public class App extends Application {
    /**
     * 实例
     */
    private static App instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        // Application本已单例
        instance = this;
        // timber
        TimberInitConfigure.initTimber(BuildConfig.DEBUG);
        // 高德（定位）
        LocationInitConfigure.initLocation(this);
    }

    public static App getInstance() {
        return instance;
    }
}
