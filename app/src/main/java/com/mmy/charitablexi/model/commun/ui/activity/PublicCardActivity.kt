package com.mmy.charitablexi.model.commun.ui.activity

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.View
import com.mmy.charitablexi.App
import com.mmy.charitablexi.R
import com.mmy.charitablexi.base.BaseIViewModule
import com.mmy.charitablexi.base.DaggerBaseComponent
import com.mmy.charitablexi.bean.EvBusItemBean
import com.mmy.charitablexi.model.commun.presenter.AddOrEdCardPresenter
import com.mmy.charitablexi.model.commun.ui.adapter.PublishArticleAdapter
import com.mmy.frame.AppComponent
import com.mmy.frame.base.adapter.BaseRecyclerViewAdapter
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.PersonalCenterBean
import com.mmy.frame.data.bean.PhotoBean
import com.mmy.frame.helper.PicSelectHelper
import kotlinx.android.synthetic.main.activity_public_card.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*

class PublicCardActivity : BaseActivity<AddOrEdCardPresenter>(), BaseRecyclerViewAdapter.OnItemClickListener, View.OnClickListener {
    var mCard: PersonalCenterBean.PersonalCenter? = null
    var mPosition: Int? = -1

    override fun onClick(v: View?) {

        val builder = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("uid", App.instance.userInfo.id.toString())
                .addFormDataPart("title", v_edit_title.text.toString())
                .addFormDataPart("content", v_edit_content.text.toString())
        when (v_radio_group.checkedRadioButtonId) {
            R.id.rb_employ -> {
                builder.addFormDataPart("type", "1")
            }
            R.id.rb_latest -> {
                builder.addFormDataPart("type", "2")
            }
            R.id.rb_hot -> {
                builder.addFormDataPart("type", "3")
            }

        }

        mAdapter.data.forEach {
            if (!it.path.startsWith("/Uploads")) {
                var file = File(it.path)
                builder.addFormDataPart("imgs[]", file.getName(), RequestBody.create(MediaType.parse("image/*"), file))
            }
        }
        if (mCard != null) {
            builder.addFormDataPart("id", mCard?.id.toString())
        }
        mIPresenter.addOrEditCard(builder.build().parts())
    }

    override fun requestSuccess(any: Any) {
        var bean = mCard
        if (bean == null) {
            bean = PersonalCenterBean.PersonalCenter()
        }
        mFrameApp?.mBus?.post(EvBusItemBean(mPosition!!, mCard!!))
        finish()
    }

    override fun onItemClick(view: View?, position: Int) {
        if (position == mAdapter.itemCount - 1 && mAdapter.itemCount < 4)
            mPicHelper.getPicFormAlbum(this, 4 - mAdapter.itemCount)
    }

    lateinit var mAdapter: PublishArticleAdapter
    val mPicHelper = PicSelectHelper()

    override fun setupDagger(appComponent: AppComponent) {
        DaggerBaseComponent.builder()
                .appComponent(appComponent)
                .baseIViewModule(BaseIViewModule(this))
                .build()
                .inject(this)
    }

    override fun initView() {
        if (intent.hasExtra("item")) {
            mPosition = intent.getIntExtra("position", -1)
            mCard = intent.getSerializableExtra("item") as PersonalCenterBean.PersonalCenter?
            setToolbar(getString(R.string.edit))
        } else {
            setToolbar(getString(R.string.public_card))
        }

        v_list.layoutManager = LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false)
        mAdapter = PublishArticleAdapter(this)
        v_list.adapter = mAdapter
    }

    override fun initData() {
        if (mCard != null) {
            v_edit_title.setText(mCard?.title)
            v_edit_content.setText(mCard?.content)
            var photos: LinkedList<PhotoBean> = LinkedList()
            if (mCard?.imgs != null) {
                mCard?.imgs?.split(",")?.forEach {
                    photos.add(PhotoBean(it))
                }
                mAdapter.setData(photos)
            }

        }
    }

    override fun initEvent() {
        mAdapter.setOnItemClickListener(this)
        arrayOf(v_publish).setViewListener(this)
    }

    override fun getLayoutID(): Any = R.layout.activity_public_card

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            mPicHelper.onActivityResult(requestCode, resultCode, data, {
                mAdapter.setData(LinkedList(it))
            })
        }
    }
}
