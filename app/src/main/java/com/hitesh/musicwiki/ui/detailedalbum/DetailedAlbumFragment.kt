package com.hitesh.musicwiki.ui.detailedalbum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.hitesh.musicwiki.adapter.TagsChipAdapter
import com.hitesh.musicwiki.databinding.FragmentDetailedAlbumBinding
import com.hitesh.musicwiki.repository.MusicRepository
import kotlinx.coroutines.launch

class DetailedAlbumFragment : Fragment() {

    private var _binding: FragmentDetailedAlbumBinding? = null

    private val binding get() = _binding!!

    private lateinit var tagsAdapter: TagsChipAdapter

    private lateinit var viewModel: DetailedAlbumViewModel

    private val args by navArgs<DetailedAlbumFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailedAlbumBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val musicRepository = MusicRepository()

        val viewModelFactory = DetailedAlbumViewModelFactory(musicRepository)

        viewModel = ViewModelProvider(this, viewModelFactory)[DetailedAlbumViewModel::class.java]

         tagsAdapter = TagsChipAdapter()

        binding.rvDetailedAlbumTags.apply {
            adapter = tagsAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }

        binding.albumName.text = args.albumName
        binding.artistName.text = args.artistName

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getAlbumDetails(args.albumName,args.artistName)
                viewModel.response.observe(viewLifecycleOwner) { response ->
                    binding.albumDesc.text = response.body()?.album?.wiki?.content
                    tagsAdapter.differ.submitList(response.body()?.album?.tags?.tag)
                }
            }
        }
        return root
    }
}