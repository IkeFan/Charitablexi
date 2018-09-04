package com.mmy.charitablexi.model.volunteer.ui.activity

import android.view.View
import android.widget.TextView
import com.mmy.charitablexi.App
import com.mmy.charitablexi.R
import com.mmy.charitablexi.bean.EvBusItemBean
import com.mmy.charitablexi.bean.VolunteerData
import com.mmy.charitablexi.model.project.ui.activity.SendEmailActivity
import com.mmy.charitablexi.model.project.ui.activity.ThankActivity
import com.mmy.charitablexi.model.project.ui.window.SelectPopup
import com.mmy.charitablexi.model.volunteer.component.DaggerRequestVolunteerComponent
import com.mmy.charitablexi.model.volunteer.module.RequestVolunteerModule
import com.mmy.charitablexi.model.volunteer.presenter.RequestVolunteerPresenter
import com.mmy.charitablexi.widget.MyEditView
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.activity_request_volunteer.*

/**
 * @file       RequestVolunteerActivity.kt
 * @brief      申请义工
 * @author     lucas
 * @date       2018/4/18 0018
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class RequestVolunteerActivity : BaseActivity<RequestVolunteerPresenter>(), View.OnClickListener {
    var xmId:Int? = null
    var oid:Int? = null
    override fun requestSuccess(data: Any) {
        openActivity(ThankActivity::class.java)
        mBus.post(EvBusItemBean(-1, RqstVoltRst(true)))
        finish()
    }

    class RqstVoltRst(var result: Boolean)

    override fun setupDagger(appComponent: AppComponent) {
        DaggerRequestVolunteerComponent.builder()
                .appComponent(appComponent)
                .requestVolunteerModule(RequestVolunteerModule(this))
                .build().inject(this)
    }

    override fun initView() {
        setToolbar("Apply Volunteer")
        if(!App.instance.userInfo.name.isNullOrEmpty()){
            v_name.setHintName(App.instance.userInfo.name!!)
        }
        if (!App.instance.userInfo.mobile.isNullOrEmpty()) {
            v_phone.enable(false)
            v_phone.setHintName(App.instance.userInfo.mobile!!)
        }
        if (!App.instance.userInfo.email.isNullOrEmpty()) {
            v_email.enable(false)
            v_email.setHintName(App.instance.userInfo.email!!)
        }
        v_name.enable(false)
    }

    override fun initData() {
        if(intent!=null && intent.hasExtra("xmid"))
            xmId = intent.getStringExtra("xmid").toInt()
        if(intent!=null && intent.hasExtra("oid")){
            oid = intent.getStringExtra("oid").toInt()
        }
    }

    var sex = -1
    override fun initEvent() {
        arrayOf(v_submit).setViewListener(this)
        v_sex.setOnClickListener {
            val popup = SelectPopup(this)
            popup.setNewData(arrayOf("Man", "Woman").toList())
            popup.setTitle("Chose sex*")
            popup.showCardFloatAnim(it)
            popup.itemOnClick = {
                if (it is TextView)
                    when (it.text) {
                        "Man" -> {
                            sex = 1
                            v_sex.setHintName("Man")
                        }
                        "Woman" -> {
                            sex = 2
                            v_sex.setHintName("Woman")
                        }
                    }
                popup.dismiss()
            }
        }
    }

    override fun getLayoutID(): Any = R.layout.activity_request_volunteer

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.v_submit -> {
                //判断当天填写的手机号是否为注册号，若不是则需再次验证手机号
                val list = ArrayList<MyEditView>()
                list.add(v_age)
                var email: String? = null
                var phone: String? = null
                if (App.instance.userInfo.email.isNullOrEmpty())
                    list.add(v_email)
                if (App.instance.userInfo.mobile.isNullOrEmpty())
                    list.add(v_phone)
                list.forEach {
                    if (it.hintStr.isEmpty()) {
                        "Please Input${it.nameStr}".showToast(mFrameApp)
                        return@onClick
                    }
                }
                if (sex == -1) {
                    "Please chose sex".showToast(mFrameApp)
                    return
                }
                if (!App.instance.userInfo.email.isNullOrEmpty())
                    email = v_email.hintStr
                if (!App.instance.userInfo.mobile.isNullOrEmpty())
                    phone = v_phone.hintStr
                val data = VolunteerData(email ?: (phone ?: null), sex, v_age.hintStr.toInt(), xmId, oid)
                if (!App.instance.userInfo.email.isNullOrEmpty())
                    openActivity(SendEmailActivity::class.java, "title=Email,mobile="+App.instance.userInfo.email, data)
                else if (!App.instance.userInfo.mobile.isNullOrEmpty())
                    openActivity(SendEmailActivity::class.java, "title=Mobile, mobile="+App.instance.userInfo.mobile, data)
            }
            else -> {
            }
        }
    }

    override fun registerBus(): Boolean = true

    @Subscribe
    fun onCodeCheck(code: SendEmailActivity.CheckedCode){
        mIPresenter.submit(xmId,oid, App.instance.userInfo.email, App.instance.userInfo.mobile
                ,v_age.hintStr, sex, code.code)
    }
}
