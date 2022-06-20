package pjwstk.edu.pl.s20246.prm3

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ArticlesApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { ArticleRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { ArticleRepository(database.articleDao()) }
}