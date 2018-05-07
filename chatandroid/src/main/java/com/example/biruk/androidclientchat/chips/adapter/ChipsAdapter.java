package com.example.biruk.androidclientchat.chips.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import com.example.biruk.androidclientchat.R;
import com.example.biruk.androidclientchat.chips.CircleTransform;
import com.example.biruk.androidclientchat.chips.OnRemoveListener;
import com.example.biruk.androidclientchat.model.ChipsEntity;
import com.example.biruk.androidclientchat.model.StringConstants;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;

public class ChipsAdapter extends  RecyclerView.Adapter<ChipsAdapter.ViewHolder> {

    private List<ChipsEntity> chipsEntities;
    private OnRemoveListener onRemoveListener;
    private boolean isShowingPosition;

    public ChipsAdapter(List<ChipsEntity> chipsEntities, OnRemoveListener onRemoveListener) {
        this.chipsEntities = chipsEntities;
        this.onRemoveListener = onRemoveListener;
    }

    public ChipsAdapter(List<ChipsEntity> chipsEntities, OnRemoveListener onRemoveListener, boolean isShowingPosition) {
        this.chipsEntities = chipsEntities;
        this.onRemoveListener = onRemoveListener;
        this.isShowingPosition = isShowingPosition;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chip, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindItem(chipsEntities.get(position));
    }

    @Override
    public int getItemCount() {
        return chipsEntities.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDescription;
        private ImageView ivPhoto;
        private TextView tvName;
        private ImageButton ibClose;
        private TextView tvPosition;

        ViewHolder(View itemView) {
            super(itemView);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            ivPhoto = (ImageView) itemView.findViewById(R.id.ivPhoto);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            ibClose = (ImageButton) itemView.findViewById(R.id.ibClose);
            tvPosition = (TextView) itemView.findViewById(R.id.tvPosition);
        }

        void bindItem(ChipsEntity entity) {
            itemView.setTag(entity.getName());
            if (TextUtils.isEmpty(entity.getDescription())) {
                tvDescription.setVisibility(View.GONE);
            } else {
                tvDescription.setVisibility(View.VISIBLE);
                tvDescription.setText(entity.getDescription());
            }

            if (entity.getDrawableResId() != null) {
                ImageLoader imageLoader = new ImageLoader() {
                    @Override
                    public void loadImage(ImageView imageView, String url) {
                        String ur = StringConstants.URL_BASIC+url;
                        Log.d("URL Balem PIC", ur);
                        Glide.with(ivPhoto.getContext()).load(ur)
                                .transform(new CircleTransform(ivPhoto.getContext())).into(ivPhoto);
                    }
                };
                ivPhoto.setVisibility(View.VISIBLE);
                imageLoader.loadImage(ivPhoto, entity.getDrawableResId());
            } else {
                ivPhoto.setVisibility(View.GONE);
            }

            tvName.setText(entity.getName());

            if (isShowingPosition) {
                tvPosition.setText(String.valueOf(getAdapterPosition()));
            }

            ibClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRemoveListener != null && getAdapterPosition() != -1) {
                        onRemoveListener.onItemRemoved(getAdapterPosition());
                    }
                }
            });
        }
    }

}
