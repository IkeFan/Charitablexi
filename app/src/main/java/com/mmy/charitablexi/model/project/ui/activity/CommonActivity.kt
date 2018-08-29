package com.mmy.charitablexi.model.project.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.project.component.DaggerProjectInfoComponent
import com.mmy.charitablexi.model.project.module.ProjectInfoModuel
import com.mmy.charitablexi.model.project.presenter.ProjectInfoPresenter
import com.mmy.charitablexi.model.project.ui.adapter.ProCommentAdapter
import com.mmy.charitablexi.model.project.view.ProjectInfoView
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.ProInfoBean
import kotlinx.android.synthetic.main.activity_common.*
import java.util.*

class CommonActivity : BaseActivity<ProjectInfoPresenter>(), ProjectInfoView, View.OnClickListener {

    var mInfoBean: ProInfoBean.DataBean? = null
    val mCommentAdapter = ProCommentAdapter(R.layout.adapter_comment)

    override fun requestSuccess(any: Any) {
        var common = ProInfoBean.DataBean.CommentsBean()
        common.addtime = Calendar.getInstance().timeInMillis.toString()
        common.name = mFrameApp?.userInfo?.name
        common.content = v_comment_et.text.toString().trim()
        common.avatar = mFrameApp?.userInfo?.userBean?.avatar
        mCommentAdapter.addData(common)
        mFrameApp?.mBus?.post(common)
        v_comment_et.text.clear()
    }

    override fun setupDagger(appComponent: AppComponent) {
        DaggerProjectInfoComponent.builder()
                .appComponent(appComponent)
                .projectInfoModuel(ProjectInfoModuel(this))
                .build()
                .inject(this)
    }

    override fun initData() {
        if (intent.hasExtra("sBean")) {
            mInfoBean = intent.getSerializableExtra("sBean") as ProInfoBean.DataBean
            mCommentAdapter.setNewData(mInfoBean?.comments)
            mInfoBean?.comments?.forEach {
                Log.e("xxxabc", "xxxabc:" + it.name)

            }
        }
    }

    override fun initView() {
        setToolbar(getString(R.string.commons), true)
        v_list_comment.adapter = mCommentAdapter
        v_list_comment.layoutManager = LinearLayoutManager(this)
    }

    override fun initEvent() {
        arrayOf(v_comment_bt).setViewListener(this)
    }

    override fun getLayoutID(): Any = R.layout.activity_common

    override fun onClick(v: View?) {
        if (v_comment_et.text.toString().trim().isEmpty()) {
            getString(R.string.input_content).showToast(mFrameApp)
            return
        }
        mIPresenter.comment(mInfoBean?.id?.toInt()!!, v_comment_et.text.toString().trim())
    }
}
