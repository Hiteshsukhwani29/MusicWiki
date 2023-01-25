package com.hitesh.musicwiki.ui.tracks

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.hitesh.musicwiki.adapter.TracksAdapter
import com.hitesh.musicwiki.databinding.FragmentTracksBinding
import com.hitesh.musicwiki.repository.MusicRepository
import kotlinx.coroutines.launch

class TracksFragment(private var tagname: String) : Fragment() {

    private var _binding: FragmentTracksBinding? = null

    private val binding get() = _binding!!

    private lateinit var tracksAdapter: TracksAdapter

    private lateinit var viewModel: TracksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTracksBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val musicRepository = MusicRepository()

        val viewModelFactory = TracksViewModelFactory(musicRepository)

        viewModel = ViewModelProvider(this, viewModelFactory)[TracksViewModel::class.java]

        tracksAdapter = TracksAdapter()

        binding.rvTracks.apply {
            adapter = tracksAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getTopTracks(tagname)
                viewModel.response.observe(viewLifecycleOwner) { response ->
                    Log.d("final result search", response.body().toString())
                    tracksAdapter.differ.submitList(response.body()?.tracks?.track)
                }
            }
        }
        return root
    }
}