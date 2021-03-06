package com.mmy.charitablexi.model.commun.ui.fragment

import android.support.design.widget.TabLayout
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.mmy.charitablexi.R
import com.mmy.charitablexi.bean.EvBusItemBean
import com.mmy.charitablexi.model.commun.component.DaggerPublicClassComponent
import com.mmy.charitablexi.model.commun.module.PublicClassModule
import com.mmy.charitablexi.model.commun.presenter.PublicClassPresenter
import com.mmy.charitablexi.model.commun.ui.activity.PublishArticleActivity
import com.mmy.charitablexi.model.commun.ui.adapter.TraniAdapter
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseFragment
import com.mmy.frame.data.bean.ClassBean
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.fragment_trani.*

/**
 * @file       OpenClassFragment.ktt.kt
 * @brief      公开课
 * @author     lucas
 * @date       2018/4/21 0021
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class OpenClassFragment : BaseFragment<PublicClassPresenter>(), CommunFragment.OnPopMenuClick{
    var delPosition = -1
    override fun onMenuSelected(v: View) {
        when (v.id) {
            R.id.v_publish -> {
                openActivity(PublishArticleActivity::class.java, "title="+getString(R.string.class_publish))
            }

            R.id.v_share -> {

            }
        }
    }

    override fun requestSuccess(data: Any) {
        if(data is ClassBean){
            when(data.sub){
                "del"->{
                    if(delPosition!=-1)
                        mAdapter.remove(delPosition)
                    delPosition = -1
                }
                else ->{ mAdapter.setNewData(data.data) }
            }
        }
    }

    val mAdapter = TraniAdapter(R.layout.adapter_trani)
    override fun setupDagger(appComponent: AppComponent) {
        DaggerPublicClassComponent
                .builder()
                .publicClassModule(PublicClassModule(this))
                .appComponent(appComponent)
                .build()
                .inject(this)
    }

    override fun getLayoutId(): Int = R.layout.fragment_trani

    override fun initView() {
        v_tabs.addTab(v_tabs.newTab().setText(getString(R.string.theory_of_charity)))
        v_tabs.addTab(v_tabs.newTab().setText(getString(R.string.execute_charity)))
        v_tabs.addTab(v_tabs.newTab().setText(getString(R.string.other)))
        v_list.layoutManager = GridLayoutManager(activity, 2)
        v_list.adapter = mAdapter
        v_tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

//                mIPresenter.getList(type)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0-> mIPresenter.getList(1)
                    1-> mIPresenter.getList(2)
                    2-> mIPresenter.getList(3)
                }
            }
        })
    }

    override fun registerBus(): Boolean = true

    override fun initData() {
        mIPresenter.getList(1)
//        mAdapter.setNewData(VRData.getIntData(10))
    }

    override fun initEvent() {
        mAdapter.onEditArticle = {position, item ->
            openActivity(PublishArticleActivity::class.java, "title = "+getString(R.string.edit_class)
                    +",type = "+(v_tabs.selectedTabPosition+1),mAdapter.getItem(position))
        }
        mAdapter.onDeleted = {position, item->
            delPosition = position
            mIPresenter.delClass(item.id!!)
        }

        mAdapter.onSelected = {_,item->
            openActivity(PublishArticleActivity::class.java, "title = "+getString(R.string.edit_class)
                    +",type = "+(v_tabs.selectedTabPosition+1),item)
        }
    }


    @Subscribe
    fun onClassUpdate( data: EvBusItemBean<ClassBean.DataBean>){
        mIPresenter.getList(v_tabs.selectedTabPosition+1)
    }
}