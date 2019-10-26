package com.syabonbubble.profilecode.DataBase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.syabonbubble.profilecode.DataBase.Profile.ProfileRepository
import com.syabonbubble.profilecode.DataBase.Profile.Profile
import com.syabonbubble.profilecode.DataBase.Profile.ProfileDatabase
import com.syabonbubble.profilecode.DataBase.Profile.UserRepository
import com.syabonbubble.profilecode.DataBase.User.User
import com.syabonbubble.profilecode.DataBase.User.UserDatabase
import kotlinx.coroutines.launch


class MyViewModel(application: Application) : AndroidViewModel(application) {

    private val userrepository: UserRepository
    val allUsers: LiveData<List<User>>

    private val profilerepository: ProfileRepository
    val allProfiles: LiveData<List<Profile>>


    init {
        val UsersDao = UserDatabase.getDatabase(application).userDao()
        userrepository = UserRepository(UsersDao)
        allUsers = userrepository.allUsers

        val ProfilesDao = ProfileDatabase.getDatabase(application).profileDao()
        profilerepository = ProfileRepository(ProfilesDao)
        allProfiles = profilerepository.allProfiles
    }

    //UserViewModel
    fun insert(user: User) = viewModelScope.launch {
        userrepository.insert(user)
    }

    fun delete() = viewModelScope.launch {
        userrepository.delete()
    }

    //ProfileViewModel
    fun insert(profile: Profile) = viewModelScope.launch {
        profilerepository.insert(profile)
    }

    fun delete(id: Int) = viewModelScope.launch {
        profilerepository.delete(id)
    }

//    fun update(id: MutableSet<Int>) = viewModelScope.launch {
//        profilerepository.update(uids)
//    }

    fun update(id: Int, name: String, gh: String, tw: String) = viewModelScope.launch{
        profilerepository.update(id, name, gh, tw)
    }

}