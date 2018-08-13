package com.mmy.charitablexi.model.login.ui.activity

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.login.component.DaggerLoginComponent
import com.mmy.charitablexi.model.login.module.LoginModule
import com.mmy.charitablexi.model.login.presenter.LoginPresenter
import com.mmy.charitablexi.wxapi.WXEntryActivity
import com.mmy.frame.AppComponent
import com.mmy.frame.BuildConfig
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.WXUserInfoBean
import com.squareup.otto.Subscribe
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.identity.TwitterAuthClient
import com.twitter.sdk.android.core.models.User
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

/**
* @file       LoginActivity.kt
* @brief      登录
* @author     lucas
* @date       2018/5/15 0015
* @version
* @par        Copyright (c):
* @par History:
*             version: zsr, 2017-09-23
*/
class LoginActivity : BaseActivity<LoginPresenter>(), View.OnClickListener {
    override fun requestSuccess(any: Any) {
    }

    val mFBCallback = CallbackManager.Factory.create()

    override fun setupDagger(appComponent: AppComponent) {
        DaggerLoginComponent.builder()
                .appComponent(appComponent)
                .loginModule(LoginModule(this))
                .build().inject(this)
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun initEvent() {
        super.initEvent()
        arrayOf(v_login, v_register, v_forget_pwd, v_fb, v_tw, v_weixin).setViewListener(this)
        //判断是否已登录
        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired
        if (!isLoggedIn)
            LoginManager.getInstance().registerCallback(mFBCallback, object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    result.accessToken?.userId?.showToast(mFrameApp)
                }

                override fun onError(error: FacebookException?) {
                }

                override fun onCancel() {
                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mFBCallback.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun getLayoutID(): Any = R.layout.activity_login

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.v_login -> {
                if (v_phone.text.isEmpty()) {
                    "请输入手机号".showToast(mFrameApp)
                    return
                }
                if (v_pwd.text.isEmpty()) {
                    "请输入密码".showToast(mFrameApp)
                    return
                }
                mIPresenter.login(v_phone.text.toString().trim(), v_pwd.text.toString().trim())
            }
            R.id.v_register -> {
                openActivity(RegisterActivity::class.java)
            }
            R.id.v_forget_pwd -> {
            }
            R.id.v_fb -> {
                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"))
            }
            R.id.v_tw -> {
                loginTwitter()
            }

            R.id.v_weixin -> {
                val mApi = WXAPIFactory.createWXAPI(this, null)
                //使授权界面显示loading,并回调
                WXEntryActivity.ACTION = WXEntryActivity.WX_LOGIN
                //
                mApi.registerApp(BuildConfig.WEIXIN_APPKEY)
                if (mApi != null && mApi.isWXAppInstalled) {
                    val req = SendAuth.Req()
                    req.scope = "snsapi_userinfo"
                    req.state = "wechat_sdk_mmy_charitablexi"
                    mApi.sendReq(req)
                } else
                    Toast.makeText(this, "用户未安装微信", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginTwitter() {
        val client = TwitterAuthClient()
        client.authorize(this,object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>?) {
                val userId = result?.data?.userId
                val userName = result?.data?.userName
                "userid:$userId,username:$userName".ld()
                //获取用户信息
                getTWUserInfo(userId,userName)
            }

            override fun failure(exception: TwitterException?) {
            }
        })
    }

    private fun getTWUserInfo(userId: Long?, userName: String?) {
        val activeSession = TwitterCore.getInstance().sessionManager.activeSession
        val client = TwitterApiClient(activeSession)
        val service = client.accountService
        service.verifyCredentials(false,false,false)
                .enqueue(object :Callback<User>(){
                    override fun success(result: Result<User>?) {
                        val data = result?.data
                        val profileImageUrl = data?.profileImageUrl?.replace("_normal", "")
                        val name = data?.name
                       "name:$name,header:$profileImageUrl".ld()
                    }

                    override fun failure(exception: TwitterException?) {
                        exception?.printStackTrace()
                    }
    })
    }

    @Subscribe
    fun onWXLogin(wxUserInfoBean: WXUserInfoBean){

    }
}
