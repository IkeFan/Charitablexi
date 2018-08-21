package com.mmy.charitablexi.model.personal.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.component.DaggerAgreedManagerComponent
import com.mmy.charitablexi.model.personal.module.AgreedManagerModule
import com.mmy.charitablexi.model.personal.presenter.AgreedManagerPresenter
import com.mmy.charitablexi.model.personal.ui.adapter.AgreementManagerAdapter
import com.mmy.charitablexi.model.personal.view.AgreedManagerView
import com.mmy.charitablexi.widget.SwipeItemLayout
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.AgreListBean
import com.mmy.frame.data.bean.IBean
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.activity_agreement_manager.*

/**
 * @file       AgreementManagerActivity.kt
 * @brief      协议管理
 * @author     lucas
 * @date       2018/5/24 0024
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class AgreementManagerActivity : BaseActivity<AgreedManagerPresenter>(), View.OnClickListener, AgreedManagerView {
    override fun onDel(bean: IBean) {

    }

    override fun onAdd(bean: IBean) {

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.v_add->{
                openActivity(AddAgreActivity::class.java)
            }
        }
    }

    override fun requestSuccess(any: Any) {
        if(any is AgreListBean)
            mAdapter.setNewData(any.data)
        if(any is IBean){
            if(any.status!=1){
                any.info.showToast(mFrameApp)
                mIPresenter.getList()
            }
        }
    }

    lateinit var type: String
    val mAdapter = AgreementManagerAdapter(R.layout.adapter_agreement_manager)
    override fun setupDagger(appComponent: AppComponent) {
        DaggerAgreedManagerComponent.builder()
                .appComponent(appComponent)
                .agreedManagerModule(AgreedManagerModule(this))
                .build()
                .inject(this)
    }

    override fun initView() {
        type = intent.getStringExtra("type")
        if (type == "view") {
            v_add.visibility = View.GONE
            setToolbar(getString(R.string.view_my_protocol))
        } else
            setToolbar(getString(R.string.manage_protocol))
        v_list.layoutManager = LinearLayoutManager(this)
        v_list.adapter = mAdapter
    }


    @Subscribe
    fun onAgreListChange(bean:AddAgreActivity.BusAddAgre){
        mIPresenter.getList()
    }
    override fun initData() {
        mIPresenter.getList()
    }

    override fun registerBus(): Boolean = true

    override fun initEvent() {
        if (type == "manager") {
            v_list.addOnItemTouchListener(SwipeItemLayout.OnSwipeItemTouchListener(this))
            arrayOf(v_add).setViewListener(this)
            mAdapter.click={
              view, position->
                when(view.id){
                    R.id.v_modify->{
                        openActivity(AddAgreActivity::class.java, "",mAdapter.getItem(position))
                    }
                    R.id.v_delete->{
                        mIPresenter.delAgre(mAdapter.getItem(position)?.id!!.toInt())
                        mAdapter.remove(position)
                    }
                }
            }
        }
    }

    override fun getLayoutID(): Any = R.layout.activity_agreement_manager
}
