package com.oliver.easy.widght;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oliver.easy.R;

/**
 * Created by wangdongdong on 16-3-22.
 */
public class MemoryStatusView extends LinearLayout {

    //标题
    private String mTitleName;  //名称
    private int mTitleNameColor;  //字体颜色
    private float mTitleNameSize;  //字体大小
    private int mTitleNameVisible;  //TextView可见性

    private String mMemUsedValue;
    private int mMemUsedValueColor;
    private float mMemUsedValueSize;

    private String mMemTotalValue;
    private int mMemTotalValueColor;
    private float mMemTotalValueSize;

    private int mSymbolColor;  //符号颜色
    private int mBracketsVisible; //括号可见性

    private TextView mTitle;
    private TextView mSymbolLeft;
    private TextView mMemUsed;
    private TextView mSymbolMiddle;
    private TextView mMemTotal;
    private TextView mSymbolRight;

    public MemoryStatusView(Context context) {
        this(context, null);
    }

    public MemoryStatusView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MemoryStatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MemoryStatusView);
        mTitleName = typedArray.getString(R.styleable.MemoryStatusView_titleName);
        mTitleNameColor = typedArray.getInt(R.styleable.MemoryStatusView_titleNameColor, 0);
        mTitleNameSize = typedArray.getDimension(R.styleable.MemoryStatusView_titleNameSize, 0);
        mTitleNameVisible = typedArray.getInt(R.styleable.MemoryStatusView_titleNameVisible, 0);

        mMemUsedValue = typedArray.getString(R.styleable.MemoryStatusView_memUsedValue);
        mMemUsedValueColor = typedArray.getInt(R.styleable.MemoryStatusView_memUsedValueColor, 0);
        mMemUsedValueSize = typedArray.getDimension(R.styleable.MemoryStatusView_memUsedValueSize, 0);

        mMemTotalValue = typedArray.getString(R.styleable.MemoryStatusView_memTotalValue);
        mMemTotalValueColor = typedArray.getInt(R.styleable.MemoryStatusView_memTotalValueColor, 0);
        mMemTotalValueSize = typedArray.getDimension(R.styleable.MemoryStatusView_memTotalValueSize, 0);

        mSymbolColor = typedArray.getInt(R.styleable.MemoryStatusView_symbolColor, 0);
        mBracketsVisible = typedArray.getInt(R.styleable.MemoryStatusView_bracketsVisible, 0);
        typedArray.recycle();
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.memory_status_view, this, true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTitle = (TextView) findViewById(R.id.title);
        mSymbolLeft = (TextView) findViewById(R.id.symbol_left);
        mMemUsed = (TextView) findViewById(R.id.memory_used);
        mSymbolMiddle = (TextView) findViewById(R.id.symbol_middle);
        mMemTotal = (TextView) findViewById(R.id.memory_total);
        mSymbolRight = (TextView) findViewById(R.id.symbol_right);

        setTitle(mTitleName);
        if (mTitleNameColor != 0) setTitleColor(mTitleNameColor);
        setTitleSize(mTitleNameSize);
        setTitleVisible(mTitleNameVisible);
        setMemUsed(mMemUsedValue);
        if (mMemUsedValueColor != 0) setMemUsedColor(mMemUsedValueColor);
        setMemUsedSize(mMemUsedValueSize);
        setMemTotal(mMemTotalValue);
        if (mMemTotalValueColor != 0) setMemTotalColor(mMemTotalValueColor);
        setMemTotalSize(mMemTotalValueSize);

        if (mSymbolColor != 0) setSymbolColor(mSymbolColor);
        setBracketsVisible(mBracketsVisible);

        setSymbolSize(mMemTotalValueSize);

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

    public void setTitleVisible(int visible) {
        switch (visible) {
            case 0x00:mTitle.setVisibility(VISIBLE);break;
            case 0x10:mTitle.setVisibility(GONE);break;
            case 0x20:mTitle.setVisibility(INVISIBLE);break;
        }
    }

    public void setMemUsed(String title) {
        mMemUsed.setText(title);
    }

    public void setMemUsedColor(int resourceId) {
        mMemUsed.setTextColor(resourceId);
    }

    public void setMemUsedSize(float size) {
        mMemUsed.setTextSize(size);
    }

    public void setMemTotal(String title) {
        mMemTotal.setText(title);
    }

    public void setMemTotalColor(int resourceId) {
        mMemTotal.setTextColor(resourceId);
    }

    public void setMemTotalSize(float size) {
        mMemTotal.setTextSize(size);
    }

    public void setSymbolColor(int resourceId) {
        mSymbolLeft.setTextColor(resourceId);
        mSymbolMiddle.setTextColor(resourceId);
        mSymbolRight.setTextColor(resourceId);
    }

    public void setSymbolSize(float symbolSize) {
        mSymbolLeft.setTextSize(symbolSize);
        mSymbolMiddle.setTextSize(symbolSize);
        mSymbolRight.setTextSize(symbolSize);
    }

    public void setBracketsVisible(int visible) {
        switch (visible) {
            case 0x00:mSymbolLeft.setVisibility(VISIBLE);mSymbolRight.setVisibility(VISIBLE);break;
            case 0x10:mSymbolLeft.setVisibility(GONE);mSymbolRight.setVisibility(GONE);break;
            case 0x20:mSymbolLeft.setVisibility(INVISIBLE);mSymbolRight.setVisibility(INVISIBLE);break;
        }
    }
}
