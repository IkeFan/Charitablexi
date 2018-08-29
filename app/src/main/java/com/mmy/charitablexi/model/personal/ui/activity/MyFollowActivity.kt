package com.mmy.charitablexi.model.personal.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.component.DaggerMyFollowComponent
import com.mmy.charitablexi.model.personal.module.MyFollowModule
import com.mmy.charitablexi.model.personal.presenter.MyFollowPresenter
import com.mmy.charitablexi.model.personal.ui.adapter.MyFollowAdapter
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.FollowListBean
import kotlinx.android.synthetic.main.activity_my_collection.*

/**
 * @file       MyFollowActivity.kt
 * @brief      我的关注和关注者
 * @author     lucas
 * @date       2018/5/28 0028
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class MyFollowActivity : BaseActivity<MyFollowPresenter>(), View.OnClickListener {
    val mAdapter = MyFollowAdapter(R.layout.adapter_my_collection)
    lateinit var title: String

    override fun requestSuccess(data: Any) {
        if (data is FollowListBean)
            mAdapter.setNewData(data.data)
    }

    override fun setupDagger(appComponent: AppComponent) {
        DaggerMyFollowComponent.builder()
                .appComponent(appComponent)
                .myFollowModule(MyFollowModule(this))
                .build().inejct(this)
    }

    override fun initView() {
        title = intent.getStringExtra("title")
        if(title == getString(R.string.follow)){
            setToolbar(title, rightRes = getString(R.string.add), rightTextColor = getResColor(R.color.colorPrimary), rightClickListener = this)
        }else{
            setToolbar(title)
        }
        v_list.layoutManager = LinearLayoutManager(this)
        v_list.adapter = mAdapter
    }

    override fun initData() {
        when (title) {
            getString(R.string.follow) -> mIPresenter.getList(mIPresenter.FOLLOW)
            getString(R.string.follower) -> mIPresenter.getList(mIPresenter.FOLLOWER)
            getString(R.string.join_ogn) -> mIPresenter.getList(mIPresenter.ORGANIZATION)
        }
    }

    override fun initEvent() {
        mAdapter.setOnItemClickListener { adapter, view, baseViewHolder, position ->
            openActivity(FollowInfoActivity::class.java)
        }
    }

    override fun getLayoutID(): Any = R.layout.activity_my_collection

    override fun onClick(v: View) {
        when (v.id) {
            R.id.toolbar_right -> {
                //添加
            }
            else -> {
            }
        }
    }
}
