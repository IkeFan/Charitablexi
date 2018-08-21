package com.mmy.charitablexi.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import com.mmy.frame.base.view.BaseFragment
/**
 * @file       CommonAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/20 0020
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class CommonAdapter(val tabStr: Array<String>, val tabFragment: Array< BaseFragment<out IPresenter<IView>>>, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment = tabFragment[position]

    override fun getCount(): Int = tabStr.size

    override fun getPageTitle(position: Int): CharSequence {
        return tabStr[position]
    }
}