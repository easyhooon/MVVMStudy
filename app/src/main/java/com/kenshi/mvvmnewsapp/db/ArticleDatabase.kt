package com.kenshi.mvvmnewsapp.db

import android.content.Context
import androidx.room.*
import com.kenshi.mvvmnewsapp.models.Article

@Database(
    entities = [Article::class],
    version = 1
)

//typeConverter DatabaseClass 에 명시 해주어야 함
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao

    companion object {
        //means other thread can immediately see when a thread changes this instance
        @Volatile
        private var instance: ArticleDatabase? = null
        //single instance of our database
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            // synchronized block - other thread can't access at the same time
            // other thread can't set while we already set it
            instance ?: createDatabase(context).also { instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()
    }
}