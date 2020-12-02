package com.zsp.amap;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zsp.amap.library.kit.LocationKit;
import com.zsp.utilone.permission.SoulPermissionUtils;
import com.zsp.utilone.toast.ToastUtils;

import application.App;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @decs: 主页
 * @author: 郑少鹏
 * @date: 2019/6/22 11:20
 */
public class MainActivity extends AppCompatActivity {
    @SuppressLint("NonConstantResourceId")
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

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.mainActivityMbLocationResult)
    public void onViewClicked(View view) {
        if (view.getId() == R.id.mainActivityMbLocationResult) {
            soulPermissionUtils.checkAndRequestPermissions(soulPermissionUtils, false,
                    new SoulPermissionUtils.CheckAndRequestPermissionsCallBack() {
                        @Override
                        public void onAllPermissionOk() {
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
                    }, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION);
        }
    }
}
