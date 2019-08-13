package jp.co.cyberagent.dojo2019.DataBase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.roomUrlssample.UrlRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UrlViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UrlRepository

    val allUrls: LiveData<List<Url>>

    init {
        val UrlsDao = UrlRoomDatabase.getDatabase(application, viewModelScope).urlDao()
        repository = UrlRepository(UrlsDao)
        allUrls = repository.allUrls
    }

    fun insert(url: Url) = viewModelScope.launch {
        repository.insert(url)
    }
}