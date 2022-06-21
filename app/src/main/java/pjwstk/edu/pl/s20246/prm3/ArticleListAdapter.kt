package pjwstk.edu.pl.s20246.prm3

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

class ArticleListAdapter : ListAdapter<Article, ArticleListAdapter.ArticleViewHolder>(ArticlesComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        println(".....................................................................................")
        val current = getItem(position)
        holder.itemView.findViewById<TextView>(R.id.itemTitle).text=current.title
        holder.itemView.findViewById<TextView>(R.id.itemNote).text=current.note
        Picasso.get().load(current.photoPath).into( holder.itemView.findViewById<ImageView>(R.id.itemPicture));
        if(current.seen){
            holder.itemView.findViewById<TextView>(R.id.itemTitle).setTextColor(Color.BLUE)
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