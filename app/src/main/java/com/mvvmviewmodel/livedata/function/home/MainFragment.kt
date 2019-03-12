package com.mvvmviewmodel.livedata.function.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import com.mvvmviewmodel.livedata.R
import com.mvvmviewmodel.livedata.base.BaseFragment
import com.mvvmviewmodel.livedata.model.RepositoriesEntity
import com.mvvmviewmodel.livedata.model.UserEntity
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : BaseFragment() {

    private lateinit var viewModel: UserViewModel

    companion object {
        fun newInstance(): MainFragment {
            val fragment = MainFragment()
            return fragment
        }
    }

    override fun getRootLayoutId(): Int {
        return R.layout.main_fragment
    }

    override fun setupViewModel() {
        Log.d(TAG, "setupViewModel")
        //0.0 Config UserViewModel
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        setObserveLive(viewModel)

        //1.0  Observe showUserInfo
        val userObserver = Observer<UserEntity> { userEntity ->
            setupUserInfo(userEntity)
        }
        viewModel.showUserInfo().observe(this, userObserver)

        //2.0 Observe showRepositories
        val repoObserver = Observer<List<RepositoriesEntity>> {
            setupRepositories(it)
        }
        viewModel.showRepositories().observe(this, repoObserver)
    }

    private fun setupRepositories(it: List<RepositoriesEntity>?) {
        val repoName: MutableList<String> = mutableListOf()
        for (repo in it!!) {
            repoName.add(repo.name + "\n" + repo.full_name)
        }
        val adapter = ArrayAdapter(context!!,
                android.R.layout.simple_list_item_1, android.R.id.text1, repoName)
        list_repositories.adapter = adapter
    }

    private fun setupUserInfo(entity: UserEntity?) {
        tv_name_user.text = entity!!.name
    }

    override fun setupUI(view: View) {
        Log.d(TAG, "setupViewModel")
        btn_load_data.setOnClickListener {
            viewModel.getUserInfo("nguyenlinhnttu")
            viewModel.getRepositories("nguyenlinhnttu")
        }
    }
}
