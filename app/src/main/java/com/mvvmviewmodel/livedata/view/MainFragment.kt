package com.mvvmviewmodel.livedata.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.view.View
import android.widget.ArrayAdapter
import com.mvvmviewmodel.livedata.R
import com.mvvmviewmodel.livedata.base.BaseFragment
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
        //1.0 getUserInfo
        viewModel.getUserInfo("nguyenlinhnttu").observe(this, Observer {
            if (it != null) {
                tv_name_user.text = it.name
            }
        })

        //2.0 Call method get getRepositories
        btn_load_data.setOnClickListener {
            viewModel.getRepositories("nguyenlinhnttu").observe(this, Observer {
                if (it != null) {
                    val repoName: MutableList<String> = mutableListOf()
                    for (repo in it) {
                        repoName.add(repo.name + "\n" + repo.full_name)
                    }
                    val adapter = ArrayAdapter(context!!,
                            android.R.layout.simple_list_item_1, android.R.id.text1, repoName)
                    list_repositories.adapter = adapter
                }
            })
        }
    }

}
