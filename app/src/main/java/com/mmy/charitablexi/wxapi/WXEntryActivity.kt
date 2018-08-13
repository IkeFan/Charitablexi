package com.mmy.charitablexi.wxapi

import android.content.Intent
import cn.pedant.SweetAlert.SweetAlertDialog
import com.mmy.frame.AppComponent
import com.mmy.frame.BuildConfig
import com.mmy.frame.base.view.BaseActivity
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.tencent.mm.opensdk.openapi.WXAPIFactory

/**
 * @file       WXEntryActivity.kt
 * @brief      登录与分享
 * @author     lucas
 * @date       2018/5/15 0015
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class WXEntryActivity : BaseActivity<WXEntryPresenter>(), WXEntryView, IWXAPIEventHandler {
    override fun requestSuccess(any: Any) {
    }

    // IWXAPI 是第三方app和微信通信的openapi接口
    private var api: IWXAPI? = null
    private var mSweetAlertDialog: SweetAlertDialog? = null

    companion object {
         val WX_LOGIN = 0//微信登录
         val WX_SHARE = 1//微信分享
         var ACTION = WX_LOGIN
    }

    override fun onResp(resp: BaseResp?) {
        if (resp?.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
            when (resp.errCode) {
                BaseResp.ErrCode.ERR_OK//成功
                -> if (ACTION == WX_LOGIN) {
                    val sendResp = resp as SendAuth.Resp
                    mIPresenter.getTokenOpenid(sendResp.code)
                }
                BaseResp.ErrCode.ERR_COMM, BaseResp.ErrCode.ERR_USER_CANCEL -> mSweetAlertDialog?.dismiss()
                else//
                -> mSweetAlertDialog?.dismiss()
            }
        }

        //分享授权结束后关闭界面
        if (ACTION == WX_SHARE)
            finish()
    }

    override fun onReq(p0: BaseReq?) {
    }

    override fun setupDagger(appComponent: AppComponent) {
        DaggerWXEntryComponent.builder()
                .appComponent(appComponent)
                .wXEntryModule(WXEntryModule(this))
                .build().inject(this)
    }

    override fun initView() {
        if (ACTION == WX_LOGIN) {
            mSweetAlertDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
                    .setTitleText("登录中...")
            mSweetAlertDialog?.show()
        }
        api = WXAPIFactory.createWXAPI(this, null)
        api?.registerApp(BuildConfig.WEIXIN_APPKEY)
        api?.handleIntent(intent, this)
    }

    override fun initData() {
    }


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        api?.handleIntent(intent, this)
    }


    override fun getLayoutID(): Any = 0

    override fun authComplete() {
        mSweetAlertDialog?.dismiss()
    }
}