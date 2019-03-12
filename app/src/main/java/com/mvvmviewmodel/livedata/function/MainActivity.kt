package com.mvvmviewmodel.livedata.function

import android.os.Bundle
import com.mvvmviewmodel.livedata.base.BaseActivity
import com.mvvmviewmodel.livedata.R
import com.mvvmviewmodel.livedata.function.home.MainFragment

class MainActivity : BaseActivity() {

    override fun setupView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitAllowingStateLoss()
        }
    }

    override fun getRootLayoutId(): Int {
        return R.layout.main_activity
    }

}
