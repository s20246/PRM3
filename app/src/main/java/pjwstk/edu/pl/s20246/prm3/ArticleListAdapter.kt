package pjwstk.edu.pl.s20246.prm3

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ArticleListAdapter : ListAdapter<Article, ArticleListAdapter.ArticleViewHolder>(ArticlesComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val current = getItem(position)
        /*holder.bind(current.id.toString())
        holder.bind(current.title)
        holder.bind(current.photoPath)
        holder.bind(current.note)*/
        // wyswietlanie elementow !!!!
        //dodac zdj
        holder.itemView.findViewById<TextView>(R.id.itemTitle).text=current.title
        holder.itemView.findViewById<TextView>(R.id.itemNote).text=current.note



    }


    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
/*        private val articleItemView: TextView = itemView.findViewById(R.id.itemTitle)
        private val articleItemView2: TextView = itemView.findViewById(R.id.itemNote)*/

        /*fun bind(text: String?) {
            articleItemView.text = text
        }*/

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