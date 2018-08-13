package com.mmy.charitablexi.model.project.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.mmy.frame.base.view.BaseFragment

/**
 * @file       ProjectVPAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/4/16 0016
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class ProjectVPAdapter(val tabs: Array<String>, val fragments: Array<Any>, val fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment = fragments[position] as BaseFragment<*>

    override fun getCount(): Int = tabs.size
//
//    override fun getPageTitle(position: Int): CharSequence =tabs[position]
}