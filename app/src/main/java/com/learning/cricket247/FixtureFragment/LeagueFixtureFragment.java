package com.learning.cricket247.FixtureFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.learning.cricket247.FixtureFragment.adapters.DateWiseRecyclerViewAdapter;
import com.learning.cricket247.FixtureFragment.adapters.TeamWiseRecyclerViewAdapter;
import com.learning.cricket247.FixtureFragment.model.SeriesViewModel;
import com.learning.cricket247.R;
import com.learning.cricket247.model.MatchWithTitle;
import com.learning.cricket247.utility.constantfiles.RecyclerItemClickEvent;

import java.util.ArrayList;

public class LeagueFixtureFragment extends Fragment implements View.OnClickListener, RecyclerItemClickEvent {

    private RecyclerView dateWiseRecyclerview, seriesWiseRecyclerview, teamWiseRecyclerview;
    private TextView date_btn, series_btn, team_btn;
    private DateWiseRecyclerViewAdapter sectionRecyclerViewAdapter;
    private SeriesViewModel seriesViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_league_fixture, container, false);
        dateWiseRecyclerview = view.findViewById(R.id.dateWiseRecyclerview);
        seriesWiseRecyclerview = view.findViewById(R.id.seriesWiseRecyclerview);
        teamWiseRecyclerview = view.findViewById(R.id.teamWiseRecyclerview);
        date_btn = view.findViewById(R.id.first_btn);
        series_btn = view.findViewById(R.id.secondt_btn);
        team_btn = view.findViewById(R.id.third_btn);
        date_btn.setOnClickListener(this::onClick);
        team_btn.setOnClickListener(this::onClick);
        series_btn.setOnClickListener(this::onClick);

        dateWiseRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        seriesWiseRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        teamWiseRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        seriesViewModel = new ViewModelProvider(this).get(SeriesViewModel.class);
        seriesViewModel.getAllFixture().observeForever(new Observer<ArrayList<MatchWithTitle>>() {
            @Override
            public void onChanged(ArrayList<MatchWithTitle> matchWithTitles) {
                dateWiseRecyclerview.setAdapter(new DateWiseRecyclerViewAdapter(matchWithTitles, getContext(), LeagueFixtureFragment.this::onClick));
            }
        });
        teamWiseRecyclerview.setAdapter(new TeamWiseRecyclerViewAdapter());
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.first_btn) {
            seriesWiseRecyclerview.setVisibility(View.GONE);
            teamWiseRecyclerview.setVisibility(View.GONE);
            dateWiseRecyclerview.setVisibility(View.VISIBLE);

        }
        if (view.getId() == R.id.secondt_btn) {
            dateWiseRecyclerview.setVisibility(View.GONE);
            teamWiseRecyclerview.setVisibility(View.GONE);
            seriesWiseRecyclerview.setVisibility(View.VISIBLE);
        }
        if (view.getId() == R.id.third_btn) {
            seriesWiseRecyclerview.setVisibility(View.GONE);
            dateWiseRecyclerview.setVisibility(View.GONE);
            teamWiseRecyclerview.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onClick(String matchType, String matchId, String title, String message) {

    }
}