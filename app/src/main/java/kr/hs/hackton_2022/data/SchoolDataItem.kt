package kr.hs.hackton_2022.data

import java.io.Serializable

data class SchoolDataItem(
    val school_title: String,
    val school_name: String,
    val school_content: String
) : Serializable
