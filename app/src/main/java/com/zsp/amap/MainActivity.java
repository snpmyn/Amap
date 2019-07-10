package com.zsp.amap;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zsp.utilone.permission.SoulPermissionUtils;
import com.zsp.utilone.toast.ToastUtils;

import application.App;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import location.kit.LocationKit;

/**
 * @decs: 主页
 * @author: 郑少鹏
 * @date: 2019/6/22 11:20
 */
public class MainActivity extends AppCompatActivity {
    @BindView(R.id.mainActivityTvResult)
    TextView mainActivityTvResult;
    /**
     * SoulPermissionUtils
     */
    private SoulPermissionUtils soulPermissionUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initConfiguration();
    }

    protected void initConfiguration() {
        soulPermissionUtils = new SoulPermissionUtils();
    }

    @OnClick(R.id.mainActivityMbLocationResult)
    public void onViewClicked(View view) {
        if (view.getId() == R.id.mainActivityMbLocationResult) {
            soulPermissionUtils.checkAndRequestPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION, soulPermissionUtils,
                    true, new SoulPermissionUtils.CheckAndRequestPermissionCallBack() {
                        @Override
                        public void onPermissionOk() {
                            mainActivityTvResult.setText(LocationKit.getInstanceByDcl(App.getInstance()).locationResult());
                        }

                        @Override
                        public void onPermissionDeniedNotRationaleInMiUi(String s) {
                            ToastUtils.shortShow(MainActivity.this, s);
                            finish();
                        }

                        @Override
                        public void onPermissionDeniedNotRationaleWithoutLoopHint(String s) {

                        }
                    });
        }
    }
}
