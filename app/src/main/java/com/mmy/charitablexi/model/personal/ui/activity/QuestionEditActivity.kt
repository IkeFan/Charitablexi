package com.mmy.charitablexi.model.personal.ui.activity

import android.content.Intent
import android.view.View
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.component.DaggerQuestionEditComponent
import com.mmy.charitablexi.model.personal.module.QuestionEditModule
import com.mmy.charitablexi.model.personal.presenter.QuestionEditPresenter
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.helper.PicSelectHelper
import kotlinx.android.synthetic.main.activity_question_edit.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * @file       QuestionEditActivity
 * @brief      题库编辑
 * @author     lucas
 * @date       2018/6/5 0005
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class QuestionEditActivity : BaseActivity<QuestionEditPresenter>(), View.OnClickListener {
    val mPicHelper = PicSelectHelper()
    var mSelectPic: String? = null

    override fun requestSuccess(data: Any) {
    }

    override fun setupDagger(appComponent: AppComponent) {
        DaggerQuestionEditComponent.builder()
                .appComponent(appComponent)
                .questionEditModule(QuestionEditModule(this))
                .build().inject(this)
    }

    override fun initView() {
        setToolbar("编辑", rightRes = "保存", rightTextColor = resources.getColor(R.color.colorPrimary), rightClickListener = this)
    }

    override fun initData() {
    }

    override fun initEvent() {
        arrayOf(v_pic).setViewListener(this)
    }

    override fun getLayoutID(): Any = R.layout.activity_question_edit

    override fun onClick(v: View) {
        when (v.id) {
            R.id.toolbar_right -> {
                submit()
                finish()
            }
            R.id.v_pic -> {
                mPicHelper.showSelectPicPopup(this, 1)
            }
        }
    }

    private fun submit() {
        checkData(arrayOf(v_title, v_pic_count, v_a1, v_a2, v_a3, v_a4, v_analyze),
                arrayOf("请输入题目", "请选择图片", "请输入答案A", "请输入答案B", "请输入答案C", "请输入答案D", "请输入答案解析"),
                {   var file = File(mSelectPic)
                    var builder: MultipartBody.Builder = MultipartBody.Builder()
                            .addFormDataPart("uid", mFrameApp?.userInfo?.id.toString())
                            .addFormDataPart("title", v_title.text.toString())
                            .addFormDataPart("opta", v_a1.text.toString())
                            .addFormDataPart("optb", v_a2.text.toString())
                            .addFormDataPart("optc", v_a3.text.toString())
                            .addFormDataPart("optd", v_a4.text.toString())
                            .addFormDataPart("analyze", v_analyze.text.toString())
                            .addFormDataPart("img", file.getName(), RequestBody.create(MediaType.parse("image/*"), file))

                    mIPresenter.addQue(builder.build().parts())
                },
                {
                    if (v_c1.isChecked || v_c2.isChecked || v_c3.isChecked || v_c4.isChecked)
                        false
                    else {
                        "请至少选择一个答案".showToast(mFrameApp)
                        true
                    }
                })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        mPicHelper.onActivityResult(requestCode, resultCode, data, {
            if (it.size > 0) {
                v_pic_count.text = "1"
                mSelectPic = it[0].path
            } else {
                v_pic_count.text = ""
                mSelectPic = null
            }
        })
        super.onActivityResult(requestCode, resultCode, data)
    }
}
