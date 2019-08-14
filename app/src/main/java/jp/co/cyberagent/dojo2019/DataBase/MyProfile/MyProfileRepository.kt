package jp.co.cyberagent.dojo2019.DataBase.MyProfile

import androidx.lifecycle.LiveData

class MyProfileRepository(private val myprofileDao: MyProfileDao) {

    val allMyProfiles: LiveData<List<MyProfile>> = myprofileDao.getAll()

    suspend fun insert(mypfrofile: MyProfile) {
        myprofileDao.insert(mypfrofile)
    }
}
