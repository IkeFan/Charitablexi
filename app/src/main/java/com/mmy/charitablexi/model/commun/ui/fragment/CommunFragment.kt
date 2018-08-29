package com.mmy.charitablexi.model.commun.ui.fragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.commun.ui.window.CommunPopup
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_commun.*

/**
 * @file       ProjectFragment.kt
 * @brief      交流圈
 * @author     lucas
 * @date       2018/4/13 0013
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class CommunFragment : BaseFragment<IPresenter<*>>(), View.OnClickListener {
    override fun requestSuccess(data: Any) {

    }

    val tabStr = arrayOf("公开课", "互动", "个人中心")
    val tabFragment = arrayOf(OpenClassFragment(), InteractionFragment(), PersonalCenterFragment())
    lateinit var popup: CommunPopup

    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun getLayoutId(): Int = R.layout.fragment_commun

    override fun initView() {
        tabStr.forEach {
            v_tabs.addTab(v_tabs.newTab().setText(it))
        }
        v_tabs.setupWithViewPager(v_pager)
        v_pager.adapter = CommunAdapter(tabStr, tabFragment, childFragmentManager)
        popup = CommunPopup(getAc())
//        popup.showDel(false)
//        popup.showEdit(false)
    }

    override fun initData() {
    }

    override fun initEvent() {
        arrayOf(v_add).setViewListener(this)
        popup.onPopClick = {
            var click:OnPopMenuClick = tabFragment[v_pager.currentItem] as OnPopMenuClick
            click.onMenuSelected(it)
            popup.dismiss()
        }
        v_pager.addOnPageChangeListener( object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                when(position){
                    0->{
                        popup.showDel(true)
                        popup.showEdit(true)
                    }
                    1->{
                        popup.showDel(false)
                        popup.showEdit(false)
                    }
                    2->{
                        popup.showDel(true)
                        popup.showEdit(true)
                    }
                }
            }

        })

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.v_add -> {
                popup.showAsDropDown(v)
            }
            else -> {
            }
        }
    }
    class CommunAdapter(val tabStr: Array<String>, val tabFragment: Array<Any>, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
        override fun getItem(position: Int): Fragment {
            return tabFragment[position] as BaseFragment<*>
        }

        override fun getCount(): Int = tabStr.size

        override fun getPageTitle(position: Int): CharSequence {
            return tabStr[position]
        }
    }

    interface OnPopMenuClick{
        fun onMenuSelected(v: View)
    }
}