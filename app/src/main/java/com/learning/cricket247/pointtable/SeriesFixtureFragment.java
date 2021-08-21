package com.learning.cricket247.pointtable;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.learning.cricket247.R;
import com.learning.cricket247.databinding.SeriesFixtureFragmentBinding;
import com.learning.cricket247.livescore.TabLayoutMainActivity;
import com.learning.cricket247.model.LiveMatchModel;
import com.learning.cricket247.utility.constantfiles.RecyclerItemClickEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class SeriesFixtureFragment extends Fragment implements RecyclerItemClickEvent {

    private SeriesFixtureViewModel mViewModel;

    public static SeriesFixtureFragment newInstance() {
        return new SeriesFixtureFragment();
    }

    private SeriesFixtureFragmentBinding seriesFixtureFragmentBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        seriesFixtureFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.series_fixture_fragment, container, false);
        View view = seriesFixtureFragmentBinding.getRoot();
        mViewModel = new ViewModelProvider(this).get(SeriesFixtureViewModel.class);
        seriesFixtureFragmentBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("seriesid", getActivity().getIntent().getStringExtra("matchId"));
        mViewModel.getAllSeriesData(stringStringHashMap).observeForever(new Observer<ArrayList<LiveMatchModel>>() {
            @Override
            public void onChanged(ArrayList<LiveMatchModel> liveMatchModels) {
                seriesFixtureFragmentBinding.recyclerView.setAdapter(new SeriesFixtureRecyclerAdapter(liveMatchModels, getContext(), SeriesFixtureFragment.this::onClick));
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onClick(String matchType, String matchId, String title, String message) {
        startActivity(new Intent(getActivity(), TabLayoutMainActivity.class)
                .putExtra("matchId", matchId)
                .putExtra("matchType", matchType)
                .putExtra("title", title).putExtra("message", message));
    }
}