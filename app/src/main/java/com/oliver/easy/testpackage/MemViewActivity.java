package com.oliver.easy.testpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.oliver.easy.R;

public class MemViewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem_view);
    }

    public static void lunchMemViewActivity(Context ctx) {
        Intent intent = new Intent(ctx, MemViewActivity.class);
        ctx.startActivity(intent);
    }

}
