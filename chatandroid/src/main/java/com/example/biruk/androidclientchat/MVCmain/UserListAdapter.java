package com.example.biruk.androidclientchat.MVCmain;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.biruk.androidclientchat.ProviderData.model.User;
import com.example.biruk.androidclientchat.R;

import java.util.List;

/**
 * Created by Biruk on 3/28/2018.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ItemHolder> {

    public interface ItemClickListner{
        void onItemClick(User user);
    }

    private ItemClickListner listener;
    private List<User> users;


    public UserListAdapter(ItemClickListner itemClickListner, List<User> users){
        this.listener = itemClickListner;
        this.users = users;
        notifyDataSetChanged();
    }
    public void addUsers(List<User> users){
        this.users.addAll(users);
        notifyDataSetChanged();
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(users.get(holder.getAdapterPosition()));
                }
            }
        });

        String name = users.get(position).getName();
        holder.name.setText(name);
    }

    @Override
    public int getItemCount() {
        return users != null ? users.size() : 0;

    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private View view;
        public ItemHolder(View view) {
            super(view);
            this.view = view;
            this.name = (TextView) view.findViewById(R.id.name);
        }
    }
}
