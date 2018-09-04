package com.mmy.charitablexi.model.volunteer.ui.activity

import android.view.View
import com.bumptech.glide.Glide
import com.mmy.charitablexi.R
import com.mmy.charitablexi.base.BaseIViewModule
import com.mmy.charitablexi.base.DaggerBaseComponent
import com.mmy.charitablexi.model.volunteer.presenter.ZpDetailPresenter
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.RecruitDetailBean
import com.mmy.frame.utils.Config
import kotlinx.android.synthetic.main.activity_recruit_info.*
import java.text.SimpleDateFormat
import java.util.*

/**
* @file       RecruitInfoActivity.kt
* @brief      招聘详情
* @author     lucas
* @date       2018/5/17 0017
* @version
* @par        Copyright (c):
* @par History:
*             version: zsr, 2017-09-23
*/
class RecruitInfoActivity : BaseActivity<ZpDetailPresenter>(), View.OnClickListener {
    override fun requestSuccess(any: Any) {
        when(any){
            is RecruitDetailBean->{
                dataToUI(any.data!!)
            }
        }
    }

    override fun setupDagger(appComponent: AppComponent) {
        DaggerBaseComponent.builder()
                .baseIViewModule(BaseIViewModule(this))
                .appComponent(appComponent)
                .build()
                .inject(this)
    }

    fun dataToUI(recruit: RecruitDetailBean.RecruitDetail){
        var format = SimpleDateFormat(getString(R.string.date_format))
        var calender = Calendar.getInstance()

        v_title.text = recruit.name
        v_work_place.text = recruit.address
        v_work_content.text = recruit.content
        v_phone.text = recruit.telphone
        v_email.text = recruit.email
        v_need_count.text = recruit.ygs.toString() + "/"+recruit.persons


        if(recruit.endtime == 1L){
            calender.timeInMillis = recruit.endtime
            v_end_time.text = format.format(calender.time)
        }else{
            v_end_time.text = getString(R.string.always)
        }

        var workTime =""
        recruit.worktime?.split(",")?.forEach{
            calender.timeInMillis = it.toLong()
            workTime + format.format(calender.time)+"~"
        }
        workTime.substring(0, workTime.lastIndex-1)
        v_work_time.text = workTime

        Glide.with(this).load(Config.HOST+recruit.photo)
                .error(R.mipmap.ic_def)
                .placeholder(R.mipmap.ic_def)
                .into(v_logo)

    }

    override fun initView() {
        setToolbar(getString(R.string.recruit_detail), true)
    }

    override fun initData() {
        if(intent!=null && intent.hasExtra("id"))
            mIPresenter.getZpDetail(intent.getStringExtra("id").toInt())
    }

    override fun getLayoutID(): Any = R.layout.activity_recruit_info

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.toolbar_right -> {
                //修改招聘
            }
            else -> {
            }
        }
    }

}
