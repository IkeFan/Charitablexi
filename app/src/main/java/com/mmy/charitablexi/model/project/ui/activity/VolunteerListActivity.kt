package com.mmy.charitablexi.model.project.ui.activity

import android.net.Uri
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.LinearLayout
import com.mmy.charitablexi.App
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.project.ui.adapter.VolunteerListAdapter
import com.mmy.charitablexi.model.volunteer.component.DaggerVolunteerListComponent
import com.mmy.charitablexi.model.volunteer.module.VolunteerListModule
import com.mmy.charitablexi.model.volunteer.presenter.VolunteerListPresenter
import com.mmy.charitablexi.widget.SwipeItemLayout
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.IBean
import com.mmy.frame.data.bean.VolunteersBean
import io.rong.imkit.RongIM
import io.rong.imkit.fragment.ConversationFragment
import io.rong.imlib.RongIMClient
import io.rong.imlib.model.Conversation
import kotlinx.android.synthetic.main.activity_volunteer_list.*


/**
 * @file       VolunteerListActivity.kt
 * @brief      项目义工列表
 * @author     lucas
 * @date       2018/5/16 0016
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class VolunteerListActivity : BaseActivity<VolunteerListPresenter>(), View.OnClickListener {
    var xmid: String? = null
    var oid: String? = null
    var mDeletePosition: Int = -1

    override fun requestSuccess(data: Any) {
        when (data) {
            is VolunteersBean -> {
                mAdapter.setNewData(data.data)
            }
            is IBean -> {
                mAdapter.remove(mDeletePosition)
                mDeletePosition = -1
            }
        }

    }

    val mAdapter = VolunteerListAdapter(R.layout.adapter_volunteer_list)

    var isOpenConver = false

    override fun setupDagger(appComponent: AppComponent) {
        DaggerVolunteerListComponent.builder()
                .appComponent(appComponent)
                .volunteerListModule(VolunteerListModule(this))
                .build()
                .inject(this)
    }

    override fun initView() {
        var rigthTitle = ""
        //判断用户身份
        if(mFrameApp?.userInfo?.type == 1 || (oid!=null && mFrameApp?.userInfo?.id == oid?.toInt())){
            setToolbar("Volunteers", true, getString(R.string.send_massage), R.color.colorPrimary, this)
            v_select_all.visibility = View.VISIBLE
        }else{
            v_select_all.visibility = View.GONE
            setToolbar("Volunteers", true)
        }

        v_list.layoutManager = LinearLayoutManager(this)
        v_list.adapter = mAdapter

        if (intent.hasExtra("xmid")) {
            xmid = intent.getStringExtra("xmid")
            mIPresenter.getVorlist(xmid = xmid!!.toInt())
        } else {
            oid = intent.getStringExtra("oid")
            mIPresenter.getVorlist(oid = oid!!.toInt())
        }
    }

    override fun initData() {
        //加入聊天室，使用项目的id作为聊天室的id
        val roomID = if (intent.hasExtra("xmid")) {
            intent.getStringExtra("xmid")
        } else {
            //加入聊天室，使用机构的id作为聊天室的id
            intent.getStringExtra("oid")
        }
        RongIM.connect(App.instance.userInfo.rongToken, object : RongIMClient.ConnectCallback() {
            override fun onSuccess(p0: String?) {
                /* 新建 ConversationFragment 实例，通过 setUri() 设置相关属性*/
                val fragment = ConversationFragment()
                val uri = Uri.parse("rong://" + applicationInfo.packageName).buildUpon()
                        .appendPath("conversation").appendPath(Conversation.ConversationType
                        .CHATROOM.getName()
                        .toLowerCase())
                        .appendQueryParameter("targetId", roomID).build()

                fragment.uri = uri
                supportFragmentManager.beginTransaction()
                        .add(R.id.rong_content, fragment, "conversation")
                        .commit()
            }

            override fun onError(p0: RongIMClient.ErrorCode?) {
            }

            override fun onTokenIncorrect() {
            }
        })
    }

    override fun initEvent() {
        if(mFrameApp?.userInfo?.type == 1 || (oid!=null && mFrameApp?.userInfo?.id == oid?.toInt())){
            v_list.addOnItemTouchListener(SwipeItemLayout.OnSwipeItemTouchListener(this))
            mAdapter.delete = { view, position ->
                v_select_all_cb.isChecked = !v_select_all_cb.isChecked
                mDeletePosition = position
                mIPresenter.delVolunteer(mAdapter.getItem(position)?.id!!)
            }
        }

        arrayOf(v_select_all, v_open).setViewListener(this)
    }

    override fun getLayoutID(): Any = R.layout.activity_volunteer_list

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.v_select_all -> {
                v_select_all_cb.isChecked = !v_select_all_cb.isChecked
                mAdapter.setSelectAll(v_select_all_cb.isChecked)
            }
            R.id.toolbar_right -> {
                //群发消息
                var idList =  ArrayList<Int>()
                if(mAdapter.mChoseCache.size == 0){
                    "Chose a member at least ".showToast(mFrameApp)
                    return
                }
                mAdapter.mChoseCache.forEach {
                    idList.add(it.id!!)
                }
                openActivity(MassMsgActivity::class.java, "title = " + getString(R.string.organization),idList)
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

}
