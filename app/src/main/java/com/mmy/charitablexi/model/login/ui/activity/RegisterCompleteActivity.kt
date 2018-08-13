package com.mmy.charitablexi.model.login.ui.activity

import android.content.Intent
import android.view.View
import com.bumptech.glide.Glide
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.login.component.DaggerRegisterCompleteComponent
import com.mmy.charitablexi.model.login.module.RegisterCompleteModule
import com.mmy.charitablexi.model.login.presenter.RegisterCompletePresenter
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.helper.PicSelectHelper
import jp.wasabeef.glide.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.activity_register_complete.*

/**
 * @file       RegisterCompleteActivity.kt
 * @brief      完成注册
 * @author     lucas
 * @date       2018/4/25 0025
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class RegisterCompleteActivity : BaseActivity<RegisterCompletePresenter>(), View.OnClickListener {
    override fun requestSuccess(any: Any) {
    }

    lateinit var mPopup: PicSelectHelper

    override fun setupDagger(appComponent: AppComponent) {
        DaggerRegisterCompleteComponent.builder()
                .appComponent(appComponent)
                .registerCompleteModule(RegisterCompleteModule(this))
                .build().inject(this)
    }

    override fun initView() {
        setToolbar("完成注册")
        mPopup = PicSelectHelper()
    }

    override fun initData() {
    }

    override fun initEvent() {
        super.initEvent()
        arrayOf(v_complete,v_head).setViewListener(this)
    }

    override fun getLayoutID(): Any = R.layout.activity_register_complete

    override fun onClick(p0: View?) {
        when (p0) {
            v_complete -> {
                if (v_nickname.text.isEmpty()) {
                    "请输入昵称".showToast(mFrameApp)
                    return
                }

//                mIPresenter.register(intent.getStringExtra("phone"), v_pwd.text.toString().trim(), v_nickname.text.toString().trim())
            }
            v_head -> {
                mPopup.showSelectPicPopup(this, 1)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        mPopup.onActivityResult(requestCode, resultCode, data, {
            val photoBean = it.get(0)
            Glide.with(this).load("file://"+photoBean.path).asBitmap().transform(CropCircleTransformation(this)).into(v_head)
        })
    }
}
