package com.cretin.www.wheelsurfdemo;

import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.cretin.www.wheelsruflibrary.listener.RotateListener;
import com.cretin.www.wheelsruflibrary.view.WheelSurfView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //注意点：
        //1、在使用暴利模式的时候，分区个数请务必与你提供的底盘上的分区个数一致，
        //并且，请保证你提供的底图是圆的，背景是透明的，而且每个分区是均等分的
        //2、在使用自定义模式时：请保证文字描述，图标，背景颜色的集合长度是一样的，
        //并且长度与分类个数一致typenum
        //3、重要的事情说三遍
        //    typenum必传
        //    typenum必传
        //    typenum必传

        //获取第一个视图
        final WheelSurfView wheelSurfView = findViewById(R.id.wheelSurfView);
        //添加滚动监听
        wheelSurfView.setRotateListener(new RotateListener() {
            @Override
            public void rotateEnd(int position, String des) {
                Toast.makeText(MainActivity.this, "结束了 " + position + "   " + des, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rotating(ValueAnimator valueAnimator) {

            }

            @Override
            public void rotateBefore(ImageView goImg) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("温馨提示");
                builder.setMessage("确定要花费100积分抽奖？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //模拟位置
                        int position = new Random().nextInt(7) + 1;
                        wheelSurfView.startRotate(position);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();

            }
        });


        //获取第二个视图
        final WheelSurfView wheelSurfView1 = findViewById(R.id.wheelSurfView1);
        //添加滚动监听
        wheelSurfView1.setRotateListener(new RotateListener() {
            @Override
            public void rotateEnd(int position, String des) {
                Toast.makeText(MainActivity.this, "结束了 位置：" + position + "   描述：" + des, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rotating(ValueAnimator valueAnimator) {

            }

            @Override
            public void rotateBefore(ImageView goImg) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("温馨提示");
                builder.setMessage("确定要花费100积分抽奖？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //模拟位置
                        int position = new Random().nextInt(7) + 1;
                        wheelSurfView1.startRotate(position);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();

            }
        });
    }
}
