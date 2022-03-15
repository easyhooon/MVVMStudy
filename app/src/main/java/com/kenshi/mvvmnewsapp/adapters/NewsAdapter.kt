package com.kenshi.mvvmnewsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kenshi.mvvmnewsapp.models.Article
import com.kenshi.mvvmnewsapp.databinding.ItemArticlePreviewBinding


// DiffUtil calculate the differences between two lists and enables us to only update those items that were different
class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
//        return ArticleViewHolder(
//            LayoutInflater.from(parent.context).inflate(
//                R.layout.item_article_preview,
//                parent,
//                false
//            )
//        )
        val binding = ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
//        holder.itemView.apply {
//            Glide.with(this).load(article.urlToImage).into(ivArticleImage)
//            tvSource.text = article.source.name
//            tvTitle.text = article.title
//            tvDescription.text = article.description
//            tvPublishedAt.text = article.publishedAt
//        }
        //holder.bind(article)
        holder.bind(differ.currentList[position])
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ArticleViewHolder(private val binding: ItemArticlePreviewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) = with(binding) {
            Glide.with(this.root).load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source?.name
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt
            setOnItemClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}