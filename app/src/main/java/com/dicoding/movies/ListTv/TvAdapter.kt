package com.dicoding.movies.ListTv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.movies.Data.Movies
import com.dicoding.movies.Data.TvShows
import com.dicoding.movies.Detail.DetailActivity
import com.dicoding.movies.R
import com.dicoding.movies.databinding.ItemMoviesBinding
import com.dicoding.movies.databinding.ItemTvBinding

class TvAdapter(private val callback: TvFragmentCallback) : RecyclerView.Adapter<TvAdapter.TvViewHolder>()  {
    private var listTv = ArrayList<TvShows>()

    fun setTvShows(tv: List<TvShows>?){
        if (tv == null) return
        this.listTv.clear()
        this.listTv.addAll(tv)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvAdapter.TvViewHolder {
        val itemTvBinding = ItemTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(itemTvBinding)
    }

    override fun onBindViewHolder(holder: TvAdapter.TvViewHolder, position: Int) {
        val tv = listTv[position]
        holder.bind(tv)
    }

    override fun getItemCount(): Int = listTv.size


    inner class TvViewHolder(private val binding: ItemTvBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(tv: TvShows){
            with(binding) {
                tvItemTitle.text = tv.title
                scoreDummy.text = itemView.resources.getString(R.string.score)
                tvItemScore.text = tv.score.toString()
                tvItemAired.text = tv.aired
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TV, tv.tvId)
                    itemView.context.startActivity(intent)
                }
                imgShare.setOnClickListener { callback.onShareClick(tv) }
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w500/" + tv.poster)
                    .into(imgPoster)
            }
        }
    }
}