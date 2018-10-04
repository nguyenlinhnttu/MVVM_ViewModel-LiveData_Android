package com.mvvmviewmodel.livedata.api

import com.mvvmviewmodel.livedata.model.RepositoriesEntity
import com.mvvmviewmodel.livedata.model.UserEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by NguyenLinh on 02,October,2018
 */

interface ApiInterface {

    @GET("/users/{user}")
    fun getUser(@Path("user") userId: String): Call<UserEntity>

    @GET("/users/{user}/repos")
    fun getRepositories(@Path("user") userId: String): Call<List<RepositoriesEntity>>
}