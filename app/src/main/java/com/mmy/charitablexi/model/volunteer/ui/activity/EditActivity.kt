package com.mmy.charitablexi.model.volunteer.ui.activity

import android.text.InputType
import android.text.TextUtils
import android.view.View
import com.mmy.charitablexi.R
import com.mmy.charitablexi.widget.MyEditView
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : BaseActivity<IPresenter<*>>() {
    override fun requestSuccess(any: Any) {
    }

    val dataBean = MyEditView.DataBean()

    companion object {
        val edit = 0
        val big_edit = 1
    }

    var currentType = edit

    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun initView() {
        val s = intent.getStringExtra("type")
        if (TextUtils.isEmpty(s)) {
            currentType = edit
        } else
            currentType = s.toInt()
        val title = intent.getStringExtra("title")
        val hint = intent.getStringExtra("hint")
        val input_type = intent.getIntExtra("input_type", InputType.TYPE_CLASS_TEXT)
        v_text.inputType = input_type
        v_text2.inputType = input_type

        when (currentType) {
            edit -> {
                v_text.hint = hint
                v_text2.visibility = View.GONE
            }
            big_edit -> {
                v_text2.hint = hint
                v_text.visibility = View.GONE
            }
        }

        dataBean.tag = intent.getStringExtra("tag")
        setToolbar(title)
    }

    override fun initData() {
    }

    override fun getLayoutID(): Any = R.layout.activity_edit

    override fun onDestroy() {
        super.onDestroy()
        when (currentType) {
            edit -> {
                dataBean.text = v_text.text.toString().trim()
            }
            big_edit -> {
                dataBean.text = v_text2.text.toString().trim()
            }
        }
        mFrameApp?.mBus?.post(dataBean)
    }

}
