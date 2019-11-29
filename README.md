# TestShape
使用自定义属性替代项目中的shape文件，可以给View设置背景色、弧度、背景渐变、边框、边框颜色、渐变方向等

### 在线依赖
```
implementation 'com.ansen.shape:shape:1.0.6'
```

### 属性使用
```
app:solid_color="#FF00FF" 填充颜色
app:corners_radius="5dp"  弧度
app:stroke_color="#00ff00" 边框颜色
app:stroke_width="2dp"  边框宽度
app:top_left_radius="15dp" 左上弧度
app:top_right_radius="15dp" 右上弧度
app:bottom_left_radius="15dp" 左下弧度
app:bottom_right_radius="15dp" 右下弧度
app:shape_view="oval" View形状
app:color_orientation="top_bottom" 背景颜色渐变色方向
app:start_color="#5BC9FF" 填充渐变色开始
app:center_color="#FF00FF" 填充渐变色中间
app:end_color="#4669F6" 填充渐变色结束
```

### 案例
首先需要在xml根布局控件增加一个属性:
```
xmlns:app="http://schemas.android.com/apk/res-auto"
```

如何使用?
```
<com.ansen.shape.AnsenTextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="10dp"
        android:padding="10dp"
        android:text="Hello World!"
        android:textColor="#FFFFFF"
        app:corners_radius="5dp"
        app:solid_color="#FF00FF"
        app:stroke_color="#00ff00"
        app:stroke_width="2dp" />
```



### 效果图
![shape](https://github.com/ansen666/TestShape/blob/master/shape.jpg?raw=true)
