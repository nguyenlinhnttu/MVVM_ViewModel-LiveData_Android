package com.mvvmviewmodel.livedata.api

import android.arch.lifecycle.MutableLiveData
import com.mvvmviewmodel.livedata.model.RepositoriesEntity
import com.mvvmviewmodel.livedata.model.UserEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by NguyenLinh on 02,October,2018
 */

interface ApiInterface {

    @GET("/users/{user}")
    fun getUser(@Path("user") userId: String): Observable<UserEntity>

    @GET("/users/{user}/repos")
    fun getRepositories(@Path("user") userId: String): Observable<List<RepositoriesEntity>>
}