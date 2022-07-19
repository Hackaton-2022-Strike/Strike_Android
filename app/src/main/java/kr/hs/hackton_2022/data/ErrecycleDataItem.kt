package kr.hs.hackton_2022.data

import java.io.Serializable

data class ErrecycleDataItem(
    val Er_contents: String,
    val Er_id: Int,
    val Er_title: String,
    val Er_writer: String,
    val mb_id: String
) : Serializable