package pjwstk.edu.pl.s20246.prm3

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ArticlesApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val applicationScope = CoroutineScope(SupervisorJob())


    val database by lazy { ArticleRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { ArticleRepository(database.articleDao()) }
}