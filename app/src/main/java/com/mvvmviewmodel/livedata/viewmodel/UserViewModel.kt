package com.mvvmviewmodel.livedata.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.mvvmviewmodel.livedata.model.RepositoriesEntity
import com.mvvmviewmodel.livedata.model.UserEntity
import com.mvvmviewmodel.livedata.model.UserRepository

class UserViewModel : ViewModel() {
    private val TAG = UserViewModel::class.java.simpleName
    private lateinit var view: IBaseView
    lateinit var userData: LiveData<UserEntity>
    lateinit var arrRepositories: LiveData<List<RepositoriesEntity>>
    lateinit var userRepository: UserRepository
    fun init(view: IBaseView) {
        this.view = view
        userRepository = UserRepository()
    }

    fun getUserInfo(userId: String): LiveData<UserEntity> {
        if (this::userData.isInitialized) {
            return this.userData
        } else {
            userData = userRepository.getUser(userId, view)
            return userData
        }
    }

    fun getRepositories(userId: String): LiveData<List<RepositoriesEntity>> {
        if (this::arrRepositories.isInitialized) {
            return this.arrRepositories
        } else {
            arrRepositories = userRepository.getRepositories(userId, view)
            return arrRepositories
        }
    }
}
