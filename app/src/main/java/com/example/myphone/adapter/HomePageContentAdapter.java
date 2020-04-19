package com.example.myphone.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.myphone.R;
import com.example.myphone.model.domain.Commodity;
import com.example.myphone.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页商品信息RecyclerView组件适配器
 */
public class HomePageContentAdapter extends RecyclerView.Adapter<HomePageContentAdapter.InnerHolder> {

    List<Commodity.DataBean> commodities =new ArrayList<>();

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_pager_content, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        Commodity.DataBean dataBean = commodities.get(position);
        holder.setData(dataBean);
    }

    @Override
    public int getItemCount() {
        return commodities.size();
    }

    public void setCommodities(List<Commodity.DataBean> commodities) {
        this.commodities.clear();
        this.commodities.addAll(commodities);
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        TextView goods_title;
        TextView price;
        TextView category;
        ImageView goods_cover;
        Commodity.DataBean dataBean=new Commodity.DataBean();

        public InnerHolder(@NonNull final View itemView) {
            super(itemView);
            goods_title=itemView.findViewById(R.id.goods_title);
            price=itemView.findViewById(R.id.price);
            category=itemView.findViewById(R.id.category);
            goods_cover=itemView.findViewById(R.id.goods_cover);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("点击商品-->",dataBean.getCommodityName());
                }
            });
        }

        public void setData(Commodity.DataBean dataBean) {
            this.dataBean = dataBean;
            goods_title.setText(dataBean.getCommodityName());
            price.setText(String.valueOf(dataBean.getPrice()));
            category.setText(dataBean.getCategoryName());
            Glide.with(itemView.getContext()).load(Constants.KEY_HOME_PAGER_PREFIX+dataBean.getPicture()).into(goods_cover);
        }

    }
}
