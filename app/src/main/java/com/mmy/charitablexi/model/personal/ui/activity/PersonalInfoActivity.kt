package com.mmy.charitablexi.model.personal.ui.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.view.Gravity
import android.view.View
import com.bumptech.glide.Glide
import com.mmy.charitablexi.App
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.ui.window.LoveAnswerPopup
import com.mmy.charitablexi.model.project.ui.activity.LoveAnswerActivity
import com.mmy.charitablexi.setpcount.StepService
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.helper.GlideCircleTransform
import com.mmy.frame.utils.Config
import kotlinx.android.synthetic.main.activity_personal_info.*

/**
 * @file       PersonalInfoActivity.kt
 * @brief      个人中心
 * @author     lucas
 * @date       2018/5/30 0030
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class PersonalInfoActivity : BaseActivity<IPresenter<*>>(), View.OnClickListener {
    override fun requestSuccess(any: Any) {
    }

    /**
     * 用于查询应用服务（application Service）的状态的一种interface，
     * 更详细的信息可以参考Service 和 context.bindService()中的描述，
     * 和许多来自系统的回调方式一样，ServiceConnection的方法都是进程的主线程中调用的。
     */
    internal var conn: ServiceConnection = object : ServiceConnection {
        /**
         * 在建立起于Service的连接时会调用该方法，目前Android是通过IBind机制实现与服务的连接。
         * @param name 实际所连接到的Service组件名称
         * @param service 服务的通信信道的IBind，可以通过Service访问对应服务
         */
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val stepService = (service as StepService.StepBinder).service
            //设置步数监听回调
            stepService.registerCallback {
                v_setp_count.text = "$it 颗"
            }
        }

        /**
         * 当与Service之间的连接丢失的时候会调用该方法，
         * 这种情况经常发生在Service所在的进程崩溃或者被Kill的时候调用，
         * 此方法不会移除与Service的连接，当服务重新启动的时候仍然会调用 onServiceConnected()。
         * @param name 丢失连接的组件名称
         */
        override fun onServiceDisconnected(name: ComponentName) {

        }
    }
    private var isBind = false

    lateinit var mPopup: LoveAnswerPopup

    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun initView() {
        setToolbar("个人主页", true, R.mipmap.ic_my_modif, 0, this)
        mPopup = LoveAnswerPopup(this)
    }

    override fun initData() {
        val intent = Intent(this, StepService::class.java)
        isBind = bindService(intent, conn, Context.BIND_AUTO_CREATE)
        val userInfo = App.instance.userInfo
        Glide.with(this)
                .load(Config.HOST +userInfo.userBean?.avatar)
                .bitmapTransform(GlideCircleTransform(this))
                .placeholder(R.mipmap.ic_user_def)
                .error(R.mipmap.ic_user_def).into(v_header)
        v_name.text = userInfo.name
        v_guanzu.text = "关注 ${userInfo.userBean?.guanzhu}"
        v_guanzhe.text = "关注者 ${userInfo.userBean?.gzz}"
    }

    override fun initEvent() {
        arrayOf(v_answer).setViewListener(this)
        mPopup.click = {
            openActivity(LoveAnswerActivity::class.java)
            mPopup.dismiss()
        }
    }

    override fun getLayoutID(): Any = R.layout.activity_personal_info

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.toolbar_right -> {
                openActivity(ModifyInfoActivity::class.java)
            }
            R.id.v_answer -> {
                mPopup.showAtLocation(p0,Gravity.CENTER,0,0)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(conn)
    }
}
