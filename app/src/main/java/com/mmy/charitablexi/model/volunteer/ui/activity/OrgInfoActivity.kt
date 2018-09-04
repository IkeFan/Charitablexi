package com.mmy.charitablexi.model.volunteer.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bumptech.glide.Glide
import com.mmy.charitablexi.R
import com.mmy.charitablexi.base.BaseIViewModule
import com.mmy.charitablexi.base.DaggerBaseComponent
import com.mmy.charitablexi.model.project.ui.activity.MassMsgActivity
import com.mmy.charitablexi.model.project.ui.activity.VolunteerListActivity
import com.mmy.charitablexi.model.project.ui.window.VolunteerAgreementPopup
import com.mmy.charitablexi.model.volunteer.presenter.OrgDetailPresenter
import com.mmy.charitablexi.model.volunteer.ui.adapter.OrgInfoAdapter
import com.mmy.charitablexi.widget.SwipeItemLayout
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.IBean
import com.mmy.frame.data.bean.OrgDetailBean
import com.mmy.frame.utils.Config
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
class OrgInfoActivity : BaseActivity<OrgDetailPresenter>(), View.OnClickListener {
    var mOrganization: OrgDetailBean.Organization? = null
    var oid:Int? = null
    var mDelPosition = -1

    override fun requestSuccess(any: Any) {
        when (any) {
            is OrgDetailBean -> {
                mOrganization = any?.data
                v_name.text = any?.data?.name
                v_volunteer_num.text = getString(R.string.volunteer_num) + " " + any?.data?.ygs.toString()
                Glide.with(this).load(Config.HOST + any?.data?.logo)
                        .error(R.mipmap.ic_def_header)
                        .placeholder(R.mipmap.ic_def_header)
                        .into(v_logo)
                when (any?.data?.is_focus) {
                    1 -> v_attention.text = getString(R.string.attentions_already)
                    0 -> v_attention.text = getString(R.string.click_attentions)
                }
                if(mOrganization?.uid == mFrameApp?.userInfo?.id){
                    mToolBar?.removeAllViews()
                    setToolbar(getString(R.string.org_detail), true, getString(R.string.public_recruitment), getResColor(R.color.colorPrimaryDark), this)
                    rv_list.addOnItemTouchListener(SwipeItemLayout.OnSwipeItemTouchListener(this))
                }else if(mFrameApp?.userInfo?.type == 1){
                    rv_list.addOnItemTouchListener(SwipeItemLayout.OnSwipeItemTouchListener(this))
                }
                mAdapter.setNewData(any?.data?.zplist)
            }
            is IBean -> {
                when(mDelPosition){
                    -1->{
                        if (any.status == 1) {
                            if(mOrganization?.is_focus==1){
                                mOrganization?.is_focus =0
                            }else{
                                mOrganization?.is_focus =1
                            }
                            when (mOrganization?.is_focus) {
                                1 -> v_attention.text = getString(R.string.attentions_already)
                                0 -> v_attention.text = getString(R.string.click_attentions)
                            }
                        }
                    }
                    else ->{
                        if(any.status == 1)
                            mAdapter.remove(mDelPosition)
                        mDelPosition = -1
                    }
                }
            }
        }
    }

    val mAdapter = OrgInfoAdapter(R.layout.adapter_org_info)
    lateinit var mPopup: VolunteerAgreementPopup
    override fun setupDagger(appComponent: AppComponent) {
        DaggerBaseComponent.builder()
                .appComponent(appComponent)
                .baseIViewModule(BaseIViewModule(this))
                .build()
                .inject(this)
    }

    override fun initView() {
        setToolbar(getString(R.string.org_detail), true)
        mPopup = VolunteerAgreementPopup(this)
        rv_list.adapter = mAdapter
        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.addItemDecoration(SpacesItemDecoration(UIUtil.dp2px(this, 1)))
    }

    override fun initData() {
        oid = intent.getStringExtra("id").toInt()
        mIPresenter.getOrgDetail(oid!!)
    }

    override fun initEvent() {
        super.initEvent()
        arrayOf(v_request_volunteer, v_send_msg, v_attention, v_volunteer_num).setViewListener(this)

        mPopup.setClick {
            mPopup.dismiss()
            openActivity(RequestVolunteerActivity::class.java)
        }

        mAdapter.setOnItemChildClickListener({_,view,position->
            when(view.id){
                R.id.v_del ->{
                    mDelPosition = position
                    mIPresenter.delZp(mAdapter.getItem(position)?.id!!)
                }
                else->{
                    openActivity(RecruitInfoActivity::class.java, "id = "+mAdapter.getItem(position)?.id)
                }
            }
        })
    }

    override fun getLayoutID(): Any = R.layout.activity_org_info

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.toolbar_right -> {
                openActivity(PublishRecruitActivity::class.java, "id=" +mOrganization?.id)
            }
            R.id.v_request_volunteer -> {
                mPopup.showAnim(p0)
            }
            R.id.v_attention->{
                if(mOrganization!=null){
                    var status = if(mOrganization?.is_focus==1){
                        0
                    }else{
                        1
                    }
                    mIPresenter.orgAttention(mOrganization?.id!!, mOrganization?.uid!!, status)
                }
            }
            R.id.v_send_msg -> {
               if(mOrganization!=null) openActivity(MassMsgActivity::class.java, "title = " + getString(R.string.send_massage),
                        arrayOf(mOrganization?.uid))
            }
            R.id.v_volunteer_num -> {
                openActivity(VolunteerListActivity::class.java, "oid = "+oid)
            }
        }
    }

}
