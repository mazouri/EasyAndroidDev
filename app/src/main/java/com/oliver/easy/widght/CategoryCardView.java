package com.oliver.easy.widght;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oliver.easy.R;

/**
 * Created by wangdongdong on 16-3-23.
 */
public class CategoryCardView extends RelativeLayout implements View.OnClickListener {

    private String mTextTitle;
    private int mTextTitleColor;
    private float mTextTitleSize;

    private String mTextCount;
    private int mTextCountColor;
    private float mTextCountSize;

    private Drawable mIconSource;
    private Drawable mBackground;

    private RelativeLayout mRoot;
    private TextView mTitle;
    private TextView mBracketsLeft;
    private TextView mBracketsRight;
    private TextView mCount;
    private ImageView mIcon;

    private OnSelectListener mListener;

    public CategoryCardView(Context context) {
        this(context, null);
    }

    public CategoryCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CategoryCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CategoryCardView);
        mTextTitle = typedArray.getString(R.styleable.CategoryCardView_ccvTitle);
        mTextTitleColor = typedArray.getInt(R.styleable.CategoryCardView_ccvTitleColor, 0);
        mTextTitleSize = typedArray.getDimension(R.styleable.CategoryCardView_ccvTitleSize, 0);

        mTextCount = typedArray.getString(R.styleable.CategoryCardView_ccvCount);
        mTextCountColor = typedArray.getInt(R.styleable.CategoryCardView_ccvCountColor, 0);
        mTextCountSize = typedArray.getDimension(R.styleable.CategoryCardView_ccvCountSize, 0);

        mIconSource = typedArray.getDrawable(R.styleable.CategoryCardView_ccvIconSource);
        mBackground = typedArray.getDrawable(R.styleable.CategoryCardView_ccvBackground);
        typedArray.recycle();
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.category_card_view, this, true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mRoot = (RelativeLayout) findViewById(R.id.root);
        mTitle = (TextView) findViewById(R.id.title);
        mBracketsLeft = (TextView) findViewById(R.id.brackets_left);
        mCount = (TextView) findViewById(R.id.count);
        mBracketsRight = (TextView) findViewById(R.id.brackets_right);
        mIcon = (ImageView) findViewById(R.id.icon);

        if (mBackground != null) mRoot.setBackground(mBackground);
        setTitle(mTextTitle);
        if (mTextTitleColor != 0) setTitleColor(mTextTitleColor);
        setTitleSize(mTextTitleSize);

        setCount(mTextCount);
        if (mTextCountColor != 0) setCountColor(mTextCountColor);
        setCountSize(mTextCountSize);

        setmIconSource(mIconSource);
        setBackground(mBackground);

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


    public void setCount(String title) {
        mCount.setText(title);
    }

    public void setCountColor(int resourceId) {
        mCount.setTextColor(resourceId);
        mBracketsLeft.setTextColor(resourceId);
        mBracketsRight.setTextColor(resourceId);
    }

    public void setCountSize(float size) {
        mCount.setTextSize(size);
        mBracketsLeft.setTextSize(size);
        mBracketsRight.setTextSize(size);
    }


    public void setmIconSource(Drawable drawable) {
        mIcon.setImageDrawable(drawable);
    }

    public void setBackground(Drawable drawable) {
        mRoot.setBackground(drawable);
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
