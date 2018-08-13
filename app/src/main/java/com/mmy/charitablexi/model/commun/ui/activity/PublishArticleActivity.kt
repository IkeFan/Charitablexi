package com.mmy.charitablexi.model.commun.ui.activity

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.View
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.commun.ui.adapter.PublishArticleAdapter
import com.mmy.frame.AppComponent
import com.mmy.frame.base.adapter.BaseRecyclerViewAdapter
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.helper.PicSelectHelper
import kotlinx.android.synthetic.main.activity_publish_article.*
import java.util.*
/**
* @file       PublishArticleActivity.kt
* @brief      发表文章
* @author     lucas
* @date       2018/5/19 0019
* @version
* @par        Copyright (c):
* @par History:
*             version: zsr, 2017-09-23
*/
class PublishArticleActivity : BaseActivity<IPresenter<*>>(), BaseRecyclerViewAdapter.OnItemClickListener {
    override fun requestSuccess(any: Any) {
    }

    override fun onItemClick(view: View?, position: Int) {
        if (position==mAdapter.itemCount-1)
            mPicHelper.getPicFormAlbum(this,3)
    }

    lateinit var mAdapter:PublishArticleAdapter
    val mPicHelper = PicSelectHelper()

    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun initView() {
        setToolbar(intent.getStringExtra("title"))
        v_list.layoutManager = LinearLayoutManager(this,OrientationHelper.HORIZONTAL,false)
        mAdapter = PublishArticleAdapter(this)
        v_list.adapter = mAdapter
    }

    override fun initData() {
    }

    override fun initEvent() {
        mAdapter.setOnItemClickListener(this)
    }

    override fun getLayoutID(): Any = R.layout.activity_publish_article

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        mPicHelper.onActivityResult(requestCode,resultCode,data,{
            mAdapter.setData(LinkedList(it))
        })
    }

}
