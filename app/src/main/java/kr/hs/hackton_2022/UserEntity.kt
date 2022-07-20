package kr.hs.hackton_2022

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserInfo")
class UserEntity(
    @PrimaryKey var mb_id: String,
    @ColumnInfo(name = "mb_pw") var mb_pw: String,
    @ColumnInfo(name = "mb_name") var mb_name: String
){
    constructor(): this("default", "", "")
}

