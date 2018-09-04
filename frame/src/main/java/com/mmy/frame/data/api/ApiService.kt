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
    @Multipart
    @POST(Config.REGISTER)
    fun register(@Part list: List<MultipartBody.Part>): Observable<RegisterBean>

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
    fun addAgre(@Field("uid") uid: Int, @Field("title") title: String, @Field("content") content: String): Observable<IBean>

    //编辑协议
    @FormUrlEncoded
    @POST("Project/xieyiAdd")
    fun addAgre(@Field("uid") uid: Int, @Field("id") id: Int, @Field("title") title: String, @Field("content") content: String): Observable<IBean>

    //删除协议
    @FormUrlEncoded
    @POST("Project/delXiey")
    fun delAgre(@Field("uid") uid: Int, @Field("id") id: Int): Observable<IBean>

    //获取项目列表
    @FormUrlEncoded
    @POST("Project/index")
    fun getProList(@Field("status") status: Int): Observable<ProListBean>

    //获取user项目列表
    @FormUrlEncoded
    @POST("Project/index")
    fun getProList(@Field("uid") uid: Int, @Field("status") status: Int): Observable<ProListBean>

    //送爱心
    @FormUrlEncoded
    @POST("Project/donateLove")
    fun sendLove(@Field("uid")uid:Int, @Field("xmid") xmid: Int, @Field("value") value: Int): Observable<IBean>

    //项目详情
    @FormUrlEncoded
    @POST("Project/detail")
    fun getProInfo(@Field("xmid") xmid: Int): Observable<ProInfoBean>

    //资助方详情
    @FormUrlEncoded
    @POST("Project/zhfDetail")
    fun getSupportDetail(@Field("uid") uid: Int, @Field("zid") zid: Int): Observable<SponsorDetailBean>

    //收藏项目
    @FormUrlEncoded
    @POST("Project/collects")
    fun collectPro(@Field("uid")uid:Int, @Field("xmid") xmid: Int, @Field("status") status: Int): Observable<IBean>

    //执行进度列表
    @FormUrlEncoded
    @POST("Project/processList")
    fun getProgressList(@Field("uid") uid: Int, @Field("xmid") xmid: Int): Observable<ProjectProgressBean>

    //增加执行进度
    @Multipart
    @POST("Project/addProcess")
    fun addProcess(@Part list: List<MultipartBody.Part>): Observable<IBean>

    //编辑执行进度
    @Multipart
    @POST("Project/editProcess")
    fun editProcess(@Part list: List<MultipartBody.Part>): Observable<IBean>

    //删除执行过程
    @FormUrlEncoded
    @POST("Project/delProcess")
    fun delProcess(@Field("uid")uid:Int, @Field("id")id:Int): Observable<IBean>
    //评论
    @FormUrlEncoded
    @POST("Project/discuss")
    fun comment(@Field("uid") uid: Int,
                @Field("xmid") xmid: Int? = null,
                @Field("cid") cid: Int? = null,
                @Field("classid")classId:Int? = null,
                @Field("content") content: String,
                @Field("comid") comid: Int?= null): Observable<IBean>

    //申请义工
    @FormUrlEncoded
    @POST("Volunteer/add")
    fun requestVolunteer(
            @Field("uid") uid:Int,
            @Field("email") email: String?,
            @Field("sex") sex: Int,
            @Field("age") age: Int,
            @Field("oid") oid: Int? = null,
            @Field("xmid") xmid: Int? = null,
            @Field("rid") rid: Int? = null,
            @Field("code") code: String? = null,
            @Field("is_xy") is_xy:Int=1): Observable<IBean>

    //机构列表
    @FormUrlEncoded
    @POST("Volunteer/orgList")
    fun getOrglist(@Field("uid") uid: Int): Observable<OrganizationBean>

    //机构详情
    @FormUrlEncoded
    @POST("organize/detail")
    fun orgDetail(@Field("uid") uid:Int, @Field("oid") oid:Int): Observable<OrgDetailBean>

    //删除机构
    @FormUrlEncoded
    @POST("Volunteer/delOrg")
    fun delOrg(@Field("uid")uid:Int, @Field("id")id:Int):Observable<IBean>

    //关注/取消关注
    @FormUrlEncoded
    @POST("Volunteer/orgAttend")
    fun orgAttention(@Field("uid")uid: Int, @Field("oid") oid:Int,
                     @Field("bid")bid:Int, @Field("status")status:Int):Observable<IBean>
    //义工列表
    @FormUrlEncoded
    @POST("Volunteer/VorList")
    fun getVorList(@Field("uid") uid: Int, @Field("id") xmid:Int?=null,
                   @Field("oid") oid:Int?=null, @Field("name") name:String?=null): Observable<VolunteersBean>

    //删除义工
    @FormUrlEncoded
    @POST("Volunteer/delVor")
    fun delVorlunteer(@Field("uid") uid: Int, @Field("id")id:Int): Observable<IBean>

    //发布招聘
    @Multipart
    @POST("Volunteer/addZp")
    fun addZp(@Part list: List<MultipartBody.Part>):Observable<IBean>

    //删除招聘
    @FormUrlEncoded
    @POST("Volunteer/delZp")
    fun delZp(@Field("uid")uid:Int, @Field("id") id:Int):Observable<IBean>

    //招聘详情
    @FormUrlEncoded
    @POST("Volunteer/zpDetail")
    fun zpDetail(@Field("zpid") zpid:Int):Observable<RecruitDetailBean>
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

    //审核义工列表
    @FormUrlEncoded
    @POST("volunteer/auditList")
    fun auditList(@Field("uid") uid: Int, @Field("page")page:Int):Observable<IBean>

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
    fun getTeamList(@Field("id") id: Int): Observable<TeamInfoBean>

    //新增、修改团队概括
    @Multipart
    @POST("User/editTeam")
    fun editTeam(@Part list: List<MultipartBody.Part>): Observable<IBean>

    //删除团队成员
    @FormUrlEncoded
    @POST("User/delTeam")
    fun delTeam(@Field("id") id: Int): Observable<IBean>

    //修改关于我们
    @Multipart
    @POST("User/editAbout")
    fun editAbout(@Part list: List<MultipartBody.Part>): Observable<IBean>

    //公开课列表
    @FormUrlEncoded
    @POST("Circle/classList")
    fun getClassList(@Field("type") type: Int): Observable<ClassBean>

    //添加/修改公开课
    @Multipart
    @POST("Circle/addClass")
    fun addOrEdClass(@Part list: List<MultipartBody.Part>): Observable<IBean>
    //删除公开课
    @FormUrlEncoded
    @POST("Circle/delClass")
    fun delClass(@Field("uid") uid:Int, @Field("id") classId:Int): Observable<IBean>

    //公开课详情
    @FormUrlEncoded
    @POST("Circle/ClassInfo")
    fun getClassDetail(@Field("id")id:Int):Observable<IBean>

    //个人中心
    @FormUrlEncoded
    @POST("Circle/cardsPersonal")
    fun getCardsPersonal(@Field("uid") uid: Int): Observable<PersonalCenterBean>

    //帖子列表"互动"
    @FormUrlEncoded
    @POST("Circle/cardsList")
    fun getCardList(@Field("type") type: Int): Observable<InteractBean>

    //修改/发布贴子
    @Multipart
    @POST("Circle/addCards")
    fun addOrEditCard(@Part list: List<MultipartBody.Part>): Observable<IBean>

    //删除贴子
    @FormUrlEncoded
    @POST("Circle/delCards")
    fun delCard(@Field("uid")uid:Int, @Field("cid") cid:Int): Observable<IBean>

    //贴子详情
    @FormUrlEncoded
    @POST("Circle/cardsDetail")
    fun getCardDetail(@Field("cid") cid: Int): Observable<CardetailBean>

    //参加义工项目列表
    @FormUrlEncoded
    @POST("Api/User/VoList")
    fun getMyVoList(@Field("uid") uid: Int): Observable<CommonProjectBean>

    //参加的义工组织
    @FormUrlEncoded
    @POST("Api/User/joinOrg")
    fun getJoinVolOrg(@Field("uid") uid: Int): Observable<VolunteerOrgnBean>

    //参加的义工招聘
    @FormUrlEncoded
    @POST("User/joinZp")
    fun getJoinZp(@Field("uid") uid: Int): Observable<VolunteerZpBean>
}