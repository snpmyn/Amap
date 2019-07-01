package com.zsp.amap;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.qw.soul.permission.SoulPermission;
import com.qw.soul.permission.bean.Permission;
import com.qw.soul.permission.bean.Permissions;
import com.qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.zsp.utilone.permission.SoulPermissionUtils;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        execute();
    }

    private void execute() {
        SoulPermission.getInstance().checkAndRequestPermissions(
                Permissions.build(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
                // if you want do noting or no need all the callbacks you may use SimplePermissionsAdapter instead
                new CheckRequestPermissionsListener() {
                    @Override
                    public void onAllPermissionOk(Permission[] allPermissions) {

                    }

                    @Override
                    public void onPermissionDenied(Permission[] refusedPermissions) {
                        SoulPermissionUtils soulPermissionUtils = new SoulPermissionUtils();
                        soulPermissionUtils.multiPermissionsDenied(MainActivity.this, refusedPermissions);
                    }
                });
    }

    @OnClick(R.id.mainActivityMbLocationResult)
    public void onViewClicked(View view) {
        if (view.getId() == R.id.mainActivityMbLocationResult) {
            mainActivityTvResult.setText(LocationKit.getInstanceByDcl(App.getInstance()).locationResult());
        }
    }
}
