package com.mmy.charitablexi.model.personal.ui.activity

import android.content.Intent
import android.text.TextUtils
import com.mmy.charitablexi.App
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.component.DaggerTeamEditComponent
import com.mmy.charitablexi.model.personal.module.TeamEditModule
import com.mmy.charitablexi.model.personal.presenter.TeamEditPresenter
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.AboutBean
import com.mmy.frame.data.bean.IBean
import com.mmy.frame.utils.CommonUtil
import kotlinx.android.synthetic.main.activity_team_edit.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * @file       TeamEditActivity.kt
 * @brief      团队新增
 * @author     lucas
 * @date       2018/6/14 0014
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class TeamEditActivity : BaseActivity<TeamEditPresenter>() {
    var mImag: String? = null
    var mBean: AboutBean.DataBean.TeamsBean? = null

    override fun requestSuccess(data: Any) {
        if (data is IBean && data.status == 1)
            finish()
    }

    override fun setupDagger(appComponent: AppComponent) {
        DaggerTeamEditComponent.builder()
                .appComponent(appComponent)
                .teamEditModule(TeamEditModule(this))
                .build().inject(this)
    }

    override fun initView() {

        val extra = intent.getSerializableExtra("sBean")
        if (extra != null){
            setToolbar("修改")
            mBean = extra as AboutBean.DataBean.TeamsBean
            v_name.setHintName(mBean?.team_name!!)
            v_desc.setHintName(mBean?.team_desc!!)
            v_icon.setHintName("1张")
        }
        else{
            setToolbar("添加")
        }
    }

    override fun initData() {
    }

    override fun initEvent() {
        v_submit.setOnClickListener {
            submit()
        }
        v_icon.listener = {
            if (it.uploadUris.size > 0)
                mImag = it.uploadUris[0].path
        }
    }

    private fun submit() {
        if (TextUtils.isEmpty(mImag)) {
            "请选择图片".showToast(mFrameApp)
            return
        }
        val name = v_name.hintStr
        val desc = v_desc.hintStr
        if (name.isEmpty()) {
            "请输入名称".showToast(mFrameApp)
            return
        }
        if (desc.isEmpty()) {
            "请输入简介".showToast(mFrameApp)
            return
        }
        val body = RequestBody.create(MediaType.parse("multipart/form-mData"), File(mImag))
        val builder = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("uid", App.instance.userInfo.id.toString())
                .addFormDataPart("token", App.instance.userInfo.token)
                .addFormDataPart("team_name", name)
                .addFormDataPart("team_desc", desc)
                .addFormDataPart("team_img", CommonUtil.getFileName(mImag), body)
        if (mBean != null)
            builder.addFormDataPart("id", mBean?.id)

        val parts = builder.build().parts()
        mIPresenter.submit(parts)
    }

    override fun getLayoutID(): Any = R.layout.activity_team_edit
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        v_icon.onActivityResult(requestCode, resultCode, data)
    }
}
