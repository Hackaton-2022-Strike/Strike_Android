package kr.hs.hackton_2022

import android.graphics.Paint
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface API {
    @POST("/login")
    fun Hackathonlogin(@Body loginData: LoginData) : Call<LoginData>

    @POST("/join")
    fun HackathonJoin(@Body joinData: JoinData) : Call<JoinData>
}