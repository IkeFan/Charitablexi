package com.mmy.charitablexi.model.volunteer.ui.activity

import android.content.Intent
import android.support.annotation.IdRes
import android.view.View
import android.widget.EditText
import com.bigkoo.pickerview.TimePickerView
import com.mmy.charitablexi.R
import com.mmy.charitablexi.base.BaseIViewModule
import com.mmy.charitablexi.base.DaggerBaseComponent
import com.mmy.charitablexi.model.personal.ui.activity.ChoiceAgreActivity
import com.mmy.charitablexi.model.volunteer.presenter.AddOrEditZpPresenter
import com.mmy.charitablexi.widget.MyEditView
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.AgreListBean
import com.mmy.frame.data.bean.PhotoBean
import com.mmy.frame.helper.PicSelectHelper
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.activity_publish_recruit.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * @file       PublishRecruitActivity.kt
 * @brief      发布招聘
 * @author     lucas
 * @date       2018/4/18 0018
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class PublishRecruitActivity : BaseActivity<AddOrEditZpPresenter>(), View.OnClickListener {
    val mPicHelper = PicSelectHelper()
    var mChosePic: List<PhotoBean>? = null
    var oid: Int? = null
    var  agreid:String? = null

    override fun requestSuccess(any: Any) {
        finish()
    }

    override fun setupDagger(appComponent: AppComponent) {
        DaggerBaseComponent.builder()
                .appComponent(appComponent)
                .baseIViewModule(BaseIViewModule(this))
                .build().inject(this)
    }

    override fun initView() {
        setToolbar(getString(R.string.public_recruitment))
    }

    override fun initData() {
        if (intent.hasExtra("id")) {
            oid = intent.getStringExtra("id").toInt()
        }
    }

    override fun initEvent() {
        arrayOf(v_publish, v_start_time, v_end_time1, v_protocol, v_end_time).setViewListener(this)
    }

    override fun getLayoutID(): Any = R.layout.activity_publish_recruit


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            mPicHelper.onActivityResult(requestCode, resultCode, data, {
                mChosePic = it
                v_pic.setHint( getString(R.string.chose_already))
            })
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.v_publish -> {
                arrayOf(v_name, v_need_number, v_address, v_work_content, v_phone, v_email, v_protocol).forEach {
                    if (it.hintStr.isEmpty() || it.hintStr == "*") {
                        "Please Input ${it.nameStr}".showToast(mFrameApp)
                        return@onClick
                    }
                }
                arrayOf(v_start_time, v_end_time1).forEach {
                    if (it.text.toString().isNullOrEmpty()) {
                        "Please Input ${it.hint}".showToast(mFrameApp)
                        return@onClick
                    }
                }

                if (mChosePic == null) {
                    getString(R.string.chose_pic).showToast(mFrameApp)
                    return@onClick
                }
                if(agreid==null){
                    "Please chose protocol".showToast(mFrameApp)
                    return@onClick
                }

                val format = SimpleDateFormat(getString(R.string.date_format))
                var start = format.parse( v_start_time.text.toString()).time
                var end = format.parse(v_end_time1.text.toString()).time
                var worktime = start.toString()+","+end.toString()

                val builder = MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("name", v_name.hintStr)
                        .addFormDataPart("persons", v_need_number.hintStr)
                        .addFormDataPart("address", v_address.hintStr)
                        .addFormDataPart("worktime", worktime)
                        .addFormDataPart("content", v_work_content.hintStr)
                        .addFormDataPart("telphone", v_phone.hintStr)
                        .addFormDataPart("email", v_email.hintStr)
                        .addFormDataPart("oid", oid.toString())
                        .addFormDataPart("other", v_other.hintStr)
                        .addFormDataPart("xyid", agreid)

                if(!v_end_time?.hintStr.isNullOrEmpty() && v_end_time.hintStr!=getString(R.string.always)){
                    var endTime = format.parse(v_end_time.hintStr.trim())
                    builder.addFormDataPart("endtime", endTime.toString())
                }else{
                    builder.addFormDataPart("endtime", "1")
                }

                mChosePic!!.forEach {
                    var file = File(it.path)
                    val body = RequestBody.create(MediaType.parse("multipart/form-mData"), file)
                    builder.addFormDataPart("photo", file.name, body)
                }
                mIPresenter.addZp(builder.build().parts())
            }
            R.id.v_start_time -> showPickerDate(R.id.v_start_time)
            R.id.v_end_time1 -> showPickerDate(R.id.v_end_time1)
            R.id.v_end_time->  showPickerDate(R.id.v_end_time)
            R.id.v_protocol ->  openActivity(ChoiceAgreActivity::class.java, "title="+getString(R.string.chose_protocol))
        }
    }


    //显示日期选择器
    private fun showPickerDate(@IdRes id: Int) {
        val text = findViewById<View>(id)
        val pickerView = TimePickerView.Builder(this, TimePickerView.OnTimeSelectListener { date, v ->
            val format = SimpleDateFormat(getString(R.string.date_format))
            val dateStr = format.format(date)
            when(text){
                is MyEditView->{text.setHint(dateStr)}
                is EditText->text.setText(dateStr)
            }

        }).setType(booleanArrayOf(true, true, true, false, false, false)).build()
        pickerView.setDate(Calendar.getInstance())
        if (!pickerView.isShowing())
            pickerView.show()
    }

    override fun registerBus(): Boolean = true

    @Subscribe
    fun onChoiceAgre(data: AgreListBean.DataBean) {
        v_protocol.setHint(data.title)
        agreid = data.id
    }
}
