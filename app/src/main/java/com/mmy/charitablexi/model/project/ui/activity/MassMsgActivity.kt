package com.mmy.charitablexi.model.project.ui.activity

import android.net.Uri
import android.util.Log
import com.mmy.charitablexi.App
import com.mmy.charitablexi.R
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseActivity
import io.rong.imkit.RongIM
import io.rong.imkit.fragment.ConversationFragment
import io.rong.imlib.IRongCallback
import io.rong.imlib.RongIMClient
import io.rong.imlib.model.Conversation
import io.rong.imlib.model.Message
import io.rong.message.ImageMessage
import io.rong.message.RichContentMessage
import io.rong.message.TextMessage
import io.rong.message.VoiceMessage


/**
 * @file       MassMsgActivity.kt
 * @brief      群发消息
 * @author     lucas
 * @date       2018/5/17 0017
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class MassMsgActivity : BaseActivity<IPresenter<*>>() {
    override fun requestSuccess(any: Any) {
    }

    var ids:List<Int>? = null
    //和一个不存在的用户单聊，目的：借用官方界面，再消息回调中群发消息
    val targetId = "-1"

    val mSendListener = object : RongIM.OnSendMessageListener {
        /**
         * 消息发送前监听器处理接口（是否发送成功可以从 SentStatus 属性获取）。
         *
         * @param message 发送的消息实例。
         * @return 处理后的消息实例。
         */
        override fun onSend(message: Message): Message {
            //开发者根据自己需求自行处理逻辑

            return message
        }

        /**
         * 消息在 UI 展示后执行/自己的消息发出后执行,无论成功或失败。
         *
         * @param message              消息实例。
         * @param sentMessageErrorCode 发送消息失败的状态码，消息发送成功 SentMessageErrorCode 为 null。
         * @return true 表示走自己的处理方式，false 走融云默认处理方式。
         */
        override fun onSent(message: Message, sentMessageErrorCode: RongIM.SentMessageErrorCode?): Boolean {
            //过滤监听
            if (message.targetId != targetId) return false
            if (message.sentStatus == Message.SentStatus.FAILED) {
                if (sentMessageErrorCode == RongIM.SentMessageErrorCode.NOT_IN_CHATROOM) {
                    //不在聊天室
                } else if (sentMessageErrorCode == RongIM.SentMessageErrorCode.NOT_IN_DISCUSSION) {
                    //不在讨论组
                } else if (sentMessageErrorCode == RongIM.SentMessageErrorCode.NOT_IN_GROUP) {
                    //不在群组
                } else if (sentMessageErrorCode == RongIM.SentMessageErrorCode.REJECTED_BY_BLACKLIST) {
                    //你在他的黑名单中
                }
            }
            //群发消息
            ids?.forEach {
                message.targetId = it.toString()
                message.conversationType = Conversation.ConversationType.PRIVATE
                RongIM.getInstance().sendMessage(message, null, null, object : IRongCallback.ISendMessageCallback {
                    override fun onAttached(p0: Message?) {
                    }

                    override fun onSuccess(p0: Message?) {
                        Log.d("lucas", "${p0?.targetId}:发送成功！")
                    }

                    override fun onError(p0: Message?, p1: RongIMClient.ErrorCode?) {
                    }
                })
            }
            val messageContent = message.content

            if (messageContent is TextMessage) {//文本消息
                Log.d("lucas", "onSent-TextMessage:" + messageContent.content)
            } else if (messageContent is ImageMessage) {//图片消息
                Log.d("lucas", "onSent-ImageMessage:" + messageContent.remoteUri)
            } else if (messageContent is VoiceMessage) {//语音消息
                Log.d("lucas", "onSent-voiceMessage:" + messageContent.uri.toString())
            } else if (messageContent is RichContentMessage) {//图文消息
                Log.d("lucas", "onSent-RichContentMessage:" + messageContent.content)
            } else {
                Log.d("lucas", "onSent-其他消息，自己来判断处理")
            }

            return false
        }
    }


    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun initView() {
        setToolbar(intent.getStringExtra("title"), rightRes = R.drawable.ic_menu)
    }

    override fun initData() {
        ids = intent.getIntegerArrayListExtra("sBean")
        RongIM.connect(App.instance.userInfo.rongToken, object : RongIMClient.ConnectCallback() {
            override fun onSuccess(p0: String?) {
                RongIM.getInstance().setSendMessageListener(mSendListener)
                /* 新建 ConversationFragment 实例，通过 setUri() 设置相关属性*/
                val fragment = ConversationFragment()
                val uri = Uri.parse("rong://" + applicationInfo.packageName).buildUpon()
                        .appendPath("conversation").appendPath(Conversation.ConversationType
                        .PRIVATE.getName()
                        .toLowerCase())
                        .appendQueryParameter("targetId", targetId).build()

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

    override fun getLayoutID(): Any = R.layout.activity_mass_msg


}
