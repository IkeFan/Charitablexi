package com.mmy.charitablexi.model.project.ui.activity

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.mmy.charitablexi.R
import com.mmy.charitablexi.bean.EvBusItemBean
import com.mmy.charitablexi.model.project.component.DaggerProjectInfoComponent
import com.mmy.charitablexi.model.project.module.ProjectInfoModuel
import com.mmy.charitablexi.model.project.presenter.ProjectInfoPresenter
import com.mmy.charitablexi.model.project.view.ProjectInfoView
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_send_love.*

class SendLoveActivity : BaseActivity<ProjectInfoPresenter>(), ProjectInfoView, View.OnClickListener{
    var mPid:Int?=0
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.v_summit->{
                if(v_love_et.text.toString().trim().isEmpty()){
                    (getString(R.string.input_notice)+getString(R.string.love_value)).showToast(mFrameApp)
                }else if(v_love_et.text.toString().trim().toInt() > mFrameApp?.userInfo?.lovesum!!){
                    getString(R.string.love_value_less).showToast(mFrameApp)
                }else{
                    mIPresenter.sendLove(mPid!!, v_love_et.text.toString().toInt())
                }
            }
            else->{

            }

        }
    }

    override fun setupDagger(appComponent: AppComponent) {
        DaggerProjectInfoComponent.builder()
                .projectInfoModuel(ProjectInfoModuel(this))
                .appComponent(appComponent)
                .build()
                .inject(this)
    }

    override fun initView() {
        setToolbar(getString(R.string.send_love))
    }

    override fun initData() {
        v_love_count.text = mFrameApp?.userInfo?.lovesum.toString()

    }

    override fun initEvent() {
        v_check_all.setOnCheckedChangeListener { compoundButton, b ->
            if(b){
                v_love_et.setText(mFrameApp?.userInfo?.lovesum.toString())
            }
        }

        v_love_et.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                v_check_all.isChecked = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
        arrayOf(v_summit).setViewListener(this)
    }

    override fun getLayoutID(): Any = R.layout.activity_send_love

    override fun requestSuccess(any: Any) {
        val value = v_love_et.text.toString().trim().toInt()
        openActivity(ThankGiveLoveActivity::class.java, "star="+v_love_et.text.toString().trim().toInt())
        mBus.post(EvBusItemBean(0,value))
        finish()
    }


}
