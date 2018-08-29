package com.mmy.charitablexi.model.personal.ui.activity

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.commun.ui.adapter.PublishArticleAdapter
import com.mmy.charitablexi.model.personal.component.DaggerAddAdvComponent
import com.mmy.charitablexi.model.personal.module.AddAdvModule
import com.mmy.charitablexi.model.personal.presenter.AddAdvPresenter
import com.mmy.frame.AppComponent
import com.mmy.frame.base.adapter.BaseRecyclerViewAdapter
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.helper.PicSelectHelper
import com.mmy.frame.utils.CommonUtil
import kotlinx.android.synthetic.main.activity_add_adv.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*

/**
 * @file       PublishArticleActivity.kt
 * @brief      添加广告
 * @author     lucas
 * @date       2018/5/19 0019
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class AddAdvActivity : BaseActivity<AddAdvPresenter>(), BaseRecyclerViewAdapter.OnItemClickListener {
    override fun requestSuccess(any: Any) {
    }

    override fun onItemClick(view: View?, position: Int) {
        if (position == mAdapter.itemCount - 1)
            mPicHelper.getPicFormAlbum(this, 3)
    }

    lateinit var mAdapter: PublishArticleAdapter
    val mPicHelper = PicSelectHelper()

    override fun setupDagger(appComponent: AppComponent) {
        DaggerAddAdvComponent.builder()
                .addAdvModule(AddAdvModule(this))
                .appComponent(appComponent)
                .build().inject(this)
    }

    override fun initView() {
        setToolbar("新增广告")
        v_list.layoutManager = LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false)
        mAdapter = PublishArticleAdapter(this)
        v_list.adapter = mAdapter
    }

    override fun initData() {
    }

    override fun initEvent() {
        mAdapter.setOnItemClickListener(this)
        v_submit.setOnClickListener {
            val title = v_title.text.toString().trim()
            if (TextUtils.isEmpty(title)) {
                "请输入标题".showToast(mFrameApp)
                return@setOnClickListener
            }
            val pic = mAdapter.data
            if (pic == null || pic.size == 0) {
                "请上传图片".showToast(mFrameApp)
                return@setOnClickListener
            }
            val content = v_content.text.toString().trim()
            if (TextUtils.isEmpty(content)) {
                "请输入广告内容".showToast(mFrameApp)
                return@setOnClickListener
            }

            val builder = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("title", title)
                    .addFormDataPart("content", content)
            var index = 0
            pic.forEach {
                val body = RequestBody.create(MediaType.parse("multipart/form-data"), File(it.path))
                builder.addFormDataPart("imgs[${index++}]", CommonUtil.getFileName(it.path), body)
                Log.e("xxxbc", "xxxbc:"+CommonUtil.getFileName(it.path))
            }
            mIPresenter.submit(builder.build().parts())
        }
    }

    override fun getLayoutID(): Any = R.layout.activity_add_adv

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        mPicHelper.onActivityResult(requestCode, resultCode, data, {
            mAdapter.setData(LinkedList(it))
        })
    }

}