package location.configure;

import android.app.Application;

import location.kit.LocationKit;

/**
 * Created on 2019/7/1.
 *
 * @author 郑少鹏
 * @desc 定位初始化配置
 */
public class LocationInitConfigure {
    public static void initLocation(Application application) {
        LocationKit.getInstanceByDcl(application).commonModel();
    }
}
