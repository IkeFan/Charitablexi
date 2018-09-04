package com.mmy.charitablexi.model.commun.ui.fragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.View
import android.widget.TextView
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.commun.ui.window.CommunPopup
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseFragment
import com.mmy.frame.utils.UIUtil
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

    lateinit var tabStr:Array<String>
    val tabFragment = arrayOf(OpenClassFragment(), InteractionFragment())
    lateinit var popup: CommunPopup

    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun getLayoutId(): Int = R.layout.fragment_commun

    override fun initView() {
        tabStr = arrayOf(getString(R.string.open_class), getString(R.string.communicate))
        tabStr?.forEach {
            var tab =  v_tabs.newTab().setText(it)
            var textView = tab.customView?.findViewById<TextView>(R.id.v_tv)
            textView?.text = it
            textView?.textSize = UIUtil.dp2px(getAc(),12).toFloat()
            v_tabs.addTab(tab)
        }
        v_tabs.setupWithViewPager(v_pager)
        v_pager.adapter = CommunAdapter(tabStr!!, tabFragment, childFragmentManager)
        popup = CommunPopup(getAc())
        popup.showDel(false)
        popup.showEdit(false)
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