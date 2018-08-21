package com.mmy.charitablexi

import android.support.design.widget.BottomNavigationView
import com.mmy.charitablexi.helper.BottomNavigationViewHelper
import com.mmy.charitablexi.model.commun.ui.fragment.CommunFragment
import com.mmy.charitablexi.model.login.ui.activity.LoginActivity
import com.mmy.charitablexi.model.personal.ui.fragment.PersonalFragment
import com.mmy.charitablexi.model.project.ui.fragment.ProjectFragment
import com.mmy.charitablexi.model.volunteer.ui.fragment.VolunteerFragment
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.UserInfo
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainPresenter>() {
    override fun requestSuccess(any: Any) {

    }

    val mFragmentTags = arrayOf("project", "volunteer", "commun", "personal")
    val mFragments = arrayOf(ProjectFragment(), VolunteerFragment(), CommunFragment(), PersonalFragment())

    override fun setupDagger(appComponent: AppComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(MainModule(this))
                .build().inject(this)
    }

    override fun initView() {
        BottomNavigationViewHelper.disableShiftMode(navigation)
        if (supportFragmentManager.findFragmentByTag(mFragmentTags[0])==null){
            supportFragmentManager.beginTransaction()
                    .add(R.id.fl_content, mFragments[0], mFragmentTags[0])
                    .commit()
        }
    }

    override fun initData() {
        mIPresenter.test()
    }

    override fun initEvent() {
        super.initEvent()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun getLayoutID(): Any = R.layout.activity_main

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_project -> {
                showFragmentByPosition(0)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_volunteer -> {
                showFragmentByPosition(1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_commun -> {
                showFragmentByPosition(2)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_personal -> {
                showFragmentByPosition(3)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun showFragmentByPosition(order: Int) {
        val showFragment = supportFragmentManager.findFragmentByTag(mFragmentTags[order])
        val transaction = supportFragmentManager.beginTransaction()
        if (showFragment == null) {
            transaction.add(R.id.fl_content, mFragments[order], mFragmentTags[order])
        } else {
            transaction.show(showFragment)
        }
        mFragmentTags.forEach {
            if (!it.equals(mFragmentTags[order])) {
                val fragment = supportFragmentManager.findFragmentByTag(it)
                if (fragment != null)
                    transaction.hide(fragment)
            }
        }
        transaction.commit()
    }


    override fun registerBus(): Boolean = true

    @Subscribe
    fun onUserEvent(userEvent: UserInfo.UserEvent) {
        when (userEvent.action) {
            "login" -> {
                //打开登录界面
                openActivity(LoginActivity::class.java)
            }
            else -> {
            }
        }
    }
}
