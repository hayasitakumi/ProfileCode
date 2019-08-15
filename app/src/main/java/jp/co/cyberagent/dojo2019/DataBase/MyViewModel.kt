package jp.co.cyberagent.dojo2019.DataBase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.roomUrlssample.Profile.ProfileRepository
import com.example.android.roomUrlssample.User.UserRepository
import jp.co.cyberagent.dojo2019.DataBase.Profile.Profile
import jp.co.cyberagent.dojo2019.DataBase.Profile.ProfileRoomDatabase
import jp.co.cyberagent.dojo2019.DataBase.User.User
import jp.co.cyberagent.dojo2019.DataBase.User.UserRoomDatabase
import kotlinx.coroutines.launch


class MyViewModel(application: Application) : AndroidViewModel(application) {

    private val userrepository: UserRepository
    val allUsers: LiveData<List<User>>

    private val profilerepository: ProfileRepository
    val allProfiles: LiveData<List<Profile>>


    init {
        val UsersDao = UserRoomDatabase.getDatabase(application).userDao()
        userrepository = UserRepository(UsersDao)
        allUsers = userrepository.allUsers

        val ProfilesDao = ProfileRoomDatabase.getDatabase(application).profileDao()
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

}