package com.zsp.amap;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import location.LocationKit;

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
        LocationKit.getInstanceByDcl(this).commonModel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocationKit.getInstanceByDcl(this).destroy();
    }

    @OnClick(R.id.mainActivityMbLocation)
    public void onViewClicked(View view) {
        if (view.getId() == R.id.mainActivityMbLocation) {
            mainActivityTvResult.setText(LocationKit.getInstanceByDcl(this).locationResult());
        }
    }
}
