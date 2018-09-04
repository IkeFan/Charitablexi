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
import com.mmy.frame.utils.CommonUtil
import jp.wasabeef.glide.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.activity_register_complete.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

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
    var mPath: String? = null
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
        setToolbar("Finish register")
        mPopup = PicSelectHelper()
    }

    override fun initData() {
    }

    override fun initEvent() {
        super.initEvent()
        arrayOf(v_complete, v_head).setViewListener(this)
    }

    override fun getLayoutID(): Any = R.layout.activity_register_complete

    override fun onClick(p0: View?) {
        when (p0) {
            v_complete -> {
                if (v_nickname.text.isEmpty()) {
                    getString(R.string.input_nickname).showToast(mFrameApp)
                    return
                }
                if (mPath == null) {
                    getString(R.string.chose_avatar).showToast(mFrameApp)
                    return
                }

                var builder = MultipartBody.Builder()
                        .addFormDataPart("email", intent.getStringExtra("phone"))
                        .addFormDataPart("password", intent.getStringExtra("pwd"))
                        .addFormDataPart("conpassword", intent.getStringExtra("pwd"))
                        .addFormDataPart("name", v_nickname.text.toString().trim())

                val body = RequestBody.create(MediaType.parse("multipart/form-mData"), File(mPath))
                builder.addFormDataPart("avatar", CommonUtil.getFileName(mPath), body)
                mIPresenter.register(builder.build().parts())
            }
            v_head -> {
                mPopup.showSelectPicPopup(this, 1)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data!=null) {
            mPopup.onActivityResult(requestCode, resultCode, data, {
                val photoBean = it[0]
                mPath = photoBean.path
                Glide.with(this).load("file://" + photoBean.path).asBitmap().transform(CropCircleTransformation(this)).into(v_head)
            })
        }
    }
}
