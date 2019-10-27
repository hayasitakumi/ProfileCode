package com.syabonbubble.profilecode.database.user

import androidx.lifecycle.LiveData
import com.syabonbubble.profilecode.database.user.User
import com.syabonbubble.profilecode.database.user.UserDao

class UserRepository(private val userDao: UserDao) {

    val allUsers: LiveData<List<User>> = userDao.getAll()

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun delete(){
        userDao.delete()
    }
}
