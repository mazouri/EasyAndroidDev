package com.oliver.easy.testpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.oliver.easy.R;
import com.oliver.easy.widght.StorageGridLayout;
import com.oliver.easy.widght.StorageItemView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StorageGridLayoutActivity extends ActionBarActivity {

    @Bind(R.id.storage_grid_layout)
    StorageGridLayout mStorageGridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_grid_layout);
        ButterKnife.bind(this);

        setupView();
    }

    private void setupView() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (int i = 0; i < 5; i++) {
            StorageItemView storageItemViewI = (StorageItemView) inflater.inflate(R.layout.item_storage_view, null, false);

            final int count = i;
            mStorageGridLayout.addStorageItem(new StorageGridLayout.StorageItemParams(storageItemViewI,
                    new StorageGridLayout.OtherStorageInfo(StorageGridLayout.OtherStorageType.SDCARD.toString(), "SD卡" + i, i+"9.7G", i+"2.5G", i*10+50)), new StorageGridLayout.OnStorageGridItemClick() {
                @Override
                public void onItemClick(View v) {
                    Toast.makeText(StorageGridLayoutActivity.this, "you click item " + count, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public static void lunchStorageGridLayoutActivity(Context ctx) {
        Intent intent = new Intent(ctx, StorageGridLayoutActivity.class);
        ctx.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
