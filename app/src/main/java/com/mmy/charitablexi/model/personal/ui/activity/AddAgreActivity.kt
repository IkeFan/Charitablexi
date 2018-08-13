package com.mmy.charitablexi.model.personal.ui.activity

import android.text.TextUtils
import android.view.View
import com.mmy.charitablexi.App
import com.mmy.charitablexi.R
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_add_agre.*

/**
 * @file       AddAgreActivity.kt
 * @brief      新增协议
 * @author     lucas
 * @date       2018/5/19 0019
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class AddAgreActivity : BaseActivity<IPresenter<*>>() ,View.OnClickListener{
    override fun requestSuccess(any: Any) {
    }

    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun initView() {
        setToolbar("新增协议",rightRes = "完成",rightClickListener = this)
    }

    override fun initData() {
    }

    override fun getLayoutID(): Any = R.layout.activity_add_agre

    override fun onClick(v: View) {
        val title = v_title.text.toString().trim()
        val content = v_content.text.toString().trim()
        if (TextUtils.isEmpty(title)){
            "请输入标题".showToast(mFrameApp)
            return
        }
        if (TextUtils.isEmpty(content)){
            "请输入内容".showToast(mFrameApp)
            return
        }
        App.instance.mBus.post(BusAddAgre(title,content))
        finish()
    }

    class BusAddAgre constructor(val title:String,val content:String)
}
