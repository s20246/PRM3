package pjwstk.edu.pl.s20246.prm3

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "article_table")
data class Article(
    @PrimaryKey(autoGenerate = true)
    // TODO id serialized usunac
    @SerializedName("notid")
    val id: Int,
    val title: String,
    @SerializedName("thumbnail")
    val photoPath: String,
    @SerializedName("description")
    val note: String,
    val link: String,
    var seen: Boolean)
