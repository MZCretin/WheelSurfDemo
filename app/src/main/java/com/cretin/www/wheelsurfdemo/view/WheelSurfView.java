package com.cretin.www.wheelsurfdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cretin.www.wheelsurfdemo.R;

/**
 * Created by cretin on 2017/12/26.
 */

public class WheelSurfView extends RelativeLayout {
    //记录当前的视图大小
    private int mWidth;
    //中心点横坐标
    private int mCenter;
    //绘制扇形的半径
    private int mRadius;
    //当前的圆盘VIew
    private WheelSurfPanView mWheelSurfPanView;
    //Context
    private Context mContext;
    //开始按钮
    private ImageView mStart;

    public WheelSurfView(Context context) {
        super(context);
        init(context);
    }

    public WheelSurfView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WheelSurfView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;

        //添加圆盘视图
        mWheelSurfPanView = new WheelSurfPanView(mContext);
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        mWheelSurfPanView.setLayoutParams(layoutParams);
        addView(mWheelSurfPanView);

        //添加开始按钮
        mStart = new ImageView(mContext);
        mStart.setImageResource(R.mipmap.node);
        RelativeLayout.LayoutParams llStart =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llStart.addRule(RelativeLayout.CENTER_IN_PARENT);
        mStart.setLayoutParams(llStart);
        addView(mStart);

        mStart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mWheelSurfPanView.startRotate(-1);
            }
        });
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //视图是个正方形的 所以有宽就足够了 默认值是500 也就是WRAP_CONTENT的时候
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));

        // Children are just made to fill our space.
        int childWidthSize = getMeasuredWidth();
        int childHeightSize = getMeasuredHeight();
        //高度和宽度一样
        heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
