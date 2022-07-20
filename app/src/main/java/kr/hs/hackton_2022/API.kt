package kr.hs.hackton_2022

import kr.hs.hackton_2022.data.*
import retrofit2.Call
import retrofit2.http.*

interface API {
    @POST("/login")
    fun Hackathonlogin(@Body loginData: LoginData) : Call<UserEntity>

    @POST("/join")
    fun HackathonJoin(@Body joinData: JoinData) : Call<JoinData>

    @GET("/Erviewposts")
    fun getErposts() : Call<ErRecycleData>

    @GET("/infoviewposts")
    fun getinfoposts() : Call<infoRecycleData>

    @POST("/updatemy")
    fun updatemy(@Body userdata: UserEntity) : Call<UserEntity>

    @POST("/Erpost")
    fun Erpost(@Body postdata: PostData) : Call<PostData>

    @POST("/infopost")
    fun infopost(@Body postdata: PostData) : Call<PostData>

    @GET("/getmyEr")
    fun getmyEr(@Query("id")id : String) : Call<ErRecycleData>

    @GET("/getmyinfo")
    fun getmyinfo(@Query("id") id : String) : Call<infoRecycleData>
}