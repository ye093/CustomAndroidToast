## 两种自定义Toast的方式

### 第一种通过自定义PopupWindow实现

### 第二种通过WindowManager添加一个自定义View完成，属于高仿的系统Toast

#### 第一种在Dialog之上弹Toast会被背景蒙层遮住，虽然可以通过传递Dialog的VIEW实现在最顶层显示，但显示位置有影响，特别是有EditText在Dialog

#### 第二种方式属于高仿型，适用于任何场景。