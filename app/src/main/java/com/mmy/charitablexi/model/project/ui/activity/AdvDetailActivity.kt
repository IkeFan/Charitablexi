package com.mmy.charitablexi.model.project.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.project.ui.adapter.PhotoAdapter
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.ProInfoBean
import kotlinx.android.synthetic.main.activity_adv_detail.*
import java.text.SimpleDateFormat

class AdvDetailActivity : BaseActivity<IPresenter<IView>>() {
    var mAdapter = PhotoAdapter(R.layout.item_image)
    override fun initView() {
        setToolbar(getString(R.string.av_detail),true)
        v_img_list.adapter = mAdapter
        v_img_list.layoutManager = LinearLayoutManager(this)
    }

    override fun initData() {
       var bean = intent.getSerializableExtra("sBean") as ProInfoBean.DataBean.AdinfoBean
        v_title.text = bean.title
        v_content.text = bean.content
        var format =  SimpleDateFormat(getString(R.string.date_format))
        v_time.text = format.format(bean.addtime.toLong())
        if(bean.imgs!=null){
            mAdapter.setNewData( bean.imgs.split(","))
        }
    }

    override fun requestSuccess(any: Any) {

    }

    override fun setupDagger(appComponent: AppComponent) {

    }

    override fun getLayoutID(): Any = R.layout.activity_adv_detail
}
