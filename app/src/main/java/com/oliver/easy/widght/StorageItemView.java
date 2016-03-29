package com.oliver.easy.widght;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oliver.easy.R;


/**
 * Created by wangdongdong on 16-3-28.
 */
public class StorageItemView extends RelativeLayout implements View.OnClickListener {

    private String mTextTitle;
    private int mTextTitleColor;
    private float mTextTitleSize;

    private String mMemUsed;
    private int mMemUsedColor;
    private float mMemUsedSize;

    private String mMemTotal;
    private int mMemTotalColor;
    private float mMemTotalSize;

    private Drawable mIconSource;
    private Drawable mBackground;
    private int mProgress;

    private RelativeLayout mRoot;
    private TextView mTitle;
    private TextView mMemoryUsed;
    private TextView mMemoryTotal;
    private TextView mSymbolMiddle;
    private ImageView mIcon;
    private ProgressBar mMemStateProgressBar;

    private OnSelectListener mListener;

    public StorageItemView(Context context) {
        this(context, null);
    }

    public StorageItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StorageItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StorageItemView);
        mTextTitle = typedArray.getString(R.styleable.StorageItemView_sivTitle);
        mTextTitleColor = typedArray.getInt(R.styleable.StorageItemView_sivTitleColor, 0);
        mTextTitleSize = typedArray.getDimension(R.styleable.StorageItemView_sivTitleSize, 0);

        mMemUsed = typedArray.getString(R.styleable.StorageItemView_sivMemUsed);
        mMemUsedColor = typedArray.getInt(R.styleable.StorageItemView_sivMemUsedColor, 0);
        mMemUsedSize = typedArray.getDimension(R.styleable.StorageItemView_sivMemUsedSize, 0);

        mMemTotal = typedArray.getString(R.styleable.StorageItemView_sivMemTotal);
        mMemTotalColor = typedArray.getInt(R.styleable.StorageItemView_sivMemTotalColor, 0);
        mMemTotalSize = typedArray.getDimension(R.styleable.StorageItemView_sivMemTotalSize, 0);

        mIconSource = typedArray.getDrawable(R.styleable.StorageItemView_sivIconSource);
        mBackground = typedArray.getDrawable(R.styleable.StorageItemView_sivBackground);
        mProgress = typedArray.getInt(R.styleable.StorageItemView_sivProgress, 0);
        typedArray.recycle();
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.storage_item_view, this, true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mRoot = (RelativeLayout) findViewById(R.id.root);
        mTitle = (TextView) findViewById(R.id.title);
        mMemoryUsed = (TextView) findViewById(R.id.memory_used);
        mSymbolMiddle = (TextView) findViewById(R.id.symbol_middle);
        mMemoryTotal = (TextView) findViewById(R.id.memory_total);
        mIcon = (ImageView) findViewById(R.id.icon);
        mMemStateProgressBar = (ProgressBar) findViewById(R.id.pb_storage_memory_state);

        if (mBackground != null) mRoot.setBackground(mBackground);
        setTitle(mTextTitle);
        if (mTextTitleColor != 0) setTitleColor(mTextTitleColor);
        setTitleSize(mTextTitleSize);

        setMemUsed(mMemUsed);
//        if (mMemUsedColor != 0) setMemUsedColor(mMemUsedColor);
//        setMemUsedSize(mMemUsedSize);

        setMemTotal(mMemTotal);
        if (mMemTotalColor != 0) setMemTotalColor(mMemTotalColor);
        setMemTotalSize(mMemTotalSize);

        setIconSource(mIconSource);
        setBackground(mBackground);
        setProgress(mProgress);

        mRoot.setOnClickListener(this);
        setOnClickListener(this);
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setTitleColor(int resourceId) {
        mTitle.setTextColor(resourceId);
    }

    public void setTitleSize(float size) {
        mTitle.setTextSize(size);
    }


    public void setMemUsed(String title) {
        mMemoryUsed.setText(title);
    }

    public void setMemTotal(String title) {
        mMemoryTotal.setText(title);
    }

    public void setMemTotalColor(int resourceId) {
        mSymbolMiddle.setTextColor(resourceId);
        mMemoryUsed.setTextColor(resourceId);
        mMemoryTotal.setTextColor(resourceId);
    }

    public void setMemTotalSize(float size) {
        mSymbolMiddle.setTextSize(size);
        mMemoryUsed.setTextSize(size);
        mMemoryTotal.setTextSize(size);
    }


    public void setIconSource(Drawable drawable) {
        Log.d("seticonSource", "drawable = " + drawable);
        if (drawable != null) mIcon.setImageDrawable(drawable);
    }

    public void setBackground(Drawable drawable) {
        if (drawable != null) mRoot.setBackground(drawable);
    }

    public void setProgress(int progress) {
        mMemStateProgressBar.setProgress(progress);
    }

    public void setProgressDrawable(Drawable drawable) {
        mMemStateProgressBar.setProgressDrawable(drawable);
    }

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            mListener.viewClick(view);
        }
    }

    public void setOnViewClickListener(OnSelectListener listener) {
        this.mListener = listener;
    }

    public interface OnSelectListener {
        void viewClick(View v);
    }
}
