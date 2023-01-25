package com.hitesh.musicwiki.ui.detailedgenre

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
import com.hitesh.musicwiki.adapter.ViewPagerAdapter
import com.hitesh.musicwiki.databinding.FragmentDetailedGenreBinding
import com.hitesh.musicwiki.repository.MusicRepository
import kotlinx.coroutines.launch

class DetailedGenre : Fragment() {

    private var _binding: FragmentDetailedGenreBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: DetailedGenreViewModel

    private val args by navArgs<DetailedGenreArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailedGenreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val musicRepository = MusicRepository()

        val viewModelFactory = DetailedGenreViewModelFactory(musicRepository)

        viewModel = ViewModelProvider(this, viewModelFactory)[DetailedGenreViewModel::class.java]

        val viewPager = binding.viewPager
        viewPager.adapter = ViewPagerAdapter(requireFragmentManager(), tag = args.tag)
        binding.tabs.setupWithViewPager(viewPager)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getTagDetails(args.tag)
                viewModel.response.observe(viewLifecycleOwner) {
                    binding.genreTitle.text = it.body()?.tag?.name?.toUpperCase()
                    binding.genreDesc.text =
                        it.body()?.tag?.wiki?.summary?.trim()?.substringBefore("<a")
                }
            }
        }
        return root
    }
}