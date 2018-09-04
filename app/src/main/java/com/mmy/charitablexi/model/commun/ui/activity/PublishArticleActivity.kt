package com.mmy.charitablexi.model.commun.ui.activity

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
import com.mmy.charitablexi.bean.EvBusItemBean
import com.mmy.charitablexi.model.commun.presenter.AddOrEdClassPresenter
import com.mmy.charitablexi.model.commun.ui.adapter.PublishArticleAdapter
import com.mmy.charitablexi.widget.MyEditView
import com.mmy.frame.AppComponent
import com.mmy.frame.base.adapter.BaseRecyclerViewAdapter
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.ClassBean
import com.mmy.frame.data.bean.PhotoBean
import com.mmy.frame.helper.PicSelectHelper
import kotlinx.android.synthetic.main.activity_publish_article.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * @file       PublishArticleActivity.kt
 * @brief      发表文章
 * @author     lucas
 * @date       2018/5/19 0019
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class PublishArticleActivity : BaseActivity<AddOrEdClassPresenter>(), BaseRecyclerViewAdapter.OnItemClickListener, View.OnClickListener {
    var mArticle: ClassBean.DataBean? = null
    var mDate: Date?=null
    override fun requestSuccess(any: Any) {
        mFrameApp?.mBus?.post(EvBusItemBean(-1, ClassBean.DataBean()))
        finish()
    }

    override fun onItemClick(view: View?, position: Int) {
        if (position == mAdapter.itemCount - 1 && mAdapter.itemCount < 4)
            mPicHelper.getPicFormAlbum(this, 4 - mAdapter.itemCount)
    }

    lateinit var mAdapter: PublishArticleAdapter
    val mPicHelper = PicSelectHelper()

    override fun setupDagger(appComponent: AppComponent) {
        DaggerBaseComponent.builder()
                .appComponent(appComponent)
                .baseIViewModule(BaseIViewModule(this))
                .build()
                .inject(this)
    }

    override fun initView() {
        setToolbar(intent.getStringExtra("title"))
        if (intent.hasExtra("sBean")) {
            mArticle = intent.getSerializableExtra("sBean") as ClassBean.DataBean?
            v_radio_group.visibility = View.GONE
        }

        v_list.layoutManager = LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false)
        mAdapter = PublishArticleAdapter(this)
        v_list.adapter = mAdapter
    }

    override fun initData() {
        if (mArticle != null) {
            v_title.setText(mArticle?.title)
            v_content.setText(mArticle?.content)
            v_name.setText(mArticle?.speaker)
            var format = SimpleDateFormat(getString(R.string.date_format))
            var calender = Calendar.getInstance()
            calender.timeInMillis = mArticle?.addtime!!.toLong()
            mDate = calender.time
            v_2_time.setHintName(format.format(calender.time))

            when (intent.getStringExtra("type").toInt()) {
                1 -> v_radio_group.check(R.id.rb_theory)
                2 -> v_radio_group.check(R.id.rb_execute)
                3 -> v_radio_group.check(R.id.rb_other)
            }

            var photos: LinkedList<PhotoBean> = LinkedList()
            if (mArticle?.imgs != null) {
                mArticle?.imgs?.forEach {
                    photos.add(PhotoBean(it))
                }
                mAdapter.setData(photos)
            }
        }
    }

    override fun initEvent() {
        mAdapter.setOnItemClickListener(this)
        arrayOf(R.id.v_publish, R.id.v_2_time).setViewListener(this)
    }

    override fun getLayoutID(): Any = R.layout.activity_publish_article

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data!=null){
            mPicHelper.onActivityResult(requestCode, resultCode, data, {
              if(it!=null)  mAdapter.setData(LinkedList(it))
            })
        }
    }

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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.v_2_time -> showPickerDate(R.id.v_2_time)
            R.id.v_publish -> {
                if(v_2_time.hintStr.isNullOrEmpty()){
                    getString(R.string.chose_time).showToast(mFrameApp)
                    return
                }
                val builder = MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("uid", App.instance.userInfo.id.toString())
                        .addFormDataPart("title", v_title.text.toString())
                        .addFormDataPart("content", v_content.text.toString())
                        .addFormDataPart("speaker", v_name.text.toString())
                        .addFormDataPart("recordtime",mDate?.time.toString())

                if(mArticle!=null){
                    builder.addFormDataPart("id",mArticle?.id.toString())
                    builder.addFormDataPart("type",mArticle?.type.toString())
                }else{
                    when(v_radio_group.checkedRadioButtonId){
                        R.id.rb_theory->{builder.addFormDataPart("type","1")}
                        R.id.rb_execute->{builder.addFormDataPart("type","2")}
                        R.id.rb_other->{builder.addFormDataPart("type", "3")}
                    }
                }
                mAdapter.data.forEach {
                    if (!it.path.startsWith("/Uploads")) {
                        var file = File(it.path)
                        builder.addFormDataPart("imgs1[]", file.getName(), RequestBody.create(MediaType.parse("image/*"), file))
                    }else{
                        builder.addFormDataPart("imgs[]",it.path)
                    }
                }
                mIPresenter.addOrEditClass(builder.build().parts())
            }
        }
    }
}
