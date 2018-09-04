package com.mmy.charitablexi.model.volunteer.ui.fragment

import android.net.Uri
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.LinearLayout
import com.mmy.charitablexi.App
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.ui.activity.PublishProjectActivity
import com.mmy.charitablexi.model.project.ui.activity.MassMsgActivity
import com.mmy.charitablexi.model.project.ui.adapter.VolunteerListAdapter
import com.mmy.charitablexi.model.volunteer.component.DaggerVolunteerListComponent
import com.mmy.charitablexi.model.volunteer.module.VolunteerListModule
import com.mmy.charitablexi.model.volunteer.presenter.VolunteerListPresenter
import com.mmy.charitablexi.model.volunteer.ui.activity.OrgInfoActivity
import com.mmy.charitablexi.model.volunteer.ui.adapter.VolunteerAdapter
import com.mmy.charitablexi.model.volunteer.ui.window.VolunteerMenuPopup
import com.mmy.charitablexi.widget.SwipeItemLayout
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseFragment
import com.mmy.frame.data.bean.IBean
import com.mmy.frame.data.bean.OrganizationBean
import io.rong.imkit.RongIM
import io.rong.imkit.fragment.ConversationFragment
import io.rong.imlib.RongIMClient
import io.rong.imlib.model.Conversation
import kotlinx.android.synthetic.main.fragment_volunteer.*
import java.util.*

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
class VolunteerFragment : BaseFragment<VolunteerListPresenter>(), View.OnClickListener {
    var mDelOrgProsion = -1

    override fun requestSuccess(data: Any) {
        when(data){
            is OrganizationBean-> mAdapter.setNewData(data.data)
            is IBean ->{
                if(mDelOrgProsion!=-1){
                    mAdapter.remove(mDelOrgProsion)
                    mDelOrgProsion = -1
                }
            }
        }
    }

//    val mPersonalAdapter = VolunteerListAdapter(R.layout.adapter_volunteer_list)
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
        when (mFramApp.userInfo.type) {
            0 -> {
            }
            1 ->{
                v_list.addOnItemTouchListener(SwipeItemLayout.OnSwipeItemTouchListener(getAc()))
            }
        }
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

        mIPresenter.getOrgList(App.instance.userInfo.id!!)
//        mIPresenter.getVorlist(App.instance.userInfo.id!!)
    }

    override fun initEvent() {
        super.initEvent()
        arrayOf(v_open).setViewListener(this)

        mAdapter.selected = {_,position->
            openActivity(OrgInfoActivity::class.java,"id="+mAdapter.getItem(position)?.id)
        }
        mAdapter.delete = {_,position->
            mDelOrgProsion = position
            mIPresenter.delOrg(mAdapter.getItem(position)?.id!!)

        }

        mWindow.lisenter = {
            when (it.id) {
                R.id.v_menu1 -> {
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

            R.id.v_open -> {
                //展开聊天
                openConversation()
            }
            R.id.v_send_msg -> {
                var idList =  ArrayList<Int>()
                when(v_list.adapter){
                    is VolunteerListAdapter->{

//                        mPersonalAdapter.mChoseCache.forEach {
//                            idList.add(it.id!!)
//                        }
                        if(idList.size==0){
                            "Chose a member at least ".showToast(mFramApp)
                            return
                        }
                        openActivity(MassMsgActivity::class.java,"title = "+getString(R.string.platform_manager), idList)
                    }
                    is VolunteerAdapter->{
                        mAdapter.mChoseCache.forEach {
                            idList.add(it.id!!)
                        }
                        if(idList.size==0){
                            "Chose a member at least ".showToast(mFramApp)
                            return
                        }
                        openActivity(MassMsgActivity::class.java,"title = "+getString(R.string.platform_manager), idList)
                    }
                }
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
}