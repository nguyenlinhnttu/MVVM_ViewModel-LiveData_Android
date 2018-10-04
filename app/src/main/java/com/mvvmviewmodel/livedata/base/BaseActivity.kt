package com.mvvmviewmodel.livedata.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by NguyenLinh on 02,October,2018
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getRootLayoutId())
        setupView(savedInstanceState)
    }

    abstract fun getRootLayoutId(): Int

    abstract fun setupView(savedInstanceState: Bundle?)
}