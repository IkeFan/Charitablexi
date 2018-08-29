package com.mmy.charitablexi.model.project.ui.activity

import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.project.presenter.ThankGiveLovePresenter
import com.mmy.charitablexi.widget.HeartLayout
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_thank_give_love.*

/**
 * @file       ThankGiveLoveActivity.kt
 * @brief      感谢送爱心
 * @author     lucas
 * @date       2018/5/16 0016
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class ThankGiveLoveActivity : BaseActivity<ThankGiveLovePresenter>() {
    override fun requestSuccess(any: Any) {
    }

    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun initView() {
        setToolbar("项目名称")
        val stars = intent.getStringExtra("star").toInt()
        val heartLayout = findViewById<HeartLayout>(R.id.v_loves)
        stars.ld()
        for (i in 0 until stars / 100)
            handler.postDelayed({
                heartLayout.addFavor()
            }, 100L*i)
        v_love_value.text = stars.toString()+getString(R.string.love_value)
    }

    override fun initData() {
    }

    override fun getLayoutID(): Any = R.layout.activity_thank_give_love


}
