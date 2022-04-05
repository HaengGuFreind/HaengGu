package com.example.haenggu.data.remote.services

import com.example.haenggu.data.remote.datasources.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiRequest {

    @GET("oauth/login/kakao")
    fun getHToken(
        @Header ("Authorization") htoken:String
    ):Call<Login>

    @PATCH("users")
    fun updateUseript(
        @Header ("Authorization") htoken:String,
        @Body userinfo: UserInfo
    ):Call<RRUserInfo>

//
//    @FormUrlEncoded
//    @PATCH("users")
//    fun updateUseript(
//        @Header ("Authorization") htoken:String,
//        @Header("Content-Type") type:String,
//        @FieldMap userinfo: HashMap<String,Any>
//    ):Call<RRUserInfo>

//    @FormUrlEncoded
//    @PATCH("users")
//    fun updateUseript(
//        @Header ("Authorization") htoken:String,
//        @Header("Content-Type") type:String,
//        @Field ("birthday")birthday: String ,
//        @Field ("category_tag")categoryTag: List<String>,
//        @Field ("dept_id")deptId: String,
//        @Field ("email")email: String,
//        @Field ("gender")gender: String,
//        @Field ("grade")grade: Int,
//        @Field ("mbti")mbti: String,
//        @Field ("region_tag")regionTag: List<String>,
//        @Field ("username")username: String,
//    ):Call<RRUserInfo>

    @GET("users/majors")
    fun getSchool(
        @Query ("school-name")school_name: String
    ):Call<School>

    @GET("profile")
    suspend fun getProfile() : Response<Profile>

    @GET("events")
    fun getEventList(@QueryMap options:Map<String, String>): Call<ResponseEvent>

    @GET("events/{idx}")
    fun getEvenDetail(@Path("idx") idx:String): Call<ResponseEventDetail>

    @GET("borads")
    fun getBoards(@Header ("Authorization") jwt: String) : Call<ResponseBoard>

    @GET("borads/{idx}")
    fun getBoardDetail(@Header ("Authorization") jwt: String, @Path("idx") idx:String) : Call<ResponseBoardDetail>
}
