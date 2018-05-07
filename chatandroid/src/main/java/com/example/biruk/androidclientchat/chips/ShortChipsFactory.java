package com.example.biruk.androidclientchat.chips;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//import com.beloo.chipslayoutmanager.sample.entity.ChipsEntity;
import com.example.biruk.androidclientchat.R;
import com.example.biruk.androidclientchat.chips.adapter.ChipsAdapter;
import com.example.biruk.androidclientchat.model.ChipsEntity;

class ShortChipsFactory implements IItemsFactory<ChipsEntity> {

    @Override
    public List<ChipsEntity> getFewItems() {
        List<ChipsEntity> chipsList = new ArrayList<>();
        chipsList.add(ChipsEntity.newBuilder()
                .drawableResId("uploaded/convpic/beauty.jpg")
                .name("Batman")
                .build());

        chipsList.add(ChipsEntity.newBuilder()
                .name("V")
                .build());



        chipsList.add(ChipsEntity.newBuilder()
                .drawableResId("uploaded/convpic/beauty.jpg")
                .name("Cat")
                .build());



        chipsList.add(ChipsEntity.newBuilder()
                .name("C")
                .drawableResId("uploaded/convpic/breakingbad.jpg")
                .build());

        chipsList.add(ChipsEntity.newBuilder()
                .drawableResId("uploaded/convpic/beauty.vectorwomen.jpg")
                .name("Karl")
                .build());


        return chipsList;
    }

    @Override
    public List<ChipsEntity> getItems() {
        List<ChipsEntity> chipsEntities = getFewItems();

        List<ChipsEntity> secondPortion = getFewItems();
        Collections.reverse(secondPortion);
        chipsEntities.addAll(secondPortion);
        chipsEntities.addAll(getFewItems());
        chipsEntities.addAll(getFewItems());

        for (int i=0; i< chipsEntities.size(); i++) {
            ChipsEntity chipsEntity = chipsEntities.get(i);
            chipsEntity.setName(chipsEntity.getName() + " " + i);
        }

        return chipsEntities;
    }

    @Override
    public List<ChipsEntity> getDoubleItems() {
        List<ChipsEntity> chipsEntities = getFewItems();

        List<ChipsEntity> secondPortion = getFewItems();
        Collections.reverse(secondPortion);
        chipsEntities.addAll(secondPortion);
        return chipsEntities;
    }

    @Override
    public List<ChipsEntity> getALotOfItems() {
        List<ChipsEntity> entities = new LinkedList<>();
        for (int i=0; i < 5; i++){
            entities.addAll(getItems());
        }
        return entities;
    }

    @Override
    public List<ChipsEntity> getALotOfRandomItems() {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public ChipsEntity createOneItemForPosition(int position, String name, String pic) {
        return ChipsEntity.newBuilder()
                .name(name)
                .drawableResId(pic)
                .build();
    }

    @Override
    public RecyclerView.Adapter<? extends RecyclerView.ViewHolder> createAdapter(List<ChipsEntity> chipsEntities, OnRemoveListener onRemoveListener) {
        return new ChipsAdapter(chipsEntities, onRemoveListener);
    }
}
