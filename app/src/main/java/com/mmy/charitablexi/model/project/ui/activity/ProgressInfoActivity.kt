package com.mmy.charitablexi.model.project.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.mmy.charitablexi.R
import com.mmy.charitablexi.base.BaseIViewModule
import com.mmy.charitablexi.base.DaggerBaseComponent
import com.mmy.charitablexi.model.project.presenter.ProgressPresenter
import com.mmy.charitablexi.model.project.ui.adapter.ProgressInfoAdapter
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.ProjectProgressBean
import kotlinx.android.synthetic.main.adapter_progress_info.*

class ProgressInfoActivity : BaseActivity<ProgressPresenter>(), View.OnClickListener {
    override fun requestSuccess(any: Any) {
        if(any is ProjectProgressBean)
            mAdapter.setNewData(any.data)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.toolbar_right -> {
                openActivity(PublishActivity::class.java)
            }
            else -> {
            }
        }
    }

    val mAdapter = ProgressInfoAdapter(R.layout.adapter_progress_info)
    override fun setupDagger(appComponent: AppComponent) {
        DaggerBaseComponent.builder()
                .appComponent(appComponent)
                .baseIViewModule(BaseIViewModule(this))
                .build()
                .inject(this)
    }

    override fun initView() {
        setToolbar(getString(R.string.project_progress),true,getString(R.string.publish),R.color.colorPrimaryDark,this)
        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.adapter = mAdapter
    }

    override fun initData() {
        mIPresenter.getProgressList(intent.getStringExtra("id").toInt())
    }

    override fun getLayoutID(): Any = R.layout.activity_progress_info


}
