package pjwstk.edu.pl.s20246.prm3

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.runBlocking

class ArticleListAdapter :
    ListAdapter<Article, ArticleListAdapter.ArticleViewHolder>(ArticlesComparator()) {

    var currId = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {

        val current = getItem(position)
        println("........................."+position+"..."+current.title+" seen: "+current.seen+" link "+current.link)
        holder.itemView.findViewById<TextView>(R.id.itemTitle).text = current.title
        holder.itemView.findViewById<TextView>(R.id.itemNote).text = current.note
        Picasso.get().load(current.photoPath)
            .into(holder.itemView.findViewById<ImageView>(R.id.itemPicture));

        holder.itemView.setOnClickListener {
            println(".......clicked.................."+position+"..."+current.title+" seen: "+current.seen+" url: "+current.link)
            current.seen = true
            //TODO update database
            runBlocking {
                ArticlesApplication().repository.update(current)
            }
            val intent = Intent(it.context, WebActivity::class.java)
            intent.putExtra("link", current.link)
            it.context.startActivity(intent)
        }

        if (current.seen) {
            holder.itemView.findViewById<TextView>(R.id.itemTitle).setTextColor(Color.RED)
        } else {
            holder.itemView.findViewById<TextView>(R.id.itemTitle).setTextColor(Color.BLACK)
        }
    }


    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        companion object {
            fun create(parent: ViewGroup): ArticleViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return ArticleViewHolder(view)
            }
        }
    }

    class ArticlesComparator : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }
    }
}