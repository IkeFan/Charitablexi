package com.mmy.charitablexi.model.personal.ui.activity

import android.support.design.widget.TabLayout
import android.view.View
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.ui.fragment.MechanismManagerFragment
import com.mmy.charitablexi.model.personal.ui.fragment.UserManagerFragment
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_publish_project.*

/**
 * @file       UserManagerActivity.kt
 * @brief      用户管理
 * @author     lucas
 * @date       2018/5/24 0024
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class UserManagerActivity : BaseActivity<IPresenter<*>>(), View.OnClickListener, TabLayout.OnTabSelectedListener {
    override fun requestSuccess(any: Any) {
    }

    val tabStrs = arrayOf("机构", "用户")

    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun initView() {
        setToolbar("用户管理", rightRes = "发消息", rightTextColor = R.color.colorPrimary, rightClickListener = this)
        v_tabs.addTab(v_tabs.newTab().setText(tabStrs[0]))
        v_tabs.addTab(v_tabs.newTab().setText(tabStrs[1]))
        supportFragmentManager.beginTransaction()
                .add(R.id.v_content, UserManagerFragment(), tabStrs[1])
                .add(R.id.v_content, MechanismManagerFragment(), tabStrs[0])
                .commit()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        val fragment2 = supportFragmentManager.findFragmentByTag(tabStrs[1])
        supportFragmentManager.beginTransaction().hide(fragment2).commit()
    }

    override fun initData() {
    }

    override fun initEvent() {
        v_tabs.addOnTabSelectedListener(this)
    }

    override fun getLayoutID(): Any = R.layout.activity_user_manager

    override fun onClick(v: View) {
        when (v.id) {
            R.id.toolbar_right -> {
                //发消息
            }
            else -> {
            }
        }
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabSelected(tab: TabLayout.Tab) {
            val fragment1 = supportFragmentManager.findFragmentByTag(tabStrs[0])
            val fragment2 = supportFragmentManager.findFragmentByTag(tabStrs[1])
        if (tab.position == 0) {
            supportFragmentManager.beginTransaction().show(fragment1).hide(fragment2).commit()
        }else{
            supportFragmentManager.beginTransaction().show(fragment2).hide(fragment1).commit()
        }
    }

}
