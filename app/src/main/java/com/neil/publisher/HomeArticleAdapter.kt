package com.neil.publisher

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.neil.publisher.data.Article
import com.neil.publisher.databinding.ItemHomeArticleBinding
import java.sql.Date

class HomeArticleAdapter(): ListAdapter<Article, HomeArticleAdapter.ViewHolder>(DiffCallBack()) {

    inner class ViewHolder(private val binding: ItemHomeArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article) {

            // -----------------------------------bind method-----------------------------------
            binding.articleTitle.text = item.title
            binding.authorName.text = item.author?.name ?: null
            binding.articleCreatedTime.text = item.createdTime?.let { Date(it).toString() }
            binding.category.text = item.category
            binding.articleContent.text = item.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeArticleAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_article, parent, false)
        return ViewHolder(binding = ItemHomeArticleBinding.bind(view))
    }

    override fun onBindViewHolder(holder: HomeArticleAdapter.ViewHolder, position: Int) {
        val data = getItem(position) as Article
        holder.bind(data)
    }

    class DiffCallBack : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
}