package com.mmy.charitablexi.model.commun.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.mmy.charitablexi.R
import com.mmy.charitablexi.bean.EvBusItemBean
import com.mmy.charitablexi.model.commun.component.DaggerPersonCenterComponent
import com.mmy.charitablexi.model.commun.module.PersonalCenterModule
import com.mmy.charitablexi.model.commun.presenter.PersonalCenterPresenter
import com.mmy.charitablexi.model.commun.ui.activity.PublicCardActivity
import com.mmy.charitablexi.model.commun.ui.adapter.PersonCenterAdapter
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseFragment
import com.mmy.frame.data.bean.PersonalCenterBean
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.fragment_trani.*

/**
 * @file       OpenClassFragment.ktt.kt
 * @brief      个人中心
 * @author     lucas
 * @date       2018/4/21 0021
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class PersonalCenterFragment : BaseFragment<PersonalCenterPresenter>(), CommunFragment.OnPopMenuClick, View.OnClickListener {
    override fun onMenuSelected(v: View) {
        when (v.id) {
            R.id.v_publish -> {
                openActivity(PublicCardActivity::class.java)
            }
            R.id.v_edit -> {
                if(v_select_all.visibility == View.VISIBLE) {
                    v_select_all.visibility = View.GONE
                    mAdapter.changeEditMode()
                }
                else{
                    v_select_all.visibility = View.VISIBLE
                    mAdapter.changeEditMode()
                }
            }
            R.id.v_del -> {
                mAdapter.delSelected()
                asyAdapterStatus()
            }
            R.id.v_share -> {

            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.v_select_all -> {
                mAdapter.changeSelectAll()
                v_select_all_cb.isChecked = !v_select_all_cb.isChecked
            }
        }
    }

    override fun requestSuccess(any: Any) {
        if (any is PersonalCenterBean) {
            mAdapter.setNewData(any.data)
        }
    }

    val mAdapter = PersonCenterAdapter(R.layout.adapter_commun_person_center)
    override fun setupDagger(appComponent: AppComponent) {
        DaggerPersonCenterComponent.builder()
                .appComponent(appComponent)
                .personalCenterModule(PersonalCenterModule(this))
                .build()
                .inject(this)
    }

    override fun getLayoutId(): Int = R.layout.fragment_trani

    override fun initView() {
        v_list.layoutManager = LinearLayoutManager(activity)
        v_list.adapter = mAdapter
        v_tabs.visibility = View.GONE
    }

    override fun initData() {
        mIPresenter.getList(mFramApp.userInfo.id!!)
    }

    override fun initEvent() {
        arrayOf(v_select_all).setViewListener(this)
    }

    override fun onResume() {
        super.onResume()
        asyAdapterStatus()
    }

    fun asyAdapterStatus(){
        if(mAdapter.editMode){
            v_select_all.visibility = View.VISIBLE
        }else{
            v_select_all.visibility = View.GONE
        }
        v_select_all_cb.isChecked = mAdapter.isSelectALL
    }

    override fun registerBus(): Boolean = true

    @Subscribe
    fun onCardChange(bean: EvBusItemBean<PersonalCenterBean.PersonalCenter>){
//        if(bean.positon!=-1){
//            mAdapter.remove(bean.positon)
//            mAdapter.addData(bean.positon, bean.model)
//        }else{
//            mIPresenter.getList(mFramApp.userInfo.id!!)
//        }
        mIPresenter.getList(mFramApp.userInfo.id!!)
    }
}