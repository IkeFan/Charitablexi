package com.mmy.charitablexi.model.personal.ui.activity

import android.content.Intent
import com.bumptech.glide.Glide
import com.mmy.charitablexi.App
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.component.DaggerAboutEditComponent
import com.mmy.charitablexi.model.personal.module.AboutEditModule
import com.mmy.charitablexi.model.personal.presenter.AboutEditPresenter
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.AboutBean
import com.mmy.frame.data.bean.IBean
import com.mmy.frame.helper.PicSelectHelper
import com.mmy.frame.utils.CommonUtil
import com.mmy.frame.utils.Config
import kotlinx.android.synthetic.main.activity_about_edit.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
* @file       AboutEditActivity.kt
* @brief      关于我们编辑
* @author     lucas
* @date       2018/6/14 0014
* @version
* @par        Copyright (c):
* @par History:
*             version: zsr, 2017-09-23
*/
class AboutEditActivity : BaseActivity<AboutEditPresenter>(){
    var imgUrl=""

    override fun requestSuccess(data: Any) {
        if (data is AboutBean) {
            v_desc.setText(data.data.desc)
            Glide.with(this)
                    .load(Config.HOST+data.data.img)
                    .error(R.mipmap.ic_def)
                    .placeholder(R.mipmap.ic_def)
                    .into(v_icon)
            v_address.setText(data.data.url)
        }
        if (data is IBean && data.status==1){
            finish()
        }
    }

    val mPicHelper = PicSelectHelper()

    override fun setupDagger(appComponent: AppComponent) {
        DaggerAboutEditComponent.builder()
                .aboutEditModule(AboutEditModule(this))
                .appComponent(appComponent)
                .build().inject(this)
    }

    override fun initView() {
        setToolbar("修改团队概括")
    }

    override fun initData() {
        mIPresenter.loadData()
    }

    override fun initEvent() {
        v_icon.setOnClickListener {
            mPicHelper.showSelectPicPopup(this,1)
        }
        v_submit.setOnClickListener {
            submit()
        }
    }

    private fun submit() {
        if (imgUrl.isEmpty()){
            "请选择图片".showToast(mFrameApp)
            return
        }
        val address = v_address.text.toString()
        val desc = v_desc.text.toString()
        if (address.isEmpty()){
            "请输入官网地址".showToast(mFrameApp)
            return
        }
        if (desc.isEmpty()){
            "请输入简介".showToast(mFrameApp)
            return
        }
        val body = RequestBody.create(MediaType.parse("multipart/form-mData"), File(imgUrl))
        val builder = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("uid", App.instance.userInfo.id.toString())
                .addFormDataPart("token", App.instance.userInfo.token)
                .addFormDataPart("id", "1")
                .addFormDataPart("name", "慈善喜")
                .addFormDataPart("desc", desc)
                .addFormDataPart("img", CommonUtil.getFileName(imgUrl), body)

        val parts = builder.build().parts()
        mIPresenter.submit(parts)
    }

    override fun getLayoutID(): Any = R.layout.activity_about_edit

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        mPicHelper.onActivityResult(requestCode,resultCode,data,{
            Glide.with(this)
                    .load(it[0].path)
                    .into(v_icon)
            imgUrl = it[0].path
        })
    }

}
