package com.example.alfonso.dogsapp;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DogApi {
    @GET("/dogs/{name}.json")
    Single<Dog> getDog(@Path("name") String dogName);
}