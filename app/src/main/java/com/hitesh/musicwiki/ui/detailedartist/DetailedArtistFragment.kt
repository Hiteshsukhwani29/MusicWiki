package com.hitesh.musicwiki.ui.detailedartist

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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.hitesh.musicwiki.adapter.AlbumsChipAdapter
import com.hitesh.musicwiki.adapter.TagsChipAdapter
import com.hitesh.musicwiki.adapter.TracksChipAdapter
import com.hitesh.musicwiki.databinding.FragmentDetailedArtistBinding
import com.hitesh.musicwiki.repository.MusicRepository
import kotlinx.coroutines.launch


class DetailedArtistFragment : Fragment() {

    private var _binding: FragmentDetailedArtistBinding? = null

    private val binding get() = _binding!!

    private lateinit var tagsAdapter: TagsChipAdapter
    private lateinit var albumsAdapter: AlbumsChipAdapter
    private lateinit var tracksAdapter: TracksChipAdapter

    private lateinit var viewModel: DetailedArtistViewModel

    private val args by navArgs<DetailedArtistFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailedArtistBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val musicRepository = MusicRepository()

        val viewModelFactory = DetailedArtistViewModelFactory(musicRepository)

        viewModel = ViewModelProvider(this, viewModelFactory)[DetailedArtistViewModel::class.java]

        tagsAdapter = TagsChipAdapter()
        albumsAdapter = AlbumsChipAdapter()
        tracksAdapter = TracksChipAdapter()

        binding.rvDetailedArtistTags.apply {
            adapter = tagsAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }

        binding.rvDetailedArtistAlbums.apply {
            adapter = albumsAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }

        binding.rvDetailedArtistTracks.apply {
            adapter = tracksAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }

        binding.artistName.text = args.artistName

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getArtistDetails(args.artistName)
                viewModel.getArtistTopAlbums(args.artistName)
                viewModel.getArtistTopTracks(args.artistName)
                viewModel.response.observe(viewLifecycleOwner) { response ->

                    binding.playcountStats.text =
                        if (response.body()?.artist?.stats?.playcount!!.toInt()
                                .div(1000000) > 1
                        ) response.body()?.artist?.stats?.playcount!!.toInt().div(1000000)
                            .toString() + "M" else if (response.body()?.artist?.stats?.playcount?.toInt()
                                ?.div(1000)!! > 1
                        ) response.body()?.artist?.stats?.playcount!!.toInt().div(1000)
                            .toString() + "M" else response.body()?.artist?.stats?.listeners

                    binding.followersStats.text =
                        if (response.body()?.artist?.stats?.listeners!!.toInt().div(1000000) > 1)
                            response.body()?.artist?.stats?.listeners!!.toInt().div(1000000)
                                .toString() + "M" else if (response.body()?.artist?.stats?.listeners?.toInt()
                                ?.div(1000)!! > 1
                        )
                            response.body()?.artist?.stats?.listeners!!.toInt().div(1000)
                                .toString() + "M"
                        else
                            response.body()?.artist?.stats?.listeners

                    binding.artistBio.text = response.body()?.artist?.bio?.summary
                    tagsAdapter.differ.submitList(response.body()?.artist?.tags?.tag)
                }
                viewModel.topAlbums.observe(viewLifecycleOwner) { response ->
                    albumsAdapter.differ.submitList(response.body()?.topalbums?.album)
                }
                viewModel.topTracks.observe(viewLifecycleOwner) { response ->
                    tracksAdapter.differ.submitList(response.body()?.toptracks?.track)
                }
            }
        }
        return root
    }
}