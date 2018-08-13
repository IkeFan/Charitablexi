package com.mmy.charitablexi.model.personal.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.mmy.charitablexi.App
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.component.DaggerChoiceSupportComponent
import com.mmy.charitablexi.model.personal.module.ChoiceSupportModule
import com.mmy.charitablexi.model.personal.presenter.ChoiceSupportPresenter
import com.mmy.charitablexi.model.personal.ui.adapter.ChoiceSupportAdapter
import com.mmy.charitablexi.model.personal.view.ChoiceSupportView
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.SupportListBean
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.activity_choice_support.*

/**
 * @file       ChoiceSupportActivity.kt
 * @brief      选择资助方
 * @author     lucas
 * @date       2018/5/18 0018
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class ChoiceSupportActivity : BaseActivity<ChoiceSupportPresenter>(), View.OnClickListener, ChoiceSupportView {
    override fun requestSuccess(any: Any) {
    }

    val mAdapter = ChoiceSupportAdapter(R.layout.adapter_choice_support)

    override fun setupDagger(appComponent: AppComponent) {
        DaggerChoiceSupportComponent.builder()
                .appComponent(appComponent)
                .choiceSupportModule(ChoiceSupportModule(this))
                .build().inject(this)
    }

    override fun initView() {
        setToolbar("选择资助方", rightRes = "完成", rightClickListener = this)
        v_list.layoutManager = LinearLayoutManager(this)
        v_list.adapter = mAdapter
    }

    override fun initData() {
        mAdapter.setNewData(ArrayList<SupportListBean.DataBean>())
//        mIPresenter.getSupportList()
    }

    override fun initEvent() {
        arrayOf(v_add).setViewListener(this)
        mAdapter._onCheckMain = {
//            mIPresenter.setMainSupport(mCollectionAdapter.getItem(it)?.id)
        }
        mAdapter._onDelete = {}
    }

    override fun getLayoutID(): Any = R.layout.activity_choice_support

    override fun registerBus(): Boolean = true

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.v_add -> {
                openActivity(SearchSupportActivity::class.java,
                        "type=${intent.getStringExtra("type")},title=${intent.getStringExtra("title")}")
            }
            R.id.toolbar_right -> {
                //排序，将主投资方放在第一位
                mAdapter.sortBean()
                val list = ArrayList<SupportIdsNames>()
                mAdapter.data.forEach {
                   list.add(SupportIdsNames(it.id,it.name,it.material))
                }
                App.instance.mBus.post(SupportData(list))
                finish()
            }
        }
    }

    override fun refreshList(data: MutableList<SupportListBean.DataBean>) {
//        mCollectionAdapter.setNewData(data)
    }

    @Subscribe
    fun onChoice(busSupportBean: SearchSupportActivity.BusSupportBean) {
        //求改备注，并添加
        mIPresenter.addSupport(busSupportBean)
        val bean = SupportListBean.DataBean()
        bean.id = busSupportBean.zid
        bean.name = busSupportBean.name
        bean.material = busSupportBean.content
        mAdapter.addData(bean)
    }

    open class SupportData(val data: ArrayList<SupportIdsNames>)
    open class SupportIdsNames(val ids: Int, val names: String,val content:String)
}
