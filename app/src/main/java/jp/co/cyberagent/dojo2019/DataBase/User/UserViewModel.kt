package jp.co.cyberagent.dojo2019.DataBase.User

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.roomUrlssample.Profile.ProfileRepository
import com.example.android.roomUrlssample.User.UserRepository
import kotlinx.coroutines.launch
//
//class UserViewModel(application: Application) : AndroidViewModel(application) {
//
//    private val repository: UserRepository
//
//    val allUsers: LiveData<List<User>>
//
//    init {
//        val UsersDao = UserRoomDatabase.getDatabase(application).userDao()
//        repository = UserRepository(UsersDao)
//        allUsers = repository.allUsers
//    }
//
//    fun insert(user: User) = viewModelScope.launch {
//        repository.insert(user)
//    }
//
//    fun delete() = viewModelScope.launch {
//        repository.delete()
//    }
//}