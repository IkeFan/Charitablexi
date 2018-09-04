package com.mmy.charitablexi.model.project.ui.activity

import android.view.View
import com.mmy.charitablexi.R
import com.mmy.charitablexi.bean.VolunteerData
import com.mmy.charitablexi.model.project.component.DaggerSendEmailComponent
import com.mmy.charitablexi.model.project.module.SendEmailModule
import com.mmy.charitablexi.model.project.presenter.SendEmailPresenter
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.IBean
import kotlinx.android.synthetic.main.activity_send_email.*

/**
 * @file       SendEmailActivity.kt
 * @brief      邮箱验证
 * @author     lucas
 * @date       2018/5/15 0015
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class SendEmailActivity : BaseActivity<SendEmailPresenter>(), View.OnClickListener {
    lateinit var title: String
    override fun requestSuccess(any: Any) {
        when(any){
            is IBean->{
                when(any.sub){
                    "sendCode"->{
                        "Verify code sent".showToast(mFrameApp)
                    }
                    "submit" ->{
                        mBus.post(CheckedCode(v_code.text.toString().trim()))
                        finish()
                    }
                }
            }
        }

    }

    class CheckedCode(var code:String)
    override fun setupDagger(appComponent: AppComponent) {
        DaggerSendEmailComponent.builder()
                .appComponent(appComponent)
                .sendEmailModule(SendEmailModule(this))
                .build().inject(this)
    }

    override fun initView() {
        title = intent.getStringExtra("title")
        setToolbar("${title}Verify")
        v_name.text = title
        v_et.hint = "Pls input${title}"
    }

    override fun initData() {
        if(intent.hasExtra("mobile")){
            v_et.setText(intent.getStringExtra("mobile"))
        }
    }

    override fun initEvent() {
        arrayOf(v_send, v_submit).setViewListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            v_send -> {
                val trim = v_et.text.toString().trim()
                if (trim.isNullOrEmpty()){
                    "Pls input${title}".showToast(mFrameApp)
                    return
                }
                mIPresenter.sendCode(trim)
            }
            v_submit -> {
                val da: VolunteerData = intent.getSerializableExtra("sBean") as VolunteerData
                val code = v_code.text.toString().trim()
                if (code.isNullOrEmpty()){
                    "Pls input verify code".showToast(mFrameApp)
                    return
                }
                mIPresenter.submit(da,code)
            }
        }
    }

    override fun getLayoutID(): Any = R.layout.activity_send_email

}
