package com.mvvmviewmodel.livedata.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mvvmviewmodel.livedata.api.BaseResponse
import com.mvvmviewmodel.livedata.viewmodel.Event

/**
 * Created by NguyenLinh on 05,October,2018
 */
open class BaseViewModel : ViewModel() {
    val eventLoading = MutableLiveData<Event<Boolean>>()
    val eventError = MutableLiveData<Event<BaseResponse>>()
    val eventFailure = MutableLiveData<Event<Throwable>>()

    fun showLoading(value: Boolean) {
        eventLoading.value = Event(value)
    }

    fun showError(baseResponse: BaseResponse) {
        eventError.value = Event(baseResponse)
    }

    fun showFailure(throwable: Throwable) {
        eventFailure.value = Event(throwable)
    }
}