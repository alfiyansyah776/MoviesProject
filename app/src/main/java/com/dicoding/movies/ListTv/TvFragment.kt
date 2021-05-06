package com.dicoding.movies.ListTv

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.movies.Data.Movies
import com.dicoding.movies.Data.TvShows
import com.dicoding.movies.ListMovies.MoviesAdapter
import com.dicoding.movies.ListMovies.MoviesViewModel
import com.dicoding.movies.R
import com.dicoding.movies.databinding.FragmentMoviesBinding
import com.dicoding.movies.databinding.FragmentTvBinding
import com.dicoding.movies.viewmodel.ViewModelFactory


class TvFragment : Fragment(), TvFragmentCallback {
    private lateinit var fragmentTvBinding: FragmentTvBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentTvBinding = FragmentTvBinding.inflate(layoutInflater, container, false )
        return fragmentTvBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvViewModel::class.java]
            val tvAdapter = TvAdapter(this)

            fragmentTvBinding.progressBar.visibility = View.VISIBLE
            viewModel.getTvShows().observe(this, { tv ->
                fragmentTvBinding.progressBar.visibility = View.GONE
                tvAdapter.setTvShows(tv)
                tvAdapter.notifyDataSetChanged()
            })

            with(fragmentTvBinding.rvTv){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }
        }
    }

    override fun onShareClick(tvShows : TvShows){
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                .setChooserTitle("Share this Tv Shows now.")
                .setText(resources.getString(R.string.share_text, tvShows.title))
                .startChooser()
        }
    }

}