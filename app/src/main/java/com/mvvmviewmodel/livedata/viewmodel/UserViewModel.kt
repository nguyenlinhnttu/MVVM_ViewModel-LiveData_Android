package com.mvvmviewmodel.livedata.viewmodel

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import com.mvvmviewmodel.livedata.api.ApiBuilder
import com.mvvmviewmodel.livedata.base.BaseViewModel
import com.mvvmviewmodel.livedata.model.RepositoriesEntity
import com.mvvmviewmodel.livedata.model.UserEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


@SuppressLint("CheckResult")
class UserViewModel : BaseViewModel() {
    private val TAG = UserViewModel::class.java.simpleName
    private val userResponse = MutableLiveData<UserEntity>()
    private var repositoriesResponse = MutableLiveData<List<RepositoriesEntity>>()
    private val disposables = CompositeDisposable()

    fun userResponse(): MutableLiveData<UserEntity> {
        return userResponse
    }

    fun repositoriesResponse(): MutableLiveData<List<RepositoriesEntity>> {
        return repositoriesResponse
    }

    fun loadUserInfo(userId: String) {
        disposables.add(ApiBuilder.getWebService().getUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showLoading(true) }
                .doFinally { showLoading(false) }
                .subscribe({
                    userResponse.value = it
                }, {
                    showFailure(it)
                }))
    }

    fun loadRepositories(userId: String) {
        ApiBuilder.getWebService().getRepositories(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showLoading(true) }
                .doFinally { showLoading(false) }
                .subscribe({
                    repositoriesResponse.value = it
                }, {
                    showFailure(it)
                })
    }

    override fun onCleared() {
        disposables.clear()
    }
}
