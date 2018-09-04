package com.mmy.charitablexi.model.project.ui.activity

import android.content.Intent
import android.support.annotation.IdRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.View
import com.bigkoo.pickerview.TimePickerView
import com.mmy.charitablexi.App
import com.mmy.charitablexi.R
import com.mmy.charitablexi.base.BaseIViewModule
import com.mmy.charitablexi.base.DaggerBaseComponent
import com.mmy.charitablexi.model.project.presenter.AddProgressPresenter
import com.mmy.charitablexi.model.project.ui.adapter.PublishPicAdapter
import com.mmy.charitablexi.widget.MyEditView
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.PhotoBean
import com.mmy.frame.data.bean.ProjectProgressBean
import com.mmy.frame.helper.PicSelectHelper
import kotlinx.android.synthetic.main.activity_progress_publish.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class PublishProgressActivity : BaseActivity<AddProgressPresenter>(), View.OnClickListener {
    var mDate: Date? = null
    var mXmid: String? = null
    var mProcess:ProjectProgressBean.ProjectProgress? = null

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.v_2_time -> {
                showPickerDate(R.id.v_2_time)
            }
            R.id.v_submit -> {
                arrayOf(v_title, v_edit).forEach {
                    if (it.text.isEmpty()) {
                        it.hint.toString().showToast(mFrameApp)
                        return
                    }
                }

                if (mDate == null) {
                    getString(R.string.chose_time).showToast(mFrameApp)
                    return
                }
                if (mAdapter.itemCount == 1 && mProcess==null) {
                    getString(R.string.chose_pic).showToast(mFrameApp)
                    return
                }
                val builder = MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("uid", App.instance.userInfo.id.toString())
                        .addFormDataPart("xmid", mXmid)
                        .addFormDataPart("title", v_title.text.toString())
                        .addFormDataPart("desc", v_edit.text.toString())
                        .addFormDataPart("uptime", mDate?.time.toString())
                mAdapter.data.forEach {
                    if (!it.path.startsWith("/Uploads")) {
                        var file = File(it.path)
                        builder.addFormDataPart("imgs1[]", file.getName(), RequestBody.create(MediaType.parse("image/*"), file))
                    }else{
                        builder.addFormDataPart("photos[]",it.path)
                    }

                }
                if(mProcess==null){
                    mIPresenter.addProgress(builder.build().parts())
                }else{
                    builder.addFormDataPart("id",mProcess?.id.toString())
                    mIPresenter.editProgress(builder.build().parts())
                }
            }
        }
    }

    override fun requestSuccess(any: Any) {
        mFrameApp?.mBus?.post( ProjectProgressBean.ProjectProgress())
        finish()
    }

    class ProcessChange(var name:String)
    val mAdapter = PublishPicAdapter(this)
    val picSelectHelper = PicSelectHelper()


    override fun setupDagger(appComponent: AppComponent) {
        DaggerBaseComponent.builder()
                .appComponent(appComponent)
                .baseIViewModule(BaseIViewModule(this))
                .build()
                .inject(this)
    }

    override fun initView() {
        setToolbar(intent.getStringExtra("title"))
        rv_list.adapter = mAdapter
        rv_list.layoutManager = LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false)
    }

    override fun initData() {
        mXmid = intent.getStringExtra("xmid")
        if(intent.hasExtra("sBean")){
            mProcess = intent.getSerializableExtra("sBean") as ProjectProgressBean.ProjectProgress
            var format = SimpleDateFormat(getString(R.string.date_format))
            var calender = Calendar.getInstance()
            calender.timeInMillis = mProcess?.uptime!!
            mDate = calender.time

            v_title.setText(mProcess?.title)
            v_edit.setText(mProcess?.desc)
            v_2_time.setHintName(format.format(mDate))
            var photos: ArrayList<PhotoBean> = ArrayList()
            mProcess?.photos?.forEach {
                photos.add(PhotoBean(it))
            }
            mAdapter.addData(photos)
        }
    }

    override fun initEvent() {
        super.initEvent()
        mAdapter.addOnItemCliclListener { _, position ->
            if (mAdapter.getItemViewType(position) == PublishPicAdapter.ADD)
                picSelectHelper.showSelectPicPopup(this, 3)
        }
        arrayOf(v_2_time, v_submit).setViewListener(this)
    }

    override fun getLayoutID(): Any = R.layout.activity_progress_publish


    //显示日期选择器
    private fun showPickerDate(@IdRes id: Int) {
        val text = findViewById<MyEditView>(id)
        val pickerView = TimePickerView.Builder(this, TimePickerView.OnTimeSelectListener { date, v ->
            val format = SimpleDateFormat(getString(R.string.date_format))
            val dateStr = format.format(date)
            text.setHintName(dateStr)
            mDate = date
        }).setType(booleanArrayOf(true, true, true, false, false, false)).build()
        pickerView.setDate(Calendar.getInstance())
        if (!pickerView.isShowing())
            pickerView.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(data!=null) picSelectHelper.onActivityResult(requestCode, resultCode, data!!, {
            mAdapter.addData(it)
        })
        super.onActivityResult(requestCode, resultCode, data)
    }
}
