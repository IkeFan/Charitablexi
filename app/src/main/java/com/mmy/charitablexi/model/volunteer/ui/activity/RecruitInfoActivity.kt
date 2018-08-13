package com.mmy.charitablexi.model.volunteer.ui.activity

import android.view.View
import com.mmy.charitablexi.R
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseActivity
/**
* @file       RecruitInfoActivity.kt
* @brief      招聘详情
* @author     lucas
* @date       2018/5/17 0017
* @version
* @par        Copyright (c):
* @par History:
*             version: zsr, 2017-09-23
*/
class RecruitInfoActivity : BaseActivity<IPresenter<*>>(), View.OnClickListener {
    override fun requestSuccess(any: Any) {
    }

    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun initView() {
        setToolbar("招聘详情", rightRes = "修改招聘", rightTextColor = getResColor(R.color.colorPrimary), rightClickListener = this)
    }

    override fun initData() {
    }

    override fun getLayoutID(): Any = R.layout.activity_recruit_info

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.toolbar_right -> {
                //修改招聘
            }
            else -> {
            }
        }
    }

}
