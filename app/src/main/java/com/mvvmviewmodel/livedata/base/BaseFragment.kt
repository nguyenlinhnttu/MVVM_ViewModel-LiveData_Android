package com.mvvmviewmodel.livedata.base

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import com.mvvmviewmodel.livedata.R
import com.mvvmviewmodel.livedata.api.BaseResponse
import com.mvvmviewmodel.livedata.viewmodel.IBaseView

/**
 * Created by NguyenLinh on 02,October,2018
 */
abstract class BaseFragment : Fragment(), IBaseView {
    private val TAG = BaseFragment::class.java.simpleName
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getRootLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupData(view)
    }

    abstract fun getRootLayoutId(): Int

    abstract fun setupViewModel()

    abstract fun setupData(view: View)

    override fun setProgress(show: Boolean) {
        Log.i(TAG, "setProgress: " + show.toString())
        if (show) {
            showLoadingDialog()
        } else {
            hideLoadingDialog()
        }
    }

    override fun showRequestError(baseRes: BaseResponse) {
        Log.i(TAG, "showRequestError: " + baseRes.toString())
    }

    override fun showQuestFailure(throwable: Throwable) {
        if (throwable.message != null) {
            Log.i(TAG, "showQuestFailure: " + throwable.message)
        }
    }

    lateinit var mProgressDialog: Dialog

    private fun showLoadingDialog() {
        if (isVisible) {
            if (!this::mProgressDialog.isInitialized) {
                mProgressDialog = Dialog(context!!)
                mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                mProgressDialog.setCancelable(false)
                mProgressDialog.setContentView(R.layout.custom_progress_loading)
                if (mProgressDialog.window != null && !activity!!.isFinishing) {
                    mProgressDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        val lp = WindowManager.LayoutParams()
                        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
                        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
                        lp.gravity = Gravity.CENTER
                        mProgressDialog.window!!.attributes = lp
                    } else {
                        mProgressDialog.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                    }
                    if (!mProgressDialog.isShowing) {
                        mProgressDialog.show()
                        Log.i(TAG, "setProgress: success")
                    }
                }
            }
            if (!activity!!.isFinishing && this::mProgressDialog.isInitialized && !mProgressDialog.isShowing) {
                try {
                    mProgressDialog.show()
                    Log.i(TAG, "setProgress: success")
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }

            }
        }
    }

    private fun hideLoadingDialog() {
        if (this::mProgressDialog.isInitialized && mProgressDialog.isShowing) {
            mProgressDialog.dismiss()
        }
    }

}