package com.mmy.charitablexi.model.personal.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.component.DaggerCommonProjectComponent
import com.mmy.charitablexi.model.personal.module.CommonProjectModule
import com.mmy.charitablexi.model.personal.presenter.CommonProjectPresenter
import com.mmy.charitablexi.model.personal.ui.adapter.CommonListAdapter
import com.mmy.charitablexi.model.project.ui.activity.ProjectInfoActivity
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.CommonProjectBean
import kotlinx.android.synthetic.main.activity_common_project.*
import java.io.Serializable

/**
 * @file       CommonProjectActivity.kt
 * @brief      项目列表-公用
 * @author     lucas
 * @date       2018/5/28 0028
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class CommonProjectActivity : BaseActivity<CommonProjectPresenter>() {
    lateinit var type: CommonType

    override fun requestSuccess(data: Any) {
        if (data is CommonProjectBean)
            mCollectionAdapter.setNewData(data.data)
    }

    val mCollectionAdapter = CommonListAdapter(R.layout.adapter_project)

    override fun setupDagger(appComponent: AppComponent) {
        DaggerCommonProjectComponent.builder()
                .appComponent(appComponent)
                .commonProjectModule(CommonProjectModule(this))
                .build().inject(this)
    }

    override fun initView() {
        type = intent.getSerializableExtra("sBean") as CommonType
        setToolbar(type.title)
        v_list.layoutManager = LinearLayoutManager(this)
        v_list.adapter = mCollectionAdapter
    }

    override fun initData() {
        mIPresenter.getList(type)
    }

    override fun initEvent() {
        mCollectionAdapter.setOnItemClickListener { adapter, view, baseViewHolder, position ->
            //进入详情界面
            val bean = adapter.getItem(position) as CommonProjectBean.DataBean
            openActivity(ProjectInfoActivity::class.java
                    , "id=${bean.id}", serializableBean = ProjectInfoActivity.getTypeByValue(bean.status.toInt()))
        }
    }

    override fun getLayoutID(): Any = R.layout.activity_common_project


    enum class CommonType(val title: String) : Serializable {
        COLLECTION("收藏项目"),
        DONATION("已捐项目"),
        JOIN("参加义工项目")
    }
}
