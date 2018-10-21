# LubinWidget

    几个Android开发小控件，便于快速开发，后续更新中
    整合其它控件库
    
[![](img/InputBox.gif)](https://github.com/JC0127/InputBoxLayout/blob/master/README.md)
[![](img/Tabbar.gif)](https://github.com/JC0127/BottomTabBar/blob/master/README.md)

* [![](https://jitpack.io/v/JC0127/LubinWidget.svg)](https://jitpack.io/#JC0127/LubinWidget)
 ![](https://img.shields.io/badge/author-Lubin-red.svg)

### 使用 
```markdown
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	dependencies {
    	        implementation 'com.github.JC0127:LubinWidget:0.5.2'
    	}
```

#### InputBoxLayout

xml 使用路径有所更改
```xml
    <com.lubin.widget.inputbox.InputBoxLayout
        android:id="@+id/box"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        app:box_account="4"
        app:box_height="40dp"
        app:box_margin="4dp"
        app:box_width="40dp" />
```

#### LubinBottomTabBar

xml 使用路径有所更改
```xml
    <com.lubin.widget.tabbar.LubinBottomTabBar
        android:id="@+id/tab_bar"
        android:layout_width="match_parent"
        android:layout_height="45dp"
        android:layout_alignParentBottom="true" />
```

### 详细使用请参考
* ![InputBoxLayout](https://github.com/JC0127/InputBoxLayout/blob/master/README.md)
* ![LubinBottomTabBar](https://github.com/JC0127/BottomTabBar/blob/master/README.md)
 
