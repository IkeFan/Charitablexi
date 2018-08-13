package com.mmy.charitablexi.model.personal.ui.fragment

import android.net.Uri
import android.support.v4.app.Fragment
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.ui.adapter.ConversationListAdapterEx
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseFragment
import io.rong.imkit.RongContext
import io.rong.imkit.fragment.ConversationListFragment
import io.rong.imlib.model.Conversation

/**
 * @file       LatterFragment.kt
 * @brief      私信
 * @author     lucas
 * @date       2018/4/21 0021
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class LatterFragment : BaseFragment<IPresenter<*>>() {
    override fun requestSuccess(any: Any) {
    }

    private val isDebug: Boolean = false

    /**
     * 会话列表的fragment
     */
    private var mConversationListFragment: ConversationListFragment? = null


    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun getLayoutId(): Int = R.layout.fragment_latter

    override fun initView() {

        childFragmentManager.beginTransaction()
                .add(R.id.rong_content, initConversationList(), "conversationlist")
                .commit()
    }

    override fun initData() {
    }

    private fun initConversationList(): Fragment {
        if (mConversationListFragment == null) {
            val listFragment = ConversationListFragment()
            listFragment.setAdapter(ConversationListAdapterEx(RongContext.getInstance()))
            val uri: Uri
            if (isDebug) {
                uri = Uri.parse("rong://" + getAc().packageName).buildUpon()
                        .appendPath("conversationlist")
                        .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "true") //设置私聊会话是否聚合显示
                        .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//群组
                        .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                        .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                        .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "true")
                        .build()

            } else {
                uri = Uri.parse("rong://" + getAc().packageName).buildUpon()
                        .appendPath("conversationlist")
                        .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                        .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                        .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                        .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                        .build()
            }
            listFragment.uri = uri
            mConversationListFragment = listFragment
            return listFragment
        } else {
            return mConversationListFragment as ConversationListFragment
        }
    }
}