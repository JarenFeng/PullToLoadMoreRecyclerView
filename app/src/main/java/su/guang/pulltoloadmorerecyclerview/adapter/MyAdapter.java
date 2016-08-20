package su.guang.pulltoloadmorerecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import su.guang.pulltoloadmorerecyclerview.R;


/**
 * Created by Jaren on 2016/8/19.
 */
public class MyAdapter extends CustomAdapter {

    private Context context;
    private List<String> data;

    public MyAdapter(Context context, List<String> data) {
        super(context, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if(!isFooterItem(position)){
            MyViewHolder tvViewHolder = (MyViewHolder) holder;
            tvViewHolder.tv.setText(data.get(position));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == NORMAL_ITEM){
            View itemView = null;
            itemView = LayoutInflater.from(context).inflate(R.layout.item_recyclerview,null);
            return new MyViewHolder(itemView);
        }

        return super.onCreateViewHolder(parent, viewType);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv;
        private ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv= (TextView) itemView.findViewById(R.id.tv);
            iv  = (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}
