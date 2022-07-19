package kr.hs.hackton_2022

import kr.hs.hackton_2022.data.JoinData
import kr.hs.hackton_2022.data.LoginData
import kr.hs.hackton_2022.data.ErRecycleData
import kr.hs.hackton_2022.data.infoRecycleData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface API {
    @POST("/login")
    fun Hackathonlogin(@Body loginData: LoginData) : Call<LoginData>

    @POST("/join")
    fun HackathonJoin(@Body joinData: JoinData) : Call<JoinData>

    @GET("/Erviewposts")
    fun getErposts() : Call<ErRecycleData>

    @GET("/infoviewposts")
    fun getinfoposts() : Call<infoRecycleData>
}