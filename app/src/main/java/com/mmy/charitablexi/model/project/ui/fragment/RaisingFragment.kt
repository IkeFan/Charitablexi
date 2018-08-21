package com.mmy.charitablexi.model.project.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.project.component.DaggerRaisingComponent
import com.mmy.charitablexi.model.project.module.RaisingModule
import com.mmy.charitablexi.model.project.presenter.RaisingPresenter
import com.mmy.charitablexi.model.project.ui.activity.ProjectInfoActivity
import com.mmy.charitablexi.model.project.ui.adapter.ProjectAdapter
import com.mmy.frame.AppComponent
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.base.view.BaseFragment
import com.mmy.frame.data.bean.ProListBean
import kotlinx.android.synthetic.main.fragment_raising.*

/**
 * @file       RaisingFragment.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/4/16 0016
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class RaisingFragment : BaseFragment<RaisingPresenter>(), BaseQuickAdapter.OnItemClickListener {
    override fun requestSuccess(data: Any) {
        if (data is ProListBean) {
            mAdapter.setNewData(data.data)
        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, baseViewHolder: BaseViewHolder?, position: Int) {
        val intent = Intent(activity!!, ProjectInfoActivity::class.java)
        intent.putExtra("sBean", ProjectInfoActivity.Type.Raising)
        intent.putExtra("id", (adapter?.getItem(position) as ProListBean.DataBean).id)
        activity?.startActivity(intent)
    }

    val mAdapter = ProjectAdapter(R.layout.adapter_project, true)

    override fun setupDagger(appComponent: AppComponent) {
        DaggerRaisingComponent.builder()
                .appComponent(appComponent)
                .raisingModule(RaisingModule(this))
                .build().inject(this)
    }

    override fun getLayoutId(): Int = R.layout.fragment_raising

    override fun initView() {
        rv_list.adapter = mAdapter
        rv_list.layoutManager = LinearLayoutManager(activity)
    }

    override fun initData() {
        mIPresenter.getList()
    }

    override fun initEvent() {
        super.initEvent()
        mAdapter.onItemClickListener = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}