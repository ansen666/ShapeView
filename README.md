# TestShape
使用自定义属性替代项目中的shape文件，可以给View设置背景色、弧度、背景渐变、边框、边框颜色、渐变方向等

### 在线依赖
```
implementation 'com.ansen.shape:shape:1.0.6'
```

### 属性使用
```
app:solid_color="#FF00FF" 填充颜色
app:select_solid_color="#FFFFFFFF" 选中填充颜色
app:pressed_solid_color="#FFDEE0E2" 按压填充颜色

app:corners_radius="5dp"  弧度

app:stroke_color="#00ff00" 边框颜色
app:select_stroke_color="#ffbababa" 选中情况边框色
app:stroke_width="2dp"  边框宽度
app:select_stroke_width="0.5dp" 选中情况边框宽度

app:top_left_radius="15dp" 左上弧度
app:top_right_radius="15dp" 右上弧度
app:bottom_left_radius="15dp" 左下弧度
app:bottom_right_radius="15dp" 右下弧度
app:shape_view="oval" View形状
app:color_orientation="top_bottom" 背景颜色渐变色方向

app:start_color="#5BC9FF" 填充渐变色开始
app:center_color="#FF00FF" 填充渐变色中间
app:end_color="#4669F6" 填充渐变色结束

app:select_start_color="#EB89FF" 渐变色选中开始色
app:select_center_color="#FCCE5F" 渐变色选中中间色
app:select_end_color="#FCCE5F" 渐变色选中结束色


 <!--   TextView/EditView控件独有属性     -->
app:text_color="#FFFFFFFF" 文本未选中颜色
app:select_text_color="#BBBBBB" 文本选中颜色

app:unselect_drawable="@mipmap/icon_sex_select_male" 图片未选中
app:select_drawable="@mipmap/icon_sex_select_woman" 图片选中
app:drawable_direction="right" 图片显示TextView方向，left：左边 top：上面 right：右边 bottom：底部

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
![shape](https://github.com/ansen666/TestShape/blob/master/shape.png?raw=true)
