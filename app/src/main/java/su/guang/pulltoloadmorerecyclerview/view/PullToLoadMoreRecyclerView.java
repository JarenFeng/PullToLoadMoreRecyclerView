package su.guang.pulltoloadmorerecyclerview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import su.guang.pulltoloadmorerecyclerview.adapter.CustomAdapter;

/**
 * Created by Jaren on 2016/8/17.
 */
public class PullToLoadMoreRecyclerView extends RecyclerView{

    private final String TAG  = "PullToLoadMoreRView";

    private OnLoadMoreListener loadMoreListener;
    private CustomAdapter mAdapter;

    private boolean isLoading = false;

    private String loadMoreText;
    private String loadingMoreText;
    private String loadMoreFailText;

    public PullToLoadMoreRecyclerView(Context context) {
        super(context);
    }

    public PullToLoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToLoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        int lastVisibleItem = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
        int totalItemCount = getLayoutManager().getItemCount();

        if(totalItemCount - lastVisibleItem <= 1){
            if(!isLoading){
                isLoading = true;
                loadMoreListener.onLoadMore();
            }
        }else {
            isLoading = false;
        }
       // Log.i(TAG,"lastVisibleItem="+lastVisibleItem+",totalItemCount="+totalItemCount);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if(adapter instanceof CustomAdapter){
            mAdapter = (CustomAdapter) adapter;
        }else {
            throw new IllegalArgumentException("Only support the CustomAdapter");
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener loadMoreListener){
        this.loadMoreListener = loadMoreListener;
    }

    public final void onLoadMoreFinish(boolean isSuccessed){
        if(isSuccessed){
            mAdapter.setLoadMoreText(loadMoreText);
            mAdapter.notifyDataSetChanged();
            mAdapter.setLoadMoreClickEnabled(false);
        }else {
            mAdapter.setLoadMoreText(loadMoreFailText);
            mAdapter.setOnLoadMoreClickedListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadMoreListener.onLoadMore();
                }
            });
            mAdapter.setLoadMoreClickEnabled(true);
        }
    }

    public void onLoadingMore(){
        mAdapter.setLoadMoreText(loadingMoreText);
    }

    public void setLoadMoreText(String loadMoreText) {
        this.loadMoreText = loadMoreText;
    }

    public void setLoadingMoreText(String loadingMoreText) {
        this.loadingMoreText = loadingMoreText;
    }

    public void setLoadMoreFailText(String loadMoreFailText) {
        this.loadMoreFailText = loadMoreFailText;
    }

    public interface OnLoadMoreListener{
        void onLoadMore();
    }
}
