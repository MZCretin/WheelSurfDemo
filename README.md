# WheelSurfLibrary

-------------------

### 特点概述

一、可以直接暴力使用，直接给一张底图和一个"开始旋转"的按钮就可以了，但是请保证你的底图是圆形的，背景是透明的，而且底图的扇形分区是均等份的，否则后果自负。

二、当然你也可以自定义一些东西：

   + 自定义文字描述
   + 自定义分区个数
   + 自定义每个扇形里面的图标展示
   + 自定义中间的"开始旋转"按钮的图片
   + 自定义旋转的最低圈数
   + 自定义每个扇形的旋转时间
   + 自定义每个扇形的背景颜色
   + 自定义圆环的图片
   + 。。。

三、注意点
   + 1、在使用暴利模式的时候，分区个数请务必与你提供的底盘上的分区个数一致，并且，请保证你提供的底图是圆的，背景是透明的，而且每个分区是均等分的
   + 2、在使用自定义模式时：请保证文字描述，图标，背景颜色的集合长度是一样的，并且长度与分类个数一致typenum
   + 3、重要的事情说三遍

            typenum必传
            typenum必传
            typenum必传
            
四、应用户要求，需要实现用代码设置这些属性，现在已实现(更新时间 2018年03月09日14:13:01)

   + 请注意：
            
            使用这种方式需要在引入布局文件的时候在布局文件中设置mTypeNums = -1 来告诉我你现在要用代码传入这些属性
            使用这种方式需要在引入布局文件的时候在布局文件中设置mTypeNums = -1 来告诉我你现在要用代码传入这些属性
            使用这种方式需要在引入布局文件的时候在布局文件中设置mTypeNums = -1 来告诉我你现在要用代码传入这些属性
    
   重要的事情说三遍
    
   例如
       
       <com.cretin.www.wheelsruflibrary.view.WheelSurfView
             android:id="@+id/wheelSurfView2"
             android:layout_width="match_parent"
             android:layout_height="match_parent"
             wheelSurfView:typenum="-1"
             android:layout_margin="20dp">
                  
   + 请注意：
   
           你在传入所有的图标文件之后需要调用 WheelSurfView.rotateBitmaps() 此方法来处理一下你传入的图片
           你在传入所有的图标文件之后需要调用 WheelSurfView.rotateBitmaps() 此方法来处理一下你传入的图片
           你在传入所有的图标文件之后需要调用 WheelSurfView.rotateBitmaps() 此方法来处理一下你传入的图片
   
   重要的事情说三遍
   
   然后调用setConfig()方法来设置你的属性
   
   + 请注意：
   
            .setmColors(colors)
            .setmDeses(des)
            .setmIcons(mListBitmap)
            这三个方法中的参数长度必须一致 否则会报运行时异常
   
   如果需要此功能，请使用最新的版本
   ```
        compile 'com.github.MZCretin:WheelSurfDemo:v1.1.0'
   ```
   
-------------------

### 效果预览

额，第二个那种底盘模式，我的图切的不圆，所以。。。

![效果图](https://github.com/MZCretin/WheelSurfDemo/blob/master/pic/ezgif.com-video-to-gif-3.gif)

-------------------

### 使用说明


-------------------

**Step 1.** Add the JitPack repository to your build file Add it in your root build.gradle at the end of repositories:
```gradle
allprojects { repositories { ... maven { url 'https://jitpack.io' } } }
```

**Step 2.** Add the dependency
```gradle
dependencies { compile 'com.github.MZCretin:WheelSurfDemo:v1.0.0' }
```

**Step 3.** Start using it wherever you want as below.

```
 <com.cretin.www.wheelsruflibrary.view.WheelSurfView
        android:id="@+id/wheelSurfView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_margin="20dp"
        wheelSurfView:colors="@array/colors"
        wheelSurfView:deses="@array/names"
        wheelSurfView:goImg="@mipmap/node"
        wheelSurfView:huanImg="@mipmap/yuanhuan"
        wheelSurfView:icons="@array/icons"
        wheelSurfView:mainImg="@mipmap/all"
        wheelSurfView:minTimes="3"
        wheelSurfView:textColor="#FF0000"
        wheelSurfView:textSize="16sp"
        wheelSurfView:type="1"
        wheelSurfView:typenum="7"
        wheelSurfView:vartime="75">

    </com.cretin.www.wheelsruflibrary.view.WheelSurfView>
```

### 方式一

+ 按照下面的方式引用自定义View

```xml
<com.cretin.www.wheelsruflibrary.view.WheelSurfView
            android:id="@+id/wheelSurfView"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_margin="20dp"
            wheelSurfView:mainImg="@mipmap/all"
            wheelSurfView:minTimes="3"
            wheelSurfView:type="2"
            wheelSurfView:typenum="7"
            wheelSurfView:vartime="75">

        </com.cretin.www.wheelsruflibrary.view.WheelSurfView>
```

说明：只需要配置

            wheelSurfView:minTimes="3" 最少转的圈数
            wheelSurfView:type="2" 引用类型 1 默认 正常 2 暴力只设置底盘模式
            wheelSurfView:typenum="7" 分区数量
            wheelSurfView:goImg="@mipmap/node" 开始按钮的图标
            wheelSurfView:vartime="75" 每一个扇形旋转的时间  毫秒


### 方式二

+ 按照下面的方式引用自定义View

```xml
<com.cretin.www.wheelsruflibrary.view.WheelSurfView
            android:id="@+id/wheelSurfView1"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_margin="20dp"
            wheelSurfView:colors="@array/colors"
            wheelSurfView:deses="@array/names"
            wheelSurfView:goImg="@mipmap/node"
            wheelSurfView:huanImg="@mipmap/yuanhuan"
            wheelSurfView:icons="@array/icons"
            wheelSurfView:minTimes="3"
            wheelSurfView:textColor="#FF0000"
            wheelSurfView:textSize="16sp"
            wheelSurfView:type="1"
            wheelSurfView:typenum="7"
            wheelSurfView:vartime="75">

        </com.cretin.www.wheelsruflibrary.view.WheelSurfView>
```

说明：只需要配置

            wheelSurfView:colors="@array/colors" 每个扇形背景颜色
            wheelSurfView:deses="@array/names" 每个扇形描述文字
            wheelSurfView:goImg="@mipmap/node" 开始按钮的图标
            wheelSurfView:huanImg="@mipmap/yuanhuan"  圆盘的图片资源
            wheelSurfView:icons="@array/icons" 每个扇形显示的图标
            wheelSurfView:minTimes="3" 最少转的圈数
            wheelSurfView:type="1" 引用类型 1 默认 正常 2 暴力只设置底盘模式
            wheelSurfView:typenum="7" 分区数量
            wheelSurfView:goImg="@mipmap/node" 开始按钮的图标
            wheelSurfView:vartime="75" 每一个扇形旋转的时间  毫秒
            wheelSurfView:textColor="#FF0000" 扇形上文字的颜色
            wheelSurfView:textSize="16sp" 扇形上文字的大小

----------------------------

请把Demo clone下来看更加具体的使用方式。

使用过程中如果有什么问题或者建议 欢迎在issue中提出来或者直接联系我 792075058 嘿嘿

PS:如果显示异常，欢迎移步 [Android撸一个转盘抽奖](http://blog.csdn.net/u010998327/article/details/78920351)
