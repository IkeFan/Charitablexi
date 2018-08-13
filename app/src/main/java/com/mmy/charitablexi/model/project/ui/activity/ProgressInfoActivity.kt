package com.mmy.charitablexi.model.project.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.project.ui.adapter.ProgressInfoAdapter
import com.mmy.charitablexi.utils.VRData
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseActivity
import kotlinx.android.synthetic.main.adapter_progress_info.*

class ProgressInfoActivity : BaseActivity<IPresenter<*>>(), View.OnClickListener {
    override fun requestSuccess(any: Any) {
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
    }

    override fun initView() {
        setToolbar("项目进度",true,"发布",R.color.colorPrimaryDark,this)
        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.adapter = mAdapter
    }

    override fun initData() {
        mAdapter.setNewData(VRData.getIntData(10))
    }

    override fun getLayoutID(): Any = R.layout.activity_progress_info


}
