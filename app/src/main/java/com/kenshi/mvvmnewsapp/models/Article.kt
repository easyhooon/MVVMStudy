package com.kenshi.mvvmnewsapp.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


//Article class is a table in our database
@Entity(
    tableName = "articles"
)

//@SerailizedName 해줘야하는 이유
data class Article(
    // not every article will have an id(only displayed articles exist)
    // only saved articles needed
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("source")
    val source: Source?, //primitive 한 type 이 아니기 때문에 type converter 필요
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?
) : Serializable
// nav_graph 를 이용하여 Serializable 로 묶어 전달