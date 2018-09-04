package com.mmy.charitablexi.model.personal.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.ui.adapter.PhotoUploadAdapter
import com.mmy.frame.AppComponent
import com.mmy.frame.base.adapter.BaseRecyclerViewAdapter
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.popup.PictureSelectPopup
import com.mmy.frame.utils.CommonUtil
import com.yanzhenjie.album.Album
import kotlinx.android.synthetic.main.activity_photo.*
import java.io.File
import java.io.Serializable
import java.util.*

/**
 * @file       PhotoUploadActivity.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/19 0019
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class PhotoUploadActivity :BaseActivity<IPresenter<*>>(), BaseRecyclerViewAdapter.OnItemClickListener, View.OnClickListener, BaseRecyclerViewAdapter.OnItemLongClickListener{
    override fun requestSuccess(any: Any) {
    }

    private var mRecyclerView: RecyclerView? = null
    private var mDeleted: FloatingActionButton? = null
    private var mCancel: FloatingActionButton? = null
    /* 请求识别码 */
    private val CODE_GALLERY_REQUEST = 0xa0
    private val CODE_CAMERA_REQUEST = 0xa1
    /* 头像文件 */
    private var IMAGE_FILE_NAME = "temp_head_image.jpg"
    private var mPictureSelectPopup: PictureSelectPopup? = null
    private var mAdapter: PhotoUploadAdapter? = null
    private var mData: LinkedList<PhotoUploadAdapter.PhotoBean>? = null

    private var mMaxPic = 10//最大上传数量，intent使用
    private var mMinPic = 1//最小上传数量，intent使用
    val PIC_RESULT_CODE = 0xf000

    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun initView() {
        mRecyclerView = list
        mDeleted = delete
        mCancel = cancel

        val attr = intent.getSerializableExtra("attr") as PhotoAttrs
        setToolbar(attr.title,rightRes = "完成")
        mMaxPic = attr.maxPhoto
        mMinPic = attr.minPhoto
        val layoutManager = GridLayoutManager(this, 2)
        mRecyclerView?.setLayoutManager(layoutManager)
        mAdapter = PhotoUploadAdapter(this)
        mRecyclerView?.setAdapter(mAdapter)
        mRecyclerView?.setItemAnimator(DefaultItemAnimator())
        mPictureSelectPopup = PictureSelectPopup(this, this)
    }

    override fun initData() {
        mData = LinkedList()
        mAdapter?.setData(mData)
    }

    override fun initEvent() {
        mAdapter?.setOnItemClickListener(this)
        mAdapter?.setOnItemLongClickListener(this)
        mDeleted?.setOnClickListener(this)
        mCancel?.setOnClickListener(this)
    }

    override fun getLayoutID(): Any =R.layout.activity_photo

    override fun onItemClick(view: View, position: Int) {
        //判断上传数量是否上限
        if (mData?.size!! >= mMaxPic) {
            Toast.makeText(this, "最多只能选择" + mMaxPic + "张图片", Toast.LENGTH_SHORT).show()
            return
        }
        //只响应无图的item
        if (position == mData?.size) {
            mPictureSelectPopup?.showAsDropDown(view)
        }
        checkItem(position)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.bt_select_photo // 从本地相册选取图片作为头像
            -> {
                // 1. 使用默认风格，并指定选择数量：
                // 第一个参数Activity/Fragment； 第二个request_code； 第三个允许选择照片的数量，不填可以无限选择。
                // Album.startAlbum(this, ACTIVITY_REQUEST_SELECT_PHOTO, 9);

                // 2. 使用默认风格，不指定选择数量：
                // Album.startAlbum(this, ACTIVITY_REQUEST_SELECT_PHOTO); // 第三个参数不填的话，可以选择无数个。

                // 3. 指定风格，并指定选择数量，如果不想限制数量传入Integer.MAX_VALUE;
                Album.startAlbum(this, CODE_GALLERY_REQUEST, mMaxPic                                                         // 指定选择数量。
                        , ContextCompat.getColor(this, R.color.colorPrimary)        // 指定Toolbar的颜色。
                        , ContextCompat.getColor(this, R.color.colorPrimaryDark))  // 指定状态栏的颜色。
                IMAGE_FILE_NAME = "pic_" + System.currentTimeMillis() + ".jpg"
            }
            R.id.bt_select_takephoto // 启动手机相机拍摄照片作为头像
            -> {
                IMAGE_FILE_NAME = "pic_" + System.currentTimeMillis() + ".jpg"
                choseHeadImageFromCameraCapture()
            }
            R.id.delete -> {
                mDeleted?.setVisibility(View.GONE)
                mCancel?.setVisibility(View.GONE)
                mAdapter?.isShowDeleted(false)
                val list = mAdapter?.delete()
                Log.d("PhotoUploadActivity", "list.size():" + list?.size)
                //同步数据
                mData = list as LinkedList<PhotoUploadAdapter.PhotoBean>
            }
            R.id.cancel -> {
                mDeleted?.setVisibility(View.GONE)
                mCancel?.setVisibility(View.GONE)
                mAdapter?.isShowDeleted(false)
            }
            R.id.toolbar_right -> resultData()
        }//                choseHeadImageFromGallery();
        if (mPictureSelectPopup?.isShowing()!!)
            mPictureSelectPopup?.dismiss()
    }

    private fun resultData() {
        //判断下限
        if (mData?.size!! < mMinPic) {
            Toast.makeText(this, "请最少选择" + mMinPic + "张图片", Toast.LENGTH_SHORT).show()
            return
        }
        val intent = Intent()
        intent.putExtra("mData", mData)
        setResult(PIC_RESULT_CODE, intent)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent) {
        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            return
        }
        var data: Uri? = null
        when (requestCode) {
            CODE_GALLERY_REQUEST -> {
                // 拿到用户选择的图片路径List：
                val pathList = Album.parseResult(intent)
                for (path in pathList) {
                    mData?.add(PhotoUploadAdapter.PhotoBean(path))
                }
                mAdapter?.setData(mData)
            }
            CODE_CAMERA_REQUEST -> {
                if (hasSdcard()) {
                    val tempFile = File(Environment.getExternalStorageDirectory(),
                            IMAGE_FILE_NAME)
                    data = Uri.fromFile(tempFile)
                } else {
                    Toast.makeText(applicationContext, "没有SDCard!", Toast.LENGTH_LONG).show()
                }
                val path = CommonUtil.getPathByUri4kitkat(this, data)
                mData?.add(PhotoUploadAdapter.PhotoBean(path))
                mAdapter?.setData(mData)
            }
        }//                mData = intent.getMData();
        super.onActivityResult(requestCode, resultCode, intent)
    }

    // 从本地相册选取图片作为头像
    private fun choseHeadImageFromGallery() {
        val intentFromGallery = Intent()
        // 设置文件类型
        intentFromGallery.type = "image/*"
        intentFromGallery.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intentFromGallery, CODE_GALLERY_REQUEST)
    }

    // 启动手机相机拍摄照片作为头像
    private fun choseHeadImageFromCameraCapture() {
        val intentFromCapture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // 判断存储卡是否可用，存储照片文件
        if (hasSdcard()) {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                    .fromFile(File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)))
        }
        startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST)
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    fun hasSdcard(): Boolean {
        val state = Environment.getExternalStorageState()
        return state == Environment.MEDIA_MOUNTED
    }

    override fun onItemLongClick(view: View, position: Int) {
        mAdapter?.isShowDeleted(true)
        if (position == mData?.size)
            return
        mDeleted?.setVisibility(View.VISIBLE)
        mCancel?.setVisibility(View.VISIBLE)
        checkItem(position)
    }

    private fun checkItem(position: Int) {
        if (position == mData?.size)
            return
        val bean = mData?.get(position)
        bean?.isDelete = !bean?.isDelete!!
        mData?.removeAt(position)
        mData?.add(position, bean)
        mAdapter?.setData(mData)
    }


    class PhotoAttrs : Serializable {
        internal var title = "图片上传"//标题
        internal var maxPhoto = 10//最大上传数量
        internal var minPhoto = 1//最小上传数量
        internal var hint: String? = null//提示信息
    }
}