package com.mmy.charitablexi.widget

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.volunteer.ui.activity.EditActivity
import com.mmy.frame.FrameApp
import com.mmy.frame.data.bean.PhotoBean
import com.mmy.frame.helper.PicSelectHelper
import com.squareup.otto.Subscribe

/**
 * @file       MyEditView.kt
 * @brief      自定义组合控件
 * @author     lucas
 * @date       2018/4/18 0018
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class MyEditView(context: Context, attributes: AttributeSet) : FrameLayout(context, attributes), View.OnClickListener {
    var nameStr = ""
    var hintStr = ""
    var enable = true

    var isShowDivide = true
    var type: Int = 1
    lateinit var rootView: ViewGroup
    //唯一标识用于bus数据的标记
    val tag = System.currentTimeMillis().toString()
    var data: DataBean? = null
    var popup: PicSelectHelper? = null
    //最大选择图片数量
    var maxPic: Int = 1


    //回调
    var listener: (DataBean) -> Unit = {}

    init {
        val array = context.obtainStyledAttributes(attributes, R.styleable.MyEditView)
        nameStr = array.getString(R.styleable.MyEditView_name) ?: ""
        hintStr = array.getString(R.styleable.MyEditView_hint) ?: ""
        isShowDivide = array.getBoolean(R.styleable.MyEditView_isShowDivide, true)
        enable = array.getBoolean(R.styleable.MyEditView_enable, true)
        type = array.getInt(R.styleable.MyEditView_type, 1)
        maxPic = (array.getString(R.styleable.MyEditView_maxPic) ?: "1").toInt()
        rootView = LayoutInflater.from(context).inflate(R.layout.my_edit_view, this) as ViewGroup
        setName(nameStr)
        setHint(hintStr)
        if (isShowDivide)
            setDivideVisib(View.VISIBLE)
        else
            setDivideVisib(View.GONE)
        isEnabled = enable
        if (enable)
            rootView.findViewById<View>(R.id.v_enable).visibility = View.VISIBLE
        else
            rootView.findViewById<View>(R.id.v_enable).visibility = View.GONE

        setOnClickListener(this)
    }

    fun enable(value:Boolean){
        isEnabled = value
        if (value)
            rootView.findViewById<View>(R.id.v_enable).visibility = View.VISIBLE
        else
            rootView.findViewById<View>(R.id.v_enable).visibility = View.GONE
    }

    fun setDivideVisib(visib: Int) {
        rootView.findViewById<View>(R.id.divide).visibility = visib
    }

    fun setName(name: String) {
        rootView.findViewById<TextView>(R.id.name).text = name
        nameStr = name
    }

    fun setHint(hint: String) {
        rootView.findViewById<TextView>(R.id.hint).hint = hint
        hintStr = hint
    }

    fun setHintName(text: String) {
        rootView.findViewById<TextView>(R.id.hint).text = text
        hintStr = text
    }

    override fun onClick(p0: View?) {
        FrameApp.frameInstance.mBus.register(this)
        when (type) {
            1 -> {
                if (context is Activity) {
                    val intent = Intent(context, EditActivity::class.java)
                    intent.putExtra("tag", tag)
                    intent.putExtra("title", nameStr)
                    intent.putExtra("hint", "请输入$nameStr")
                    context.startActivity(intent)
                }
            }
            2 -> {
                if (popup == null)
                    popup = PicSelectHelper()
                popup?.showSelectPicPopup(context as Activity, maxPic)
            }
        }
    }

    @Subscribe
    fun onReceiver(bean: DataBean) {
        if (bean.tag == tag) {
            listener(bean)
            when (type) {
                1 -> {
                    setHintName(bean.text)
                }
                2 -> {
                    setHintName("已选择${bean.uploadUris.size}个文件")
                }
            }
        }
        FrameApp.frameInstance.mBus.unregister(this)
    }

    //如果type == 2那么必须要在activity的onActivityResult里调用该方法
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(data!=null){
            popup?.onActivityResult(requestCode, resultCode, data!!, {
                onReceiver(DataBean(tag, "", it))
            })
        }
        else{
            FrameApp.frameInstance.mBus.unregister(this)
        }
    }

    class DataBean constructor(var tag: String = "", var text: String = "", var uploadUris: ArrayList<PhotoBean> = arrayListOf())
}