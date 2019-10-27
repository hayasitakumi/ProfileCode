package com.syabonbubble.profilecode.database.profile

import androidx.lifecycle.LiveData

class ProfileRepository(private val profileDao: ProfileDao) {

    val allProfiles: LiveData<List<Profile>> = profileDao.getAll()

    suspend fun insert(profile: Profile) {
        profileDao.insert(profile)
    }

    suspend fun delete(id: Int){
        profileDao.delete(id)
    }

    suspend fun update(id: Int, name: String, gh: String, tw: String){
        profileDao.update(id, name, gh, tw)
    }
}
