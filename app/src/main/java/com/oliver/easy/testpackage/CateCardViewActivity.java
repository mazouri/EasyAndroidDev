package com.oliver.easy.testpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

import com.oliver.easy.R;
import com.oliver.easy.widght.CategoryCardView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CateCardViewActivity extends ActionBarActivity {

    @Bind(R.id.cate_card_view)
    CategoryCardView categoryCardView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cate_card_view);
        ButterKnife.bind(this);

        setupView();
    }

    private void setupView() {
        categoryCardView.setOnViewClickListener(new CategoryCardView.OnSelectListener() {
            @Override
            public void viewClick(View v) {
                Toast.makeText(CateCardViewActivity.this, "You click CategoryCardView", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void lunchCateCardViewActivity(Context ctx) {
        Intent intent = new Intent(ctx, CateCardViewActivity.class);
        ctx.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
