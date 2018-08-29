package com.mmy.charitablexi.model.project.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mmy.charitablexi.R
import com.mmy.charitablexi.base.BaseIViewModule
import com.mmy.charitablexi.base.DaggerBaseComponent
import com.mmy.charitablexi.model.project.presenter.SponsorInfoPresenter
import com.mmy.charitablexi.model.project.ui.adapter.SupportProAdapter
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.SponsorDetailBean
import com.mmy.frame.helper.GlideCircleTransform
import com.mmy.frame.utils.Config
import kotlinx.android.synthetic.main.activity_sponsor.*

class SponsorActivity : BaseActivity<SponsorInfoPresenter>() {
    var mAdapter = SupportProAdapter(R.layout.adapter_support_pro_item)

    override fun setupDagger(appComponent: AppComponent) {
        DaggerBaseComponent.builder()
                .appComponent(appComponent)
                .baseIViewModule(BaseIViewModule(this))
                .build()
                .inject(this)
    }

    override fun initView() {
        setToolbar(getString(R.string.org_detail), true)
        v_sponsored_list.layoutManager = LinearLayoutManager(this)
        v_sponsored_list.adapter = mAdapter
    }

    override fun initData() {
        val id = intent.getStringExtra("id").trim()
        if(!id.isNullOrEmpty() && id!="null") {
            mIPresenter.getSponsorDetail(intent.getStringExtra("id").toInt())
        }
    }

    override fun getLayoutID(): Any = R.layout.activity_sponsor

    override fun requestSuccess(any: Any) {
        if(any is SponsorDetailBean){
            v_name.text = any?.data?.name
            if(any.data?.name==null){
                v_name.text= getString(R.string.no_define)
            }
            Glide.with(this).load(Config.HOST + any?.data?.avatar)
                    .bitmapTransform(GlideCircleTransform(this))
                    .placeholder(R.mipmap.ic_user_def).error(R.mipmap.ic_user_def).into(v_portrait)
            if(any.data?.lists!=null){
                v_count.text = any.data?.counts.toString() + getString(R.string.sponsored_count)
                mAdapter.setNewData(any.data?.lists)
            }
        }
    }
}
