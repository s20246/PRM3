package pjwstk.edu.pl.s20246.prm3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DashboardActivity : AppCompatActivity() {

    private val articleViewModel: ArticleViewModel by viewModels {
        ArticleViewModelFactory((application as ArticlesApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ArticleListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        articleViewModel.allArticles.observe(this, Observer { words ->
            words?.let { adapter.submitList(it) }
        })

        findViewById<Button>(R.id.logoutButton).setOnClickListener {
            MainActivity().auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        findViewById<RecyclerView>(R.id.recyclerview).addOnItemTouchListener(RecyclerItemClick(this, recyclerView, object : RecyclerItemClick.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+position)
                println(adapter.currentList[position].link)
                adapter.currentList[position].seen=true
                println(adapter.currentList[position].seen)
                //findViewById<TextView>(R.id.itemTitle).setTextColor(Color.BLUE)
                //val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(adapter.currentList[position].link))
                //startActivity(browserIntent)
                //TODO przekazac link do WebActivity
                // zmiana koloru po przeczytaniu
                val intent = Intent(this@DashboardActivity, WebActivity::class.java)
                startActivity(intent)

            }
        }))

    }
}