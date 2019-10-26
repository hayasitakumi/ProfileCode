package com.syabonbubble.profilecode.DataBase.Profile

import androidx.lifecycle.LiveData
import com.syabonbubble.profilecode.DataBase.User.User
import com.syabonbubble.profilecode.DataBase.User.UserDao

class UserRepository(private val userDao: UserDao) {

    val allUsers: LiveData<List<User>> = userDao.getAll()

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun delete(){
        userDao.delete()
    }
}
