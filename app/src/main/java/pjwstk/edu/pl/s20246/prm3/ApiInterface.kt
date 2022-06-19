package pjwstk.edu.pl.s20246.prm3

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {
    //TODO zmienic url
    @GET("v1/api.json?rss_url=https%3A%2F%2Fmoxie.foxnews.com%2Ffeedburner%2Fscitech.xml")
    fun getArticles() : Call<FeedResponse>

    companion object {

        var BASE_URL = "https://api.rss2json.com/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}