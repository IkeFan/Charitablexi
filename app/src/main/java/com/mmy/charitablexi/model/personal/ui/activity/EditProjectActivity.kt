package com.mmy.charitablexi.model.personal.ui.activity

import android.content.Intent
import android.support.annotation.IdRes
import android.text.TextUtils
import android.view.View
import com.bigkoo.pickerview.TimePickerView
import com.google.gson.Gson
import com.mmy.charitablexi.App
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.component.DaggerEditProjectComponent
import com.mmy.charitablexi.model.personal.module.EditProjectModule
import com.mmy.charitablexi.model.personal.presenter.EditProjectPresenter
import com.mmy.charitablexi.widget.MyEditView
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.AdvListBean
import com.mmy.frame.data.bean.AgreListBean
import com.mmy.frame.data.bean.ChoiceTypeBean
import com.mmy.frame.data.bean.ProInfoBean
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.activity_edit_project.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * @file       EditProjectActivity.kt
 * @brief      发起项目
 * @author     lucas
 * @date       2018/5/19 0019
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class EditProjectActivity : BaseActivity<EditProjectPresenter>(), View.OnClickListener {
    var mProjectInfo:ProInfoBean.DataBean? = null

    override fun requestSuccess(data: Any) {
        if(data is ChoiceTypeBean){
            data.data.forEach{
                if(it.id.toInt() == mProjectInfo?.typeid?.toInt()){
                   v_2_pro_type.setHintName(it?.name!!)
                }
            }
        }
    }

    var type = "user"

    var zuIds: ChoiceSupportActivity.SupportData? = null
    var zxId = ""
    var typeid = ""
    var advid = ""
    var agreid = ""
    //图片地址
    var imgPaths: String? = null

    override fun setupDagger(appComponent: AppComponent) {
        DaggerEditProjectComponent.builder()
                .appComponent(appComponent)
                .editProjectModule(EditProjectModule(this))
                .build().inject(this)
    }

    override fun initView() {
        if(intent.hasExtra("sBean")){
            mProjectInfo = intent.getSerializableExtra("sBean") as ProInfoBean.DataBean?
            setToolbar(getString(R.string.fix))
        }else{
            setToolbar(getString(R.string.publish_project))
        }
        when (mFrameApp?.userInfo?.userLevel    ) {
            0 -> {
                //普通用户
                arrayOf(v_2_agreement, v_2_love, v_2_pro_img, v_2_pro_type, v_2_time, v_2_volun_count, v_2_adv).forEach {
                    it.visibility = View.GONE
                }
                v_1_1.visibility = View.VISIBLE
            }
            1 -> {
                //平台
                arrayOf(v_2_agreement, v_2_love, v_2_pro_img, v_2_pro_type, v_2_time, v_2_volun_count, v_2_adv).forEach {
                    it.visibility = View.VISIBLE
                }
                v_1_1.visibility = View.GONE
            }
            else->{
                //普通用户
                arrayOf(v_2_agreement, v_2_love, v_2_pro_img, v_2_pro_type, v_2_time, v_2_volun_count, v_2_adv).forEach {
                    it.visibility = View.GONE
                }
                v_1_1.visibility = View.VISIBLE
            }
        }
    }

    override fun initData() {
        if(mProjectInfo!=null){
            v_pro_name.setHintName(mProjectInfo?.name!!)
            v_pro_info.setHintName(mProjectInfo?.description!!)
            v_2_love.setHintName(mProjectInfo?.lovesum!!.toString())
            v_2_volun_count.setHintName(mProjectInfo?.volunteers!!)
            v_address.setHintName(mProjectInfo?.addr!!)

            val format = SimpleDateFormat(getString(R.string.date_format))
            var calendar = Calendar.getInstance()
            v_2_time.setHintName(format.format(calendar.time))

            if(mProjectInfo?.adinfo!=null){
                v_2_adv.setHintName(mProjectInfo?.adinfo?.title!!)
            }
            mIPresenter.getTypeList()
        }
    }

    override fun initEvent() {
        arrayOf(v_submit, v_zid, v_zxid, v_2_time, v_2_pro_type, v_2_adv, v_2_agreement).setViewListener(this)
        v_2_pro_img.listener = {
            if (it.uploadUris.size > 0)
                imgPaths = it.uploadUris[0].path
        }
    }

    override fun getLayoutID(): Any = R.layout.activity_edit_project

    override fun registerBus(): Boolean = true

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.v_submit -> {
                submit()
            }
            R.id.v_zid -> {
                openActivity(ChoiceSupportActivity::class.java, "type=zu,title=资助方")
            }
            R.id.v_zxid -> {
                openActivity(SearchSupportActivity::class.java, "type=zx,title=执行方")
            }
            R.id.v_2_time -> {
                showPickerDate(R.id.v_2_time)
            }
            R.id.v_2_pro_type -> {
                openActivity(ChoiceTypeActivity::class.java, "title=选择类型")
            }
            R.id.v_2_adv -> {
                openActivity(ChoiceAdvActivity::class.java)
            }
            R.id.v_2_agreement -> {
                openActivity(ChoiceAgreActivity::class.java, "title=选择协议")
            }
        }
    }

    //提交数据
    private fun submit() {
        when (type) {
            "user" -> {
                arrayOf(v_pro_name, v_pro_info, v_zid, v_zxid, v_address).forEach {
                    val proName = it.hintStr
                    if (TextUtils.isEmpty(proName)) {
                        "请输入${it.nameStr}".showToast(mFrameApp)
                        return@submit
                    }
                }
                val parts = MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("uid", App.instance.userInfo.id!!.toString())
                        .addFormDataPart("name", v_pro_name.hintStr)
                        .addFormDataPart("description", v_pro_info.hintStr)
                        .addFormDataPart("zid", Gson().toJson(zuIds))
                        .addFormDataPart("zxid", zxId)
                        .addFormDataPart("addr", v_address.hintStr)
                        .build().parts()
                mIPresenter.subimt(parts)
            }
            "plate" -> {
                arrayOf(v_pro_name, v_pro_info, v_zid, v_zxid, v_address, v_2_agreement, v_2_love,
                        v_2_pro_img, v_2_pro_type, v_2_time, v_2_volun_count, v_2_adv).forEach {
                    val proName = it.hintStr
                    if (TextUtils.isEmpty(proName)) {
                        "请输入${it.nameStr}".showToast(mFrameApp)
                        return@submit
                    }
                }
                if (imgPaths == null && mProjectInfo==null) {
                    "请选择项目图片".showToast(mFrameApp)
                    return
                }

                val builder = MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("uid", App.instance.userInfo.id!!.toString())
                        .addFormDataPart("name", v_pro_name.hintStr)
                        .addFormDataPart("typeid", typeid)
                        .addFormDataPart("description", v_pro_info.hintStr)
                        .addFormDataPart("lovesum", v_2_love.hintStr)
                        .addFormDataPart("volunteers", v_2_volun_count.hintStr)
                        .addFormDataPart("zid", Gson().toJson(zuIds))
                        .addFormDataPart("zxid", zxId)
                        .addFormDataPart("endtime", v_2_time.hintStr)
                        .addFormDataPart("advsid", advid)
                        .addFormDataPart("xyid", agreid)
                        .addFormDataPart("addr", v_address.hintStr)

                imgPaths?.split(",")?.forEach {
                    var file = File(it)
                    val body = RequestBody.create(MediaType.parse("multipart/form-data"), file)
                    builder.addFormDataPart("img", file.name, body)
                }

                mIPresenter.subimt(builder.build().parts())
            }
        }

    }

    //显示日期选择器
    private fun showPickerDate(@IdRes id: Int) {
        val text = findViewById<MyEditView>(id)
        val pickerView = TimePickerView.Builder(this, TimePickerView.OnTimeSelectListener { date, v ->
            val format = SimpleDateFormat("yyyy年 MM月 dd日")
            val dateStr = format.format(date)
            text.setHintName(dateStr)
        }).setType(booleanArrayOf(true, true, true, false, false, false)).build()
        pickerView.setDate(Calendar.getInstance())
        if (!pickerView.isShowing())
            pickerView.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        v_2_pro_img.onActivityResult(requestCode, resultCode, data)
    }

    @Subscribe
    fun onSelectSupport(data: ChoiceSupportActivity.SupportData) {
        var names = ""
        data.data.forEach {
            names +=  "${it.names},"
        }
        v_zid.setHintName(names)
        zuIds = data
    }

    @Subscribe
    fun onSelectImp(data: SearchSupportActivity.BusImpBean) {
        v_zxid.setHintName(data.name)
        zxId = data.zxid.toString()
    }

    @Subscribe
    fun onChoiceType(data: ChoiceTypeBean.DataBean) {
        v_2_pro_type.setHintName(data.name)
        typeid = data.id
    }

    @Subscribe
    fun onChoiceAdv(data: AdvListBean.DataBean) {
        v_2_adv.setHintName(data.title)
        advid = data.id
    }

    @Subscribe
    fun onChoiceAgre(data: AgreListBean.DataBean) {
        v_2_agreement.setHintName(data.title)
        agreid = data.id
    }
}
