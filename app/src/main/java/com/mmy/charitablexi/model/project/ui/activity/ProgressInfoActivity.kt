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
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.adapter_progress_info.*

class ProgressInfoActivity : BaseActivity<ProgressPresenter>(), View.OnClickListener {
    var mXmid:String?=null
    var deId:Int = -1
    override fun requestSuccess(any: Any) {
        if(any is ProjectProgressBean)
            mAdapter.setNewData(any.data)
        if(deId!=-1){
            mAdapter.remove(deId)
            deId = -1
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.toolbar_right -> {
                openActivity(PublishProgressActivity::class.java,"title="+getString(R.string.add_process)+ ", xmid=$mXmid")
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
        mXmid = intent.getStringExtra("id")
    }

    override fun initEvent() {
        mAdapter.setOnItemChildClickListener({_, view, position ->
            when(view.id){
                R.id.v_edit->{ openActivity(PublishProgressActivity::class.java,"title=" +getString(R.string.edit_process)+ ", xmid=$mXmid",
                        mAdapter.getItem(position))}
                R.id.v_delete->{
                    deId = position
                    mIPresenter.delProcess(mAdapter.getItem(position)?.id!!)
                }
            }

        })
    }

    @Subscribe
    fun updateList( item:ProjectProgressBean.ProjectProgress){
        mIPresenter.getProgressList(mXmid!!.toInt())
    }

    override fun getLayoutID(): Any = R.layout.activity_progress_info

    override fun registerBus(): Boolean = true
}
