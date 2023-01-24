package com.hitesh.musicwiki.ui.albums

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
import com.hitesh.musicwiki.adapter.TagsAdapter
import com.hitesh.musicwiki.databinding.FragmentAlbumsBinding
import com.hitesh.musicwiki.databinding.FragmentDetailedGenreBinding
import com.hitesh.musicwiki.databinding.FragmentGenreBinding
import com.hitesh.musicwiki.repository.MusicRepository
import com.hitesh.musicwiki.ui.detailedgenre.DetailedGenreViewModel
import com.hitesh.musicwiki.ui.genre.GenreViewModel
import com.hitesh.musicwiki.ui.genre.GenreViewModelFactory
import kotlinx.coroutines.launch

class AlbumsFragment(var tagname: String = "disco") : Fragment() {

    private var _binding: FragmentAlbumsBinding? = null

    private val binding get() = _binding!!

    private lateinit var albumsAdapter: AlbumsAdapter

    private lateinit var viewModel: AlbumsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlbumsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val musicRepository = MusicRepository()

        val viewModelFactory = AlbumsViewModelFactory(musicRepository)

        viewModel = ViewModelProvider(this, viewModelFactory)[AlbumsViewModel::class.java]

        albumsAdapter = AlbumsAdapter()

        binding.rvAlbums.apply {
            adapter = albumsAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getTopAlbums(tagname)
                viewModel.response.observe(viewLifecycleOwner) { response ->
                    Log.d("final result search", response.body().toString())
                    albumsAdapter.differ.submitList(response.body()?.albums?.album?.subList(0, 9))
                }
            }
        }
        return root
    }
}