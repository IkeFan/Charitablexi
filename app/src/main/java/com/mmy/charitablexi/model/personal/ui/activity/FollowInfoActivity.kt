package com.mmy.charitablexi.model.personal.ui.activity

import android.support.design.widget.TabLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.ui.adapter.FollowInfo1Adapter
import com.mmy.charitablexi.model.personal.ui.adapter.FollowInfo2Adapter
import com.mmy.charitablexi.model.personal.ui.adapter.FollowInfoAdapter
import com.mmy.charitablexi.utils.VRData
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_follow_info.*

/**
 * @file       FollowInfoActivity.kt
 * @brief      关注详情
 * @author     lucas
 * @date       2018/5/29 0029
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class FollowInfoActivity : BaseActivity<IPresenter<*>>(), View.OnClickListener {

    val adapters = arrayOf(FollowInfo1Adapter(R.layout.adapter_follow_info1),
            FollowInfo2Adapter(R.layout.adapter_follow_info2),
            FollowInfoAdapter(R.layout.adapter_volunteer))

    override fun requestSuccess(data: Any) {
    }

    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun initView() {
        setToolbar("我关注的人", rightRes = "发消息", rightTextColor = getResColor(R.color.colorPrimary), rightClickListener = this)
        v_tabs.addTab(v_tabs.newTab().setText("个人中心"))
        v_tabs.addTab(v_tabs.newTab().setText("已捐项目"))
        v_tabs.addTab(v_tabs.newTab().setText("参加的义工组织"))
        v_list.layoutManager = LinearLayoutManager(this)
        v_list.adapter = adapters[0]
        adapters.forEach {
            it.setNewData(VRData.getIntData(10))
        }
    }

    override fun initData() {
    }

    override fun initEvent() {
        v_tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                v_list.adapter = adapters[tab.position]
            }
        })
    }

    override fun getLayoutID(): Any = R.layout.activity_follow_info

    override fun onClick(v: View) {
        when (v.id) {
            R.id.toolbar_right -> {
                //发消息
            }
            else -> {
            }
        }
    }
}
