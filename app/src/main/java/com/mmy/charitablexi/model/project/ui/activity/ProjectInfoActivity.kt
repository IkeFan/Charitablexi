package com.mmy.charitablexi.model.project.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import com.blankj.utilcode.util.KeyboardUtils
import com.mmy.charitablexi.App
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.project.component.DaggerProjectInfoComponent
import com.mmy.charitablexi.model.project.module.ProjectInfoModuel
import com.mmy.charitablexi.model.project.presenter.ProjectInfoPresenter
import com.mmy.charitablexi.model.project.ui.adapter.ProCommentAdapter
import com.mmy.charitablexi.model.project.ui.adapter.ProVolunAdapter
import com.mmy.charitablexi.model.project.ui.adapter.SponsorAdapter
import com.mmy.charitablexi.model.project.view.ProjectInfoView
import com.mmy.charitablexi.model.volunteer.ui.activity.RequestVolunteerActivity
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.IBean
import com.mmy.frame.data.bean.ProInfoBean
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
    lateinit var currentType: Type
    val mVolunAdapter = ProVolunAdapter(R.layout.adapter_pro_volun)
    val mSponsorAdapter = SponsorAdapter(R.layout.adapter_sponsor)
    val mCommentAdapter = ProCommentAdapter(R.layout.adapter_comment)
    val timeFormat = SimpleDateFormat("yyyy.MM.dd")

    override fun requestSuccess(data: Any) {
        when (data) {
            is ProInfoBean -> {
                v_address.text = data.data.addr
                v_title.text = data.data.title
                v_content.text = data.data.description
                v_progress.max = data.data.lovesum
                v_progress.progress = data.data.yjlove
                v_progress_value.text = "${(data.data.yjlove / data.data.lovesum.toFloat()) * 100.toInt()}%"
                v_progress_text.text = "已有${data.data.nums}人次捐赠爱心，${data.data.yjlove}/${data.data.lovesum}"
                v_volun_count.text = "${data.data.nowyg}/${data.data.volunteers}"
                mVolunAdapter.setNewData(data.data.users)
                v_time.text = timeFormat.format(Date(data.data.endtime.toLong() * 1000))
                v_time_text.text = "剩余${Math.ceil((data.data.endtime.toLong() - System.currentTimeMillis() / 1000) / (3600 * 24f).toDouble()).toInt()}天"
                mSponsorAdapter.setNewData(data.data.zhfList)
                v_ad_title.text = data.data.adinfo.title
                v_ad_content.text = data.data.adinfo.content
                v_comment_count.text = "${data.data.comments.size}条"
                mCommentAdapter.setNewData(data.data.comments)
                v_type.text = data.data.name
            }
            is IBean -> {
                when (data.sub) {
                    "collection" -> {
                        if (data.status == 1)
                            v_collection.isChecked = !v_collection.isChecked
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
                else -> {
                    Type.Complete
                }

            }
        }
    }

    enum class Type(val value: Int) {
        Raising(3), Donation(4), Complete(5)
    }

    override fun setupDagger(appComponent: AppComponent) {
        DaggerProjectInfoComponent.builder()
                .appComponent(appComponent)
                .projectInfoModuel(ProjectInfoModuel(this))
                .build().inject(this)
    }

    override fun initView() {
        setToolbar(getResStr(R.string.project_project_info))
        mId = intent.getStringExtra("id")
        currentType = intent.getSerializableExtra("sBean") as Type
        when (currentType) {
            Type.Raising -> {
                v_tag.visibility = View.GONE
                v_view1.visibility = View.GONE
                v_view2.visibility = View.GONE
//                v_divide1.visibility = View.GONE
//                v_hist_time.visibility = View.GONE
            }
            Type.Donation -> {
                v_tag.visibility = View.GONE
//                v_divide1.visibility = View.GONE
//                v_hist_time.visibility = View.GONE
            }
            Type.Complete -> {
                v_tag.visibility = View.VISIBLE
//                v_divide1.visibility = View.VISIBLE
//                v_hist_time.visibility = View.VISIBLE
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
    }

    override fun initEvent() {
        super.initEvent()
        arrayOf(click_love_progress, v_request_support, v_request_volunteer,
                v_give_love, v_volunteer_list, v_give, v_comment,
                v_share, v_comment_bt).setViewListener(this)
        v_give_root.setOnClickListener { v_give_root.visibility = View.GONE }
        v_cb1.setOnCheckedChangeListener { compoundButton, b ->
            if (b) v_cb2.isChecked = false
        }
        v_cb2.setOnCheckedChangeListener { compoundButton, b ->
            if (b) v_cb1.isChecked = false
        }
        v_collection.setOnCheckedChangeListener { compoundButton, b ->
            //收藏
            mIPresenter.collection(mId.toInt())
        }
        v_comment_et.setOnFocusChangeListener { view, b ->
            if (!b) {
                v_comment_view.visibility = View.GONE
                v_footer.visibility = View.VISIBLE
            }
            KeyboardUtils.toggleSoftInput()
        }
    }

    override fun getLayoutID(): Any = R.layout.activity_project_info

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.click_love_progress -> {
                openActivity(ProgressInfoActivity::class.java)
            }
            R.id.v_request_support -> {
                //成为资助方
                openActivity(RequestSupportActivity::class.java)
            }
            R.id.v_request_volunteer -> {
                //成为义工
                openActivity(RequestVolunteerActivity::class.java, "id=${mId}")
            }
            R.id.v_give_love -> {
                //送爱心
                v_give_root.visibility = View.VISIBLE
            }
            R.id.v_volunteer_list -> {
                //义工列表
                openActivity(VolunteerListActivity::class.java, "id=${mId}")
            }
            R.id.v_give -> {
                val love: Int
                if (v_cb1.isChecked)
                    love = v_love_count.text.toString().trim().toInt()
                else
                    love = App.instance.userInfo.lovesum
                if (love > App.instance.userInfo.lovesum || love == 0) {
                    "您的爱心数量不足".showToast(mFrameApp)
                    return
                }
                mIPresenter.sendLove(1, love)
            }
            R.id.v_comment -> {
                //评论
                v_comment_view.visibility = View.VISIBLE
                v_footer.visibility = View.GONE
                v_comment_et.requestFocus()
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
                mIPresenter.comment(mId.toInt(), text)
            }
        }
    }
}
