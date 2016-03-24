package com.oliver.easy.testpackage;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.oliver.easy.R;
import com.oliver.easy.utils.StringUtils;
import com.oliver.easy.utils.TextSpanUtils;
import com.oliver.easy.utils.log.EasyLogHelper;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends ActionBarActivity {
    private static final EasyLogHelper sLogger = EasyLogHelper.getLogger(MainActivity.class.getSimpleName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        String cutString = StringUtils.cutString("year = 2015");
        sLogger.d("cutString = " + cutString);

        TextView textView = (TextView) findViewById(R.id.text);
        TextSpanUtils textHighlightUtils = new TextSpanUtils(Color.RED);
        textHighlightUtils.setPrefixText(textView, "I'm MAZOURI! and my name has processed into high light text.", "MAZOURI");

        setupFunc();
    }

    private void setupFunc() {

    }

    @OnClick(R.id.btn_get_metrics)
    public void OnGetMetricsClick() {
        GetMetricsActivity.lunchGetMetricsActivity(this);
    }

    @OnClick(R.id.btn_memory_status_view)
    public void onMemViewClick() {
        MemViewActivity.lunchMemViewActivity(this);
    }

    @OnClick(R.id.btn_cate_card_view)
    public void onCateCardClick() {
        CateCardViewActivity.lunchCateCardViewActivity(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
