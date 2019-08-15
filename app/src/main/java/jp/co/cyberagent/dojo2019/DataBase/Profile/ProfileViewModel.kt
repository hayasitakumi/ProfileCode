//package jp.co.cyberagent.dojo2019.DataBase.Profile
//
//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.viewModelScope
//import com.example.android.roomUrlssample.Profile.ProfileRepository
//import kotlinx.coroutines.launch
//
//class ProfileViewModel(application: Application) : AndroidViewModel(application) {
//
//    private val repository: ProfileRepository
//
//    val allProfiles: LiveData<List<Profile>>
//
//    init {
//        val ProfilesDao = ProfileRoomDatabase.getDatabase(application).profileDao()
//        repository = ProfileRepository(ProfilesDao)
//        allProfiles = repository.allProfiles
//    }
//
//    fun insert(profile: Profile) = viewModelScope.launch {
//        repository.insert(profile)
//    }
//
//    fun delete(id: Int) = viewModelScope.launch {
//        repository.delete(id)
//    }
//
//    fun update(id: Int, name: String, gh: String, tw: String) = viewModelScope.launch {
//        repository.update(id, name, gh, tw)
//    }
//}