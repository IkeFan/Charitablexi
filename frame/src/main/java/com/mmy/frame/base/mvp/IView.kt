package com.mmy.frame.base.mvp

import android.app.Activity
import android.support.v4.widget.SwipeRefreshLayout
import android.view.ViewGroup


/**
 * @创建者     lucas
 * @创建时间   2017/12/23 0023 16:28
 * @描述          TODO
 */
interface IView {
    fun showLoading()
    fun hidLoading()
    fun requestSuccess(any: Any)

    //打开一个activity
    //打开一个activity并且传入参数 格式 ：key=value,key=value...
    //是否返回数据
    fun <A : Activity> openActivity(a: Class<A>, params: String = "", serializableBean:Any? = null, isResult: Boolean = false, requestCode: Int = 0)

    //关闭swipeRefreshView
    fun setRefreshing(isRefresh: Boolean) {
        if (this is Activity) {
            val root = findViewById<ViewGroup>(android.R.id.content)
            getViewByType(root)?.isRefreshing = isRefresh
        }
    }

    fun getViewByType(root: ViewGroup?): SwipeRefreshLayout? {
        (root?.childCount!! - 1).downTo(0).forEach {
            val childAt = root.getChildAt(it)
            if (childAt is SwipeRefreshLayout)
                return childAt
            if (childAt is ViewGroup)
                getViewByType(childAt)
        }
        return null
    }

    //关闭界面
    fun finishView() {
        if (this is Activity)
            finish()
    }

}