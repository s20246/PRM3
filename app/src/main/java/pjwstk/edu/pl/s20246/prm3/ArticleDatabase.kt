package pjwstk.edu.pl.s20246.prm3

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Article class
@Database(entities = arrayOf(Article::class), version = 1, exportSchema = false)
public abstract class ArticleRoomDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ArticleRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ArticleRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleRoomDatabase::class.java,
                    "artic_database"
                ).addCallback(ArticleDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    private class ArticleDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.articleDao())
                }
            }
        }

        suspend fun populateDatabase(articleDao: ArticleDao) {
            // Delete all content here.
            articleDao.deleteAll()

            // Add sample.
            var article = Article(1, "title1", "path", "note", "link",false)
            articleDao.insert(article)
            article = Article(2,"title2", "path2", "note2", "link2",false)
            articleDao.insert(article)

            //
        }
    }
}