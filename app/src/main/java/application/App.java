package application;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import location.configure.LocationInitConfigure;

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
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Application本已单例
        instance = this;
        // 高德（定位）
        LocationInitConfigure.initLocation(this);
    }

    public static App getInstance() {
        return instance;
    }
}
