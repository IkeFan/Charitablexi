package com.mmy.frame.data.api

import com.mmy.frame.data.bean.*
import com.mmy.frame.utils.Config
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*


/**
 * @创建者     lucas
 * @创建时间   2017/12/25 0025 11:18
 * @描述          TODO
 */
interface ApiService {

    @GET(Config.VERSION)
    fun checkVersion(): Observable<VersionBean>

    //获取用户token
    @Headers("needUserId:false")
    @FormUrlEncoded
    @POST(Config.MSG_USER_TOKEN)
    fun getMsgUserToken(@HeaderMap headers: Map<String, String>,
                        @Field("userId") userID: String,
                        @Field("name") name: String,
                        @Field("portraitUri") portraitUri: String): Observable<UserTokenBean>

    //聊天室禁言


    //@GET(Config.SEARCH_WEATER)
//fun searchWeater(@Query("cityId") cityId:String):Call<ResponseBody>

    //注册
    @Headers("needUserId:false")
    @FormUrlEncoded
    @POST(Config.REGISTER)
    fun register(@Field("mobile") mobile: String, @Field("password") pwd: String, @Field("conpassword") pwd2: String, @Field("name") name: String): Observable<RegisterBean>

    //发送验证码
    @Headers("needUserId:false")
    @FormUrlEncoded
    @POST(Config.SEND_CODE)
    fun sendCode(@Field("mobile") mobile: String): Observable<IBean>

    //验证code
    @Headers("needUserId:false")
    @FormUrlEncoded
    @POST(Config.CHECK_CODE)
    fun checkCode(@Field("mobile") mobile: String, @Field("code") code: String): Observable<IBean>

    //登录
    @Headers("needUserId:false")
    @FormUrlEncoded
    @POST(Config.LOGIN)
    fun login(@Field("mobile") mobile: String, @Field("password") pwd: String): Observable<LoginBean>

    //获取token openid
    @Headers("needUserId:false")
    @GET(Config.GET_TOKEN_OPENID)
    fun getTokenOpenid(@Query("code") code: String): Observable<WXBean>

    //获取微信用户个人信息
    @Headers("needUserId:false")
    @GET(Config.WX_USER_INFO)
    fun getWXUserInfo(@Query("access_token") access_token: String,
                      @Query("openid") openid: String): Observable<WXUserInfoBean>

    //搜索资助和执行方
    @FormUrlEncoded
    @POST("Project/search")
    fun searchSupport(@Field("name") name: String): Observable<SearchSupportBean>

    //添加资助方到项目
    @FormUrlEncoded
    @POST("Project/upzhf")
    fun addSupportInPro(@Field("zid") zid: Int, @Field("content") content: String): Observable<IBean>

    //获取资助方列表
    @POST("Project/chosezhf")
    fun getSupportList(): Observable<SupportListBean>

    //设为主投资商
    @FormUrlEncoded
    @POST("Project/import")
    fun setMainSupport(@Field("importid") importid: Int): Observable<IBean>

    //发起项目
    @Multipart
    @POST("Project/add")
    fun addProject(@Part list: List<MultipartBody.Part>): Observable<IBean>

    //获取类型列表
    @GET("Project/typeList")
    fun getTypeList(): Observable<ChoiceTypeBean>

    //新增类型
    @FormUrlEncoded
    @POST("Project/typeAdd")
    fun addType(@Field("name") name: String): Observable<IBean>

    //获取广告列表
    @GET("Project/advsList")
    fun getAdvList(): Observable<AdvListBean>

    //新增广告
    @Multipart
    @POST("Project/advsAdd")
    fun addAdv(@Part list: List<MultipartBody.Part>): Observable<IBean>

    //协议列表
    @GET("Project/xieyiList")
    fun getAgreList(): Observable<AgreListBean>

    //新增协议
    @FormUrlEncoded
    @POST("Project/xieyiAdd")
    fun addAgre(@Field("title") title: String, @Field("content") content: String): Observable<IBean>

    //获取项目列表
    @FormUrlEncoded
    @POST("Project/index")
    fun getProList(@Field("status") status: Int): Observable<ProListBean>

    //送爱心
    @FormUrlEncoded
    @POST("Project/donateLove")
    fun sendLove(@Field("xmid") xmid: Int, @Field("value") value: Int): Observable<IBean>

    //项目详情
    @FormUrlEncoded
    @POST("Project/detail")
    fun getProInfo(@Field("xmid") xmid: Int): Observable<ProInfoBean>

    //收藏项目
    @FormUrlEncoded
    @POST("Project/collects")
    fun collectPro(@Field("xmid") xmid: Int, @Field("status") status: Int): Observable<IBean>

    //评论
    @FormUrlEncoded
    @POST("Project/discuss")
    fun comment(@Field("xmid") xmid: Int, @Field("content") content: String, @Field("comid") comid: Int, @Field("cid") cid: Int = 0): Observable<IBean>

    //申请义工
    @FormUrlEncoded
    @POST("Volunteer/add")
    fun requestVolunteer(
            @Field("email") email: String?,
            @Field("sex") sex: Int,
            @Field("age") age: Int,
            @Field("oid") oid: Int? = null,
            @Field("xmid") xmid: Int? = null,
            @Field("rid") rid: Int? = null,
            @Field("code") code: String? = null): Observable<IBean>

    //我的
    @FormUrlEncoded
    @POST("User/index")
    fun getPersonalInfo(@Field("tem") tem: String = ""): Observable<PersonalInfoBean>

    //收藏项目
    @FormUrlEncoded
    @POST("User/collectList")
    fun getCollection(@Field("id") id: Int): Observable<CommonProjectBean>

    //已捐项目
    @FormUrlEncoded
    @POST("User/donatedList")
    fun getDonated(@Field("id") id: Int): Observable<CommonProjectBean>

    //收藏项目
    @FormUrlEncoded
    @POST("User/VoList")
    fun getVoList(@Field("id") id: Int): Observable<CommonProjectBean>

    //关注、关注着、参与
    @FormUrlEncoded
    @POST("User/lists")
    fun getMyFollow(@Field("id") id: Int? = null, @Field("bid") bid: Int? = null, @Field("oid") oid: Int? = null): Observable<FollowListBean>

    //修改个人信息
    @Multipart
    @POST("User/savePersonal")
    fun modifyProsnalInfo(@Part list: List<MultipartBody.Part>): Observable<IBean>

    //添加题目
    @Multipart
    @POST("User/addQue")
    fun addQuestion(@Part list: List<MultipartBody.Part>): Observable<IBean>

    //关于
    @GET("User/about")
    fun getAbout(): Observable<AboutBean>

    //修改获取团队概况
    @FormUrlEncoded
    @POST("User/teamInfo")
    fun getTeamList(@Field("id") id:Int): Observable<TeamInfoBean>

    //新增、修改团队概括
    @Multipart
    @POST("User/editTeam")
    fun editTeam(@Part list: List<MultipartBody.Part>):Observable<IBean>

    //删除团队成员
    @FormUrlEncoded
    @POST("User/delTeam")
    fun delTeam(@Field("id") id:Int):Observable<IBean>

    //修改关于我们
    @Multipart
    @POST("User/editAbout")
    fun editAbout(@Part list: List<MultipartBody.Part>):Observable<IBean>

}