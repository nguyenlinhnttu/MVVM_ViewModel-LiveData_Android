package com.mvvmviewmodel.livedata.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.mvvmviewmodel.livedata.api.ApiBuilder
import com.mvvmviewmodel.livedata.api.BaseResponse
import com.mvvmviewmodel.livedata.viewmodel.ICallBack
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by NguyenLinh on 02,October,2018
 */
class UserRepository {

    fun getUser(userId: String, callBack: ICallBack): LiveData<UserEntity> {
        callBack.setProgress(true)
        val data = MutableLiveData<UserEntity>()
        ApiBuilder.getWebService().getUser(userId).enqueue(object : Callback<UserEntity> {
            override fun onResponse(call: Call<UserEntity>, response: Response<UserEntity>) {
                callBack.setProgress(false)
                if (response.isSuccessful) {
                    data.value = response.body()
                } else {
                    data.value = null
                    callBack.showRequestError(Gson().fromJson(response.errorBody()!!.string(), BaseResponse::class.java))
                }
            }

            override fun onFailure(call: Call<UserEntity>, t: Throwable) {
                callBack.setProgress(false)
                data.value = null
                callBack.showQuestFailure(t)
            }
        })
        return data
    }

    fun getRepositories(userId: String, callBack: ICallBack): LiveData<List<RepositoriesEntity>> {
        callBack.setProgress(true)
        val data = MutableLiveData<List<RepositoriesEntity>>()
        ApiBuilder.getWebService().getRepositories(userId).enqueue(object : Callback<List<RepositoriesEntity>> {
            override fun onResponse(call: Call<List<RepositoriesEntity>>, response: Response<List<RepositoriesEntity>>) {
                callBack.setProgress(false)
                if (response.isSuccessful) {
                    data.value = response.body()
                } else {
                    data.value = null
                    callBack.showRequestError(Gson().fromJson(response.errorBody()!!.string(), BaseResponse::class.java))
                }
            }

            override fun onFailure(call: Call<List<RepositoriesEntity>>, t: Throwable) {
                callBack.setProgress(false)
                data.value = null
                callBack.showQuestFailure(t)
            }
        })
        return data
    }
}