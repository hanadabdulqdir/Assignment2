package com.example.hanad.assignment2.service;

/**
 * Created by hanad on 09/02/2018.
 */
import com.example.hanad.assignment2.rockDetails.model.RockMusicList;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface IRequestInterface {
    @GET(ApiList.ROCK_API)
     Observable<RockMusicList> getRockMusicList();

}
