package pjwstk.edu.pl.s20246.prm3

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_table")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val photoPath: String,
    val note: String,
    val link: String,
    var seen: Boolean)