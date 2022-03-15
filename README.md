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
    implementation 'com.github.eknow314:ImageTextView:1.0.0'
}
```

```text
<com.eknow.imagetext.ImageTextView
        android:id="@+id/itv_test"
        android:layout_width="88dp"
        android:layout_height="88dp"
        android:background="@drawable/pad_shape_device_func_bg"
        app:itv_imgRes="@drawable/pad_sel_dehumidifier_speed"
        app:itv_imgSize="44dp"
        app:itv_imgTintNormalColor="#8A8D94"
        app:itv_imgTintSelectedColor="#00ECC9"
        app:itv_imgTopMargin="8dp"
        app:itv_maskBackgroundRes="@drawable/pad_shape_lock_bg_corner_15"
        app:itv_maskCan="true"
        app:itv_maskRes="@drawable/ic_lock"
        app:itv_textHoldColor="#00ECC9"
        app:itv_textLines="2"
        app:itv_textNormalColor="#8A8D94"
        app:itv_textStr="this is button and text"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
        
```