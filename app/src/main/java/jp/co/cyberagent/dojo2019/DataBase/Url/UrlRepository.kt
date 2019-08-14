package com.example.android.roomUrlssample

import androidx.lifecycle.LiveData
import jp.co.cyberagent.dojo2019.DataBase.Url
import jp.co.cyberagent.dojo2019.DataBase.UrlDao

class UrlRepository(private val UrlDao: UrlDao) {

    val allUrls: LiveData<List<Url>> = UrlDao.getAll()

    suspend fun insert(url: Url) {
        UrlDao.insert(url)
    }

    suspend fun delete(urlId: Int){
        UrlDao.delete(urlId)
    }
}
