package com.mmy.charitablexi.model.project.ui.activity

import android.text.Html
import com.mmy.charitablexi.R
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_request_support.*

/**
 * @file       RequestSupportActivity.kt
 * @brief      成为资助方
 * @author     lucas
 * @date       2018/5/15 0015
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class RequestSupportActivity : BaseActivity<IPresenter<*>>() {
    override fun requestSuccess(any: Any) {
    }

    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun initView() {
        setToolbar("成为资助方")
        v_str1.text = Html.fromHtml(getResStr(R.string._1_app_font_color_b61e17_font2)+
                "<font color=\'#B61E17\'>"+getResStr(R.string._1_app_font_color_b61e17_font)+"</font>")
        v_str2.text = Html.fromHtml(getResStr(R.string._2_font_color_b61e17_font2)+
                "<font color=\'#B61E17\'>"+getResStr(R.string._2_font_color_b61e17_font)+"</font>")
    }

    override fun initData() {
    }

    override fun getLayoutID(): Any = R.layout.activity_request_support

}
