package kr.hs.hackton_2022.data

import java.io.Serializable

data class infoRecycleDataItem(
    val info_category: String,
    val info_contents: String,
    val info_id: Int,
    val info_title: String,
    val info_writer: String,
    val mb_id: String
) : Serializable