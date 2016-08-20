package su.guang.pulltoloadmorerecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import su.guang.pulltoloadmorerecyclerview.R;


/**
 * Created by Jaren on 2016/8/17.
 */
public class CustomAdapter extends RecyclerView.Adapter {

    protected final int NORMAL_ITEM = 1;
    protected final int FOOTER_ITEM = 2;

    private List<?> data;
    private Context context;
    private View.OnClickListener clickListener;

    private TextView tvLoadMore;

    private boolean loadMoreClickEnabled = false;

    public CustomAdapter(Context context, List<?> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       if(viewType == FOOTER_ITEM){
           View itemView = LayoutInflater.from(context).inflate(R.layout.item_footerview,null);
            return new FooterViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(isFooterItem(position)){
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            tvLoadMore = footerViewHolder.tv;
            tvLoadMore.setOnClickListener(loadMoreClickEnabled?clickListener:null);
        }
    }

    @Override
    public int getItemCount() {
        return data.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(isFooterItem(position)){
            return FOOTER_ITEM;
        }else {
            return NORMAL_ITEM;
        }
    }

    public boolean isFooterItem(int position){
        return position == getItemCount()-1;
    }

    public void setLoadMoreText(String text){
        tvLoadMore.setText(text);
    }

    public final void setLoadMoreClickEnabled(boolean loadMoreClickEnabled) {
        this.loadMoreClickEnabled = loadMoreClickEnabled;
        tvLoadMore.setOnClickListener(loadMoreClickEnabled?clickListener:null);
    }

    public void setOnLoadMoreClickedListener(View.OnClickListener clickedListener){
        this.clickListener = clickedListener;
    }

    class FooterViewHolder extends RecyclerView.ViewHolder{

        private TextView tv;

        public FooterViewHolder(View itemView) {
            super(itemView);
            tv= (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
