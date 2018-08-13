package com.mmy.charitablexi.model.personal.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import com.mmy.charitablexi.App
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.component.DaggerSearchSupportComponent
import com.mmy.charitablexi.model.personal.module.SearchSupportModule
import com.mmy.charitablexi.model.personal.presenter.SearchSupportPresenter
import com.mmy.charitablexi.model.personal.ui.adapter.SearchSupportAdapter
import com.mmy.charitablexi.model.personal.view.SearchSupportView
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.SearchSupportBean
import kotlinx.android.synthetic.main.activity_search_support.*

/**
 * @file       SearchSupportActivity.kt
 * @brief      搜索资助方或者执行方
 * @author     lucas
 * @date       2018/5/18 0018
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class SearchSupportActivity : BaseActivity<SearchSupportPresenter>(), View.OnClickListener, SearchSupportView {
    override fun requestSuccess(any: Any) {
    }

    val mAdapter = SearchSupportAdapter(R.layout.adapter_search_support)
    var item: SearchSupportBean.DataBean? = null
    override fun setupDagger(appComponent: AppComponent) {
        DaggerSearchSupportComponent.builder()
                .appComponent(appComponent)
                .searchSupportModule(SearchSupportModule(this))
                .build().inject(this)
    }

    override fun initView() {
        setToolbar("搜索${intent.getStringExtra("title")}")
        v_list.layoutManager = LinearLayoutManager(this)
        v_list.adapter = mAdapter
    }

    override fun initData() {
    }

    override fun initEvent() {
        arrayOf(v_search, v_confirm).setViewListener(this)
        mAdapter.setOnItemClickListener { adapter, view, baseViewHolder, position ->
            mAdapter.singleChoice(position)
            item = adapter.getItem(position) as SearchSupportBean.DataBean
        }
    }

    override fun getLayoutID(): Any = R.layout.activity_search_support
    override fun onClick(p0: View?) {
        when (p0) {
            v_search -> {
                val name = v_name.text.toString().trim()
                if (TextUtils.isEmpty(name)) {
                    "请输入搜索关键字".showToast(mFrameApp)
                    return
                }
                mIPresenter.search(name)
            }
            v_confirm -> {
                if (item == null) {
                    "请选择一个${intent.getStringExtra("title")}".showToast(mFrameApp)
                    return
                }
                if (intent.getStringExtra("type") == "zu") {
                    val content = v_content.text.toString().trim()
                    if (TextUtils.isEmpty(content)) {
                        "请输入投资内容".showToast(mFrameApp)
                        return
                    }
                    App.instance.mBus.post(BusSupportBean(item?.id!!, item?.name!!, content))
                }else{
                    App.instance.mBus.post(BusImpBean(item?.id!!, item?.name!!, ""))
                }
                finish()
            }
        }
    }

    override fun refreshList(data: MutableList<SearchSupportBean.DataBean>) {
        mAdapter.setNewData(data)
    }

    open class BusSupportBean constructor(var zid: Int, var name: String, var content: String)
    open class BusImpBean constructor(var zxid: Int, var name: String, var content: String)
}
