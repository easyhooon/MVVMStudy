package com.kenshi.mvvmnewsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kenshi.mvvmnewsapp.models.Article

//DAO: Data Access Object

//Dao annotation needed
@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    //whenever article inside of that list changes then this live data will notify all of its observer
    //that subscribed tp changes of that live data

    //recyclerview immediately gets the most up-to-date data from the live data object
    //automatically notifies if there is change in our database(add or delete)
    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)

}