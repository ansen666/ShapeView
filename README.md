# ShapeView
使用自定义属性替代项目中的shape文件，可以给View设置背景色、弧度、背景渐变、边框、边框颜色(可以单独指定4条边)、渐变方向、按下效果等

### Jcenter在线依赖（默认）不再更新
```
implementation 'com.ansen.shape:shape:1.3.1'
```

### JitPack在线依赖（因为Jcenter不在支持开发者更新项目）
在你的项目跟目录的build.gradle文件的增加依赖
```
allprojects {
        repositories {
                ...
                maven { url 'https://jitpack.io' }
        }
}
```

使用跟Jcenter在线依赖一样的,在moudle里面依赖
```
implementation 'com.github.ansen666:ShapeView:1.3.2'
```


### 属性使用
```
app:solid_color="#FF00FF" 填充颜色
app:corners_radius="5dp"  弧度

app:stroke_color="#00ff00" 边框颜色
app:stroke_width="2dp"  边框宽度
app:stroke_direction="left|top|right|bottom" 需要显示的边框方向 默认全边框（不支持选中状态）

如果是虚线边框增加以下两个属性:
app:dash_width="4dp" 虚线边框的长度
app:dash_gap="2dp" 虚线边框两个线之间的间隙

app:top_left_radius="15dp" 左上弧度
app:top_right_radius="15dp" 右上弧度
app:bottom_left_radius="15dp" 左下弧度
app:bottom_right_radius="15dp" 右下弧度
app:shape_view="oval" View形状

app:color_orientation="top_bottom" 颜色渐变色方向(背景/边框/文字)
app:start_color="#5BC9FF" 填充渐变色开始
app:center_color="#FF00FF" 填充渐变色中间
app:end_color="#4669F6" 填充渐变色结束

按压渐变色
app:pressed_start_color="#983458"
app:pressed_center_color="#98633F"
app:pressed_end_color="#98633F"
app:pressed_solid_color="#FFDEE0E2" 按压填充颜色

选中属性
app:select_solid_color="#FFFFFFFF" 选中填充颜色
app:select_start_color="#EB89FF" 渐变色选中开始色
app:select_center_color="#FCCE5F" 渐变色选中中间色
app:select_end_color="#FCCE5F" 渐变色选中结束色
app:select_stroke_color="#ffbababa" 选中情况边框色
app:select_stroke_width="0.5dp" 选中情况边框宽度

TextView/EditView控件独有属性
app:text="点击我切换选中效果"
app:select_text="选中时显示的文字"

app:text_color="#FFFFFFFF" 文本未选中颜色
app:select_text_color="#BBBBBB" 文本选中颜色

app:text_size="10sp" 字体大小
app:select_text_size="20sp" 选中字体大小

app:border_gradient="true/false" 边框渐变
app:text_gradient="true/false" 文字渐变

app:unselect_drawable="@mipmap/icon_sex_select_male" 图片未选中
app:select_drawable="@mipmap/icon_sex_select_woman" 图片选中
app:drawable_direction="right" 图片显示TextView方向，left：左边 top：上面 right：右边 bottom：底部

app:selected_reset_background="true" 设置选中/未选中状态时是否更新背景 默认为true

图片独有属性
app:scale_type="top/center/bottom/fitXY" 显示类型
app:stroke_space="0.5dp" 描边与图片间距

AnsenPriorityLinearLayout控件才有的属性:
app:layout_priority="incompressible" 提高当前View在LinearLayout中的权重，保证内容不被压缩
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
![shape](https://raw.githubusercontent.com/ansen666/TestShape/master/shape_prev.gif)
