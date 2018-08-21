package com.mmy.charitablexi.model.personal.ui.activity

import android.text.TextUtils
import android.view.View
import com.mmy.charitablexi.App
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.component.DaggerAgreedManagerComponent
import com.mmy.charitablexi.model.personal.module.AgreedManagerModule
import com.mmy.charitablexi.model.personal.presenter.AgreedManagerPresenter
import com.mmy.charitablexi.model.personal.view.AgreedManagerView
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.AgreListBean
import com.mmy.frame.data.bean.IBean
import kotlinx.android.synthetic.main.activity_add_agre.*

/**
 * @file       AddAgreActivity.kt
 * @brief      新增协议
 * @author     lucas
 * @date       2018/5/19 0019
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class AddAgreActivity : BaseActivity<AgreedManagerPresenter>() ,View.OnClickListener, AgreedManagerView{
    private var mBean:AgreListBean.DataBean?=null
    override fun onDel(bean: IBean) {
        App.instance.mBus.post(BusAddAgre(v_title.text.toString().trim(),v_content.text.toString().trim()))
    }

    override fun onAdd(bean: IBean) {
        App.instance.mBus.post(BusAddAgre(v_title.text.toString().trim(),v_content.text.toString().trim()))
        finish()
    }

    override fun requestSuccess(any: Any) {
    }

    override fun setupDagger(appComponent: AppComponent) {
        DaggerAgreedManagerComponent.builder()
                .agreedManagerModule(AgreedManagerModule(this))
                .appComponent(appComponent)
                .build()
                .inject(this)
    }

    override fun initView() {
        mBean = intent.getSerializableExtra("sBean") as AgreListBean.DataBean?
        if (mBean !=null){
            setToolbar(getString(R.string.edit),rightRes = getString(R.string.finish),rightClickListener = this)
        }else{
            setToolbar(getString(R.string.add_protocol),rightRes = getString(R.string.finish),rightClickListener = this)
        }
    }

    override fun initData() {
        if(mBean!=null){
            v_title.setText(mBean?.title)
            v_content.setText(mBean?.content)
        }
    }



    override fun getLayoutID(): Any = R.layout.activity_add_agre

    override fun onClick(v: View) {
        val title = v_title.text.toString().trim()
        val content = v_content.text.toString().trim()
        if (TextUtils.isEmpty(title)){
            "请输入标题".showToast(mFrameApp)
            return
        }
        if (TextUtils.isEmpty(content)){
            "请输入内容".showToast(mFrameApp)
            return
        }

        if(mBean==null){
            mIPresenter.addAgre(BusAddAgre(title, content))
        }else{
            mIPresenter.editAgre(mBean?.id!!.toInt(), title, content)
        }
    }

    class BusAddAgre constructor(val title:String,val content:String)
}
