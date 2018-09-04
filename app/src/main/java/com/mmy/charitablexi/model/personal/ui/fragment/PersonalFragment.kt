package com.mmy.charitablexi.model.personal.ui.fragment

import android.view.View
import com.blankj.utilcode.util.SPUtils
import com.bumptech.glide.Glide
import com.mmy.charitablexi.App
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.login.ui.activity.LoginActivity
import com.mmy.charitablexi.model.personal.component.DaggerPersonalComponent
import com.mmy.charitablexi.model.personal.module.PersonalModule
import com.mmy.charitablexi.model.personal.presenter.PersonalPresenter
import com.mmy.charitablexi.model.personal.ui.activity.*
import com.mmy.charitablexi.model.volunteer.ui.activity.RequestMechActivity
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseFragment
import com.mmy.frame.data.bean.PersonalInfoBean
import com.mmy.frame.data.bean.UserInfo
import com.mmy.frame.utils.Config
import jp.wasabeef.glide.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.fragment_personal.*

/**
 * @file       ProjectFragment.kt
 * @brief      个人
 * @author     lucas
 * @date       2018/4/13 0013
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class PersonalFragment : BaseFragment<PersonalPresenter>(), View.OnClickListener {
    override fun requestSuccess(data: Any) {
        if (data is PersonalInfoBean && data.status == 1) {
            App.instance.userInfo.userBean = data.data
            v_name.text = data.data.name
            Glide.with(getAc())
                    .load(Config.HOST + data.data.avatar)
                    .bitmapTransform(CropCircleTransformation(getAc()))
                    .placeholder(R.mipmap.ic_user_def)
                    .error(R.mipmap.ic_user_def)
                    .into(v_icon)
            v_type.text = UserInfo.getUserTypeName(data.data.type)
            v_collection_count.text = data.data.guanzhu
            v_collection_user_count.text = data.data.gzz
            v_partake_count.text = data.data.zhuzhi
            v_msg_count.text = "${data.data.xiaoxi}条新消息"
            v_fqxm.text = data.data.fqxm
            v_scxm.text = data.data.scxm
            v_yjxm.text = data.data.yjxm
            v_cjyg.text = data.data.ygxm
            v_yhgl.text = data.data.users_counts.toString()
            v_xygl.text = data.data.xy_counts.toString()
//            v_xygl.text = mData.mData.
//            v_wdxy.text = mData.mData.
//            v_yhgl.text = mData.mData.
        }
    }

    override fun setupDagger(appComponent: AppComponent) {
        DaggerPersonalComponent.builder()
                .appComponent(appComponent)
                .personalModule(PersonalModule(this))
                .build().inject(this)
    }

    override fun getLayoutId(): Int = R.layout.fragment_personal

    override fun initView() {
        when(mFramApp.userInfo.type){
            0->{ v_be_org.visibility = View.VISIBLE}
            1->{}
            2->{}
        }
    }

    override fun initData() {
        mIPresenter.getInfo()
        v_name.text = App.instance.userInfo.name
    }

    override fun initEvent() {
        super.initEvent()
        arrayOf(v_personal_info, v_message, v_publish, v_collection, v_donation, v_join,
                v_user_manager, v_view_agreement, v_agreement_manager, v_follow, v_follow_user,
                v_partake,v_tiku_manager,v_about,v_about_modify,v_login_out, v_be_org,v_volunteer_audit).setViewListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            v_follow -> openActivity(MyFollowActivity::class.java, "title="+getString(R.string.follow))
            v_follow_user -> openActivity(MyFollowActivity::class.java, "title="+getString(R.string.follower))
            v_partake -> openActivity(PartakeActivity::class.java)
            v_personal_info -> openActivity(PersonalInfoActivity::class.java)
            v_message -> openActivity(MessageActivity::class.java)
            v_publish -> openActivity(PublishProjectActivity::class.java)
            v_collection -> openActivity(CommonProjectActivity::class.java, serializableBean = CommonProjectActivity.CommonType.COLLECTION)
            v_donation -> openActivity(CommonProjectActivity::class.java, serializableBean = CommonProjectActivity.CommonType.DONATION)
            v_join -> openActivity(CommonProjectActivity::class.java, serializableBean = CommonProjectActivity.CommonType.JOIN)
            v_user_manager -> openActivity(UserManagerActivity::class.java)
            v_view_agreement -> openActivity(AgreementManagerActivity::class.java, "type=view")
            v_agreement_manager -> openActivity(AgreementManagerActivity::class.java, "type=manager")
            v_tiku_manager -> openActivity(QuestionManagerActivity::class.java)
            v_reader_manager->openActivity(ReaderManagerActivity::class.java)
            v_about->openActivity(AboutActivity::class.java)
            v_about_modify->openActivity(AboutModifyActivity::class.java)
            v_login_out->{
//                SPUtils.getInstance().remove("phone")
                SPUtils.getInstance().remove("pwd")
                openActivity(LoginActivity::class.java)
                getAc().finish()
            }
            v_be_org ->{
                openActivity(RequestMechActivity::class.java)
            }

            v_volunteer_audit->{

            }
        }
    }
}