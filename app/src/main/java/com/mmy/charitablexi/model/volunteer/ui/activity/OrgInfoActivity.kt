package com.mmy.charitablexi.model.volunteer.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.project.ui.window.VolunteerAgreementPopup
import com.mmy.charitablexi.model.volunteer.ui.adapter.OrgInfoAdapter
import com.mmy.charitablexi.utils.VRData
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.utils.UIUtil
import com.mmy.frame.witget.SpacesItemDecoration
import kotlinx.android.synthetic.main.activity_org_info.*

/**
 * @file       OrgInfoActivity.kt
 * @brief      组织详情
 * @author     lucas
 * @date       2018/4/18 0018
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class OrgInfoActivity : BaseActivity<IPresenter<*>>(), View.OnClickListener {
    override fun requestSuccess(any: Any) {
    }

    val mAdapter = OrgInfoAdapter(R.layout.adapter_org_info)
    lateinit var mPopup: VolunteerAgreementPopup
    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun initView() {
        setToolbar("组织详情", true, "发起招聘", getResColor(R.color.colorPrimaryDark), this)
        mPopup = VolunteerAgreementPopup(this)
        rv_list.adapter = mAdapter
        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.addItemDecoration(SpacesItemDecoration(UIUtil.dp2px(this, 1)))
    }

    override fun initData() {
        mAdapter.setNewData(VRData.getIntData(10))
    }

    override fun initEvent() {
        super.initEvent()
        arrayOf(v_request_volunteer).setViewListener(this)
        mAdapter.setOnItemClickListener { adapter, view, baseViewHolder, position ->
            openActivity(RecruitInfoActivity::class.java)
        }
        mPopup.setClick {
            mPopup.dismiss()
            openActivity(RequestVolunteerActivity::class.java)
        }
    }

    override fun getLayoutID(): Any = R.layout.activity_org_info

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.toolbar_right -> {
                openActivity(PublishRecruitActivity::class.java)
            }
            R.id.v_request_volunteer -> {
                mPopup.showAnim(p0)
            }
        }
    }

}
