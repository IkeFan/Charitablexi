package com.mmy.charitablexi.model.project.ui.activity

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.project.ui.adapter.PublishPicAdapter
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.helper.PicSelectHelper
import kotlinx.android.synthetic.main.activity_publish.*

class PublishActivity : BaseActivity<IPresenter<*>>() {
    override fun requestSuccess(any: Any) {
    }

    val mAdapter = PublishPicAdapter(this)
    val picSelectHelper = PicSelectHelper()


    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun initView() {
        setToolbar("活动过程")
        rv_list.adapter = mAdapter
        rv_list.layoutManager = LinearLayoutManager(this,OrientationHelper.HORIZONTAL,false)
    }

    override fun initData() {
    }

    override fun initEvent() {
        super.initEvent()
        mAdapter.addOnItemCliclListener { view, position ->
            if (mAdapter.getItemViewType(position)==PublishPicAdapter.ADD)
                picSelectHelper.showSelectPicPopup(this,1)
        }
    }

    override fun getLayoutID(): Any =R.layout.activity_publish


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        picSelectHelper.onActivityResult(requestCode,resultCode,data!!,{
            mAdapter.addData(it)
        })
        super.onActivityResult(requestCode, resultCode, data)
    }
}
