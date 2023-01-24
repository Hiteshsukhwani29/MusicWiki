package com.hitesh.musicwiki.ui.artists

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.hitesh.musicwiki.R
import com.hitesh.musicwiki.adapter.AlbumsAdapter
import com.hitesh.musicwiki.adapter.ArtistsAdapter
import com.hitesh.musicwiki.adapter.TagsAdapter
import com.hitesh.musicwiki.databinding.FragmentAlbumsBinding
import com.hitesh.musicwiki.databinding.FragmentArtistsBinding
import com.hitesh.musicwiki.databinding.FragmentDetailedGenreBinding
import com.hitesh.musicwiki.databinding.FragmentGenreBinding
import com.hitesh.musicwiki.repository.MusicRepository
import com.hitesh.musicwiki.ui.detailedgenre.DetailedGenreViewModel
import com.hitesh.musicwiki.ui.genre.GenreViewModel
import com.hitesh.musicwiki.ui.genre.GenreViewModelFactory
import kotlinx.coroutines.launch

class ArtistsFragment(var tagname: String = "disco") : Fragment() {

    private var _binding: FragmentArtistsBinding? = null

    private val binding get() = _binding!!

    private lateinit var artistsAdapter: ArtistsAdapter

    private lateinit var viewModel: ArtistsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArtistsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val musicRepository = MusicRepository()

        val viewModelFactory = ArtistsViewModelFactory(musicRepository)

        viewModel = ViewModelProvider(this, viewModelFactory)[ArtistsViewModel::class.java]

        artistsAdapter = ArtistsAdapter()

        binding.rvArtists.apply {
            adapter = artistsAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getTopArtists(tagname)
                viewModel.response.observe(viewLifecycleOwner) { response ->
                    Log.d("final result search", response.body().toString())
                    artistsAdapter.differ.submitList(response.body()?.topartists?.artist)
                }
            }
        }
        return root
    }
}