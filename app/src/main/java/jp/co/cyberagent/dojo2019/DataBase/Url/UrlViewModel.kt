package jp.co.cyberagent.dojo2019.DataBase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.roomUrlssample.UrlRepository
import kotlinx.coroutines.launch

class UrlViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UrlRepository

    val allUrls: LiveData<List<Url>>

    init {
        val UrlsDao = UrlRoomDatabase.getDatabase(application).urlDao()
        repository = UrlRepository(UrlsDao)
        allUrls = repository.allUrls
    }

    fun insert(url: Url) = viewModelScope.launch {
        repository.insert(url)
    }

    fun delete(urlId: Int) = viewModelScope.launch {
        repository.delete(urlId)
    }
}