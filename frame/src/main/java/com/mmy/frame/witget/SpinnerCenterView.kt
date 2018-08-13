package com.mmy.frame.witget

import android.content.Context
import android.support.v7.widget.AppCompatSpinner
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.mmy.frame.R
import java.util.*

/**
 * @file       SpinnerCenterView.kt
 * @brief      内容居中的spinner
 * @author     lucas
 * @date       2018/3/28 0028
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class SpinnerCenterView : AppCompatSpinner {
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.SpinnerCenterView)
        val resourceId = attributes.getResourceId(R.styleable.SpinnerCenterView_spinner_value, -1)
        if (resourceId != -1) {
            var array = context.resources.getStringArray(resourceId)
//            if (array.isEmpty()) {
//                val intArray = context.resources.getIntArray(resourceId)
//                    array = intChangeString(intArray)
//            }
            //重写adapter
            resetAdapter(context, array)
        }
    }

    private fun resetAdapter(context: Context, array: Array<String>) {
        adapter = object : ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, array) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                return setCenterView(super.getView(position, convertView, parent))
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
                return setCenterView(super.getDropDownView(position, convertView, parent))
            }

            //给内部的textview添加居中属性
            private fun setCenterView(view: View): TextView {
                val textView = view.findViewById<TextView>(android.R.id.text1)
                textView.gravity = Gravity.CENTER
                textView.setPadding(0, 10, 0, 10)
                return textView
            }
        }
    }

    //数字数组转化字符数组
    fun intChangeString(array: IntArray): Array<out String>? {
        val arrayList = ArrayList<String>()
        array.forEach {
            arrayList.add(it.toString())
        }
        return arrayList.toArray() as Array<out String>
    }
}