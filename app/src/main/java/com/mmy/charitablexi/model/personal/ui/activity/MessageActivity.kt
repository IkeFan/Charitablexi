package com.mmy.charitablexi.model.personal.ui.activity

import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.commun.ui.fragment.CommunFragment
import com.mmy.charitablexi.model.personal.ui.fragment.AuditingFragment
import com.mmy.charitablexi.model.personal.ui.fragment.LatterFragment
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_message.*
/**
* @file       MessageActivity.kt
* @brief      消息
* @author     lucas
* @date       2018/5/25 0025
* @version
* @par        Copyright (c):
* @par History:
*             version: zsr, 2017-09-23
*/
class MessageActivity : BaseActivity<IPresenter<*>>() {
    override fun requestSuccess(any: Any) {
    }

    val tabStr = arrayOf("私信", "审核")
    val tabFragment = arrayOf(LatterFragment(),AuditingFragment())

    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun initView() {
        setToolbar("消息")
        tabStr.forEach { v_tabs.addTab(v_tabs.newTab().setText(it)) }
        v_tabs.setupWithViewPager(v_pager)
        v_pager.adapter = CommunFragment.CommunAdapter(tabStr,tabFragment,supportFragmentManager)
    }

    override fun initData() {
    }

    override fun getLayoutID(): Any = R.layout.activity_message

}
