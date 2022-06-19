package pjwstk.edu.pl.s20246.prm3

import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Services {

   // var articleList = mutableListOf<Article>()

     fun readJson(articleDao: ArticleDao){
        val apiInterface = ApiInterface.create().getArticles()

        apiInterface.enqueue( object : Callback<FeedResponse> {
            //TODO po wyjsciu z tej fun lista jest znowu pusta
            override fun onResponse(call: Call<FeedResponse>?, response: Response<FeedResponse>?) {
                println("|||||||||||||||||||||||||||")
                if (response?.body() != null) {
                    var feedResponse = response?.body()
                    var articles = feedResponse?.items
                    runBlocking {
                        articleDao.deleteAll()

                        if (articles != null) {
                            var count = 0
                            for (i in articles) {
                                articleDao.insert(
                                    Article(
                                        count,
                                        i.title,
                                        i.photoPath,
                                        i.note,
                                        i.link,
                                        false
                                    )
                                )
                            }
                        }
                    }
                }
            }
            override fun onFailure(call: Call<FeedResponse>?, t: Throwable?) {
                if (t != null) {
                    println("#############failed: "+t.localizedMessage)
                }
            }
        })
    }
}