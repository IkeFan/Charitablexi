package com.mmy.charitablexi.model.personal.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bumptech.glide.Glide
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.component.DaggerAboutComponent
import com.mmy.charitablexi.model.personal.module.AboutModule
import com.mmy.charitablexi.model.personal.presenter.AboutPresenter
import com.mmy.charitablexi.model.personal.ui.adapter.AboutAdapter
import com.mmy.charitablexi.model.personal.ui.adapter.AboutAdapter2
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.AboutBean
import com.mmy.frame.utils.Config
import kotlinx.android.synthetic.main.activity_about.*

/**
* @file       AboutActivity.kt
* @brief      关于我们
* @author     lucas
* @date       2018/6/9 0009
* @version
* @par        Copyright (c):
* @par History:
*             version: zsr, 2017-09-23
*/
class AboutActivity : BaseActivity<AboutPresenter>() ,View.OnClickListener{
    val mAdapter1 = AboutAdapter(R.layout.adapter_about)
    val mAdapter2 = AboutAdapter2(R.layout.adapter_about)

    override fun requestSuccess(data: Any) {
        if (data is AboutBean){
            (Config.HOST+data.data.img).ld()
            mAdapter1.setNewData(data.data.teams)
            mAdapter2.setNewData(data.data.linkmans)
            Glide.with(this)
                    .load(Config.HOST+data.data.img)
                    .into(v_icon)
            v_name.text = "关于${data.data.name}"
            v_desc.text = data.data.desc
        }
    }

    override fun setupDagger(appComponent: AppComponent) {
        DaggerAboutComponent.builder()
                .aboutModule(AboutModule(this))
                .appComponent(appComponent)
                .build().inject(this)
    }

    override fun initView() {
        setToolbar("关于我们",rightRes = "分享",rightClickListener = this)
        v_list_about.layoutManager = LinearLayoutManager(this)
        v_list_about.adapter = mAdapter1
        v_list_contact.layoutManager = LinearLayoutManager(this)
        v_list_contact.adapter =mAdapter2
        v_list_about.isNestedScrollingEnabled = false
        v_list_contact.isNestedScrollingEnabled = false
//        v_tabs.addTab(v_tabs.newTab().setText("关于我们"))
//        v_tabs.addTab(v_tabs.newTab().setText("团队概括"))
//        v_tabs.addTab(v_tabs.newTab().setText("联系我们"))
    }

    override fun initData() {
        mIPresenter.loadData()
    }

    override fun getLayoutID(): Any =R.layout.activity_about

    override fun onClick(v: View) {
        when (v.id) {
            R.id.toolbar_right -> {
                //分享
            }
            else -> {
            }
        }
    }

}
