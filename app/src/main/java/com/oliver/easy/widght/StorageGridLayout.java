package com.oliver.easy.widght;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;

import com.oliver.easy.R;


/**
 * Created by wangdongdong on 16-3-28.
 */
public class StorageGridLayout extends GridLayout {

    //attrs
    private float mHorizontalSpacing;
    private float mVerticalSpacing;
    private float mItemWidth;
    private float mItemHeight;

    private int mItemMemColor;
    private float mItemMemSize;
    private int mItemTitleColor;
    private float mItemTitleSize;

    private Context mContext;

    public StorageGridLayout(Context context) {
        this(context, null);
    }

    public StorageGridLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StorageGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StorageGridLayout);
        mHorizontalSpacing = typedArray.getDimension(R.styleable.StorageGridLayout_sgHorizontalSpacing, 0);
        mVerticalSpacing = typedArray.getDimension(R.styleable.StorageGridLayout_sgVerticalSpacing, 0);
        mItemWidth = typedArray.getDimension(R.styleable.StorageGridLayout_sgItemWidth, 0);
        mItemHeight = typedArray.getDimension(R.styleable.StorageGridLayout_sgItemHeight, 0);

        mItemMemColor = typedArray.getInt(R.styleable.StorageGridLayout_sgItemMemColor, 0);
        mItemMemSize = typedArray.getDimension(R.styleable.StorageGridLayout_sgItemMemSize, 0);

        mItemTitleColor = typedArray.getInt(R.styleable.StorageGridLayout_sgItemTitleColor, 0);
        mItemTitleSize = typedArray.getDimension(R.styleable.StorageGridLayout_sgItemTitleSize, 0);

        typedArray.recycle();
    }

    public void addStorageItem(StorageItemParams storageItemParams, final OnStorageGridItemClick storageGridItemClick) {

        Log.d("storage_grid_view", storageItemParams.otherStorageInfo.progress + "");

        storageItemParams.storageItemView.setBackground(mContext.getDrawable(R.drawable.bg_file_other));
        storageItemParams.storageItemView.setTitle(storageItemParams.otherStorageInfo.name);
        storageItemParams.storageItemView.setTitleColor(mItemTitleColor);
        storageItemParams.storageItemView.setTitleSize(mItemTitleSize);
        storageItemParams.storageItemView.setMemUsed(storageItemParams.otherStorageInfo.used);
        storageItemParams.storageItemView.setMemTotal(storageItemParams.otherStorageInfo.total);
        storageItemParams.storageItemView.setMemTotalColor(mItemMemColor);
        storageItemParams.storageItemView.setMemTotalSize(mItemMemSize);

        storageItemParams.storageItemView.setProgress(storageItemParams.otherStorageInfo.progress);
        if (storageItemParams.otherStorageInfo.progress >= 90) {
            storageItemParams.storageItemView.setProgressDrawable(mContext.getResources().getDrawable(R.drawable.other_storage_item_pb_bg_red));
        } else {
            storageItemParams.storageItemView.setProgressDrawable(mContext.getResources().getDrawable(R.drawable.other_storage_item_pb_bg));
        }

        Log.d("storage_grid_view", storageItemParams.otherStorageInfo.type + " : " + getDrawableFromType(OtherStorageType.SDCARD));
        if (storageItemParams.otherStorageInfo.type.equals(OtherStorageType.SDCARD.toString())) {
            Log.d("storage_grid_view", getDrawableFromType(OtherStorageType.SDCARD) + "");
            storageItemParams.storageItemView.setIconSource(getDrawableFromType(OtherStorageType.SDCARD));
        } else if (storageItemParams.otherStorageInfo.type.equals(OtherStorageType.USB.toString())) {
            storageItemParams.storageItemView.setIconSource(getDrawableFromType(OtherStorageType.USB));
        } else if (storageItemParams.otherStorageInfo.type.equals(OtherStorageType.BLUETOOTH.toString()) ||
                storageItemParams.otherStorageInfo.type.equals(OtherStorageType.WIFI.toString())) {
            storageItemParams.storageItemView.setIconSource(getDrawableFromType(OtherStorageType.BLUETOOTH));
        }

        storageItemParams.storageItemView.setOnViewClickListener(new StorageItemView.OnSelectListener() {
            @Override
            public void viewClick(View v) {
                storageGridItemClick.onItemClick(v);
            }
        });

        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.setMargins(0, 0, (int) mHorizontalSpacing, (int) mVerticalSpacing);

        addView(storageItemParams.storageItemView, layoutParams);
    }

    private Drawable getDrawableFromType (OtherStorageType type) {
        switch (type) {
            case SDCARD:
                return mContext.getDrawable(R.drawable.ic_file_sd);
            case USB:
                return mContext.getDrawable(R.drawable.ic_file_usb);
            case BLUETOOTH:
            case WIFI:
                return mContext.getDrawable(R.drawable.ic_file_phone);
        }
        return mContext.getDrawable(R.drawable.ic_file_sd);
    }

    public static class StorageItemParams {

        public StorageItemView storageItemView;
        public OtherStorageInfo otherStorageInfo;

        public StorageItemParams(StorageItemView storageItemView, OtherStorageInfo otherStorageInfo) {
            this.storageItemView = storageItemView;
            this.otherStorageInfo = otherStorageInfo;
        }
    }

    public static class OtherStorageInfo {
        public String type; // OtherStorageType : sd,usb,BT,wifi
        public String name;
        public String total;
        public String used;
        public int progress;

        public OtherStorageInfo(String type, String name, String total, String used, int progress) {
            this.type = type;
            this.name = name;
            this.total = total;
            this.used = used;
            this.progress = progress;
        }
    }

    public enum OtherStorageType {
        SDCARD, USB, BLUETOOTH, WIFI
    }

    public interface OnStorageGridItemClick {
        void onItemClick(View view);
    }
}
