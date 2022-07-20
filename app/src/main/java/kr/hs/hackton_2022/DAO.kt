package kr.hs.hackton_2022

import androidx.room.*

@Dao
interface DAO {
    @Query("SELECT * FROM UserInfo")
    fun getAll(): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(UserData: UserEntity)

    @Query("DELETE FROM UserInfo")
    fun delete()

    @Query("UPDATE UserInfo SET mb_name = :name WHERE mb_name = :oldname")
    fun update(name: String, oldname: String)
}