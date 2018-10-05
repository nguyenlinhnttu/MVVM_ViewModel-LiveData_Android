package com.mvvmviewmodel.livedata.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.view.View
import android.widget.ArrayAdapter
import com.mvvmviewmodel.livedata.R
import com.mvvmviewmodel.livedata.base.BaseFragment
import com.mvvmviewmodel.livedata.model.RepositoriesEntity
import com.mvvmviewmodel.livedata.model.UserEntity
import com.mvvmviewmodel.livedata.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : BaseFragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: UserViewModel

    override fun getRootLayoutId(): Int {
        return R.layout.main_fragment
    }

    override fun setupViewModel() {
        if (!this::viewModel.isInitialized) {
            viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
            setObserveLive(viewModel)
        }
    }


    override fun setupData(view: View) {
        //1.0  Observe userResponse
        val userObserver = Observer<UserEntity> { userEntity ->
            // Update the UI, in this case, a TextView.
            tv_name_user.text = userEntity!!.name
        }
        viewModel.userResponse().observe(this, userObserver)
        //End 1.0

        //2.0 Observe repositoriesResponse
        val repoObserver = Observer<List<RepositoriesEntity>> {
            val repoName: MutableList<String> = mutableListOf()
            for (repo in it!!) {
                repoName.add(repo.name + "\n" + repo.full_name)
            }
            val adapter = ArrayAdapter(context!!,
                    android.R.layout.simple_list_item_1, android.R.id.text1, repoName)
            list_repositories.adapter = adapter
        }
        viewModel.repositoriesResponse().observe(this, repoObserver)
        //End 2.0

        viewModel.loadUserInfo("nguyenlinhnttu")
        btn_load_data.setOnClickListener {
            viewModel.loadRepositories("nguyenlinhnttu")
        }
    }

}
