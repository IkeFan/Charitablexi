package com.mmy.charitablexi.model.personal.ui.activity

import android.app.Activity
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.component.DaggerPublishProjectComponent
import com.mmy.charitablexi.model.personal.module.PublishProjectModule
import com.mmy.charitablexi.model.personal.presenter.PublishProjectPresenter
import com.mmy.charitablexi.model.personal.ui.adapter.PublishStubAdapter
import com.mmy.charitablexi.model.personal.view.ProjectListView
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.ProListBean
import kotlinx.android.synthetic.main.activity_publish_project.*

/**
* @file       PublishProjectActivity.kt
* @brief      发起项目
* @author     lucas
* @date       2018/5/28 0028
* @version
* @par        Copyright (c):
* @par History:
*             version: zsr, 2017-09-23
*/
class PublishProjectActivity : BaseActivity<PublishProjectPresenter>(),View.OnClickListener, ProjectListView {
    override fun requestResult(proType: Int, data: List<ProListBean.DataBean>) {
        if(proType == 3){
            mIPresenter.getUnPassPro()
        }else if(proType == 1){
            adapters[1].setNewData(data)
        }

    }

    override fun requestSuccess(data: Any) {
    }

    override fun initData() {
        mIPresenter.getPassPro()
    }

    var mAdapter:PublishPagerAdapter? = null
    val adapters = arrayOf(PublishStubAdapter(R.layout.adapter_publish),PublishStubAdapter(R.layout.adapter_publish))

    override fun setupDagger(appComponent: AppComponent) {
        DaggerPublishProjectComponent.builder()
                .appComponent(appComponent)
                .publishProjectModule(PublishProjectModule(this))
                .build().inject(this)
    }

    override fun initView() {
       val  tabStr = arrayOf(getString(R.string.passed),getString(R.string.un_passed))
        setToolbar(getString(R.string.publish_project),true,getString(R.string.publish),getResColor(R.color.colorPrimaryDark),this)
        tabStr.forEach { v_tabs.addTab(v_tabs.newTab().setText(it)) }
        v_tabs.setupWithViewPager(v_pager)
        mAdapter = PublishPagerAdapter(this,tabStr,adapters)
        v_pager.adapter = mAdapter
    }

    override fun getLayoutID(): Any =R.layout.activity_publish_project

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.toolbar_right -> {
                openActivity(EditProjectActivity::class.java)
            }
            else -> {
            }
        }
    }

    class PublishPagerAdapter(val actionBar: Activity, val tabStr: Array<String>,val adapters: Array<PublishStubAdapter>) : PagerAdapter(){

        override fun isViewFromObject(view: View, o: Any): Boolean = view == o

        override fun getCount(): Int = tabStr.size

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val recyclerView = LayoutInflater.from(actionBar).inflate(R.layout.view_recyclerview,null,false) as RecyclerView
            container.addView(recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(actionBar)
            recyclerView.adapter = adapters[position]
            return recyclerView
        }

        override fun destroyItem(container: ViewGroup, position: Int, o: Any) {
            super.destroyItem(container, position, o)
            container.removeView(o as View?)
        }

        override fun getPageTitle(position: Int): CharSequence = tabStr[position]
    }
}
