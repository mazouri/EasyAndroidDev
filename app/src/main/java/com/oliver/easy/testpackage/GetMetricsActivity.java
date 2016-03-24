package com.oliver.easy.testpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.oliver.easy.R;
import com.oliver.easy.utils.DevicesInfoUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GetMetricsActivity extends ActionBarActivity {

    @Bind(R.id.tv_introduce)
    TextView tvIntroduce;
    @Bind(R.id.tv_screen_width)
    TextView tvScreenWidth;
    @Bind(R.id.tv_screen_height)
    TextView tvScreenHeight;
    @Bind(R.id.tv_screen_density)
    TextView tvScreenDensity;
    @Bind(R.id.tv_screen_densityDpi)
    TextView tvScreenDensityDpi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_metrics);
        ButterKnife.bind(this);
        
        setupFuc();
    }

    private void setupFuc() {
        int[] screenSizeArray = DevicesInfoUtils.getScreenSizeArray(this);
        int width = screenSizeArray[0];
        int height = screenSizeArray[1];
        float density = DevicesInfoUtils.getdensity(this);
        float densityDpi = DevicesInfoUtils.getdensityDpi(this);

        String introduceString = "dp和px的换算公式:dp*densityDpi/160 = px\n\tdpValue = pxValue / density + 0.5f\n\tpxValue = dpValue * density + 0.5f" +
                "\nsp 与 px 的换算公式:sp*densityDpi/160 = px\n" +
                "\n当前屏幕1dp = " + densityDpi/160 + "px";

        tvIntroduce.setText(introduceString);

        tvScreenWidth.setText("屏幕宽度（像素）:" + width + "px\t即" + DevicesInfoUtils.px2dip(this, width) + "dp");
        tvScreenHeight.setText("屏幕高度（像素）:" + height + "px\t即" + DevicesInfoUtils.px2dip(this, height) + "dp");
        tvScreenDensity.setText("屏幕密度(density):" + density);
        tvScreenDensityDpi.setText("屏幕密度DPI(densityDpi):" + densityDpi);
    }

    public static void lunchGetMetricsActivity(Context ctx) {
        Intent intent = new Intent(ctx, GetMetricsActivity.class);
        ctx.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
