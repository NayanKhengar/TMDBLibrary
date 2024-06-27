package com.nayan.tmdbmoviesdemo.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nayan.networksdk.utils.ConnectivityUtils
import com.nayan.tmdbmoviesdemo.databinding.FragmentMoviesBinding

class TopRatedMoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        adapter = MovieAdapter()

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        viewModel.topRatedMovies.observe(viewLifecycleOwner) { movies ->
            binding.progressbar.isVisible = false
            adapter.submitList(movies)
            adapter.setOnItemClickListener {
                val intent = Intent(activity, MovieDetailActivity::class.java)
                intent.putExtra("MOVIE_ID", it.id)
                startActivity(intent)
            }
        }

        if (ConnectivityUtils.isNetworkConnected(requireContext())) {
            viewModel.fetchTopRatedMovies()
        } else {
            Toast.makeText(requireContext(), "No internet connection", Toast.LENGTH_SHORT)
                .show()
        }
    }
}
