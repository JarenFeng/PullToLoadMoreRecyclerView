package su.guang.pulltoloadmorerecyclerview;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import su.guang.pulltoloadmorerecyclerview.adapter.MyAdapter;
import su.guang.pulltoloadmorerecyclerview.view.PullToLoadMoreRecyclerView;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    PullToLoadMoreRecyclerView recyclerView;
    MyAdapter mAdapter;
    List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new RefreshTask().execute(1);
            }
        });

        recyclerView = (PullToLoadMoreRecyclerView) findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        for(int i = 0;i < 10;i++){
            data.add(i+"");
        }
        recyclerView.setOnLoadMoreListener(new PullToLoadMoreRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new LoadMoreTask().execute(1);
            }
        });
        mAdapter = new MyAdapter(this,data);
        recyclerView.setLoadMoreText("上拉加载更多");
        recyclerView.setLoadingMoreText("正在加载...");
        recyclerView.setLoadMoreFailText("加载失败，请点击重试");

        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    class LoadMoreTask extends AsyncTask{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            recyclerView.onLoadingMore();
        }

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int random = (int)(Math.random()*10);
            boolean b = random%2 == 0;
            if(b){
                int last = Integer.parseInt(data.get(data.size()-1));
                for(int i =0;i<10;i++){
                    data.add(last+i+1+"");
                }
            }
            return b;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            recyclerView.onLoadMoreFinish((boolean)o);
        }
    }

    class RefreshTask extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int random = (int)(Math.random()*10);
            boolean b = random%2 == 0;
            if(b){
                data.clear();
                for(int i =0;i<10;i++){
                    data.add(i+"");
                }
            }
            return b;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            String str = null;
            if((boolean)o){
                str = "刷新成功";
            }else {
                str = "刷新失败，请重试";
            }
            swipeRefreshLayout.setRefreshing(false);
            mAdapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
        }
    }
}
