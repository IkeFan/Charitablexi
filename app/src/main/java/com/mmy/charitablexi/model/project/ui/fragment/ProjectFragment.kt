package com.mmy.charitablexi.model.project.ui.fragment

import android.graphics.Typeface
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.util.Log
import android.widget.TextView
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.project.ui.adapter.ProjectVPAdapter
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseFragment
import com.mmy.frame.utils.UIUtil
import kotlinx.android.synthetic.main.fragment_project.*

/**
 * @file       ProjectFragment.kt
 * @brief      项目
 * @author     lucas
 * @date       2018/4/13 0013
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class ProjectFragment : BaseFragment<IPresenter<*>>() {
    override fun requestSuccess(any: Any) {
    }

    val tabs = arrayOf("Raising", "Donation", "Complete")
    val fragments = arrayOf(RaisingFragment(), DonationFragment(), CompleteFragment())
    lateinit var adapter: ProjectVPAdapter

    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun getLayoutId(): Int = R.layout.fragment_project
    override fun initView() {
        adapter = ProjectVPAdapter(tabs, fragments, childFragmentManager)
        tabs.forEach {
            val tab = tl_tabs.newTab()
            tab.setCustomView(R.layout.view_textview)
            var textView = tab.customView?.findViewById<TextView>(R.id.v_tv)
            textView?.text = it
            textView?.textSize = UIUtil.dp2px(getAc(),6).toFloat()
            tl_tabs.addTab(tab)
        }
    }

    override fun initData() {
    }

    override fun initEvent() {
        super.initEvent()
        vp_list.setOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                tl_tabs.getTabAt(position)?.select()
            }
        })
        tl_tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                var textView = tab?.customView?.findViewById<TextView>(R.id.v_tv)
                textView?.textSize = UIUtil.dp2px(getAc(),6).toFloat()
                textView?.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                vp_list.setCurrentItem(tab.position,true)

                Log.d("lucas","position:${tab.position}")
                var textView = tab.customView?.findViewById<TextView>(R.id.v_tv)
                textView?.textSize = UIUtil.dp2px(getAc(),7).toFloat()
                textView?.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
            }

        })
        vp_list.adapter = adapter
    }
}