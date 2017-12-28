package com.cretin.www.wheelsruflibrary.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cretin.www.wheelsruflibrary.R;
import com.cretin.www.wheelsruflibrary.listener.RotateListener;

/**
 * Created by cretin on 2017/12/26.
 */

public class WheelSurfView extends RelativeLayout {
    //当前的圆盘VIew
    private WheelSurfPanView mWheelSurfPanView;
    //Context
    private Context mContext;
    //开始按钮
    private ImageView mStart;
    //动画回调监听
    private RotateListener rotateListener;

    public void setRotateListener(RotateListener rotateListener) {
        mWheelSurfPanView.setRotateListener(rotateListener);
        this.rotateListener = rotateListener;
    }

    public WheelSurfView(Context context) {
        super(context);
        init(context, null);
    }

    public WheelSurfView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public WheelSurfView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    //开始抽奖的图标
    private Integer mGoImgRes;

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        if ( attrs != null ) {
            //获得这个控件对应的属性。
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.wheelSurfView);
            try {
                mGoImgRes = typedArray.getResourceId(R.styleable.wheelSurfView_goImg, 0);
            } finally { //回收这个对象
                typedArray.recycle();
            }
        }

        //添加圆盘视图
        mWheelSurfPanView = new WheelSurfPanView(mContext, attrs);
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        mWheelSurfPanView.setLayoutParams(layoutParams);
        addView(mWheelSurfPanView);

        //添加开始按钮
        mStart = new ImageView(mContext);
        //如果用户没有设置自定义的图标就使用默认的
        if ( mGoImgRes == 0 ) {
            mStart.setImageResource(R.mipmap.node);
        } else {
            mStart.setImageResource(mGoImgRes);
        }
        //给图片设置LayoutParams
        RelativeLayout.LayoutParams llStart =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llStart.addRule(RelativeLayout.CENTER_IN_PARENT);
        mStart.setLayoutParams(llStart);
        addView(mStart);

        mStart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用此方法是将主动权交个调用者 由调用者调用开始旋转的方法
                rotateListener.rotateBefore(( ImageView ) v);
            }
        });
    }

    /**
     * 开始旋转
     *
     * @param pisition 旋转最终的位置 注意 从1 开始 而且是逆时针递增
     */
    public void startRotate(int pisition) {
        if ( mWheelSurfPanView != null ) {
            mWheelSurfPanView.startRotate(pisition);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //视图是个正方形的 所以有宽就足够了 默认值是500 也就是WRAP_CONTENT的时候
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
        // Children are just made to fill our space.
        final int childWidthSize = getMeasuredWidth();
        //高度和宽度一样
        heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY);

        //onMeasure调用获取到当前视图大小之后，
        // 手动按照一定的比例计算出中间开始按钮的大小，
        // 再设置给那个按钮，免得造成用户传的图片不合适之后显示贼难看
        // 只设置一次
        if ( isFirst ) {
            isFirst = !isFirst;
            //获取中间按钮的大小
            ViewTreeObserver vto = mStart.getViewTreeObserver();
            vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @TargetApi( Build.VERSION_CODES.KITKAT )
                @Override
                public void onGlobalLayout() {
                    mStart.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    float w = mStart.getMeasuredWidth();
                    float h = mStart.getMeasuredHeight();
                    //计算新的大小 默认为整个大小最大值的0.17 至于为什么是0.17  我只想说我乐意。。。。
                    int newW = ( int ) ((( float ) childWidthSize) * 0.17);
                    int newH = ( int ) ((( float ) childWidthSize) * 0.17 * h / w);
                    ViewGroup.LayoutParams layoutParams = mStart.getLayoutParams();
                    layoutParams.width = newW;
                    layoutParams.height = newH;
                    mStart.setLayoutParams(layoutParams);
                }
            });
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //记录当前是否是第一次回调onMeasure
    private boolean isFirst = true;
}
