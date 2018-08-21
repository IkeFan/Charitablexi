package com.mmy.charitablexi.model.volunteer.ui.fragment

import android.net.Uri
import android.support.design.widget.TabLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.LinearLayout
import com.mmy.charitablexi.App
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.ui.activity.PublishProjectActivity
import com.mmy.charitablexi.model.project.ui.adapter.VolunteerListAdapter
import com.mmy.charitablexi.model.volunteer.component.DaggerVolunteerListComponent
import com.mmy.charitablexi.model.volunteer.module.VolunteerListModule
import com.mmy.charitablexi.model.volunteer.presenter.VolunteerListPresenter
import com.mmy.charitablexi.model.volunteer.ui.activity.OrgInfoActivity
import com.mmy.charitablexi.model.volunteer.ui.activity.RequestMechActivity
import com.mmy.charitablexi.model.volunteer.ui.adapter.VolunteerAdapter
import com.mmy.charitablexi.model.volunteer.ui.window.VolunteerMenuPopup
import com.mmy.charitablexi.widget.SwipeItemLayout
import com.mmy.frame.AppComponent
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.base.view.BaseFragment
import com.mmy.frame.data.bean.OrganizationBean
import com.mmy.frame.data.bean.VolunteersBean
import io.rong.imkit.RongIM
import io.rong.imkit.fragment.ConversationFragment
import io.rong.imlib.RongIMClient
import io.rong.imlib.model.Conversation
import kotlinx.android.synthetic.main.fragment_volunteer.*

/**
 * @file       ProjectFragment.kt
 * @brief      义工
 * @author     lucas
 * @date       2018/4/13 0013
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class VolunteerFragment : BaseFragment<VolunteerListPresenter>(), View.OnClickListener, BaseQuickAdapter.OnItemClickListener {
    override fun requestSuccess(data: Any) {
        if( data is OrganizationBean){
            mAdapter.setNewData(data.data)
            mIPresenter.getVorlist(App.instance.userInfo.id!!)
        }else if( data is VolunteersBean){
            mPersonalAdapter.setNewData(data.data)
        }
    }

    val mPersonalAdapter = VolunteerListAdapter(R.layout.adapter_volunteer_list)
    val mAdapter = VolunteerAdapter(R.layout.adapter_volunteer2)
    lateinit var mWindow: VolunteerMenuPopup
    var isOpenConver = false

    override fun setupDagger(appComponent: AppComponent) {
        DaggerVolunteerListComponent.builder()
                .volunteerListModule(VolunteerListModule(this))
                .appComponent(appComponent)
                .build()
                .inject(this)
    }

    override fun getLayoutId(): Int = R.layout.fragment_volunteer

    override fun initView() {
        mWindow = VolunteerMenuPopup(getAc())
        v_list.layoutManager = LinearLayoutManager(getAc())
        v_list.adapter = mAdapter
        v_tabs.addTab(v_tabs.newTab().setText("义工组织"))
        v_tabs.addTab(v_tabs.newTab().setText("个人义工列表"))
    }

    override fun initData() {
//        mPersonalAdapter.setNewData(VRData.getIntData(20))
//        mAdapter.setNewData(VRData.getIntData(20))
        //加入聊天室，使用项目的id作为聊天室的id
        val roomID = 1_000_000.toString()
        RongIM.connect(App.instance.userInfo.rongToken, object : RongIMClient.ConnectCallback() {
            override fun onSuccess(p0: String?) {
                /* 新建 ConversationFragment 实例，通过 setUri() 设置相关属性*/
                val fragment = ConversationFragment()
                val uri = Uri.parse("rong://" + getAc().packageName).buildUpon()
                        .appendPath("conversation")
                        .appendPath(Conversation.ConversationType.CHATROOM.getName().toLowerCase())
                        .appendQueryParameter("targetId", roomID).build()
                fragment.uri = uri
                getAc().supportFragmentManager.beginTransaction()
                        .add(R.id.rong_content, fragment, "conversation")
                        .commit()
            }

            override fun onError(p0: RongIMClient.ErrorCode?) {
            }

            override fun onTokenIncorrect() {
            }
        })
        mAdapter.isSelectALL = v_select_all_cb.isChecked
        mPersonalAdapter.isSelectALL = v_select_all_cb.isChecked
        mIPresenter.getOrgList(App.instance.userInfo.id!!)
    }

    override fun initEvent() {
        super.initEvent()
        arrayOf(v_add, v_request, v_select_all, v_open).setViewListener(this)
        mPersonalAdapter.onItemClickListener = this
        mAdapter.onItemClickListener = this
        v_list.addOnItemTouchListener(SwipeItemLayout.OnSwipeItemTouchListener(getAc()))
        mPersonalAdapter.click = { view, position ->
            //更新ui
            v_select_all_cb.isChecked = !v_select_all_cb.isChecked
            mPersonalAdapter.remove(position)
        }
        v_tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == 0) {
                    v_list.adapter = mAdapter
                    v_add.visibility=View.VISIBLE
                    v_send_msg.visibility = View.GONE
                    v_view_search.visibility = View.VISIBLE
                } else{
                    v_add.visibility=View.GONE
                    v_send_msg.visibility = View.VISIBLE
                    v_view_search.visibility = View.GONE
                    v_list.adapter = mPersonalAdapter
                }
                v_list.invalidate()
                view?.invalidate()
            }
        })
        mWindow.lisenter={
            when(it.id){
                R.id.v_menu1->{
                    openActivity(PublishProjectActivity::class.java)
                }
            }
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.v_add -> {
                mWindow.showAsDropDown(p0)
            }
            R.id.v_request -> {
                openActivity(RequestMechActivity::class.java)
            }
            R.id.v_select_all -> {
                mPersonalAdapter.selectAll()
                mAdapter.selectAll()
                v_select_all_cb.isChecked = !v_select_all_cb.isChecked
            }
            R.id.v_open -> {
                //展开聊天
                openConversation()
            }
        }
    }


    private fun openConversation() {
        var weight = 1f
        isOpenConver = !isOpenConver
        if (isOpenConver) weight = 6f
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, weight)
        v_cv.layoutParams = params
        var from = 0f
        var to = 0f
        if (isOpenConver) {
            from = 0f
            to = 180f
        } else {
            from = 180f
            to = 360f
        }
        //更新箭头
        val anim = RotateAnimation(from, to, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        anim.fillAfter = true // 设置保持动画最后的状态
        anim.duration = 200 // 设置动画时间
        anim.interpolator = AccelerateInterpolator() // 设置插入器
        v_open.startAnimation(anim)
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, baseViewHolder: BaseViewHolder?, position: Int) {
        if (adapter == mAdapter)
            openActivity(OrgInfoActivity::class.java)
    }
}