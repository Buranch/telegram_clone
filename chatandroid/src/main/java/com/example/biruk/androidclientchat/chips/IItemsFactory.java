package com.example.biruk.androidclientchat.chips;

import android.support.v7.widget.RecyclerView;

import java.util.List;

public interface IItemsFactory<Item> {

    List<Item> getFewItems();

    List<Item> getItems();

    List<Item> getDoubleItems();

    List<Item> getALotOfItems();

    List<Item> getALotOfRandomItems();

    Item createOneItemForPosition(int position, String name, String pic);

    RecyclerView.Adapter<? extends RecyclerView.ViewHolder> createAdapter(List<Item> items, OnRemoveListener onRemoveListener);
}
