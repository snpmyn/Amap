package location;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.zsp.utilone.datetime.DateUtils;

/**
 * Created on 2019/6/22.
 *
 * @author 郑少鹏
 * @desc 定位
 */
public class LocationKit {
    /**
     * DCL（Double Check Lock）式单例
     */
    private static LocationKit dclInstance;
    /**
     * 定位客户端
     */
    private AMapLocationClient aMapLocationClient;
    /**
     * 定位客户端配置
     */
    private AMapLocationClientOption aMapLocationClientOption;
    /**
     * 定位结果
     */
    private AMapLocation aMapLocationResult;

    /**
     * constructor
     *
     * @param context 上下文
     */
    private LocationKit(Context context) {
        this.aMapLocationClient = new AMapLocationClient(context.getApplicationContext());
    }

    /**
     * DCL（Double Check Lock）获单例
     * <p>
     * 网上建议和使用最多。
     * <p>
     * 优点：
     * 构造函数private修饰（外部无法访）。
     * 用即调getInstanceByDcl()时才初始化。
     * static关键字修饰（静态变量，存于内存，仅一份数据）。
     * synchronized线程安全（多线程单例唯一性）。
     * 两次判空避多次同步。
     * <p>
     * 缺点：
     * {@link #dclInstance}、{@link #LocationKit(Context)}、getInstanceByDcl()因jvm允乱序执行。该三句代码顺序不定，或现DCL失效。
     * 步骤一：A线程执行getInstanceByDcl()时还没执行构造方法{@link #LocationKit(Context)}。
     * 步骤二：此时B线程调getInstanceByDcl()，因A已执行getInstanceByDcl()，故{@link #dclInstance}不为空就直获。
     * 步骤三：因B直获，而真实情况A线程构造方法还未执行，故{@link #dclInstance}为空。
     * <p>
     * 解决：
     * 此况概率较小。但为解决，Java1.6加volatile关键字避DCL方式失效。虽volatile消耗一些性能，但为DCL最佳写法。
     * 虽volatile使DCL方式完美，但没volatile关键字写法满足大部分情况。除非高并发运行或Java1.6前。
     *
     * @return 单例
     */
    public static LocationKit getInstanceByDcl(Context context) {
        if (dclInstance == null) {
            synchronized (LocationKit.class) {
                if (dclInstance == null) {
                    dclInstance = new LocationKit(context);
                }
            }
        }
        return dclInstance;
    }

    /**
     * 定位场景模式
     * <p>
     * 定位SDK v3.7.0+提供。
     * 选对应定位场景不自设AMapLocationClientOption参数值，SDK据场景自定。
     * 亦可于基础上自设，最后设参数值有效。
     * <p>
     * 默无场景。
     * 目前支持三种场景（签到、出行、运动）
     *
     * @param model 模式
     */
    public void positioningSceneModel(Model model) {
        aMapLocationClientOption = new AMapLocationClientOption();
        switch (model) {
            // 签到
            case SIGN_IN:
                aMapLocationClientOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
                break;
            // 出行
            case TRANSPORT:
                aMapLocationClientOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.Transport);
                break;
            // 运动
            case SPORT:
                aMapLocationClientOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.Sport);
                break;
            default:
                break;
        }
        // 设场景模式后最好调stop后再调start保场景模式生效
        aMapLocationClient.stopLocation();
        aMapLocationClient.startLocation();
    }

    /**
     * 普通模式
     */
    public void commonModel() {
        aMapLocationClientOption();
        aMapLocationClient();
    }

    private void aMapLocationClientOption() {
        aMapLocationClientOption = new AMapLocationClientOption();
        // 高精度定位模式
        // 同时网络定位和GPS定位，优先返最高精度定位结果及对应地址描述信息
        aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        // true定位SDK返近3s精度最高结果（默false）
        // true时setOnceLocation(boolean b)接口true，反不
        aMapLocationClientOption.setOnceLocationLatest(true);
        // 定位间隔最低1000ms（默2000ms）
        aMapLocationClientOption.setInterval(5000);
        // 返地址信息（默返）
        aMapLocationClientOption.setNeedAddress(true);
        // 允模拟位置（默true）
        aMapLocationClientOption.setMockEnable(true);
        // 超时建不低8000ms（默30000ms）
        aMapLocationClientOption.setHttpTimeOut(30000);
        // 关定位缓存机制
        // 开定位缓存于高精度模式和低功耗模式网络定位结果均生成本地缓存，不区分单次定位还是连续定位
        // 不缓存GPS定位结果
        aMapLocationClientOption.setLocationCacheEnable(false);
        // 强刷WIFI（默强）
        aMapLocationClientOption.setWifiScan(true);
        // 传感器
        aMapLocationClientOption.setSensorEnable(true);
    }

    private void aMapLocationClient() {
        // 定位回调监听
        aMapLocationClient.setLocationListener(aMapLocation -> {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    aMapLocationResult = aMapLocation;
                } else {
                    // 定位失败通错误码/信息确定原因，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        });
        // 设参数
        aMapLocationClient.setLocationOption(aMapLocationClientOption);
        // 启定位
        aMapLocationClient.startLocation();
    }

    /**
     * 定位结果
     *
     * @return 定位结果
     */
    public String locationResult() {
        if (null == aMapLocationResult) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        // 错误码0定位成功，否失败。详见错误码表。
        if (aMapLocationResult.getErrorCode() == 0) {
            stringBuilder.append("定位成功" + "\n");
            stringBuilder.append("定位类型：").append(aMapLocationResult.getLocationType()).append("\n");
            stringBuilder.append("经    度：").append(aMapLocationResult.getLongitude()).append("\n");
            stringBuilder.append("纬    度：").append(aMapLocationResult.getLatitude()).append("\n");
            stringBuilder.append("精    度：").append(aMapLocationResult.getAccuracy()).append("米").append("\n");
            stringBuilder.append("提 供 者：").append(aMapLocationResult.getProvider()).append("\n");
            stringBuilder.append("速    度：").append(aMapLocationResult.getSpeed()).append("米/秒").append("\n");
            stringBuilder.append("角    度：").append(aMapLocationResult.getBearing()).append("\n");
            // 当前提供定位服务卫星数
            stringBuilder.append("星    数：").append(aMapLocationResult.getSatellites()).append("\n");
            stringBuilder.append("国    家：").append(aMapLocationResult.getCountry()).append("\n");
            stringBuilder.append("省：").append(aMapLocationResult.getProvince()).append("\n");
            stringBuilder.append("市：").append(aMapLocationResult.getCity()).append("\n");
            stringBuilder.append("城市编码：").append(aMapLocationResult.getCityCode()).append("\n");
            stringBuilder.append("区：").append(aMapLocationResult.getDistrict()).append("\n");
            stringBuilder.append("区 域 码：").append(aMapLocationResult.getAdCode()).append("\n");
            stringBuilder.append("地    址：").append(aMapLocationResult.getAddress()).append("\n");
            stringBuilder.append("兴 趣 点：").append(aMapLocationResult.getPoiName()).append("\n");
            // 定位完成时间
            stringBuilder.append("定位时间：").append(DateUtils.formatUtc(aMapLocationResult.getTime(), "yyyy-MM-dd HH:mm:ss")).append("\n");
        } else {
            // 定位失败
            stringBuilder.append("定位失败" + "\n");
            stringBuilder.append("错 误 码：").append(aMapLocationResult.getErrorCode()).append("\n");
            stringBuilder.append("错误信息：").append(aMapLocationResult.getErrorInfo()).append("\n");
            stringBuilder.append("错误描述：").append(aMapLocationResult.getLocationDetail()).append("\n");
        }
        // 定位后回调时间
        stringBuilder.append("回调时间: ").append(DateUtils.formatUtc(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss")).append("\n");
        return stringBuilder.toString();
    }

    /**
     * 销毁
     * <p>
     * 销定位客户端同销本地定位服务。
     * 销定位客户端后重开定位需重创一AMapLocationClient对象。
     */
    public void destroy() {
        aMapLocationClient.onDestroy();
    }

    public enum Model {
        /**
         * 签到
         */
        SIGN_IN,
        /**
         * 出行
         */
        TRANSPORT,
        /**
         * 运动
         */
        SPORT
    }
}
