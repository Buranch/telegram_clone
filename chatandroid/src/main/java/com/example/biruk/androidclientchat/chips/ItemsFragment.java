package com.example.biruk.androidclientchat.chips;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager;
import com.beloo.widget.chipslayoutmanager.SpacingItemDecoration;
import com.example.biruk.androidclientchat.APIService.Injection;
import com.example.biruk.androidclientchat.R;
import com.example.biruk.androidclientchat.app.Dialog.DialogListPresenter;
import com.example.biruk.androidclientchat.app.Dialog.SimpleSearchAdapter;
import com.example.biruk.androidclientchat.model.SearchItem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 */
public class ItemsFragment extends Fragment implements SimpleSearchAdapter.SearchItemListener, IAddMemberGroup.View{

    private static final String EXTRA = "data";

    List<SearchItem> searchItems;
//    @BindView(R.id.rvTest)
    RecyclerView rvTest;
    EditText search;
//    @BindView(R.id.spinnerPosition)
    Spinner spinnerPosition;
//    @BindView(R.id.spinnerMoveTo)
    Spinner spinnerMoveTo;
    Button insert;
    SimpleSearchAdapter simpleSearchAdapter;
    RecyclerView searchList;
    private RecyclerView.Adapter adapter;
    private List<String> positions;
    private List items;
    AddMemberGroupPresenter addMemberGroupPresenter;

    /** replace here different data sets */
    private IItemsFactory itemsFactory = new ShortChipsFactory();



    @RestrictTo(RestrictTo.Scope.SUBCLASSES)
    public ItemsFragment() {
        // Required empty public constructor
    }

    public static ItemsFragment newInstance() {
        return new ItemsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_items, container, false);
    }


    @SuppressWarnings("unchecked")
    private RecyclerView.Adapter createAdapter(Bundle savedInstanceState) {

        List<String> items;
        if (savedInstanceState == null) {
            items = itemsFactory.getFewItems();
//            items = itemsFactory.getALotOfItems();
//            items = itemsFactory.getItems();
        } else {
            items = savedInstanceState.getStringArrayList(EXTRA);
        }

        adapter = itemsFactory.createAdapter(items, onRemoveListener);
        this.items = items;

        return adapter;

    }

    private void initPresenter() {
        addMemberGroupPresenter = new AddMemberGroupPresenter(
                Injection.provideDataService(),this, getActivity().getApplicationContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = createAdapter(savedInstanceState);

        ChipsLayoutManager spanLayoutManager = ChipsLayoutManager.newBuilder(getContext())
                .setOrientation(ChipsLayoutManager.HORIZONTAL)
                .build();
        searchItems = new ArrayList<>();

        searchItems.add(new SearchItem("the_ID", "Jerry", "profilePIC", "private"));
        searchItems.add(new SearchItem("the_ID", "Heni", "profilePIC", "private"));
        searchItems.add(new SearchItem("the_ID", "BBB", "profilePIC", "private"));
        searchItems.add(new SearchItem("the_ID", "SelamNeww", "profilePIC", "private"));

        search = (EditText) view.findViewById(R.id.edit);
//        insert = (Button) view.findViewById(R.id.btnInsert);
        searchList = (RecyclerView) view.findViewById(R.id.searchlistadd);
        searchList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        simpleSearchAdapter = new SimpleSearchAdapter(getActivity().getApplicationContext(),searchItems,this);
        searchList.setVisibility(View.VISIBLE);
        searchList.setAdapter(simpleSearchAdapter);
        rvTest = (RecyclerView) view.findViewById(R.id.rvTest);
        spinnerPosition = (Spinner) view.findViewById(R.id.spinnerPosition);
        spinnerMoveTo = (Spinner) view.findViewById(R.id.spinnerMoveTo);
        rvTest.addItemDecoration(new SpacingItemDecoration(getResources()
                .getDimensionPixelOffset(R.dimen.item_space),
                getResources().getDimensionPixelOffset(R.dimen.item_space)));

        search.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
//                    field2.setText("");
                    addMemberGroupPresenter.searchQuery(s.toString());
                else
                    addMemberGroupPresenter.searchQuery("___");

            }
        });

        positions = new LinkedList<>();
        for (int i = 0; i< items.size(); i++) {
            positions.add(String.valueOf(i));
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, positions);
        ArrayAdapter<String> spinnerAdapterMoveTo = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, positions);

        spinnerPosition.setAdapter(spinnerAdapter);
        spinnerMoveTo.setAdapter(spinnerAdapterMoveTo);

        rvTest.setLayoutManager(spanLayoutManager);
        rvTest.getRecycledViewPool().setMaxRecycledViews(0, 10);
        rvTest.getRecycledViewPool().setMaxRecycledViews(1, 10);
        rvTest.setAdapter(adapter);
        addMemberGroupPresenter.searchQuery("___");

    }

    private OnRemoveListener onRemoveListener = new OnRemoveListener() {
        @Override
        public void onItemRemoved(int position) {
            items.remove(position);
            Log.i("activity", "delete at " + position);
            adapter.notifyItemRemoved(position);
            updateSpinners();
        }
    };

    @Override
    @SuppressWarnings("unchecked")
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putParcelableArrayList(EXTRA, new ArrayList<>(items));
    }

    private void updateSpinners() {
        positions = new LinkedList<>();
        for (int i = 0; i< items.size(); i++) {
            positions.add(String.valueOf(i));
        }

        int selectedPosition = Math.min(spinnerPosition.getSelectedItemPosition(), positions.size() - 1);
        int selectedMoveToPosition = Math.min(spinnerMoveTo.getSelectedItemPosition(), positions.size() -1);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, positions);
        spinnerPosition.setAdapter(spinnerAdapter);
        selectedPosition = Math.min(spinnerAdapter.getCount() -1 , selectedPosition);
        spinnerPosition.setSelection(selectedPosition);

        ArrayAdapter<String> spinnerAdapterMoveTo = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, positions);
        spinnerMoveTo.setAdapter(spinnerAdapterMoveTo);
        spinnerMoveTo.setSelection(selectedMoveToPosition);
    }

//    @OnClick(R.id.btnRevert)
    public void onRevertClicked(View view) {
        int position = spinnerPosition.getSelectedItemPosition();
        if (position == Spinner.INVALID_POSITION)
            return;

        int positionMoveTo = spinnerMoveTo.getSelectedItemPosition();
        if (positionMoveTo == Spinner.INVALID_POSITION)
            return;

        if (position == positionMoveTo) return;

        spinnerPosition.setSelection(positionMoveTo);
        spinnerMoveTo.setSelection(position);
    }

//    @OnClick(R.id.btnDelete)
    public void onDeleteClicked(View view) {
        int position = spinnerPosition.getSelectedItemPosition();
        if (position == Spinner.INVALID_POSITION)
            return;
        items.remove(position);
        Log.i("activity", "delete at " + position);
        adapter.notifyItemRemoved(position);
        updateSpinners();
    }

//    @OnClick(R.id.btnMove)
    public void onMoveClicked(View view) {
        int position = spinnerPosition.getSelectedItemPosition();
        if (position == Spinner.INVALID_POSITION)
            return;

        int positionMoveTo = spinnerMoveTo.getSelectedItemPosition();
        if (positionMoveTo == Spinner.INVALID_POSITION)
            return;

        if (position == positionMoveTo) return;

        Object item = items.remove(position);
        items.add(positionMoveTo, item);

        adapter.notifyItemMoved(position, positionMoveTo);
    }

//    @OnClick(R.id.btnScroll)
    public void onScrollClicked(View view) {
//        rvTest.scrollBy(0, 500);
        rvTest.scrollToPosition(spinnerPosition.getSelectedItemPosition());
    }

//    @OnClick(R.id.btnInsert)
    public void onInsertClicked(View view) {
        addMemberGroupPresenter.searchQuery("N");
        int position = spinnerPosition.getSelectedItemPosition();
        if (position == Spinner.INVALID_POSITION)
            position = 0;
//        items.add(position, itemsFactory.createOneItemForPosition(position));
        Log.i("activity", "insert at " + position);
        adapter.notifyItemInserted(position);
        updateSpinners();
    }

    @Override
    public void onSearchItemClicked(int pos) {
        Log.d("onSerachItemClicked", "position "+ pos);
        int position = spinnerPosition.getSelectedItemPosition();
        if (position == Spinner.INVALID_POSITION)
            position = 0;
        items.add(position, itemsFactory.createOneItemForPosition(position,
                searchItems.get(pos).getName(), searchItems.get(pos).getAvatar()));
        Log.i("activity", "insert at " + position);
        adapter.notifyItemInserted(position);
        updateSpinners();
    }

    @Override
    public void renderSearchList(List<SearchItem> searchItems) {
        this.searchItems = searchItems;
        simpleSearchAdapter.setSearchItems(searchItems);
    }
}
