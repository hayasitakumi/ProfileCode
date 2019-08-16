package com.example.android.roomUrlssample.Profile

import android.util.Log
import androidx.lifecycle.LiveData
import jp.co.cyberagent.dojo2019.DataBase.Profile.Profile
import jp.co.cyberagent.dojo2019.DataBase.Profile.ProfileDao

class ProfileRepository(private val profileDao: ProfileDao) {

    val allProfiles: LiveData<List<Profile>> = profileDao.getAll()

    suspend fun insert(profile: Profile) {
        profileDao.insert(profile)
    }

    suspend fun delete(id: Int){
        profileDao.delete(id)
    }

    suspend fun update_position(uid: MutableSet<Int>){
        for (i in 1..2) {
            profileDao.update_position(1, 2)
        }
    }
//    suspend fun update_position()
//    suspend fun update(id: Int, name: String, gh: String,tw: String){
//        profileDao.update(id, name, gh, tw)
//    }
}
