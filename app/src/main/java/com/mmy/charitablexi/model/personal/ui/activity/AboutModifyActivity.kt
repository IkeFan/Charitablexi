package com.mmy.charitablexi.model.personal.ui.activity

import android.view.View
import com.mmy.charitablexi.R
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_about_modify.*

/**
 * @file       AboutModifyActivity.kt
 * @brief      修改关于我们
 * @author     lucas
 * @date       2018/6/14 0014
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class AboutModifyActivity : BaseActivity<IPresenter<*>>(), View.OnClickListener {
    override fun requestSuccess(data: Any) {
    }

    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun initView() {
        setToolbar("修改关于我们")
    }

    override fun initData() {
    }

    override fun initEvent() {
        arrayOf(v_about, v_genera, v_contact).setViewListener(this)
    }

    override fun onClick(v: View) {
        when (v) {
            v_about -> {//关于我们
                openActivity(AboutEditActivity::class.java)
            }
            v_genera -> {//团队概括
                openActivity(AboutGeneraManagerActivity::class.java)
            }
            v_contact -> {//联系我们

            }

        }
    }

    override fun getLayoutID(): Any = R.layout.activity_about_modify

}
