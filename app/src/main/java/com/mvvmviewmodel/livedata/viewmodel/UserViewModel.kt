package com.mvvmviewmodel.livedata.viewmodel

import android.arch.lifecycle.LiveData
import com.mvvmviewmodel.livedata.api.BaseResponse
import com.mvvmviewmodel.livedata.base.BaseViewModel
import com.mvvmviewmodel.livedata.model.RepositoriesEntity
import com.mvvmviewmodel.livedata.model.UserEntity
import com.mvvmviewmodel.livedata.model.UserRepository


class UserViewModel : BaseViewModel() {
    private val TAG = UserViewModel::class.java.simpleName
    private lateinit var userData: LiveData<UserEntity>
    private lateinit var arrRepositories: LiveData<List<RepositoriesEntity>>
    private var userRepository: UserRepository = UserRepository()

    fun getUserInfo(userId: String): LiveData<UserEntity> {
        if (this::userData.isInitialized && userData.value != null) {
            return this.userData
        } else {
            userData = userRepository.getUser(userId, object : ICallBack {

                override fun setProgress(show: Boolean) {
                    showLoading(show)
                }

                override fun showRequestError(baseRes: BaseResponse) {

                    showError(baseRes)
                }

                override fun showQuestFailure(throwable: Throwable) {
                    showFailure(throwable)
                }
            })
            return userData
        }
    }

    fun getRepositories(userId: String): LiveData<List<RepositoriesEntity>> {
        if (this::arrRepositories.isInitialized && arrRepositories.value != null) {
            return this.arrRepositories
        } else {
            arrRepositories = userRepository.getRepositories(userId, object : ICallBack {
                override fun setProgress(show: Boolean) {
                    showLoading(show)
                }

                override fun showRequestError(baseRes: BaseResponse) {

                    showError(baseRes)
                }

                override fun showQuestFailure(throwable: Throwable) {
                    showFailure(throwable)
                }
            })

            return arrRepositories
        }
    }
}
