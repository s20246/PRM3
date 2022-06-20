package pjwstk.edu.pl.s20246.prm3

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow


class ArticleRepository(private val articleDao: ArticleDao) {

    val allArticles: Flow<List<Article>> = articleDao.getArticles()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(article: Article) {
        articleDao.insert(article)
    }
}