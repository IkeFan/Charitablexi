package com.mmy.charitablexi.model.project.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.blankj.utilcode.util.KeyboardUtils
import com.bumptech.glide.Glide
import com.mmy.charitablexi.App
import com.mmy.charitablexi.R
import com.mmy.charitablexi.bean.EvBusItemBean
import com.mmy.charitablexi.model.personal.ui.activity.EditProjectActivity
import com.mmy.charitablexi.model.project.component.DaggerProjectInfoComponent
import com.mmy.charitablexi.model.project.module.ProjectInfoModuel
import com.mmy.charitablexi.model.project.presenter.ProjectInfoPresenter
import com.mmy.charitablexi.model.project.ui.adapter.ProCommentAdapter
import com.mmy.charitablexi.model.project.ui.adapter.ProVolunAdapter
import com.mmy.charitablexi.model.project.ui.adapter.SponsorAdapter
import com.mmy.charitablexi.model.project.view.ProjectInfoView
import com.mmy.charitablexi.model.volunteer.ui.activity.RequestVolunteerActivity
import com.mmy.frame.AppComponent
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.IBean
import com.mmy.frame.data.bean.ProInfoBean
import com.mmy.frame.utils.Config
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.activity_project_info.*
import kotlinx.android.synthetic.main.popup_give_love.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * @file       ProjectInfoActivity.kt
 * @brief      项目详情
 * @author     lucas
 * @date       2018/5/16 0016
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class ProjectInfoActivity : BaseActivity<ProjectInfoPresenter>(), View.OnClickListener, ProjectInfoView {
    lateinit var mId: String
    val mVolunAdapter = ProVolunAdapter(R.layout.adapter_pro_volun)
    val mSponsorAdapter = SponsorAdapter(R.layout.adapter_sponsor)
    val mCommentAdapter = ProCommentAdapter(R.layout.adapter_comment)
    var timeFormat:SimpleDateFormat?=null
    var mCurPro: ProInfoBean.DataBean? = null

    override fun requestSuccess(data: Any) {
        when (data) {
            is ProInfoBean -> {
                mCurPro = data.data
                v_address.text = data.data.addr
                v_title.text = data.data.title
                v_content.text = data.data.description
                v_progress.max = data.data.lovesum
                v_progress.progress = data.data.yjlove
                v_progress_value.text = "${(data.data.yjlove / data.data.lovesum.toFloat()) * 100.toInt()}%"
                v_progress_text.text = "已有${data.data.nums}人次捐赠爱心，${data.data.yjlove}/${data.data.lovesum}"
                v_volun_count.text = "${data.data.nowyg}/${data.data.volunteers}"
                mVolunAdapter.setNewData(data.data.users)
                v_time.text = timeFormat?.format(Date(data.data.endtime.toLong() * 1000))
                v_time_text.text = "剩余${Math.ceil((data.data.endtime.toLong() - System.currentTimeMillis() / 1000) / (3600 * 24f).toDouble()).toInt()}天"

                data.data.zhfList.forEach {
                    Log.d("xxx","xxx"+it.name)
                }
                mSponsorAdapter.setNewData(data.data.zhfList)
                if (data.data.adinfo != null) {
                    v_ad_title.text = data.data.adinfo.title
                    v_ad_content.text = data.data.adinfo.content
                } else {
                    v_ad_container.visibility = View.GONE
                }
                if (data.data.zxfinfo != null) {
                    v_exe_name.text = data.data.zxfinfo.name
                    Glide.with(this)
                            .load(Config.HOST + data?.data.zxfinfo.avatar)
                            .error(R.mipmap.ic_def)
                            .placeholder(R.mipmap.ic_def)
                            .into(v_exe_img)
                }

                if (!data?.data.img.isEmpty()) {
                    Glide.with(this)
                            .load(Config.HOST + data?.data.img)
                            .error(R.mipmap.ic_def)
                            .placeholder(R.mipmap.ic_def)
                            .into(v_img)
                }

                v_comment_count.text = "${data.data.comments.size}条"
                mCommentAdapter.setNewData(data.data.comments)
                v_type.text = data.data.name
                v_collection_cb.isChecked = data.data.is_collect==1
                setUiStatus(getTypeByValue(data?.data?.status?.toInt()!!))
            }
            is IBean -> {
                when (data.sub) {
                    "collection" -> {
                        if (data.status != 1) {
                            v_collection_cb.isChecked = !v_collection_cb.isChecked
                        }
                    }
                    "comment" -> {
                        if (data.status == 1) {
                            v_comment_view.visibility = View.GONE
                            v_footer.visibility = View.VISIBLE
                            mIPresenter.getProInfo(mId.toInt())
                        }
                    }
                }
            }
        }
    }

    companion object {
        fun getTypeByValue(status: Int): Type {
            return when (status) {
                3 -> {
                    Type.Raising
                }
                4 -> {
                    Type.Donation
                }
                5 -> {
                    Type.Complete
                }
                else->Type.UnCheck

            }
        }
    }

    enum class Type(val value: Int) {
      UnCheck(0),UNPass(1), Raising(3), Donation(4), Complete(5)
    }

    override fun setupDagger(appComponent: AppComponent) {
        DaggerProjectInfoComponent.builder()
                .appComponent(appComponent)
                .projectInfoModuel(ProjectInfoModuel(this))
                .build().inject(this)
    }

    override fun initView() {
        mId = intent.getStringExtra("id")
        setUiStatus(intent.getSerializableExtra("sBean") as Type)
    }

    private fun setUiStatus(status:Type){
        mToolBar?.removeAllViews()
        when (status) {
            Type.Raising -> {
                v_tag.visibility = View.GONE
                v_view1.visibility = View.GONE
                v_view2.visibility = View.GONE
//                v_divide1.visibility = View.GONE
//                v_hist_time.visibility = View.GONE
                if(mCurPro?.is_yg == 1){
                    v_request_volunteer.visibility = View.GONE
                }
                when(mFrameApp?.userInfo?.type){
                    1-> setToolbar(getString(R.string.project_info),true,getString(R.string.edit),getResColor(R.color.colorPrimaryDark),this)
                    else -> setToolbar(getResStr(R.string.project_info))
                }
            }
            Type.Donation -> {
                v_tag.visibility = View.GONE
                v_be_sponsor.visibility = View.GONE
//                v_hist_time.visibility = View.GONE
                v_request_volunteer.visibility = View.GONE
                v_give_love.visibility = View.GONE
                v_notice.text = getString(R.string.executing)
                v_notice.setTextColor(getResColor(R.color.colorPrimaryDark))
                v_collection_pro.text = getString(R.string.collection_finish)
                when(mFrameApp?.userInfo?.type == 1 || ( mCurPro !=null && mFrameApp?.userInfo?.id == mCurPro?.zxid?.toInt() )){
                    true-> setToolbar(getString(R.string.project_info),true,getString(R.string.edit),getResColor(R.color.colorPrimaryDark),this)
                    else -> setToolbar(getResStr(R.string.project_info))
                }
            }
            Type.Complete -> {
                v_tag.visibility = View.VISIBLE
                v_request_volunteer.visibility = View.GONE
                v_give_love.visibility = View.GONE
                v_finish.visibility = View.VISIBLE
                v_be_sponsor.visibility = View.GONE
                v_notice.text = getString(R.string.finish_already)
                v_notice.setTextColor(getResColor(R.color.colorPrimaryDark))
                v_collection_pro.text = getString(R.string.collection_finish)
//                v_divide1.visibility = View.VISIBLE
//                v_hist_time.visibility = View.VISIBLE
                setToolbar(getString(R.string.project_info),true)
            }
        }
        v_love_sum.text = "${App.instance.userInfo.lovesum}爱心"
        v_volun_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        v_volun_list.adapter = mVolunAdapter
        v_sponsor_list.layoutManager = LinearLayoutManager(this)
        v_sponsor_list.adapter = mSponsorAdapter
        v_comment_list.layoutManager = LinearLayoutManager(this)
        v_comment_list.adapter = mCommentAdapter
        v_comment_list.isNestedScrollingEnabled = false
        v_volun_list.isNestedScrollingEnabled = false
        v_sponsor_list.isNestedScrollingEnabled = false
    }

    override fun initData() {
        mIPresenter.getProInfo(mId.toInt())
        timeFormat = SimpleDateFormat(getString(R.string.date_format))
    }

    override fun initEvent() {
        super.initEvent()
        arrayOf(v_view2, v_request_support, v_request_volunteer,
                v_give_love, v_volunteer_list, v_give, v_comment,
                v_share, v_comment_bt, v_exe_detail, v_ad_info,v_collection).setViewListener(this)
        v_give_root.setOnClickListener { v_give_root.visibility = View.GONE }
        v_cb1.setOnCheckedChangeListener { compoundButton, b ->
            if (b) v_cb2.isChecked = false
        }
        v_cb2.setOnCheckedChangeListener { compoundButton, b ->
            if (b) v_cb1.isChecked = false
        }

        v_comment_et.setOnFocusChangeListener { view, b ->
            if (!b) {
                v_comment_view.visibility = View.GONE
                v_footer.visibility = View.VISIBLE
            }
            KeyboardUtils.toggleSoftInput()
        }

        mSponsorAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, baseViewHolder, position ->
            var zhf = adapter.getItem(position) as ProInfoBean.DataBean.ZhfListBean
            openActivity(SponsorActivity::class.java, "id=${zhf.id}")
        }
    }

    override fun getLayoutID(): Any = R.layout.activity_project_info

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.toolbar_right -> {
                openActivity(EditProjectActivity::class.java, "", serializableBean = mCurPro)
            }

            R.id.v_view2 -> {
                openActivity(ProgressInfoActivity::class.java, "id=${mId}")
            }
            R.id.v_request_support -> {
                //成为资助方
                openActivity(RequestSupportActivity::class.java)
            }
            R.id.v_exe_detail -> {
                openActivity(SponsorActivity::class.java, "id=${mCurPro?.zxid}")
            }

            R.id.v_request_volunteer -> {
                //成为义工
                openActivity(RequestVolunteerActivity::class.java, "xmid=${mId}")
            }
            R.id.v_give_love -> {
                //送爱心
//                v_give_root.visibility = View.VISIBLE
                openActivity(SendLoveActivity::class.java, "id=${mId}")
            }
            R.id.v_volunteer_list -> {
                //义工列表
                openActivity(VolunteerListActivity::class.java, "xmid=${mId}")
            }
            R.id.v_give -> {
//                val love: Int
//                if (v_cb1.isChecked)
//                    love =.text.toString().trim().toInt()
//                else
//                    love = App.instance.userInfo.lovesum
//                if (love > App.instance.userInfo.lovesum || love == 0) {
//                    "您的爱心数量不足".showToast(mFrameApp)
//                    return
//                }
//                mIPresenter.sendLove(1, love)
                openActivity(SendLoveActivity::class.java)
            }
            R.id.v_comment -> {
                //评论
//                v_comment_view.visibility = View.VISIBLE
//                v_footer.visibility = View.GONE
//                v_comment_et.requestFocus()
                openActivity(CommonActivity::class.java, "xmid="+mId, serializableBean = mCurPro?.comments)
            }
            R.id.v_share -> {
                //分享

            }
            R.id.v_comment_bt -> {
                //评论
                val text = v_comment_et.text.toString().trim()
                if (TextUtils.isEmpty(text)) {
                    "请输入评论内容".showToast(mFrameApp)
                    return
                }
//                mIPresenter.comment(mId.toInt(), text)
            }
            R.id.v_ad_info -> {
                openActivity(AdvDetailActivity::class.java, "", mCurPro?.adinfo)
            }

            R.id.v_collection->{
                mIPresenter.collection(mId.toInt(), !v_collection_cb.isChecked)
                v_collection_cb.isChecked = !v_collection_cb.isChecked
            }
        }
    }

    override fun registerBus(): Boolean = true
    @Subscribe
    fun onCommonAdd(common: ProInfoBean.DataBean.CommentsBean) {
        mCommentAdapter.addData(0, common)
    }

    @Subscribe
    fun onLoveAdd(love: EvBusItemBean<Int>) {
        mIPresenter.getProInfo(mId.toInt())
    }

    @Subscribe
    fun beVolunteer(result:EvBusItemBean<RequestVolunteerActivity.RqstVoltRst>){
        if(result?.model.result){
            v_request_volunteer.visibility = View.GONE
        }
    }
}
