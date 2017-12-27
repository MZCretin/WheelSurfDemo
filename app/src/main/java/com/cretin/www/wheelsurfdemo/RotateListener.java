package com.cretin.www.wheelsurfdemo;

import android.animation.ValueAnimator;
import android.widget.ImageView;

/**
 * Created by cretin on 2017/12/27.
 * 用于回调动画过程和结果
 */

public interface RotateListener {
    //动画结束
    void rotateEnd(int position, String des);

    //动画进行中
    void rotating(ValueAnimator valueAnimator);

    //点击了按钮 但是没有旋转 调用者可以在这里处理一些逻辑 比如弹出对话框确定用户是否要抽奖
    void rotateBefore(ImageView goImg);
}
