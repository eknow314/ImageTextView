# ImageTextView


## 接入指引
最新版本：[![](https://jitpack.io/v/eknow314/ImageTextView.svg)](https://jitpack.io/#eknow314/ImageTextView)


### 依赖配置
```groovy
allprojects {
    repositories {
        maven { url 'https://www.jitpack.io' }
    }
}
```

```groovy
dependencies {
    implementation 'com.github.eknow314:ImageTextView:1.0.4'
}
```

### 基本用法
```text
<com.eknow.imagetext.ImageTextView
        android:id="@+id/itv_test"
        android:layout_width="80dp"
        android:layout_height="80dp"
        android:background="@drawable/pad_shape_device_func_bg"
        app:itv_imgRes="@drawable/ic_icon_mode"
        app:itv_imgSize="24dp"
        app:itv_imgTintNormalColor="#8A8D94"
        app:itv_imgTintSelectedColor="#00ECC9"
        app:itv_imgTopMargin="18dp"
        app:itv_maskBackgroundRes="@drawable/pad_shape_lock_bg_corner_15"
        app:itv_maskCan="true"
        app:itv_maskRes="@drawable/ic_lock"
        app:itv_textGravity="center_horizontal"
        app:itv_textHoldColor="#00ECC9"
        app:itv_textLines="2"
        app:itv_textNormalColor="#8A8D94"
        app:itv_textSize="12sp"
        app:itv_textStr="this is button"
        app:itv_textTopMargin="2dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
```

### 设置
```text
        // 设置控件选中状态
        itvTest?.setEnabledNoMask(true)
        
        // 设置控件可用状态
        itvTest?.setEnabled(true)
        
        // 设置遮罩可用状态
        itvTest?.setMaskEnable(true)
        
        // 设置控件可用状态，但不显示遮罩
        itvTest?.setEnabledNoMask(true)

```
### 全部属性
```text
        <!--图标资源文件-->
        <attr name="itv_imgRes" format="reference"/>
        <!--图标大小，正方形的，默认 24-->
        <attr name="itv_imgSize" format="dimension"/>
        <!--图标选中颜色，不设置就是资源文件的选中色-->
        <attr name="itv_imgTintSelectedColor" format="color|reference"/>
        <!--图标未选中颜色-->
        <attr name="itv_imgTintNormalColor" format="color|reference"/>
        <!--图标距离顶部高度-->
        <attr name="itv_imgTopMargin" format="dimension"/>
        <!--文本大小，默认 14-->
        <attr name="itv_textSize" format="dimension"/>
        <!--文本内容-->
        <attr name="itv_textStr" format="string|reference"/>
        <!--文本常规颜色，默认 Color.DKGRAY-->
        <attr name="itv_textNormalColor" format="color|reference"/>
        <!--文本高亮颜色，默认 Color.WHITE-->
        <attr name="itv_textHoldColor" format="color|reference"/>
        <!--文本行数-->
        <attr name="itv_textLines" format="integer"/>
        <!--文本最大行数，如果小于文本行数，则等于文本行数-->
        <attr name="itv_textMaxLines" format="integer"/>
        <!--文本显示位置，默认文本框内居中-->
        <attr name="itv_textGravity" format="enum">
            <enum name="center" value="0"/>
            <enum name="center_horizontal" value="1"/>
            <enum name="center_vertical" value="2"/>
        </attr>
        <!--文本和图标之间的距离-->
        <attr name="itv_textTopMargin" format="dimension"/>
        <!--遮罩层图标-->
        <attr name="itv_maskRes" format="reference"/>
        <!--遮罩层背景色-->
        <attr name="itv_maskBackgroundRes" format="reference"/>
        <!--遮罩是否可用，默认不可用-->
        <attr name="itv_maskCan" format="boolean"/>
```