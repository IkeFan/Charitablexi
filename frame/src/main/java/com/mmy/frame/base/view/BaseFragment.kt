package com.mmy.frame.base.view

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import com.mmy.frame.AppComponent
import com.mmy.frame.FrameApp
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import com.mmy.frame.helper.CommentHelper
import javax.inject.Inject


/**
 * @创建者     lucas
 * @创建时间   2017/12/25 0025 15:20
 * @描述          TODO
 */
 abstract class BaseFragment<P : IPresenter<*>> : Fragment(), IView, CommentHelper {

    @Inject lateinit var mIPresenter: P
    @Inject lateinit var mFramApp:FrameApp
    val mHandler: Handler = Handler()
    lateinit var rootView:View

    open fun getAc() = activity!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupDagger((activity?.application as FrameApp).appComponent)
        rootView = LayoutInflater.from(activity).inflate(getLayoutId(), null, false)
        if (registerBus())
            mFramApp.mBus.register(this)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initData()
        initEvent()
    }

    abstract fun setupDagger(appComponent: AppComponent)
    abstract fun getLayoutId(): Int
    abstract fun initView()
    abstract fun initData()
    open fun initEvent(){}
    open fun registerBus(): Boolean = false

    override fun showLoading() {
        (activity as BaseActivity<*>).showLoading()
    }

    override fun hidLoading() {
        (activity as BaseActivity<*>).hidLoading()
    }

    override fun <A : Activity> openActivity(a: Class<A>, params: String, serializableBean: Any?, isResult: Boolean, requestCode: Int) {
        (activity as BaseActivity<*>).openActivity(a, params, serializableBean,isResult, requestCode)
    }

    /**
     * 优化事件设置
     * 目标：布局ID或者View
     * 作用：给控件添加任何事件
     */
    open fun Array<out Any>.setViewListener(listener: Any) {
        this.forEach {
            when (listener) {
                is View.OnClickListener -> {
                    if (it is Int)
                        rootView.findViewById<View>(it).setOnClickListener(listener)
                    (it as? View)?.setOnClickListener(listener)
                }
                is View.OnLongClickListener -> {
                    if (it is Int)
                        rootView.findViewById<View>(it).setOnLongClickListener(listener)
                    (it as? View)?.setOnLongClickListener(listener)
                }
                is View.OnTouchListener -> {
                    if (it is Int)
                        rootView.findViewById<View>(it).setOnTouchListener(listener)
                    (it as? View)?.setOnTouchListener(listener)
                }
                is RadioGroup.OnCheckedChangeListener -> {
                    if (it is Int)
                        rootView.findViewById<RadioGroup>(it).setOnCheckedChangeListener(listener)
                    (it as? RadioGroup)?.setOnCheckedChangeListener(listener)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (registerBus())
            if (registerBus())
                mFramApp.mBus.unregister(this)
    }
}