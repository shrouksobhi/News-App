package com.shrouk.newsapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "news_Table")
data class Articles(

   @ColumnInfo(name = "author")
    @SerializedName("author")
    var author:String,

   @ColumnInfo(name = "title")
   @SerializedName("title")
    var title:String,

   @ColumnInfo(name = "description")
   @SerializedName("description")
    var description:String,

    @PrimaryKey
   @ColumnInfo(name = "url")
   @SerializedName("url")
    var url:String,

   @ColumnInfo(name = "urlToImage")
   @SerializedName("urlToImage")
    var urlToImage:String,

   @ColumnInfo(name = "publishedAt")
   @SerializedName("publishedAt")
    var publishedAt:String,

   @ColumnInfo(name = "content")
   @SerializedName("content")
    var content:String,
):Parcelable
