package com.mmy.charitablexi.model.commun.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.View
import com.mmy.charitablexi.R
import com.mmy.charitablexi.base.BaseIViewModule
import com.mmy.charitablexi.base.DaggerBaseComponent
import com.mmy.charitablexi.model.commun.presenter.CardDetailPresenter
import com.mmy.charitablexi.model.commun.ui.adapter.CardCommentAdapter
import com.mmy.charitablexi.model.project.ui.adapter.ProgressInfoImgAdapter
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.CardetailBean
import com.mmy.frame.data.bean.InteractBean
import kotlinx.android.synthetic.main.activity_class_publish.*

/**
 * @file       CardDetailActivity.ktivity.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/15 0015
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class CardDetailActivity : BaseActivity<CardDetailPresenter>(), View.OnClickListener{
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.v_comment->{

            }
        }
    }

    var mCard: InteractBean.Interact? = null
    val adapter = ProgressInfoImgAdapter(R.layout.adapter_card_detail_img)
    val mCommentAdapter = CardCommentAdapter(R.layout.adapter_comment)

    override fun setupDagger(appComponent: AppComponent) {
        DaggerBaseComponent.builder()
                .appComponent(appComponent)
                .baseIViewModule(BaseIViewModule(this))
                .build()
                .inject(this)

    }

    override fun initView() {
        setToolbar(getString(R.string.card_detail))
        mCard = intent.getSerializableExtra("sBean") as InteractBean.Interact
    }

    override fun initData() {
        v_title.text = mCard?.title
        v_list_img.adapter = adapter
        v_list_img.layoutManager = LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false)
        v_list_comment.layoutManager = LinearLayoutManager(this,OrientationHelper.VERTICAL, false)
        v_list_comment.adapter = mCommentAdapter
        adapter.setNewData(mCard?.imgs)

        mIPresenter.getDetail(mCard?.id!!)
    }

    override fun initEvent() {
        arrayOf(v_comment).setViewListener(this)
    }

    override fun getLayoutID(): Any= R.layout.activity_class_publish

    override fun requestSuccess(data: Any) {
        if(data is CardetailBean){
            mCommentAdapter.setNewData(data.data?.comments)
        }
    }
}