package pjwstk.edu.pl.s20246.prm3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.get
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
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.submitList(it) }
        })

        findViewById<Button>(R.id.logoutButton).setOnClickListener {
            MainActivity().auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        findViewById<RecyclerView>(R.id.recyclerview).addOnItemTouchListener(
            RecyclerItemClick(
                this,
                recyclerView,
                object : RecyclerItemClick.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        // widzi klik, wie gdzie byl
                        //zmienic kolor tekstu
                    }
                })
        )

    }
}