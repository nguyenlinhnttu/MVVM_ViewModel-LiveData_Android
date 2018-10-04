package com.mvvmviewmodel.livedata.api

import com.mvvmviewmodel.livedata.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by NguyenLinh on 02,October,2018
 */
class ApiBuilder() {
    companion object {
        private val apiInterface: ApiInterface? = null
        fun getWebService(): ApiInterface {
            if (apiInterface != null) {
                return apiInterface
            }
            val retrofit = Retrofit.Builder().baseUrl(BuildConfig.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}