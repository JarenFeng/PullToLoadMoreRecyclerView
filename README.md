PullToLoadMoreRecyclerView
==
1.简介
----
PullToLoadMoreRecyclerView主要用于下拉加载更多。
2.用法
--
在XML中
``` xml
<su.guang.pulltoloadmorerecyclerview.view.PullToLoadMoreRecyclerView
            android:id="@+id/recyclerview"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />
```
在Activity中
``` java
recyclerView.setOnLoadMoreListener(new PullToLoadMoreRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                
            }
        });
```
同时还支持设置不同状态下footer的提示文字，如下。
``` java
recyclerView.setLoadMoreText("上拉加载更多");
recyclerView.setLoadingMoreText("正在加载...");
recyclerView.setLoadMoreFailText("加载失败，请点击重试");
```
