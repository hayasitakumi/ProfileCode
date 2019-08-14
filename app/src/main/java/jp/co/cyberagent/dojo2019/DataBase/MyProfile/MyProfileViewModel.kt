package jp.co.cyberagent.dojo2019.DataBase.MyProfile

import jp.co.cyberagent.dojo2019.DataBase.Word.Word
import jp.co.cyberagent.dojo2019.DataBase.Word.WordRepository
import jp.co.cyberagent.dojo2019.DataBase.Word.WordRoomDatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MyProfileRepository
    val allMyProfiles: LiveData<List<MyProfile>>

    init {
        val myprofilesDao = MyProfileRoomDatabase.getDatabase(
            application
        ).myprofileDao()
        repository = MyProfileRepository(myprofilesDao)
        allMyProfiles = repository.allMyProfiles
    }

    fun insert(myprofile: MyProfile) = viewModelScope.launch {
        repository.insert(myprofile)
    }
}
