package com.dicoding.movies.ListMovies

import android.content.ContentValues.TAG
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.movies.Data.Movies
import com.dicoding.movies.R
import com.dicoding.movies.databinding.FragmentMoviesBinding
import com.dicoding.movies.databinding.FragmentTvBinding
import com.dicoding.movies.viewmodel.ViewModelFactory


class MoviesFragment : Fragment(), MoviesFragmentCallback {

    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false )
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]
            val moviesAdapter = MoviesAdapter(this)

            fragmentMoviesBinding.progressBar.visibility = View.VISIBLE
            viewModel.getMovies().observe(this, { movies ->
                fragmentMoviesBinding.progressBar.visibility = View.GONE
                moviesAdapter.setMovies(movies)
                moviesAdapter.notifyDataSetChanged()

            })

            with(fragmentMoviesBinding.rvMovies){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }

    override fun onShareClick(movies : Movies){
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                .setChooserTitle("Share this movies now.")
                .setText(resources.getString(R.string.share_text, movies.title))
                .startChooser()
        }
    }
}
