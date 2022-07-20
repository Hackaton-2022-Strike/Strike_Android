package kr.hs.hackton_2022

import kr.hs.hackton_2022.data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

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
}