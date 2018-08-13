package com.mmy.charitablexi.model.personal.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.ui.adapter.UserManagerAdapter
import com.mmy.charitablexi.utils.VRData
import com.mmy.charitablexi.widget.SwipeItemLayout
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_mechanism_manager.*

/**
 * @file       UserManagerFragment.kt
 * @brief      用户管理
 * @author     lucas
 * @date       2018/5/24 0024
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class UserManagerFragment : BaseFragment<IPresenter<*>>() {
    override fun requestSuccess(any: Any) {
    }

    val mAdapter = UserManagerAdapter(R.layout.adapter_volunteer_list)
    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun getLayoutId(): Int = R.layout.fragment_user_manager

    override fun initView() {
        v_list.layoutManager = LinearLayoutManager(getAc())
        v_list.adapter= mAdapter
    }

    override fun initData() {
        mAdapter.setNewData(VRData.getIntData(10))
    }

    override fun initEvent() {
        v_list.addOnItemTouchListener(SwipeItemLayout.OnSwipeItemTouchListener(getAc()))
    }
}