package com.example.biruk.androidclientchat.app.Dialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.biruk.androidclientchat.R;
import com.example.biruk.androidclientchat.model.SearchItem;

import java.util.List;

public class SimpleSearchAdapter extends RecyclerView.Adapter<SimpleSearchAdapter.ViewHolder>{


    public Context mContext;
    public List<SearchItem> searchItems;
    public SimpleSearchAdapter.SearchItemListener listener;

    public SimpleSearchAdapter(Context mContext, List<SearchItem> searchItemList, SimpleSearchAdapter.SearchItemListener listener) {
        this.mContext = mContext;
        this.searchItems = searchItemList;
        this.listener = listener;
        Log.d("serachlist", "constructed "+searchItemList.size() );
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("onCreateViewHolder", "hm");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.serachitem_layout, null, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    public void setSearchItems(List<SearchItem> searchItems){
        this.searchItems = searchItems;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        SearchItem searchItem = searchItems.get(position);
        holder.text.setText(""+searchItem.getName());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                listener.onSearchItemClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TextView text;
        RelativeLayout relativeLayout;
        ImageView imageView;

        ViewHolder(View view) {
            super(view);
            this.text = (TextView) view.findViewById(R.id.search_name);
            this.relativeLayout = (RelativeLayout) view.findViewById(R.id.search_item_container);
            view.setOnLongClickListener(this);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
//            this.imageView = (ImageView) view.findViewById(R.id.search_avatar);
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }

    public interface SearchItemListener {
        void onSearchItemClicked(int position);

    }
}
