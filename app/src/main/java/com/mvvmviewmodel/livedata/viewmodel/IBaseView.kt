package com.mvvmviewmodel.livedata.viewmodel
import com.mvvmviewmodel.livedata.api.BaseResponse
/**
 * Created by NguyenLinh on 02,October,2018
 */
interface IBaseView {
    fun setProgress(show: Boolean)
    fun showRequestError(baseRes :BaseResponse)
    fun showQuestFailure( throwable: Throwable)
}