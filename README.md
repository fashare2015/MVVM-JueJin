# 高仿掘金App —— 基于 databinding
## 1. 项目初衷
>不同于前端 vue、 react 的火热, 移动端的 databinding 好像不受待见。鉴于 vue、 react 都有各自成熟的生态圈，我希望通过这个项目打磨出一个简单易用的 `databinding 组件库`。

### 1.1 data -> view，舍弃 Adapter
之前有不少前辈专门针对 RecyclerView 做了各自的封装，完全省去了 Adapter, 比如：
- [MVVMLight](https://github.com/Kelin-Hong/MVVMLight)
- [binding-collection-adapter](https://github.com/evant/binding-collection-adapter)

### 1.2 更进一步：view -> data -> view
在本项目中，你将会看到一个带有 `下拉刷新` + `上拉加载` 的页面如何简化到`10+行java代码` ! see [NotifyVM.kt](https://github.com/fashare2015/MVVM-JueJin/blob/master/app/src/main/kotlin/com/fashare/mvvm_juejin/viewmodel/NotifyListVM.kt)

## 2. 模块概览
> 接口全抓自掘金app, 支持登录、注册（走的官方接口，并非假数据哦~）

- 已完成:
    - 登录、注册：可以用自己的掘金帐号登录，或者临时注册一个
    - 首页：热门推荐及文章列表，以及各个分类页面（Android、前端、产品。。。）
    - 发现：一级页面，包括 banner、活动、沸点、热门文章
    - 消息：完成消息列表
    - 我的：一级页面，包括登录与未登录两个状态
    - 文章详情页面: 文章 html 以及 下方的评论列表
- TODO:
    - splash 页
    - 第三方登录
    - 发现页 - 搜索模块、活动、沸点的二级页面
    - 我的 - 个人信息页，包括从用户头像跳转
    - 我的 - 喜欢、收藏、设置等二级页面
    - 发布文章页
    - 收藏、评论、分享等其他功能
    - 夜间模式
    - ...

## 3. 效果图
![首页](https://github.com/fashare2015/MVVM-JueJin/blob/master/screen-record/home.gif)
![文章详情](https://github.com/fashare2015/MVVM-JueJin/blob/master/screen-record/article.gif)

![登录](https://github.com/fashare2015/MVVM-JueJin/blob/master/screen-record/login.gif)
![其他](https://github.com/fashare2015/MVVM-JueJin/blob/master/screen-record/other.gif)

## 4. 技术栈
- databinding
- kotlin
- rxJava + rxAndroid
- retrofit + okhttp
- glide

## 5. 关于 "10+行" 实现的分页列表
我们来看第3个tab - 消息列表： see [NotifyVM.kt](https://github.com/fashare2015/MVVM-JueJin/blob/master/app/src/main/kotlin/com/fashare/mvvm_juejin/viewmodel/NotifyListVM.kt)

![消息](https://github.com/fashare2015/MVVM-JueJin/blob/master/screen-record/notify.png)

```kotlin
// NotifyListVM.kt
@ResHolder(R.layout.item_notify_list)                               // item 布局
@HeaderResHolder(R.layout.header_notify)                            // header 布局
class NotifyListVM : TwoWayListVM<NotifyBean>() {
    override val loadTask = { lastItem: NotifyBean? ->              // 网络请求（refresh、loadMore 二合一）
        ApiFactory.getApi(JueJinApis.Notify:: class.java)
            .getUserNotification(lastItem?.createdAtString?: "")
            .compose(Composers.handleError())
    }
    override val onItemClick = ArticleActivity.START_FROM_NOTIFY    // 点击事件
    override val headerData = Any()
}
```

框架中封装了`TwoWayListVM`，我们的`NotifyListVM`继承与它，并在布局中与`RecyclerView`绑定在一起。
重点来了:

1. view(pullToRefresh) -> data(list): 当view有动作(下拉刷新 or 上拉加载)，框架会自行调用`loadTask`，然后更新`TwoWayListVM.data`
2. data(list) -> view(RecyclerView): 而当`TwoWayListVM.data`发生变化，会自动触发`RecyclerView`刷新。

然后，配合 kotlin 简洁的语法，我们实现了`也许是史上最简洁???`的分页列表。

## 6. 项目持续打磨中，有兴趣给个star~

## 7. 参考
[Android DataBinding 数据绑定 —— QQ音乐技术团队](https://mp.weixin.qq.com/s?__biz=MzI1NjEwMTM4OA==&mid=2651232170&idx=1&sn=f4d7eb8f35ebf3b13696562ca3172bac&chksm=f1d9eac9c6ae63df357c3a96aa0218b5d66237c5411de5b34cd24ddb7a1d258b34444966d8c6&scene=0#rd)

[官方文档](https://developer.android.com/topic/libraries/data-binding/index.html)

[完全掌握Android Data Binding](http://jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0603/2992.html)

[MVVMLight](https://github.com/Kelin-Hong/MVVMLight)
