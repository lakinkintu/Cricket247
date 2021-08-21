package com.learning.cricket247.livescore.models;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.learning.cricket247.home.viewmodel.MatchOddsModel;
import com.learning.cricket247.home.viewmodel.Matchst;
import com.learning.cricket247.model.LiveMatchModel;
import com.learning.cricket247.model.players.AllPlayersInfo;
import com.learning.cricket247.model.players.Players;
import com.learning.cricket247.model.stats.MatchStats;
import com.learning.cricket247.remoteconnection.ApiServices;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class LiveScoreModel extends ViewModel {

    private MutableLiveData<ArrayList<LiveMatchModel>> mutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Players>> playerIndoMutableLiveData = new MutableLiveData<>();
    public Disposable disposable;
    private MutableLiveData<ArrayList<Players>> arrayListMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Matchst>> arrayListMutableMatchOdds = new MutableLiveData<>();
    private MutableLiveData<MatchStats> matchStats = new MutableLiveData<>();

    public LiveScoreModel() {

    }

    public MutableLiveData<ArrayList<LiveMatchModel>> getUpComingData(HashMap<String, String> scoreInfo) {

        ApiServices.getLiveScore(scoreInfo).subscribeWith(new DisposableObserver<ArrayList<LiveMatchModel>>() {
            @Override
            public void onNext(ArrayList<LiveMatchModel> jsonObject) {
                if (jsonObject != null) {
                    mutableLiveData.setValue(jsonObject);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Exception", "Exception in api=================================");
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
        return mutableLiveData;
    }

    public void setDisposableDistroy() {
        disposable.dispose();
    }

    public MutableLiveData<ArrayList<Players>> getPlayerInfo(HashMap<String, String> scoreInfo) {
        ApiServices.getPlayesrInfo(scoreInfo).subscribeWith(new DisposableObserver<AllPlayersInfo>() {
            @Override
            public void onNext(AllPlayersInfo jsonObject) {
                if (jsonObject != null) {
                    playerIndoMutableLiveData.setValue(jsonObject.getPlayerslist());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        return playerIndoMutableLiveData;
    }

    public MutableLiveData<ArrayList<Players>> getFinishedScoreCard(HashMap<String, String> scoreInfo) {
        ApiServices.getPlayesrInfo(scoreInfo).subscribeWith(new DisposableObserver<AllPlayersInfo>() {
            @Override
            public void onNext(AllPlayersInfo jsonObject) {
                if (jsonObject != null) {
                    arrayListMutableLiveData.setValue(jsonObject.getPlayerslist());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        return arrayListMutableLiveData;
    }


    public MutableLiveData<ArrayList<Matchst>> getMatchOddsScoreCard(HashMap<String, String> scoreInfo) {
        ApiServices.getMatchOddsInfo(scoreInfo).safeSubscribe(new DisposableObserver<MatchOddsModel>() {
            @Override
            public void onNext(@NonNull MatchOddsModel matchOddsModel) {
                if (matchOddsModel != null) {
                    arrayListMutableMatchOdds.setValue(matchOddsModel.getMatchst());
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        return arrayListMutableMatchOdds;
    }
    public MutableLiveData<MatchStats> getMatchStats(HashMap<String, String> scoreInfo) {
        ApiServices.getMatchStats(scoreInfo).safeSubscribe(new DisposableObserver<MatchStats>() {
            @Override
            public void onNext(@NonNull MatchStats matchOddsModel) {
                if (matchOddsModel != null) {
                    matchStats.setValue(matchOddsModel);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        return matchStats;
    }
}
