package com.example.biruk.androidclientchat.app.Dialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.biruk.androidclientchat.R;
import com.example.biruk.androidclientchat.model.SearchItem;
import com.example.biruk.androidclientchat.model.StringConstants;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;

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

        Log.d("SerachITEM" , ""+searchItem.getName());
        Log.d("SerachITEM" , ""+searchItem.getAvatar());
        Log.d("SerachITEM" , ""+searchItem.getConvType());

        //        holder.searchAvatar.;
        ImageLoader imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                String ur = StringConstants.URL_BASIC+url;
                Log.d("URL PIC", ur);
                Picasso.with(mContext).load(ur).into(imageView);
            }
        };
        imageLoader.loadImage(holder.searchAvatar, searchItem.getAvatar());
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
        ImageView searchAvatar;

        ViewHolder(View view) {
            super(view);
            this.text = (TextView) view.findViewById(R.id.search_name);
            this.relativeLayout = (RelativeLayout) view.findViewById(R.id.search_item_container);
            view.setOnLongClickListener(this);
            this.searchAvatar = (ImageView) view.findViewById(R.id.search_avatar);
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
