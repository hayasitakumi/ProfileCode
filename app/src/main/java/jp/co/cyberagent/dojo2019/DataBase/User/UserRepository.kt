package com.example.android.roomUrlssample.User

import androidx.lifecycle.LiveData
import jp.co.cyberagent.dojo2019.DataBase.Profile.Profile
import jp.co.cyberagent.dojo2019.DataBase.Profile.ProfileDao
import jp.co.cyberagent.dojo2019.DataBase.User.User
import jp.co.cyberagent.dojo2019.DataBase.User.UserDao

class UserRepository(private val userDao: UserDao) {

    val allUsers: LiveData<List<User>> = userDao.getAll()

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun delete(){
        userDao.delete()
    }
}
