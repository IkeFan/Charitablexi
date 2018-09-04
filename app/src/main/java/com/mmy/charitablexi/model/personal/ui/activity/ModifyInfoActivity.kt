package com.mmy.charitablexi.model.personal.ui.activity

import android.content.Intent
import android.view.View
import com.bumptech.glide.Glide
import com.mmy.charitablexi.App
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.component.DaggerModifyInfoComponent
import com.mmy.charitablexi.model.personal.module.ModifyInfoModule
import com.mmy.charitablexi.model.personal.presenter.ModifyInfoPresenter
import com.mmy.charitablexi.model.volunteer.ui.activity.EditActivity
import com.mmy.charitablexi.widget.MyEditView
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.IBean
import com.mmy.frame.helper.GlideCircleTransform
import com.mmy.frame.helper.PicSelectHelper
import com.mmy.frame.utils.CommonUtil
import com.mmy.frame.utils.Config
import com.mmy.frame.utils.StringUtil
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.activity_modif_info.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * @file       ModifyInfoActivityy.kt
 * @brief      修改个人信息
 * @author     lucas
 * @date       2018/4/21 0021
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class ModifyInfoActivity : BaseActivity<ModifyInfoPresenter>(), View.OnClickListener {
    var mPopup = PicSelectHelper()
    var mHeaderUrl: String? = null
    var mNewName: String? = null

    override fun requestSuccess(data: Any) {
        if (data is IBean)
            data.info.showToast(mFrameApp)
    }

    override fun setupDagger(appComponent: AppComponent) {
        DaggerModifyInfoComponent.builder()
                .appComponent(appComponent)
                .modifyInfoModule(ModifyInfoModule(this))
                .build().inject(this)
    }

    override fun initView() {
        setToolbar("个人信息", rightRes = "保存", rightTextColor = resources.getColor(R.color.colorPrimary), rightClickListener = this)
        val userInfo = App.instance.userInfo
        Glide.with(this).load(Config.HOST +userInfo.userBean?.avatar)
                .bitmapTransform(GlideCircleTransform(this))
                .placeholder(R.mipmap.ic_user_def).error(R.mipmap.ic_user_def).into(v_header)
        v_name.text = userInfo.name
        v_phone.text = StringUtil.formatModeil(userInfo.mobile)
    }

    override fun initData() {
    }

    override fun initEvent() {
        arrayOf(v_upload_header, v_edit_name).setViewListener(this)
    }

    override fun getLayoutID(): Any = R.layout.activity_modif_info

    override fun onClick(v: View) {
        when (v.id) {
            R.id.v_upload_header -> {
                mPopup.showSelectPicPopup(this, 1)
            }
            R.id.v_edit_name -> {
                openActivity(EditActivity::class.java, "title=修改姓名,hint=请输入姓名,tag=$tag")
            }
            R.id.toolbar_right -> {
                save()
            }
        }
    }

    private fun save() {
        if (mHeaderUrl == null && mNewName == null) {
            finish()
            return
        }
        val builder = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
        builder.addFormDataPart("uid", App.instance.userInfo.id.toString())
        builder.addFormDataPart("token", App.instance.userInfo.token)
        if (mNewName != null)
            builder.addFormDataPart("name", mNewName)
        if (mHeaderUrl != null) {
            val body = RequestBody.create(MediaType.parse("multipart/form-mData"), File(mHeaderUrl))
            builder.addFormDataPart("avatar", CommonUtil.getFileName(mHeaderUrl), body)
        }
        mIPresenter.submit(builder.build().parts())
    }

    val tag = System.currentTimeMillis()

    @Subscribe
    fun onEdit(data: MyEditView.DataBean) {
        if (data.tag == tag.toString()){
            v_name.text = data.text
            mNewName = data.text
        }
    }

    override fun registerBus(): Boolean = true

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mPopup.onActivityResult(requestCode, resultCode, data!!, {
            if (it.size > 0) {
                Glide.with(this).load(it[0].path).transform(GlideCircleTransform(this)).into(v_header)
                mHeaderUrl = it[0].path
            }
        })
    }

}
