package com.cretin.www.wheelsurfdemo.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.cretin.www.wheelsurfdemo.R;
import com.cretin.www.wheelsurfdemo.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by cretin on 2017/12/26.
 */

public class WheelSurfPanView extends View {
    private Context mContext;
    //记录视图的大小
    private int mWidth;
    //记录当前有几个分类
    private int mTypeNum = 7;
    //画笔
    private Paint mPaint;
    //文字画笔
    private Paint mTextPaint;
    //圆环图片
    private Bitmap mYuanHuan;
    //背景颜色
    private int[] mColors = new int[]{
            Color.parseColor("#fef9f7"),
            Color.parseColor("#fbc6a9"),
            Color.parseColor("#ffdecc"),
            Color.parseColor("#fbc6a9"),
            Color.parseColor("#ffdecc"),
            Color.parseColor("#fbc6a9"),
            Color.parseColor("#ffdecc")};

    //文字描述
    private String[] des = new String[]{
            "王 者 皮 肤", "1 8 0 积 分", "L O L 皮 肤", "谢 谢 参 与", "2 8 积 分", "微 信 红 包", "5 Q 币"
    };

    //旋转一圈所需要的时间
    private static final long ONE_WHEEL_TIME = 500;

    //中心点横坐标
    private int mCenter;
    //绘制扇形的半径 减掉50是为了防止边界溢出  具体效果你自己注释掉-50自己测试
    private int mRadius;
    //每一个扇形的角度
    private float mAngle;
    //测试Bitmap
    private Bitmap mTestBitmap;

    private List<Bitmap> mListBitmap;

    public WheelSurfPanView(Context context) {
        super(context);
        init(context);
    }

    public WheelSurfPanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WheelSurfPanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        setBackgroundColor(Color.TRANSPARENT);
        //每一个扇形的角度
        mAngle = ( float ) (360.0 / mTypeNum);
        mYuanHuan = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.yuanhuan);
        mTestBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.liwu);

        mListBitmap = new ArrayList<>();

        int ww = mTestBitmap.getWidth();
        int hh = mTestBitmap.getHeight();
        for ( int i = 0; i < mTypeNum; i++ ) {
            // 定义矩阵对象
            Matrix matrix = new Matrix();
            // 缩放原图
            matrix.postScale(1f, 1f);
            // 向左旋转45度，参数为正则向右旋转
            matrix.postRotate(mAngle * i);
            //bmp.getWidth(), 500分别表示重绘后的位图宽高
            Bitmap dstbmp = Bitmap.createBitmap(mTestBitmap, 0, 0, ww, hh,
                    matrix, true);
            mListBitmap.add(dstbmp);
        }

        //其他画笔
        mPaint = new Paint();
        //设置填充样式
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        //设置抗锯齿
        mPaint.setAntiAlias(true);
        //设置边界模糊
        mPaint.setDither(true);
        //设置画笔颜色
        mPaint.setColor(Color.YELLOW);

        //文字画笔
        mTextPaint = new Paint();
        //设置填充样式
        mTextPaint.setStyle(Paint.Style.STROKE);
        //设置抗锯齿
        mTextPaint.setAntiAlias(true);
        //设置边界模糊
        mTextPaint.setDither(true);
        //设置画笔颜色
        mTextPaint.setColor(Color.parseColor("#e30000"));
        //设置字体大小
        mTextPaint.setTextSize(Util.dip2px(mContext, 14));
    }


    /**
     * 开始转动
     *
     * @param pos 如果 pos = -1 则随机，如果指定某个值，则转到某个指定区域
     */
    public void startRotate(int pos) {
        //最低圈数是3圈
        int quanshu = new Random().nextInt(2);

        int rotation = ( int ) (new Random().nextInt(360) + 720 + currAngle);
        ObjectAnimator anim = ObjectAnimator.ofFloat(this, "rotation", currAngle, rotation);
        currAngle = rotation;
        // 动画的持续时间，执行多久？
        anim.setDuration(3000);

        // 正式开始启动执行动画
        anim.start();
    }

    //目前的角度
    private float currAngle = 0;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //视图是个正方形的 所以有宽就足够了 默认值是500 也就是WRAP_CONTENT的时候
        int desiredWidth = 800;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int width;

        //Measure Width
        if ( widthMode == MeasureSpec.EXACTLY ) {
            //Must be this size
            width = widthSize;
        } else if ( widthMode == MeasureSpec.AT_MOST ) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //将测得的宽度保存起来
        mWidth = width;

        mCenter = mWidth / 2;
        //绘制扇形的半径 减掉50是为了防止边界溢出  具体效果你自己注释掉-50自己测试
        mRadius = mWidth / 2 - 50;

        //MUST CALL THIS
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //从最上面开始绘制扇形会好看一点
        float startAngle = -mAngle / 2 - 90;

        for ( int i = 0; i < mTypeNum; i++ ) {
            mPaint.setColor(mColors[i]);
            //画一个扇形
            RectF rect = new RectF(mCenter - mRadius, mCenter - mRadius, mCenter
                    + mRadius, mCenter + mRadius);
            canvas.drawArc(rect, startAngle, mAngle, true, mPaint);
            drawText(startAngle, des[i], mRadius, mTextPaint, canvas);


            int ww = mListBitmap.get(i).getWidth();
            int hh = mListBitmap.get(i).getHeight();
            int imgWidth = mRadius / 4;
            int w = ( int ) (Math.abs(Math.cos(Math.toRadians(Math.abs(180 - mAngle * i)))) * imgWidth + imgWidth * Math.abs(Math.sin(Math.toRadians(Math.abs(180 - mAngle * i)))));
            int h = ( int ) (Math.abs(Math.sin(Math.toRadians(Math.abs(180 - mAngle * i)))) * imgWidth + imgWidth * Math.abs(Math.cos(Math.toRadians(Math.abs(180 - mAngle * i)))));
            Log.e("HHHHHHH", "" + ww + "   " + hh + "   " + w + "    " + h);

            float angle = ( float ) Math.toRadians(startAngle + mAngle / 2);
            final int paddingLeft = getPaddingLeft();
            final int paddingRight = getPaddingRight();
            final int paddingTop = getPaddingTop();
            final int paddingBottom = getPaddingBottom();
            int width = getWidth() - paddingLeft - paddingRight;
            int height = getHeight() - paddingTop - paddingBottom;
            //确定图片在圆弧中 中心点的位置
            float x = ( float ) (width / 2 + (mRadius / 2 + mRadius / 12) * Math.cos(angle));
            float y = ( float ) (height / 2 + (mRadius / 2 + mRadius / 12) * Math.sin(angle));
            // 确定绘制图片的位置
            RectF rect1 = new RectF(x - w / 2, y - h / 2, x + w / 2, y + h / 2);
            canvas.drawBitmap(mListBitmap.get(i), null, rect1, null);

            startAngle = startAngle + mAngle;
        }


        //最后绘制圆环
        Rect mDestRect = new Rect(0, 0, mWidth, mWidth);
        canvas.drawBitmap(mYuanHuan, null, mDestRect, mPaint);
    }

    private void drawText(float startAngle, String string, int mRadius, Paint mTextPaint, Canvas mCanvas) {
        Path circlePath = new Path();
        RectF rect = new RectF(mCenter - mRadius, mCenter - mRadius, mCenter
                + mRadius, mCenter + mRadius);
        circlePath.addArc(rect, startAngle, mAngle);
        float textWidth = mTextPaint.measureText(string);
        float hOffset = ( float ) (Math.sin(mAngle / 2 / 180 * Math.PI) * mRadius) - textWidth / 2;
        mCanvas.drawTextOnPath(string, circlePath, hOffset, mRadius / 4, mTextPaint);
    }
}
