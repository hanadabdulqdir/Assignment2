package com.example.hanad.assignment2.rockDetails.model;


import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.hanad.assignment2.R;
import com.example.hanad.assignment2.service.IRequestInterface;
import com.example.hanad.assignment2.service.ServiceConnection;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hanad on 09/02/2018.
 */

public class RockMusicFragment extends Fragment {
    CompositeDisposable compositeDisposable;
    public IRequestInterface iRequestInterface;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshListener;

    // TopRatedMovies topRatedMovies;
    public RockMusicFragment() {

    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iRequestInterface = ServiceConnection.getConnection();
        recyclerView = view.findViewById(R.id.recycler);
        compositeDisposable = new CompositeDisposable();
        refreshListener = view.findViewById(R.id.swiperefresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//progress
        //displayRockMusic();
        CallService();

        refreshListener.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CallService();
                //if (NetworkUtils.isNetworkConnected((getActivity()))) {
                //  displayTopRatedMovies();
                //} else {
                Toast.makeText(getActivity(), "no network avaialable", Toast.LENGTH_LONG).show();
                //}
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rock, container, false);
    }
    public void CallService() {
        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isConnectedToInternet) {
                        // do something with isConnectedToInternet value
                        if (isConnectedToInternet) {
                            displayRockMusic();
                        } else {
                            Toast.makeText(getActivity(), "Off Line", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

    public void displayRockMusic() {
        compositeDisposable.add(
                iRequestInterface.getRockMusicList()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<RockMusicList>() {
                                       @Override
                                       public void accept(RockMusicList rockMusicLists) throws Exception {
                                           recyclerView.setAdapter(new RockMusicAdapter(RockMusicFragment.this.getActivity().getApplicationContext(), rockMusicLists.getResults(), RockMusicFragment.this, rockMusicLists, R.layout.row));
                                           //(RockMusicFragment.this.getActivity().getApplicationContext(),rockMusicLists.getResults(),RockMusicFragment.this,R.layout.row));
                                           //recyclerView.setAdapter(new TopRatedMoviesAdapter(TopMoviesFragment.this.getActivity().getApplicationContext(), topRatedMovies.getResults(), TopMoviesFragment.this, topRatedMovies, R.layout.row));

                                           // Toast.makeText(getActivity(), topRatedMovies.getResults().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                                           refreshListener.setRefreshing(false);
                                       }
                                   },
                                new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {
                                    }
                                }));
    }
}