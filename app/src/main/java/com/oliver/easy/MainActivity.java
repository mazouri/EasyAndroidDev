package com.oliver.easy;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.oliver.easy.utils.StringUtils;
import com.oliver.easy.utils.TextSpanUtils;
import com.oliver.easy.utils.log.EasyLogHelper;

public class MainActivity extends ActionBarActivity {
    private static final EasyLogHelper sLogger = EasyLogHelper.getLogger(MainActivity.class.getSimpleName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String cutString = StringUtils.cutString("year = 2015");
        sLogger.d("cutString = " + cutString);

        TextView textView = (TextView) findViewById(R.id.text);
        TextSpanUtils textHighlightUtils = new TextSpanUtils(Color.RED);
        textHighlightUtils.setPrefixText(textView, "this this this is hight light text", "TH");
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
}
